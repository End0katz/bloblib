package com.end0katz.bloblib;

import java.util.function.*;
import java.util.*;
import java.nio.*;
import java.nio.charset.StandardCharsets;

public abstract class Blobject<T> implements Cloneable {

    public static interface Readable extends java.lang.Readable {

        @Override
        default int read(CharBuffer cb) {
            if (cb == null) {
                throw new NullPointerException(
                        "Did not expect CharBuffer paramater to %s " + /*(%s)*/ " \"%s\".read() to be null"
                                .formatted(
                                        //this.getClass().getSimpleName(),
                                        this.getClass().getCanonicalName(),
                                        this.toString()));
            } else if (cb.isReadOnly()) {
                throw new ReadOnlyBufferException();
            }
            cb.put(this.toString().toCharArray());
            return -1;
        }
    }

    public static interface Iterable<T> extends java.lang.Iterable<T> {

        default T[] iteratorArray(T[] into, int start) {
            int i = start;
            for (T x : this) {
                into[i] = x;
                i++;
            }
            return into;
        }

        default T[] iteratorArray(T[] into) {
            return iteratorArray(into, 0);
        }

        @SuppressWarnings({"unchecked", "unused"})
        default T[] iteratorArray() {
            int size = 0;
            for (T i : this) {
                size++;
            }
            return iteratorArray((T[]) new Object[size], 0);
        }

        default List<T> iteratorList() {
            return iteratorList(new List<>(new ArrayList<>()));
        }

        default List<T> iteratorList(List<T> into) {
            return null;
        }
    }

    public static interface ByteEncodeable {

        default byte[] byteEncode() {
            return StandardCharsets.UTF_8.encode(this.toString()).array();
        }
    }

    public record Data<T>(
            T defualt,
            T clonedefualt,
            Supplier<T> newinstance) {

        public T instance() {
            return newinstance.get();
        }

        public static class Builder<T> {

            protected T defualt;
            protected T defualt_clone;
            protected Supplier<T> instance_supplier;

            public Builder() {
                this(null);
            }

            public Builder(T defualt) {
                this.defualt = defualt;
                this.defualt_clone = defualt;
                this.instance_supplier = () -> defualt;
            }

            public Builder<T> defualt_clone(T defualt_clone) {
                this.defualt_clone = defualt_clone;
                return this;
            }

            public Builder<T> newinstance(Supplier<T> getter) {
                instance_supplier = getter;
                return this;
            }

            public Blobject.Data<T> build() {
                return new Data<>(
                        this.defualt,
                        this.defualt_clone,
                        this.instance_supplier
                );
            }
        }
    }

    public Blobject.Data<T> blobject_data = new Blobject.Data.Builder<T>().build();

    //// public static Blobject.Data<?> blobject_static_data = new Blobject.Data.Builder<>().build();

    @SuppressWarnings({"unchecked", "CloneDeclaresCloneNotSupported"})
    @Override
    public T clone() {
        try {
            return (T) super.clone();
        } catch (CloneNotSupportedException e) {
            return blobject_data.clonedefualt();
        }
    }

    public T instance() {
        return blobject_data.instance();
    }

}
