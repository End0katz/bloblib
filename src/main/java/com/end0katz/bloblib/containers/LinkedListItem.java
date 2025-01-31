package com.end0katz.bloblib.containers;

public class LinkedListItem<T> {

    protected T data;
    protected LinkedListItem<T> next;

    @SuppressWarnings("unchecked")
    public LinkedListItem(T... data) {
        this.data = data[0];
        T[] body = (T[]) new Object[data.length - 1];
        System.arraycopy(data, 1, body, 0, data.length - 1);
        this.next = (data.length == 1) ? null : new LinkedListItem<>(data);
    }

    // public boolean sameList(LinkedListItem<T> as) {
    //     return Arrays.;
    // }
    // @SuppressWarnings("unchecked")
    // public T[] array() {
    //     return (T[]) new Object[] {};
    // }
    public LinkedListItem<T> next() {
        return next;
    }

    public LinkedListItem<T> next(int n) {
        LinkedListItem<T> result = this;
        for (int i = 0; i < n; i++) {
            result = result.next();
        }
        return result;
    }

    public T data() {
        return this.data;
    }

    public T item() {
        return this.data();
    }

    public T item(int n) {
        return this.next(n).item();
    }
}
