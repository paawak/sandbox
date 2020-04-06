/*
 * DailyOrderBookController.java
 *
 * Created on 04 April 2004, 13:29
 */

package controller;

/**
 *
 * @author  paawak
 */
import javax.swing.*;
import java.util.*;

import view.*;
import model.database.*;
import model.*;


public class DailyOrderBookController implements TableListDB,DailyOrderBookDB, ClientAccountDB, EmployeeDB{
        
    private Database dailyBookDB = new Database(dailyorderbook);
    private Database clAccDb = new Database(clientaccount);
    private Database empl = new Database(employee);
    private Database cnst = new Database(constants);
    
    private Object[] dataArr = null;
    
    /** Creates a new instance of DailyOrderBookController */
    public DailyOrderBookController() {
    }
    
    /** 
     *to populate database
     *
     */
    public void saveInDB(DailyOrderBookView dailyOrderBookView, DailyOrderBookObject dlObj, boolean bulk) {
        try{
            Object[] objArr = dlObj.getDailyOrderBookObjectAsArray();
            if(dailyBookDB.insertData(dailyBookDB.getColumnNames(),objArr)){
                if(!bulk)
                    JOptionPane.showMessageDialog(dailyOrderBookView,"Data enterd by you has been saved.","Saved successfully:",JOptionPane.INFORMATION_MESSAGE);
            }
            else{
                JOptionPane.showMessageDialog(dailyOrderBookView,"Data enterd by you could not be saved. Please try again.","Sorry:",JOptionPane.ERROR_MESSAGE);
            }
            dailyOrderBookView.resetFields();
        }catch(Exception e){
            System.out.println(e);
        }
    }
    
    /** 
     *to modify existing record
     *
     */
    public void modifyRecord(DailyOrderBookView dailyOrderBookView, DailyOrderBookObject dlObj, Object consNum) {
        try{
            //to get the id from the name of the client
            dlObj.setAccNum(clAccDb.queryOneElement(ClientAccountDB.accnum,"WHERE "+name+"='"+dlObj.getAccNum()+"'"));
            Object[] objArr = dlObj.getDailyOrderBookObjectAsArray();
            String where = "WHERE "+DailyOrderBookDB.consignmentnum+"='"+consNum+"'";
            if(dailyBookDB.updateData(dailyBookDB.getColumnNames(),objArr,where)){
                    JOptionPane.showMessageDialog(dailyOrderBookView,"Data enterd by you has been modified.","Modified successfully:",JOptionPane.INFORMATION_MESSAGE);
            }
            else{
                JOptionPane.showMessageDialog(dailyOrderBookView,"Data enterd by you could not be modified. Please try again.","Sorry:",JOptionPane.ERROR_MESSAGE);
            }
            dailyOrderBookView.resetFields();
        }catch(Exception e){
            System.out.println(e);
        }
    }    
    
    /**
     *to get the employee id of the person logged in
     */
    public Object getEmplIDLoggedIn(){
        Object emplID = null;
        try{
            String where = "WHERE "+EmployeeDB.firstname+"='"+LoginDialog.getLoginID()+"'";
            emplID = empl.queryOneElement(EmployeeDB.employeeid,where);
        }catch(Exception e ){
            System.out.println(e);
        }
        
        return emplID;
    }
    
    /** gets a 2-dim array containing client name and consignment num
     * this is for populating the SearchPanel
     *
     */
    public Object[][] getClientName_ConsignNum() {
        Object[] consNum = null, objClientName = null;
        
        try{
            consNum = dailyBookDB.queryOneColumn(DailyOrderBookDB.consignmentnum,true,"ORDER BY "+DailyOrderBookDB.consignmentnum);
            objClientName = clAccDb.queryOneColumn(ClientAccountDB.name,true,"ORDER BY "+ClientAccountDB.accnum);
        }catch(Exception e){
        }
        
        int len=0;
        if(consNum.length>objClientName.length)
            len = consNum.length;
        else
            len = objClientName.length;
        
        Object[][] obj = new Object[len][2];
        
        for(int i=0;i<len;i++){
            if(i<objClientName.length)
                obj[i][0] = objClientName[i];
            else
                obj[i][0] = "";
            
            if(i<consNum.length)
                obj[i][1] = consNum[i];
            else
                obj[i][1] = "";
        }
        
        return obj;
    }
    
    /**
     *returns an array containing the record of a single entry in daily order book
     */
    public DailyOrderBookObject getDailyOrderBookRecord(Object consNum){
        DailyOrderBookObject obj = null;
        try{
            String where = "WHERE "+DailyOrderBookDB.consignmentnum+"='"+consNum+"'";
            obj=new DailyOrderBookObject(dailyBookDB.queryMultipleElements(dailyBookDB.getColumnNames(),where));            
        }catch(Exception e){
        }
        
        return obj;        
    }    
    
    /**
     *deletes a given record
     */
    public boolean  deleteRecord(Object consNum){
        try{
            String where = " WHERE "+DailyOrderBookDB.consignmentnum+"='"+consNum+"'";
            dailyBookDB.executeSQLStatement("DELETE FROM "+dailyorderbook+where);            
            return true;
        }catch(Exception e){
            return false;
        }        
    }
    
