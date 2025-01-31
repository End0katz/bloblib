package com.end0katz.bloblib;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
//import java.lang.String;

public class Convertor<T> {

    public interface Converts<T> {

        public T convert(Object x);
    }

    T x;

    public Convertor(T x) {
        this.x = x;
    }

    public static <T> Convertor<T> from(T from) {
        return new Convertor<>(from);
    }

    public static <T, R> R change(T from, Convertor.Converts<R> to) {
        return Convertor.from(from).to(to);
    }

    public <R> R to(Convertor.Converts<R> conv) {
        return conv.convert(x);
    }

    @Override
    public String toString() {
        return this.toString(x.toString());
    }

    // @Override
    @SuppressWarnings("unused")
    public String toString(String backup) {
        try {
            return (String) x;
        } catch (ClassCastException err) {
        }
        if (!x.toString().equals(Objects.toIdentityString(x))) {
            return x.toString();
        }
        if (x instanceof byte[] bs) {
            //String result = "";

            //for (byte i : (byte[]) x) {
            //    result = result + new String(new char[]{(char) i});
            //}
            //return result;
            return new String(
                    StandardCharsets.UTF_8.decode(
                            ByteBuffer.allocate(((byte[]) x).length)
                                    .clear()
                                    .put((byte[]) x))
                            .array());
        }
        if (x instanceof int[] ints) {
            char[] result = new char[ints.length];
            int index = 0;
            for (int i : ints) {
                result[index] = (char) i;
                i++;

            }
            return new String(result);
        }
        return backup;

    }

    public byte[] toBytes() {
        return this.toBytes(
                StandardCharsets.UTF_8.encode(
                        this.toString()
                ).array()
        );
    }

    public byte[] toBytes(byte[] backup) {
        return switch (this.x) {
            case String s ->
                StandardCharsets.UTF_8.encode(s).array();
            case char[] cs ->
                new Convertor<>(new String(cs)).toBytes();
            default ->
                backup;
        };
    }
}
