import java.io.*;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.*;


/**
 * 
 */
public class DayOfTheWeek {


    /**
     * Given a date, return the corresponding day of the week for that date.
     * 
     * Runtime: 28 ms, faster than 5.17% of Java online submissions for Day of the Week.
     * Memory Usage: 38.7 MB, less than 14.14% of Java online submissions for Day of the Week.
     * 
     * 43 / 43 test cases passed.
     * Status: Accepted
     * Runtime: 28 ms
     * Memory Usage: 38.7 MB
     */
    static public String dayOfTheWeek0(int day, int month, int year) {
        
        // **** formatter, format date, day of week, and get display name ****
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
        LocalDate date              = LocalDate.parse("" + day + "/" + month + "/" + year, formatter);
        DayOfWeek dow               = date.getDayOfWeek();
        return dow.getDisplayName(TextStyle.FULL, Locale.US);
    }






    /**
     * Given a date, return the corresponding day of the week for that date.
     * 
     */
    static public String dayOfTheWeek1(int day, int month, int year) {
        
        // **** initialization ****
        String dayOfWeek[] =    {
                                "Sunday",
                                "Monday",
                                "Tuesday",
                                "Wednesday",
                                "Thursday",
                                "Friday",
                                "Saturday"
                                };

        int magicNumber[] =     { 0, 3, 3, 6, 1, 4, 6, 2, 5, 0, 3, 5 };

        // **** check if this is a leap year ****
        boolean isLeap = isLeapYear(year);

        // ???? ????
        System.out.println("<<< isLeap: " + isLeap);

        int j       = 0;
        String dow  = "";



        // **** check to find any year value ****
        if ((year / 100) % 2 == 0) {
            if ((year / 100) % 4 == 0)
                j = 6;
            else
                j = 2;
        } else {
            if (((year / 100) - 1) % 4 == 0)
                j = 4;
            else
                j = 0;
        }

        // ???? ????
        System.out.println("<<< j: " + j);



        // **** formula ****
        int total = (year % 100) + ((year % 100) / 4) + day + magicNumber[month - 1] + j;
        if (isLeap) {
            if ((total % 7) > 0) 
                dow = dayOfWeek[(total % 7) - 1];
            else
                dow = dayOfWeek[6];
        } else
            dow = dayOfWeek[(total % 7)];

        // **** return day of week ****
        return dow;
    }



    /**
     * Check if th especified year is or is not a leap year.
     */
    static private boolean isLeapYear(int year) {

        // **** check if  year is multiple of 400 (leap year) ****
        if (year % 400 == 0) return true;
     
        // **** check if year is multiple of 100 (not a leap year) ****
        if (year % 100 == 0) return false;
     
        // **** check if year is multiple of 4 (leap year) ****
        if (year % 4 == 0) return true;

        // **** not a leap year ****
        return false;
    }




    /**
     * Given a date, return the corresponding day of the week for that date.
     * 
     * Runtime: 0 ms, faster than 100.00% of Java online submissions.
     * Memory Usage: 37.4 MB, less than 42.41% of Java online submissions.
     */
    static public String dayOfTheWeek(int day, int month, int year) {

        // **** sanity checks ****
        if (day < 1 || day > 31) return "";

        // **** initialization ****
        int t[]             =   { 0, 3, 2, 5, 0, 3, 5, 1, 4, 6, 2, 4 };

        String dayOfWeek[]  =   {
                                "Sunday",
                                "Monday",
                                "Tuesday",
                                "Wednesday",
                                "Thursday",
                                "Friday",
                                "Saturday"
                                };

        // **** ****
        if (month < 3) year -= 1;

        // **** return string representation of day of the week ****
        return dayOfWeek[(year + year / 4 - year / 100 + year / 400 + t[month - 1] + day) % 7];
    }






    /**
     * Test scaffold
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        
        // **** open buffered reader ****
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // **** read day, month and year ****
        int[] arr = Arrays.stream(br.readLine().trim().split(", "))
                            .mapToInt(Integer::parseInt)
                            .toArray();

        // **** ****
        int day     = arr[0];
        int month   = arr[1];
        int year    = arr[2];

        // **** close buffered reader ****
        br.close();

        // ???? ????
        System.out.println("main <<< day: " + day + " month: " + month + " year: " + year);

        // **** call function of interest and return output ****
        System.out.println("main <<< output ==>" + dayOfTheWeek0(day, month, year) + "<==");

        // **** call function of interest and return output ****
        System.out.println("main <<< output ==>" + dayOfTheWeek(day, month, year) + "<==");
    }

}