package com.end0katz.bloblib;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
//import com.end0katz.Convertor;

public class List<T> implements Iterable<T> {

    @FunctionalInterface
    public interface ListFilter<T> {

        boolean allow(T x);
    }

    @FunctionalInterface
    public interface ListTransformer<T, R> {

        R transform(T x);
    }

    protected ArrayList<T> x;

    public List() {
        this.x = new ArrayList<>();
        //this.t = ((Class) T);
    }

    //public List(T[] x) {
    //    this.x = new ArrayList<>(Arrays.asList(x));
    //}
    @SuppressWarnings("unchecked")
    public List(T... x) {
        this.x = new ArrayList<>(Arrays.asList(x));
    }

    //@SuppressWarnings("unchecked")
    //public List<T> from(T... x) {
    //    return new List<>(x);
    //}
    //@SuppressWarnings("unchecked")
    public List(ArrayList<T> x) {
        this.x = x;
    }

    public ArrayList<T> toArrayList() {
        return this.x;
    }

    @SuppressWarnings("unchecked")
    public T[] toArray() {
        return (T[]) this.x.toArray();
    }

    public List<T> filter(ListFilter<T> f) {
        ArrayList<T> result = new ArrayList<>();
        for (T i : this) {
            if (f.allow(i)) {
                result.add(i);
            }
        }
        return new List<>(result);
    }

    public <R> List<R> feed(ListTransformer<T, R> f) {
        ArrayList<R> result = new ArrayList<>();
        for (T i : this) {
            result.add(f.transform(i));
        }
        return new List<>(result);
    }

    @Override
    public Iterator<T> iterator() {
        return this.x.iterator();
    }

    @SuppressWarnings("unchecked")
    public List<T> add(T... x) {
        this.x.addAll(Arrays.asList(x));
        return this;
    }

    public List<T> addl(T[] x) {
        this.x.addAll(Arrays.asList(x));
        return this;
    }

    public List<T> addl(List<T> x) {
        this.x.addAll(x.toArrayList());
        return this;
    }

    public List<T> addl(ArrayList<T> x) {
        this.x.addAll(x);
        return this;
    }

    @Override
    public String toString() {
        String contents = "";
        String sep = ", ";
        for (T i : this) {
            contents += new Convertor<>(i).toString()
                    + ((new Convertor<>(i).toString().equals(i.toString()))
                    ? ""
                    : String.format(" {%s} ", i.toString()))
                    + sep;
        }
        contents = contents.substring(0, contents.length() - sep.length());

        String tparam = this.get(0).getClass().getSimpleName();

        //tparam;
        return String.format("new List<%s>", tparam) + String.format("(%s)", contents);
    }

    public T get(int index) {// throws IndexOutOfBoundsException{
        //if (index >= this.x.size()) {
        //    throw new IndexOutOfBoundsException(index);
        //}
        // System.out.println(index);
        // System.out.println(this.x.size());
        // System.out.println(index % this.x.size());
        return this.x.get(Mathf.mod(index, this.x.size()));
    }

    @SuppressWarnings("unchecked")
    public List<T> get(int start, int end) {
        List<T> result = new List<>();
        // for (int index : new int[] { start, end }) {
        //     if (index >= this.x.size()) {
        //         throw new IndexOutOfBoundsException(index);
        //     }
        // }
        for (int index = start; index < end; index++) {
            result.add(this.get(index));
        }
        return result;
    }

    public void set(int index, T x) {
        this.x.set(index % this.x.size(), x);
    }

    public int size() {
        return this.x.size();
    }

    public List<T> copy() {
        return this.feed(Mathf.func::identity);
    }

    public List<T> copy(List<T> from) {
        this.x.clear();
        return this.addl(from);
    }

    public List<T> copy(ArrayList<T> from) {
        this.x.clear();
        return this.addl(from);
    }

    public List<T> copy(T[] from) {
        this.x.clear();
        return this.addl(from);
    }

    public List<T> clear() {
        return this.copy(new List<>());
    }

}