    /**
     *gets the no. of prices in the range of the nearest slab
     */
    public Vector getSlabPrice(int weight){
        Object[] obj = null;
        try{
            String where = "WHERE "+ConstantsDB.name+"='AmtSlab' ORDER BY "+ConstantsDB.value+" ASC";
            obj = cnst.queryOneColumn(ConstantsDB.value,true,where);
        }catch(Exception e ){
        }
        
        int lowerLt=0,upperLt=0;
        //get upper and lower limit of the slab
        for(int i=0;i<obj.length-1;i++){
            int int1 = Integer.parseInt(obj[i].toString());
            int int2 = Integer.parseInt(obj[i+1].toString());
            if(weight>=int1 && weight<=int2){
                lowerLt=int1;
                upperLt=int2;
                break;
            }
            
            if(weight<int1 && weight<int2){
                lowerLt=int1;
                break;
            }
            
            if(weight>int1 && weight>int2){
                upperLt=int2;
            }            
        }
        
        Vector vec = null;
        try{
            String where="WHERE "+DailyOrderBookDB.weightgram+"+1000*"+DailyOrderBookDB.weightkg+">='"+lowerLt+"' AND "+DailyOrderBookDB.weightgram+"+1000*"+DailyOrderBookDB.weightkg+"<='"+upperLt+"' ORDER BY "+DailyOrderBookDB.amount+" ASC";
            if(upperLt==0 && lowerLt!=0)
                where = "WHERE "+DailyOrderBookDB.weightgram+"+1000*"+DailyOrderBookDB.weightkg+"<'"+lowerLt+"' ORDER BY "+DailyOrderBookDB.amount+" ASC";
            else if(upperLt!=0 &&lowerLt==0)
                where = "WHERE "+DailyOrderBookDB.weightgram+"+1000*"+DailyOrderBookDB.weightkg+">'"+upperLt+"' ORDER BY "+DailyOrderBookDB.amount+" ASC";
            
            vec = dailyBookDB.queryOneColumn_Vec(DailyOrderBookDB.amount,true,where);
            dailyBookDB.printSQLStatement();
        }catch(Exception e ){
            System.out.println(e);
            dailyBookDB.printSQLStatement();
        }     
        
        return vec;
    }
    
    /**
     *returns array containing all enterd destination
     */
    public Object[] getDestinationArr() {
        Object obj[] = null;
        
        try{
            obj = dailyBookDB.queryOneColumn(DailyOrderBookDB.destination,true,"ORDER BY "+DailyOrderBookDB.destination);
            dailyBookDB.printSQLStatement();
        }catch(Exception e){
            System.out.println(e);
            dailyBookDB.printSQLStatement();
        }
        
        return obj;
    }    
    
    /** gets the Consignment nums between frm date and to date and the given client name: which is converted to accnum
     *
     */
    public Object[] getConsignmentNums(String frmDate, String toDate, Object clientName) {
        Object clientAcc = null;
        try{
            clientAcc = clAccDb.queryOneElement(ClientAccountDB.accnum,"WHERE "+ClientAccountDB.name+"='"+clientName+"'");
        }catch (Exception e){
        }
        Object[] obj = null;
        try{
            String where="WHERE "+DailyOrderBookDB.consignmentdate+">='"+frmDate+"' AND "+DailyOrderBookDB.consignmentdate+"<='"+toDate+"' AND "+DailyOrderBookDB.accnum+"='"+clientAcc+"'";
            obj = dailyBookDB.queryOneColumn(DailyOrderBookDB.consignmentnum,true,where+" ORDER BY "+DailyOrderBookDB.consignmentnum);
        }catch(Exception e){
        }
        return obj;
    }
    
    /** gets the Consignment nums between frm date and to date
     *
     */
    public Object[] getConsignmentNums(String frmDate, String toDate) {
        Object[] obj = null;
        try{
            String where="WHERE "+DailyOrderBookDB.consignmentdate+">='"+frmDate+"' AND "+DailyOrderBookDB.consignmentdate+"<='"+toDate+"'";
            obj = dailyBookDB.queryOneColumn(DailyOrderBookDB.consignmentnum,true,where+" ORDER BY "+DailyOrderBookDB.consignmentnum);
        }catch(Exception e){
        }
        return obj;
    }
    
    /** gets an array containing client names
     * between the given dates whose records are present in invoice table
     *
     */
    public Object[] getClientNames(String frmDate, String toDate) {
        Object[] obj = null;
        try{
            String where="WHERE "+DailyOrderBookDB.consignmentdate+">='"+frmDate+"' AND "+DailyOrderBookDB.consignmentdate+"<='"+toDate+"'";
            obj = dailyBookDB.queryOneColumn(DailyOrderBookDB.accnum,true,where+" ORDER BY "+DailyOrderBookDB.accnum);
        }catch(Exception e){  
        }
        for(int i=0;i<obj.length;i++)
            try{
                obj[i] = clAccDb.queryOneElement(ClientAccountDB.name,"WHERE "+ClientAccountDB.accnum+"='"+obj[i]+"'");
            }catch (Exception e){
            }
        return obj;
    }
    
}
