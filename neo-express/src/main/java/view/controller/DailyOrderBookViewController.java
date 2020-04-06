/*
 * DailyOrderBookViewController.java
 *
 * Created on March 30, 2004, 11:31 PM
 */

package view.controller;

import javax.swing.*;

import view.*;
import model.database.*;
import controller.*;

/**
 *
 * @author  paawak
 */
public class DailyOrderBookViewController implements TableListDB,ClientAccountDB,DailyOrderBookDB{
    
    /** Creates a new instance of DailyOrderBookViewController */
    public DailyOrderBookViewController() {
        clAccDb = new Database(clientaccount);
    }
    
    /**
     *method to set DailyOrderBookView panel to recieve fresh values: in new mode
     *the fields lbDeliveryDate, txtDeliveryDate, lbDeliveryTime, txtDeliveryTime will be invisible
     */
    public JPanel getNewDailyOrderBookPanel(){
        dailyOrderBookView = new DailyOrderBookView();
        dailyOrderBookView.setFormToAdd();
        try{
            dailyOrderBookView.fillCustNameCombo(clAccDb.queryOneColumn(ClientAccountDB.name,true,"WHERE "+status+"=1 ORDER BY "+ClientAccountDB.name));
        }catch(Exception e){
            System.out.println(e);
        }
        return dailyOrderBookView;
    }
    
    /**
     *sets form to modify mode
     */
    public JPanel getModifyDailyOrderBookPanel(){
        dailyOrderBookView = new DailyOrderBookView();
        try{
            dailyOrderBookView.fillCustNameCombo(clAccDb.queryOneColumn(ClientAccountDB.name,true,"WHERE "+status+"=1 ORDER BY "+ClientAccountDB.name));
        }catch(Exception e){
            System.out.println(e);
        }        
        dailyOrderBookView.setFormToModify();
        return dailyOrderBookView;
    }  
    
    /**
     *sets form to bulk order mode
     */
    public JPanel getBulkDailyOrderBookPanel(){
        dailyOrderBookView = new DailyOrderBookView();
        dailyOrderBookView.setFormToBulkOrder();
        try{
            dailyOrderBookView.fillCustNameCombo(clAccDb.queryOneColumn(ClientAccountDB.name,true,"WHERE "+status+"=1 ORDER BY "+ClientAccountDB.name));
        }catch(Exception e){
            System.out.println(e);
        }        
        return dailyOrderBookView;
    }  
    
    //member variables
    private DailyOrderBookView dailyOrderBookView = null;
    private Database clAccDb = null;
}
