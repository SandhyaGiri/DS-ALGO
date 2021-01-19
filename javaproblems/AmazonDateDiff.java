package javaproblems;

public class AmazonDateDiff {
    // Write a method where given two dates tell me if they are <, > or == to a month apart.

    // 1 - jan- 2020 , 15-feb-2020 > 1 month
    // 1 -jan-2020, 1-Feb-2020 > 1 month (31 days)
    // 15-Dec-2019, 14-Jan-2020

    // 1 month = 30 days

    class Date{
        int day;
        int month;
        int year;
    }

    // maxDaysMap - monthNUmber -> maxDays
    int compareDates(Date date1, Date date2){
        int numDaysInMonth = 30;
        if(Math.abs(date1.year, date2.year) <= 1){
            // number of days difference between two dates
            
            // only for dates in the same year
            Date firstDate = date1.month < date2.month ? date1 : date2; 
            Date secondDate = date1.month < date2.month ? date2 : date1;
            // count days from firstDate to secondDate
            int currMonth = firstDate.month;
            int currDay = firstDate.day;
            int daysDiff = 0;
            while(currMonth < secondDate.month){
                int maxDays = maxDaysMap.get(currMonth);
                daysDiff += Math.abs(maxDays - currDay);
                currDay = 1;// start with 1st day in next month
                currMonth = (currMonth+1) % 12;
            }
            // final window of number days in secondDate's month
            daysDiff += Math.abs(secondDate.day - currDay);
            int monthsDiff = daysDiff / numDaysInMonth;
            return monthsDiff == 1 ? 0 : ((monthsDiff < 1) ? -1 : +1);
        } else { // certainly more than 1 month apart
            return 1;
        }
    }
}
