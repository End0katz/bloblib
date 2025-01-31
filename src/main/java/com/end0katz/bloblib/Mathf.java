package com.end0katz.bloblib;

public class Mathf {

    public static int mod(int x, int y) {
        if (x >= 0) {
            return x % y;
        } else {
            return ((y - (abs(x) % y)) % y);
        }
    }

    public static int abs(int x) {
        return x * sign(x);
    }

    public static final int sign(int x) {
        if (x > 0) {
            return 1;
        } else if (x == 0) {
            return 0;
        } else {
            return -1;
        }
    }

    public static class func {

        public static <T> T identity(T x) {
            return x;
        }
    }
}
