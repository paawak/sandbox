/*
 * ClientAccountController.java
 *
 * Created on April 2, 2004, 12:36 AM
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

public class ClientAccountController implements TableListDB,ClientAccountDB{
    
    /** Creates a new instance of ClientAccountController */
    public ClientAccountController() {
    } 
    
    /**
     *to populate database
     */
    public  void saveInDB(ClientAccountView clientAccountView, ClientAccountObject clObj){
        try{
            if(clientDB.insertData(clientDB.getColumnNames(), clObj.getClientAccountObjectAsArray())){
                if(smCntr.updateMaxCount()){
                    JOptionPane.showMessageDialog(clientAccountView,"Data enterd by you has been saved.","Saved successfully:",JOptionPane.INFORMATION_MESSAGE);
                }
                else{
                    String sql = "DELETE FROM "+clientaccount+" WHERE "+accnum+"='"+clObj.getAccNum()+"'";
                    clientDB.executeSQLStatement(sql);
                    JOptionPane.showMessageDialog(clientAccountView,"Data enterd by you could not be saved. Please try again.","Sorry:",JOptionPane.ERROR_MESSAGE);                                        
                }
            }
            else{
                JOptionPane.showMessageDialog(clientAccountView,"Data enterd by you could not be saved. Please try again.","Sorry:",JOptionPane.ERROR_MESSAGE);                                        
            }
            clientAccountView.resetFields();
            clientAccountView.setAccNum(smCntr.getID());
        }catch(Exception e){
            System.out.println(e);
        }
    }
    
    /**
     *returns an instance of ClientAccountObject from database
     *if id=true, then val = accnum
     *else
     *val=name
     */
    public ClientAccountObject getClientObj(Object val,boolean id){
        String col;
        if(id)
            col=ClientAccountDB.accnum;
        else
            col=ClientAccountDB.name;
        ClientAccountObject clObj = null;
        try{
            String where="WHERE "+col+"='"+val+"'";
            clObj= new ClientAccountObject(clientDB.queryMultipleElements(clientDB.getColumnNames(),where));
        }catch(Exception e){
        }
        return clObj;
    }
    
    /**
     *updates a given record
     */
    public void updateRecord(ClientAccountView clientAccountView,ClientAccountObject clObj){
        String where = "WHERE "+ClientAccountDB.accnum+"='"+clObj.getAccNum()+"'";
        try{
            if(clientDB.updateData(clientDB.getColumnNames(),clObj.getClientAccountObjectAsArray(),where))
                JOptionPane.showMessageDialog(clientAccountView,"Data modified successfully","Modify successful",JOptionPane.INFORMATION_MESSAGE);
            else
                JOptionPane.showMessageDialog(clientAccountView,"Data could not be modified","Sorry",JOptionPane.ERROR_MESSAGE);
        }catch(Exception e){
        }
    }
    
    /** returns a 2*rows array containing client names and accnums
     * these are to be used in the search panel
     *
     */
    public Object[][] getClientName_ID() {
        Object[][] obj = null;
        try{
            obj = clientDB.queryMultipleColumns(new String[]{ClientAccountDB.name,ClientAccountDB.accnum},true,"");
        }catch (Exception e){
        }
        return obj;
    }
    
    private Object[] dataArr = null;
    private SemaphoresController smCntr = new SemaphoresController(clientaccount);
    private Database clientDB = new Database(clientaccount);
}
