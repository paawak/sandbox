/*
 * DateTimeUtils.java
 *
 * Created on 04 April 2004, 19:04
 */

package utils.general;

/**
 *
 * @author  paawak
 */
import java.util.*;
import java.text.*;

public class DateTimeUtils {
    
    /** Creates a new instance of DateTimeUtils */
    public DateTimeUtils() {
        
    }
    
    public String getMySqlDateTime(){
        String pattern = "yyyy/MM/dd HH:mm:ss";
        today = new Date();
        return getFormattedDate(today,pattern);
    }
    
    public String getMySqlDate(){
        String pattern = "yyyy/MM/dd";     
        today = new Date();
        return getFormattedDate(today,pattern);
    }
    
    public String getMySqlTime(){
        String pattern = "HH:mm:ss";
        today = new Date();
        return getFormattedDate(today,pattern);
    }    
    
    /**
     *converts the given String date to an instance of java.util.GregorianCalendar and returns the same
     *the string should be in the format yyyy-mm-dd
     */
    public GregorianCalendar getGrCalFromString(String date){
        //extract year
        int year=0,month=0,day=0;
        try{
            year = Integer.parseInt(date.substring(0,4));
            month = Integer.parseInt(date.substring(5,7));
            day = Integer.parseInt(date.substring(8,10));
        }catch(NumberFormatException e){
        }
        GregorianCalendar gCal = new GregorianCalendar(year,month-1,day);
        
        return gCal;
    }
    
    /**
     *converts the given String date to an instance of java.util.Date and returns the same
     *the string should be in the format yyyy-mm-dd
     */    
    public Date getDateFromString(String date){
        return getGrCalFromString(date).getTime();
    }
    
    /**
     *method to return the date in the desired format
     *accepts a java.util.Date as argument
     */
    public String getFormattedDate(Date date, String pattern){
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        return formatter.format(date);
    }
    
    /**
     *method to return the date in the desired format
     *accepts a String date, formatted yyyy-mm-dd as argument
     */
    public String getFormattedDate(String date, String pattern){
        return getFormattedDate(getDateFromString(date),pattern);
    }
    
    private Date today = null;
    
}
