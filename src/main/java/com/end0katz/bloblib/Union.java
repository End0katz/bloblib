package com.end0katz.bloblib;

import java.util.Optional;
import java.util.function.Function;

public class Union<T, E> {

    protected T t;
    protected E e;

    public Union() {
        this(null, null);
    }

    public Union(T t) {
        this(t, null);
    }

    public Union(T t, E e) {
        this.t = t;
        this.e = e;
    }

    public Optional<T> getOptional() {
        return Optional.ofNullable(t);
    }

    public Union<E, T> swap() {
        return new Union<>(e, t);
    }

    public T get(T backup) {
        return success() ? t : backup;
    }

    public T get() {
        return t;
    }

    public E getErr() {
        return e;
    }

    public boolean hasErr() {
        return e != null;
    }

    public boolean success() {
        return t != null;
    }

    public E getErr(E backup) {
        return hasErr() ? e : backup;
    }

    public <R> R choose(Function<T, R> success, Function<E, R> fail) {
        return success() ? success.apply(t) : fail.apply(e);
    }

    @Override
    public String toString() {
        return "Union[%s : %s]".formatted(t, e);
    }

    public static void main(String[] args) {
        Union<Integer, String> x = new Union<>(5);
        System.out.println(x);
        System.out.println(x.getOptional());
    }
}
