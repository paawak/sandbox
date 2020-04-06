/*
 * TransactionDetailsController.java
 *
 * Created on 05 May 2004, 20:03
 */

package controller;

/**
 *
 * @author  paawak
 */
import javax.swing.*;
import java.util.*;
import java.text.*;

import view.*;
import controller.*;
import model.*;
import model.database.*;
import utils.general.*;

public class TransactionDetailsController implements TableListDB, ClientAccountDB,ConstantsDB{
    
    private DateTimeUtils dt = new DateTimeUtils();
    
    private Database dlOrBkDb = new Database(dailyorderbook);
    
    private Database clAccDb = new Database(clientaccount);
    
    /** holds the tax percentage
     *
     */
    private float taxPercent = 0;
    
    /** holds the amount, exceeding which tds is charged
     *
     */
    private float tdsApplyAmt = 0;
    
    /** holds the tds percentage
     *
     */
    private float tdsPercent = 0;
    
    /**
     *Vector to hold all values to be dispalyed in the JTable
     */
    private Vector dump = new Vector(100);
    
    /**
     *holds the no. of columns to be displayed in the JTable
     */
    private final int COLUMNS = 6;
    
    private float grossAmt=0, grossTax=0, grossRecievable=0;
    
    /** Creates a new instance of TransactionDetailsController */
    public TransactionDetailsController() {
        setConstants();
    }
    
    /** This is used to show transactions till date
     * this method queries the database and sets the table in the view
     * also it calculates the various gross amounts and returns it as a formatted array
     * this array can be used in the view to set the respective text fields
     *
     */
    public Object[][] getAllClientDataByDate(TransactionDetailsView view,String fromDate, String toDate) {
        startRoll( fromDate,  toDate);
        //to set gross fields 
        DecimalFormat fmt = new DecimalFormat("0.00");
        view.setNumberFields(fmt.format(grossAmt),fmt.format(grossTax),fmt.format(grossRecievable));
        Object[][] obj = new Object[dump.size()/COLUMNS][COLUMNS];
        for(int i=0; i<obj.length; i++)
            for(int j=0; j<COLUMNS; j++)
                obj[i][j] = dump.elementAt(i*COLUMNS+j);
        return obj;
    }
        
    /** sets tdspercent, tax, etc
     *
     */
    private void setConstants() {
        Database constDB = new Database(constants);
        try{
            taxPercent = Float.parseFloat(constDB.queryOneElement(value,"WHERE "+ConstantsDB.name+"='TaxPercent'").toString());
            tdsPercent = Float.parseFloat(constDB.queryOneElement(value,"WHERE "+ConstantsDB.name+"='TDSPercent'").toString());
            tdsApplyAmt = Float.parseFloat(constDB.queryOneElement(value,"WHERE "+ConstantsDB.name+"='TDSApplyAmt'").toString());
        }catch(Exception e){
            System.out.println(e);
        }
    }
    
    private void startRoll(String fromDate, String toDate) {
        //delete all contents of vector dump
        if(dump.size()>0)
            dump.removeAllElements();
        grossAmt=0;
        grossTax=0;
        grossRecievable=0;
        Object[][] clientDet = null;
        String where  ="WHERE "+DailyOrderBookDB.consignmentdate+">='"+fromDate+"' AND "+DailyOrderBookDB.consignmentdate+"<='"+toDate+"' GROUP BY "+DailyOrderBookDB.accnum;
        
        try{
            clientDet = dlOrBkDb.queryMultipleColumns(new String[]{DailyOrderBookDB.accnum,"SUM("+DailyOrderBookDB.amount+")"},false,where);
            dlOrBkDb.printSQLStatement();
        }catch(Exception e){
            System.out.println(e);
        }
        for(int i=0; i<clientDet.length; i++){
            //set the invoice object and popualte the database
            Vector invVec = new Vector(InvoiceDB.fields);
            invVec.addElement("");//inv num
            invVec.addElement("");//invdate
            invVec.addElement("");//curmonth
            invVec.addElement("");//cur year
            invVec.addElement( clientDet[i][0]);//accnum
            invVec.addElement( clientDet[i][1]);//total amt
            //cal culate tax , tds
            float tax=0,tds=0,invAmt=0;
            try{
                float amt = Float.parseFloat(clientDet[i][1].toString());                
                tax = taxPercent*amt/100;
                if(amt>=tdsApplyAmt)
                    tds = tdsPercent*amt/100;
                invAmt = amt + tax - tds;
            }catch(Exception e){
            }
            invVec.addElement(new Float(tax));//tax
            invVec.addElement(new Float(tds));//tds
            //format inv amt to round to the nearest rupee
            DecimalFormat formatter = new DecimalFormat("0");
            String invAmtFrm = formatter.format(invAmt);
            
            invVec.addElement(invAmtFrm);//inv amt
            invVec.addElement( "0");//bookadjustment amount
            invVec.addElement( "");//remarks
            invVec.addElement(invAmtFrm);//netrecievable
            invVec.addElement( "0");//recieved amt
            invVec.addElement( "0");//is part payment
            invVec.addElement( "0");//has paid full
            invVec.addElement("");//empl. id
            invVec.addElement( dt.getMySqlTime());
            invVec.addElement( dt.getMySqlDate());
            
            //cast the invVec into Invoice object
            
            InvoiceObject invObj = null;
            try{
                invObj = new InvoiceObject(invVec); 
                getRecordDetails(invObj,fromDate,toDate);                
            }catch(Exception e){
                System.out.println(e+"nnnnnnnnnnnnnnnn");
            }            
            
        }//end for
    }
    
