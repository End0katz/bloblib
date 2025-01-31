package com.end0katz.bloblib.str;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.function.Function;

import com.end0katz.bloblib.Mathf;

public abstract class _Str<T> implements Iterable<Character> {

    protected String x;

    public static final String DFLT = "";

    protected T newinstance() {
        return this.newinstance(_Str.DFLT);
    }

    abstract protected T newinstance(String x);

    protected final void __init(String x) {
        this.x = x;
    }

    public _Str() {
        this.__init(_Str.DFLT);
    }

    public _Str(String x) {
        this.__init(x);
    }

    public int length() {
        return this.x.length();
    }

    @Override
    public Iterator<Character> iterator() {
        ArrayList<Character> result = new ArrayList<>();
        for (char i : this.x.toCharArray()) {
            result.add(i);
        }
        return result.iterator();
    }

    public T flip() {
        String result = "";
        char[] arr = this.toString().toCharArray();
        for (int i = arr.length - 1; i >= 0; i--) {
            result += this.x.charAt(i);
        }
        return this.newinstance(result);
    }

    public static <X> X flip(_Str<X> x) {
        return x.flip();
    }

    public T subset(int start, int end) {
        return this.newinstance(this.x.substring(start, end));
    }

    public static <X> X subset(_Str<X> x, int start, int end) {
        return x.subset(start, end);
    }

    public static Function<_Str<?>, _Str<?>> subsetter(int start, int end) {
        //public class Temp {
        //    public static _Str<X> temp(_Str<X> x) {
        //        return x.subset(start, end);
        //    }
        //}
        return (_Str<?> x) -> ((_Str<?>) x.subset(start, end));
    }

    public T join(_Str<?>... others) {
        String result = this.x;
        for (_Str<?> i : others) {
            result += i.x;
        }
        return this.newinstance(result);
    }

    public static Function<_Str<?>, _Str<?>> joiner(_Str<?>... others) {
        return (_Str<?> x) -> ((_Str<?>) x.join(others));
    }

    public T join(String... others) {
        String result = this.x;
        for (String i : others) {
            result += i;
        }
        return this.newinstance(result);
    }

    public static Function<_Str<?>, _Str<?>> joiner(String... others) {
        return (_Str<?> x) -> ((_Str<?>) x.join(others));
    }

    public char charAt(int index) {
        return this.x.charAt(index);
    }

    public int codePointAt(int index) {
        return this.x.codePointAt(index);
    }

    public int codePointBefore(int index) {
        return this.x.codePointBefore(index);
    }

    public int codePointsBetween(int start, int end) {
        return this.x.codePointCount(start, end);
    }

    public enum compareToResult {
        LESSTHAN,
        EQUALTO,
        GREATERTHAN;

        public static int toInt(compareToResult result) {
            return switch (result) {
                case compareToResult.LESSTHAN ->
                    -1;
                case compareToResult.EQUALTO ->
                    0;
                case compareToResult.GREATERTHAN ->
                    1;
            };
        }

        public int toInt() {
            return compareToResult.toInt(this);
        }

        public static boolean toBool(compareToResult result) {
            return toBool(result, false);
        }

        public static boolean toBool(compareToResult result, boolean IfEq) {
            return switch (result) {
                case compareToResult.LESSTHAN ->
                    false;
                case compareToResult.EQUALTO ->
                    IfEq;
                case compareToResult.GREATERTHAN ->
                    true;
            };
        }

        public boolean toBool() {
            return compareToResult.toBool(this);
        }

        public boolean toBool(boolean IfEq) {
            return compareToResult.toBool(this, IfEq);
        }
    }

    public compareToResult compareTo(_Str<?> other) {
        return this.compareTo(other, false);
    }

    public compareToResult compareTo(String other) {
        return this.compareTo(other, false);
    }

    public compareToResult compareTo(_Str<?> other, boolean ignorecase) {
        return compareToResult.values()[Mathf.sign((ignorecase) ? this.x.compareToIgnoreCase(other.x) : this.x.compareTo(other.x)) + 1];
    }

    public compareToResult compareTo(String other, boolean ignorecase) {
        return compareToResult.values()[Mathf.sign((ignorecase) ? this.x.compareToIgnoreCase(other) : this.x.compareTo(other)) + 1];
    }

    @Override
    public String toString() {
        return this.x;
    }

    // public static Str flip(Str x) {
    // return x.flip();
    // }
}
