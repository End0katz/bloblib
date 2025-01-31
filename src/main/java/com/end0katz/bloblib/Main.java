package com.end0katz.bloblib;

import com.end0katz.bloblib.io.Printer;

public class Main {

    /**
     * print any amount of objects
     */
    public static void print(Object... x) {
        printl(x);
    }

    /**
     * print an array of objects e.g. if you have just received a vararg and do
     * not want to loop through it because that would do each on a different
     * line
     */
    public static void printl(Object[] x) {
        new Printer().printl(x);
    }
}