    /** method to write the INVOICE bill for each client in a file
     * so that a printout can be taken
     *
     */
    private void getRecordDetails(InvoiceObject invObj, String fromDate, String toDate) {
        //date pattern
        String pattern = "MMM dd, yyyy";
        Object accnum = invObj.getAccNum();
        //get formatted date
        Object totalAmt = invObj.getTotalAmt();
        Object tax = invObj.getTax();
        Object tds = invObj.getTdsAmount();
        Object netRecievable = invObj.getNetReceivable();
        ClientAccountObject clObj = null;
        
        String where  ="WHERE "+DailyOrderBookDB.consignmentdate+">='"+fromDate+"' AND "+DailyOrderBookDB.consignmentdate+"<='"+toDate+"' AND "+DailyOrderBookDB.accnum+"='"+accnum+"' ORDER BY "+DailyOrderBookDB.consignmentdate;
        Object[][] obj = null;
        
        try{
            obj = dlOrBkDb.queryMultipleColumns(dlOrBkDb.getColumnNames(),false,where);
            //init ClientAccountObject
            where = "WHERE "+ClientAccountDB.accnum+"='"+accnum+"'";
            Object[] clObjArr = clAccDb.queryMultipleElements(clAccDb.getColumnNames(),where);
            clAccDb.printSQLStatement();
            clObj = new ClientAccountObject(clObjArr);
        }catch(Exception e){
            System.out.println(e);
        }
        
        if(clObj==null){
            System.out.println("Cannot create an instance of ClientAccountObject");
            return ;
        }//*/
        
        //there will be six columns
        //Client name and a/num
        dump.addElement("Client Name: ");
        dump.addElement(clObj.getName());
        dump.addElement("Client Account No.: ");
        dump.addElement(clObj.getAccNum());        
        dump.addElement("Organisation: ");
        if(clObj.getIsOrganization().toString().trim().equals("1"))
            dump.addElement("Yes");
        else
            dump.addElement("No");
        
        printBlankRow();

        for(int i=0;i<obj.length;i++){
            //sl no.
            dump.addElement(Integer.toString(i+1));
            //consignment no.
            dump.addElement(obj[i][0]);
            //consignment date
            dump.addElement(dt.getFormattedDate(obj[i][1].toString(), pattern));
            //destination
            dump.addElement(obj[i][4]);
            //weight kg, grms
            dump.addElement(obj[i][6]+"."+obj[i][7]);
            //amt
            dump.addElement(obj[i][9]);
        }
        
        printBlankRow();
        //total amt
        printBlankColumns(3);        
        dump.addElement("Total Amount:");
        dump.addElement("");
        dump.addElement(totalAmt);
        //tax
        printBlankColumns(3);
        dump.addElement("(+) Service Tax @ "+taxPercent+"%:");
        dump.addElement("");
        dump.addElement(tax);
        //tds
        //grnd total: net recievable amt
        printBlankColumns(3);
        dump.addElement("Grand Total:");
        dump.addElement("");
        dump.addElement(netRecievable);        
        
        //end of data for a client
        
        printBlankRow();
        printBlankRow();
        printBlankRow();
        
        grossAmt+=Float.parseFloat(totalAmt.toString());
        grossTax+=Float.parseFloat(tax.toString());
        grossRecievable+=Float.parseFloat(netRecievable.toString());       
    }
    
    /**
     *method to print a blank row in the JTable
     */
    private void printBlankRow(){
        for(int i=0; i<COLUMNS; i++)
            dump.addElement("");
    }
    
    /**
     *method to print 'n' blank cols in the JTable
     */
    private void printBlankColumns(int n){
        if(n>COLUMNS)
            return ;
        for(int i=0; i<n; i++)
            dump.addElement("");
    }    
            
}
