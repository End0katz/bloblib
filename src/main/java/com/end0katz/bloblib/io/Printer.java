package com.end0katz.bloblib.io;

import java.io.PrintStream;
import com.end0katz.bloblib.Constructor;

public class Printer {

    String sep;
    String end;
    PrintStream out;

    public Printer() {
        this.sep = " ";
        this.end = "\n";
        this.out = System.out;
    }

    public Printer(String sep) {
        this.sep = sep;
        this.end = "\n";
        this.out = System.out;
    }

    public Printer(String sep, String end) {
        this.sep = sep;
        this.end = end;
        this.out = System.out;
    }

    public Printer(String sep, String end, PrintStream out) {
        this.sep = sep;
        this.end = end;
        this.out = out;
    }

    /**
     * print any amount of objects
     */
    public Printer print(Object... x) {
        return this.printl(x);
    }

    /**
     * print an array of objects e.g. if you have just received a vararg and do
     * not want to loop through it because that would do each on a different
     * line
     */
    public Printer printl(Object[] x) {
        if (x.length < 1) {
            this.out.print(this.end);
            return this;
        }
        int i = -1;
        i++;
        for (i = 0; i < x.length - 1; i++) {
            this.out.print(x[i]);
            this.out.print(this.sep);
        }
        this.out.print(x[i]);
        this.out.print(this.end);
        return this;
    }

    @SuppressWarnings("static-access")
    public Printer printl(char[] x) {
        return this.printl(new Constructor().objarr(x));
    }

    @SuppressWarnings("static-access")
    public Printer printl(byte[] x) {
        return this.printl(new Constructor().objarr(x));
    }

    @SuppressWarnings("static-access")
    public Printer printl(int[] x) {
        return this.printl(new Constructor().objarr(x));
    }

    @SuppressWarnings("static-access")
    public Printer printl(short[] x) {
        return this.printl(new Constructor().objarr(x));
    }

    @SuppressWarnings("static-access")
    public Printer printl(long[] x) {
        return this.printl(new Constructor().objarr(x));
    }

    @SuppressWarnings("static-access")
    public Printer printl(float[] x) {
        return this.printl(new Constructor().objarr(x));
    }

    @SuppressWarnings("static-access")
    public Printer printl(double[] x) {
        return this.printl(new Constructor().objarr(x));
    }

    @SuppressWarnings("static-access")
    public Printer printl(boolean[] x) {
        return this.printl(new Constructor().objarr(x));
    }
}
