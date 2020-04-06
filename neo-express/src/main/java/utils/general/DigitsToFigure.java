/*
 * DigitsToFigure.java
 *
 * Created on 03 May 2004, 10:41
 */

package utils.general;

/**
 *
 * @author  paawak
 */
public class DigitsToFigure {
    
    /** Creates a new instance of DigitsToFigure */
    public DigitsToFigure() {
    }
    
    public static String toFigures(String digits){
        String figure="";
        for(int i=0;i<digits.length();i++)
            figure+=digitToFigure(Character.toString(digits.charAt(i)));
        if(figure.length()>0){
        char first = Character.toUpperCase(figure.charAt(0));
        return first+figure.substring(1);
        }
        return figure;
    }
    
    private static String digitToFigure(String digit){
        if(digit.equals("0"))
            return "zero ";
        else if(digit.equals("1"))
            return "one ";
        else if(digit.equals("2"))
            return "two ";
        else if(digit.equals("3"))
            return "three ";
        else if(digit.equals("4"))
            return "four ";
        else if(digit.equals("5"))
            return "five ";
        else if(digit.equals("6"))
            return "six ";
        else if(digit.equals("7"))
            return "seven ";  
        else if(digit.equals("8"))
            return "eight ";
        else if(digit.equals("9"))
            return "nine ";     
        else 
            return " ";
    }
    
    /*private static String tenDigitToFigure(String digit){
        if(digit.equals("0"))
            return "";
        else if(digit.equals("1"))
            return "one ";
        else if(digit.equals("2"))
            return "two ";
        else if(digit.equals("3"))
            return "three ";
        else if(digit.equals("4"))
            return "four ";
        else if(digit.equals("5"))
            return "five ";
        else if(digit.equals("6"))
            return "six ";
        else if(digit.equals("7"))
            return "seven ";  
        else if(digit.equals("8"))
            return "eight ";
        else if(digit.equals("9"))
            return "nine ";     
        else 
            return "ERROR ";
    }//*/
    
}
