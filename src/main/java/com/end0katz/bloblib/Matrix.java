package com.end0katz.bloblib;

import java.util.ArrayList;

public class Matrix<T> {

    protected ArrayList<ArrayList<T>> self = new ArrayList<>();

    public Matrix() {
        self.add(new ArrayList<>());
    }

    public Matrix(int sizex, int sizey) {
        this.self = new ArrayList<>(sizex);
        self.add(new ArrayList<>(sizey));
    }

    public T get(int x, int y) {
        return self.get(x).get(y);
    }

    public ArrayList<T> getRow(int x) {
        return self.get(x);
    }

    public Matrix<T> addRow(ArrayList<T> x) {
        self.add(x);
        return this;
    }

    public ArrayList<T> getCol(int x) {
        return this.transpose().getRow(x);
    }

    public Matrix<T> getCol(ArrayList<T> x) {
        self.add(x);
        return this;
    }

    public Matrix<T> transpose() {
        Matrix<T> result = new Matrix<>();
        return result;
    }
}
