package com.end0katz.bloblib.io;

import java.io.IOException;

import com.end0katz.bloblib.List;

public class Input {

    public static void main(String[] args) {
        new Printer("").print(input(), "#");
    }

    public static String input() {
        return input(">>> ");
    }

    public static String input(String prompt) {
        try {
            new Printer("", "").print(prompt);
            String result = clear_input_stream(System.in.read());
            String newline = "\r\n";
            return result.substring(0, result.length() - (newline.length() + 1));
        } catch (IOException err) {
            return "";
        }
    }

    public static String clear_input_stream(Integer... contents) {
        return clear_input_stream(new List<>(contents));
    }

    public static String clear_input_stream(List<Integer> initial) {
        try {
            List<Integer> x = initial.copy();
            while (System.in.available() > 0) {
                x.add(System.in.read());
            }
            //System.out.println("xorg");
            char[] result = new char[x.size()];
            int index = 0;
            for (int i : x) {
                result[index] = (char) i;
                index++;
            }
            return new String(result);
        } catch (IOException err) {
            return "";
        }
    }
}
