package com.end0katz.bloblib.str;

public class Str extends _Str<Str> {

    @Override
    protected Str newinstance(String x) {
        return new Str(x);
    }

    public Str() {
        this.__init(Str.DFLT);
    }

    public Str(String x) {
        this.__init(x);
    }
}
