package com.end0katz.bloblib;

public class Enum {

    public enum Days {
        MONDAY,
        TUESDAY,
        WEDNESDAY,
        THURSDAY,
        FRIDAY,
        SATURDAY,
        SUNDAY;

        public static Days[] weekend() {
            return new Days[]{SATURDAY, SUNDAY};
        }

        public static boolean weekend(Days x) {
            for (Days i : Days.weekend()) {
                if (i == x) {
                    return true;
                }
            }
            return false;
        }

        public static Days[] weekday() {
            Days[] result = new Days[Days.values().length - Days.weekend().length];
            int index = 0;
            for (Days i : Days.values()) {
                if (!Days.weekend(i)) {
                    result[index] = i;
                    index++;
                }
            }
            return result;
        }

        public static boolean weekday(Days x) {
            return !Days.weekend(x);
        }

    }

    public enum Months {
        JANUARY,
        FEBRUARY,
        MARCH,
        APRIL,
        MAY,
        JUNE,
        JULY,
        AUGUST,
        SEPTEMBER,
        OCTOBER,
        NOVEMBER,
        DECEMBER;

        public static int length(Months x) {
            return length(x, false);
        }

        public static int length(Months x, boolean leapyear) {
            return switch (x) {
                case FEBRUARY ->
                    28;
                case MARCH ->
                    30;
                case MAY ->
                    30;
                case JULY ->
                    30;
                case SEPTEMBER ->
                    30;
                case NOVEMBER ->
                    30;
                default ->
                    31;
            };
        }

        public static boolean days31(Months x) {
            return Months.length(x) == 31;
        }

        public static boolean days30(Months x) {
            return Months.length(x) == 30;
        }
    }
}
