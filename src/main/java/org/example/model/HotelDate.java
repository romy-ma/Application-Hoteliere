package org.example.model;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Calendar;

public class HotelDate {
    private int Day,Month,Year;
    //creating HotelDate class for managing dates instead of the date class
    public HotelDate(int day,int month,int year) throws DateException{
        if(DateValid(day, month, year)&&isAfterToday(day,month,year))
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
    }

    @Override
    public String toString() {
        return "{" +
                "Day=" + Day +
                ", Month=" + Month +
                ", Year=" + Year +
                '}';
    }

    public boolean isAfterToday(int Day, int Month, int Year)
    {
        LocalDate localDate = LocalDate.now();
        if(Year > localDate.getYear())
        {
            return true;
        }
        else if(Year == localDate.getYear())
        {
            if(Month > localDate.getMonthValue())
            {
                return true;
            }
            else if(Month == localDate.getMonthValue())
            {
                if(Day > localDate.getDayOfMonth())
                {
                    return true;
                }
                else
                {
                    return false;
                }
            }
            else
            {
                return false;
            }
        }
        else
        {
            return false;
        }
    }
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
    public void set(int Day ,int Month ,int Year)
    {
        this.Day = Day;
        this.Month = Month;
        this.Year = Year;
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

    public Date toSqlDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Year, Month - 1, Day); // Calendar months are 0-based
        return new Date(calendar.getTimeInMillis());
    }

    public long getTime() {
        return 0;
    }

    public int compareTo(HotelDate endDate) {
        if(this.Year > endDate.Year)
        {
            return 1;
        }
        else if(this.Year < endDate.Year)
        {
            return -1;
        }
        else
        {
            if(this.Month > endDate.Month)
            {
                return 1;
            }
            else if(this.Month < endDate.Month)
            {
                return -1;
            }
            else
            {
                if(this.Day > endDate.Day)
                {
                    return 1;
                }
                else if(this.Day < endDate.Day)
                {
                    return -1;
                }
                else
                {
                    return 0;
                }
            }
        }
    }
}
