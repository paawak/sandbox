/*
 * TxtFldValidation.java
 *
 * Created on January 22, 2003, 8:21 PM
 */

package utils.general;

import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.text.*;

/**
 *
 * @author  paawak
 */
public class TxtFldValidation extends javax.swing.JFrame {
    
    /** Creates a new instance of TxtFldValidation */
    public TxtFldValidation() {
    }
    
    /**
     *method to check whether a valid decimal no. is entered<br>
     *sets the  fornatted String<br>
     *if data is invalid, returns 0
     */
    public boolean checkDecimal(javax.swing.JTextField txtFld, String pattern){
        boolean flag = false;
        this.pattern=pattern;
         double num=0;
         if(txtFld.getText().trim().equals("")){
                JOptionPane.showMessageDialog(this,"This Field cannot be empty!","Error!",JOptionPane.INFORMATION_MESSAGE);    
                txtFld.setText(returnFormattedDecimal(0)); 
                txtFld.requestFocus();
        }//end else if
         
         else
       try{
            num=Double.parseDouble(txtFld.getText());   
            if(num<0){
                num=0;
                JOptionPane.showMessageDialog(this,"This Field cannot be negative!","Error!",JOptionPane.INFORMATION_MESSAGE);                    
                txtFld.setText(returnFormattedDecimal(0)); 
                txtFld.requestFocus();
            }//end if
            else
                flag=true;

        }//end try
        catch(NumberFormatException e){            
            JOptionPane.showMessageDialog(this,"Enter a valid number!","Error!",JOptionPane.ERROR_MESSAGE);    
            txtFld.setText(returnFormattedDecimal(0)); 
            txtFld.requestFocus();
        }//end catch
                txtFld.setText(returnFormattedDecimal(num));
                return flag;
    }//end methd.
   
    /**
     *to return the decimal no. formatted according to the given String
     */
    private String returnFormattedDecimal(double num){        
        DecimalFormat myFormatter = new DecimalFormat(pattern);
        return  myFormatter.format(num);        
    }
    
        public boolean checkNumber(javax.swing.JTextField txtFld){
           boolean flag = false; 
           long num=0;
         if(txtFld.getText().trim().equals("")){
             JOptionPane.showMessageDialog(this,"This Field cannot be empty!","Error!",JOptionPane.INFORMATION_MESSAGE);    
             txtFld.setText("0");                         
        }//end else if
         
         else
       try{
            num=Long.parseLong(txtFld.getText());   
            if(num<0){
                num=0;
                JOptionPane.showMessageDialog(this,"This Field cannot be negative!","Error!",JOptionPane.INFORMATION_MESSAGE);                    
                txtFld.setText("0"); 
                txtFld.requestFocus();  
            }//end if
            else
                flag = true;
        }//end try
        catch(NumberFormatException e){
            JOptionPane.showMessageDialog(this,"Enter a valid number!","Error!",JOptionPane.ERROR_MESSAGE);    
            txtFld.setText("0");          
            txtFld.requestFocus();    
        }//end catch
           txtFld.setText(Long.toString(num)); 
           return flag;
            
    }//end methd.

        private String pattern = null;
                
}//end class
