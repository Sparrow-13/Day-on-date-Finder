import java.util.Scanner;

public class DayFinder {

    int getMonthDays(int month_id) {
        int[] month = { 0, 3, 3, 6, 1, 4, 6, 2, 5, 0, 3, 5 };
        return month[month_id - 1];
    }

    int getCenturyDays(int century_id) {
        int[] centurydays = { 6, 4, 2, 0 };
        century_id %= 16;
        if (century_id > 3) {
            century_id %= 4;
        }
        return centurydays[century_id];
    }

    int getMonthNumber(String month) {

        switch (month.toLowerCase().substring(0, 3)) {

            case "jan":
                return 1;

            case "feb":
                return 2;

            case "mar":
                return 3;

            case "apr":
                return 4;

            case "may":
                return 5;

            case "jun":
                return 6;

            case "jul":
                return 7;

            case "aug":
                return 8;

            case "sep":
                return 9;

            case "oct":
                return 10;

            case "nov":
                return 11;

            case "dec":
                return 12;

            default:
                return 0;
        }
    }

    boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    String dayOnDate(String dateofyear) {
        String[] format = dateofyear.split(" ");
        int date = Integer.parseInt(format[0]);
        int month, century, year, leapyear;
        int remove_days = 0;

        if (format[2].length() < 4) {
            return "error";
        }
        // with format 12/12/2032;
        if (isNumeric(format[1])) {
            month = Integer.parseInt(format[1]);

        } else {
            month = getMonthNumber(format[1]);
            if (month == -1)
                return "error";
        }
        century = Integer.parseInt(format[2].substring(0, 2));
        year = Integer.parseInt(format[2].substring(2));
        if (year % 4 == 0 && ((month == 1) || (month == 2))) {
            remove_days = 1;
        }
        leapyear = year / 4;
        date = date % 7;
        month = getMonthDays(month);
        century = getCenturyDays(century);
        date += month + century + year + leapyear;

        return getDayWithIndex(date % 7 - remove_days);
    }

    String getDayWithIndex(int index) {
        String[] days = { "sunday", "monday", "tuesday", "wednesday", "thursday", "friday", "saturday" };
        return days[index];
    }

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.println("Format for date Ex : 12 12 2012 or 12 jan 2018");
        System.out.print("Enter the date : ");
        String date = sc.nextLine();
        DayFinder dayFinder = new DayFinder();
        String day = dayFinder.dayOnDate(date);
        if (day == "error") {
            System.out.println("You have Entered Wrong Format of DATE");
            main(args);
        } else {
            System.out.println("Day on Date(" + date + ") is " + day);
        }
        sc.close();
    }
}
