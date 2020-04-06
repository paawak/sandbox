/*
 * MonthRollViewController.java
 *
 * Created on 05 April 2004, 22:20
 */

package view.controller;

/**
 *
 * @author  paawak
 */
import javax.swing.*;

import view.*;
import controller.*;
import model.database.*;

public class MonthRollViewController implements TableListDB, ClientAccountDB{
    
    /** Creates a new instance of MonthRollViewController */
    public MonthRollViewController() {
        clAccDb = new Database(clientaccount);        
    }
    
    public JPanel getNewMonthRollPanel() {
        monthRollView = new MonthRollView();
        try{
        }catch(Exception e){
            System.out.println(e);
        }
        return monthRollView;
    }
    
    private MonthRollView monthRollView = null;
    private Database clAccDb = null;
    
}
