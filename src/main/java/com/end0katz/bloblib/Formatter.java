package com.end0katz.bloblib;

import java.util.*;
import java.util.function.*;

public class Formatter {

    protected HashMap<Character, String> percent_const = new HashMap<>();
    protected HashMap<Character, Integer> percent_len = new HashMap<>();
    protected HashMap<Character, BiFunction<String, Queue<Object>, String>> percent_func = new HashMap<>();

    protected void clear_percent(Character id) {
        percent_const.remove(id);
        percent_func.remove(id);
        percent_len.remove(id);
    }

    @SuppressWarnings("OverridableMethodCallInConstructor")
    public Formatter() {
        this.add_percent('%', "%");
    }

    public Formatter add_percent(Character id, String c) {
        clear_percent(id);
        percent_const.put(id, c);
        return this;
    }

    @SuppressWarnings("unused")
    public Formatter add_percent(Character id, int len, Function<String, String> f) {
        return add_percent(id, len, (String s, Queue<Object> objs) -> f.apply(s));
    }

    @SuppressWarnings("unused")
    public Formatter add_percent(Character id, int len, Supplier<String> f) {
        return add_percent(id, len, (String s, Queue<Object> objs) -> f.get());
    }

    public Formatter add_percent(Character id, int len, BiFunction<String, Queue<Object>, String> f) {
        clear_percent(id);
        percent_len.put(id, len);
        percent_func.put(id, f);
        return this;
    }

    protected String handle_percent(Queue<Character> chrs, Queue<Object> objs) {
        Character ch = chrs.poll();
        String result = "--%error%--";
        if (percent_const.containsKey(ch)) {
            result = percent_const.get(ch);
        } else if (percent_func.containsKey(ch)) {
            if (percent_len.containsKey(ch)) {
                String param = "";
                for (int i = 0; i < percent_len.get(ch); i++) {
                    if (chrs.peek() == null) {
                        throw new OutOfBoundsException();
                    }
                    param += String.valueOf(chrs.poll());
                }
                result = percent_func.get(ch).apply(param, objs);
            }
        } else if (ch == null) {
            result = "--%end of text%--";
        }
        return result;
    }

    public String format(String target, Object... arguments) {
        Queue<Object> objs = new LinkedList<>(Arrays.asList(arguments));
        Queue<Character> chrs = new LinkedList<>();
        for (char i : target.toCharArray()) {
            chrs.add(i);
        }
        String result = "";
        char ch;

        while (!chrs.isEmpty()) {
            ch = chrs.poll();
            switch (ch) {
                case '%' -> {
                    result += handle_percent(chrs, objs);
                }
                default -> {
                    result += String.valueOf(ch);
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(new Formatter().add_percent('f', "F").add_percent('x', 2, (String x) -> x).format("unformatted %x46ormatted"));
    }
}
