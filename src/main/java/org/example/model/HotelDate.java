package org.example.model;

public class HotelDate {
    private int Day,Month,Year;
    //creating HotelDate class for managing dates instead of the date class
    public HotelDate(int day,int month,int year) throws DateException{
        if(DateValid(day, month, year))
        {
            this.Day = day;
            this.Month = month;
            this.Year = year;
        }
        else
        {
            throw new DateException("error in Date");
            //throwing an exception if the date is not valide
        }
    };
    //generate getter sand setters
    public int getDay() {
        return Day;
    }

    public void setDay(int day) {
        Day = day;
    }

    public int getMonth() {
        return Month;
    }

    public void setMonth(int month) {
        Month = month;
    }

    public int getYear() {
        return Year;
    }

    public void setYear(int year) {
        Year = year;
    }
    //method for checking if the date is valide
    boolean DateValid(int day,int month,int year)
    {
        if(year<1 || year>Integer.MAX_VALUE)
        {
            return false;
        }
        if(month<1 || month>12)
        {
            return false;
        }
        if(day<1 || day>NumberOfDaysInMonth(month,year))
        {
            return false;
        }
        return true;
    }
    //method to give the numnber of days in a month
    public int NumberOfDaysInMonth(int month ,int year)
    {
        int[] number = {31,28,31,30,31,30,31,31,30,31,30,31};
        if(month ==2 && isLeapYear(year))
        {
            return 29;
        }
        else
        {
            return number[month - 1];
        }
    }
    //method to check if the year is leap year(feb 28 or 29)
    public boolean isLeapYear(int year)
    {
        if((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public long getTime() {
        return 0;
    }
}
