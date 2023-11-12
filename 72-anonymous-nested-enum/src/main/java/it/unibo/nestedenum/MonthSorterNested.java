package it.unibo.nestedenum;

import java.util.Comparator;
import java.util.Locale;

/**
 * Implementation of {@link MonthSorter}.
 */
public final class MonthSorterNested implements MonthSorter {

    private enum Month {
        JANUARY(31),
        FEBRUARY(29),
        MARCH(31),
        APRIL(30),
        MAY(31),
        JUNE(30),
        JULY(31),
        AUGUST(31),
        SEPTEMBER(30),
        OCTOBER(31),
        NOVEMBER(30),
        DECEMBER(31);

        private final int days;

        Month(int days) {
            this.days = days;
        }

        public static Month fromString(String name) {
            // Month match = valueOf(name);
            try {
                return valueOf(name);
            } catch (IllegalArgumentException e) {
                // if (match == null) {
                Month match = null;
                for (Month month : values()) {
                    if (month.toString().startsWith(name.toUpperCase(Locale.ROOT))) {
                        if (match != null) {
                            throw new IllegalArgumentException("Too ambiguous");
                        }
                        match = month;
                    }
                }
                
                if (match == null) {
                    throw new IllegalArgumentException("No match");
                }
                return match;
            }
            // return match;
        }

    }

    @Override
    public Comparator<String> sortByDays() {
        return new sortByDates();
        /*return new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                final Month element1 = Month.fromString(o1);
                final Month element2 = Month.fromString(o2);
                return Integer.compare(element1.days, element2.days);
            }
        };*/
    }

    @Override
    public Comparator<String> sortByOrder() {
        return new sortByMonthOrder();
        /*return new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return Month.fromString(o1).compareTo(Month.fromString(o2));
            }
        };*/
    }

    static private class sortByDates implements Comparator<String> {
        @Override
        public int compare(String o1, String o2) {
            return Integer.compare(Month.fromString(o1).days, Month.fromString(o2).days);
        }
    }

    static private class sortByMonthOrder implements Comparator<String> {
        @Override
        public int compare(String o1, String o2) {
            return Month.fromString(o1).compareTo(Month.fromString(o2));
        }
    }
}
