package com.end0katz.bloblib.str;

import java.util.function.Function;

import com.end0katz.bloblib.Main;

public class MutableStr extends _Str<MutableStr> {

    public static void main(String[] args) {
        MutableStr x = new MutableStr("dflt");
        MutableStr y = new MutableStr("yeet");
        Main.print(x);
        Main.print(y);
        Main.print((x.compareTo(y).toBool()) ? x : y);
    }

    public MutableStr() {
        this.__init(MutableStr.DFLT);
    }

    public MutableStr(String x) {
        this.__init(x);
    }

    public MutableStr copy(_Str<?> from) {
        this.x = from.x;
        return this;
    }

    public MutableStr InPlace(Function<_Str<?>, _Str<?>> f) {
        this.copy(f.apply(this));
        return this;
    }

    @Override
    protected MutableStr newinstance(String x) {
        return new MutableStr(x);
    }
}
