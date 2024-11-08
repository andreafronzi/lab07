package it.unibo.nestedenum;

import java.util.Comparator;
import java.util.Locale;
import java.util.Objects;

/**
 * Implementation of {@link MonthSorter}.
 */
public final class MonthSorterNested implements MonthSorter {

    private enum Month{
        JANUARY(31),
        FEBRUARY(28),
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

        Month(final int days){
            this.days = days;
        }

        public static Month fromString(final String monthName){
            Objects.requireNonNull(monthName);
            try{
                return valueOf(monthName);
            }catch(IllegalArgumentException e){
                Month match = null;
                for(final Month month: values()){
                    if(month.toString().toLowerCase().startsWith(monthName.toString().toLowerCase())){
                        if(match != null){
                            throw new IllegalArgumentException(monthName + " is ambiguous: both " + match + " and " + month + " would be valid matches", e);
                        }
                        match = month;
                    }
                }
                if(match == null){
                    final String msg = "your insertion " +monthName+ "does not corrispond with any month";
                    throw new IllegalArgumentException(msg);
                }
                return match;
            }
        }

    }

    @Override
    public Comparator<String> sortByDays() {
        return new SortByDate();
    }

    @Override
    public Comparator<String> sortByOrder() {
        return new SortByMonthOrder();
    }

    private class SortByMonthOrder implements Comparator<String>{

        @Override
        public int compare(String arg0, String arg1) {
            final Month month1 = Month.fromString(arg0);
            final Month month2 = Month.fromString(arg1);
            return month1.compareTo(month2);
        }
        
    }

    private final static class SortByDate implements Comparator<String>{
        @Override
        public int compare(String arg0, String arg1) {
            final Month month1 = Month.fromString(arg0);
            final Month month2 = Month.fromString(arg1);
            return Integer.compare(month1.days, month2.days);
        }
        
    }
}
