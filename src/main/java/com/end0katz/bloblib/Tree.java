package com.end0katz.bloblib;

public record Tree<T>(
        T data,
        Tree<T> left,
        Tree<T> right) {

    @Override
    public String toString() {
        if (data != null) {
            if (left != null) {
                return right != null
                        ? "%s ( %s | %s )".formatted(data, left, right)
                        : "%s:%s".formatted(data, left);
            } else {
                return right != null
                        ? "%s -> %s".formatted(data, right)
                        : data.toString();
            }
        } else {
            if (left != null) {
                return right != null
                        ? "< %s | %s >".formatted(left, right)
                        : "< %s |".formatted(left);
            } else {
                return right != null
                        ? "| %s >".formatted(left, right)
                        : "%null%";
            }
        }
    }

    public static <T> Tree<T> create(
            T data
    ) {
        return null;
    }

    public static void main(String[] args) {
        Tree<String> tree = new Tree<>(
                "call",
                new Tree<>(
                        "id",
                        new Tree<>(
                                "print",
                                null,
                                null
                        ),
                        null
                ),
                new Tree<>(
                        "arg",
                        new Tree<>(
                                "int",
                                new Tree<>(
                                        "52",
                                        null,
                                        null
                                ),
                                null
                        ),
                        new Tree<>(
                                "arg",
                                new Tree<>(
                                        "null",
                                        new Tree<>(
                                                null,
                                                null,
                                                null
                                        ),
                                        null
                                ),
                                null
                        )
                )
        );
        System.out.println(tree);
    }
}
