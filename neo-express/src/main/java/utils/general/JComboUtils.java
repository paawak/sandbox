/*
 * JComboUtils.java
 *
 * Created on January 14, 2003, 12:35 PM
 */

/**
 *modified on December 15,2003, 03:03 AM
 *the method removeComboElements() deleted.
 *all error messages in the exception block removed
 **/

package utils.general;

/**
 *
 * @author  paawak
 */

public class JComboUtils {

    
    public JComboUtils() {
    }

public static void fillComboWithNewArray(javax.swing.JComboBox combo,Object[] array){
    
        try{
                combo.removeAllItems();
        }//end try
        catch(Exception e){
        }
    try{
        for(int i=0;i<array.length;i++)
            combo.addItem(array[i]);
    }catch(Exception e){
    }
    
}


}//end class
