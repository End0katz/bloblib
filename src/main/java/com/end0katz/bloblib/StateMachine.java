package com.end0katz.bloblib;

import java.util.HashMap;

public class StateMachine<T, M> {

    public static <T> Builder<T, String> build(T startstate) {
        return new Builder<>(startstate);
    }

    public static <T> Builder<T, String> build(T startstate, Class<T> cls) {
        return new Builder<>(startstate, cls);
    }

    public static class Builder<T, M> {

        public Class<T> cls;
        public T state;

        public HashMap<T, HashMap<M, T>> connections = new HashMap<>();

        @SuppressWarnings("unchecked")
        public Builder(T startstate) {
            this(startstate, (Class<T>) startstate.getClass());
        }

        public Builder(T startstate, Class<T> cls) {
            state = startstate;
            this.cls = cls;
        }

        public Builder<T, M> connect(T from, M name, T to) {
            connections.putIfAbsent(from, new HashMap<>());
            connections.get(from).put(name, to);
            return this;
        }

        public StateMachine<T, M> build() {
            return new StateMachine<>(this);
        }

    }

    public enum test {
        NEUTRAL, LEFT, RIGHT, JUMP, DIG, SPIN;
    }

    protected Class<T> cls;
    protected T state;

    protected HashMap<T, HashMap<M, T>> connections = new HashMap<>();

    public StateMachine(Builder<T, M> builder) {
        connections = builder.connections;
        cls = builder.cls;
        state = builder.state;
    }

    public T get() {
        return state;
    }

    public StateMachine<T, M> move(M path) {
        state = this.outpaths().getOrDefault(path, state);
        return this;
    }

    public HashMap<M, T> outpaths() {
        return outpaths(state);
    }

    @SuppressWarnings("unchecked")
    public HashMap<M, T> outpaths(T from) {
        return (HashMap<M, T>) this.connections.getOrDefault(from, new HashMap<>()).clone();
    }

    public static void main(String[] args) {
        StateMachine<test, String> x = StateMachine.build(test.NEUTRAL).connect(test.NEUTRAL, "Move", test.RIGHT).build();
        System.out.println(x.get());
        System.out.println(x.move("Move").get());

        System.out.println(x.outpaths(test.LEFT));
    }
}
