package com.end0katz.bloblib;

import java.util.*;
import java.util.function.*;
import java.util.stream.*;

public interface XStream<T> extends Stream<T> {

    @Override
    public XStream<T> sequential();

    @Override
    public XStream<T> parallel();

    @Override
    public XStream<T> unordered();

    @Override
    public XStream<T> onClose(Runnable closeHandler);

    @Override
    public XStream<T> filter(Predicate<? super T> predicate);

    @Override
    public <R> XStream<R> map(Function<? super T, ? extends R> mapper);

    @Override
    public <R> XStream<R> flatMap(Function<? super T, ? extends Stream<? extends R>> mapper);

    @Override
    public XStream<T> distinct();

    @Override
    public XStream<T> sorted();

    @Override
    public XStream<T> sorted(Comparator<? super T> comparator);

    @Override
    public XStream<T> peek(Consumer<? super T> action);

    @Override
    public XStream<T> limit(long maxSize);

    @Override
    public XStream<T> skip(long n);

    @Override
    public T[] toArray();

    public default Stream<T> toStream() {
        return this;
    }

    /**
     * Return a stream equivilent to the nulls filtered out
     *
     * @return an xstream that contains no nulls
     */
    public default XStream<T> filterNull() {
        return this.filter((x) -> (x != null));
    }
}
