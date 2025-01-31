package com.end0katz.bloblib;

//import com.end0katz.bloblib.io.Printer;
import java.util.ArrayList;

public class Constructor {

    public static byte[] bytes(int... x) {
        byte[] result = new byte[x.length];
        int index = 0;
        for (int i : x) {
            //new Printer().print(i);
            result[index] = (byte) i;
            index++;
        }
        return result;
    }

    @SuppressWarnings("unchecked")
    public static <From, To> ArrayList<To> changetype(ArrayList<From> x, Class<To> y) {
        ArrayList<To> result = new ArrayList<>();
        for (From i : x) {
            result.add((To) i);
        }
        return result;
    }

    public static Object[] objarr(Object[] x) {
        Object[] result = new Object[x.length];
        int index = 0;
        for (Object i : x) {
            result[index] = (Object) i;
            index++;
        }
        return result;
    }

    public static Object[] objarr() {
        return new Object[1];
    }

    public static Object[] objarr(byte[] x) {
        Object[] result = new Object[x.length];
        int index = 0;
        for (Object i : x) {
            result[index] = (Object) i;
            index++;
        }
        return result;
    }

    public static Object[] objarr(char[] x) {
        Object[] result = new Object[x.length];
        int index = 0;
        for (Object i : x) {
            result[index] = (Object) i;
            index++;
        }
        return result;
    }

    public static Object[] objarr(int[] x) {
        Object[] result = new Object[x.length];
        int index = 0;
        for (Object i : x) {
            result[index] = (Object) i;
            index++;
        }
        return result;
    }

    public static Object[] objarr(long[] x) {
        Object[] result = new Object[x.length];
        int index = 0;
        for (Object i : x) {
            result[index] = (Object) i;
            index++;
        }
        return result;
    }

    public static Object[] objarr(short[] x) {
        Object[] result = new Object[x.length];
        int index = 0;
        for (Object i : x) {
            result[index] = (Object) i;
            index++;
        }
        return result;
    }

    public static Object[] objarr(float[] x) {
        Object[] result = new Object[x.length];
        int index = 0;
        for (Object i : x) {
            result[index] = (Object) i;
            index++;
        }
        return result;
    }

    public static Object[] objarr(double[] x) {
        Object[] result = new Object[x.length];
        int index = 0;
        for (Object i : x) {
            result[index] = (Object) i;
            index++;
        }
        return result;
    }

    public static Object[] objarr(boolean[] x) {
        Object[] result = new Object[x.length];
        int index = 0;
        for (Object i : x) {
            result[index] = (Object) i;
            index++;
        }
        return result;
    }
}
