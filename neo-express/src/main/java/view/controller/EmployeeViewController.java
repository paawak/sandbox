/*
 * EmployeeViewController.java
 *
 * Created on March 31, 2004, 1:51 AM
 */

package view.controller;

import javax.swing.*;

import view.*;
import controller.*;
import model.database.*;

/**
 *
 * @author  paawak
 */
public class EmployeeViewController implements TableListDB{        
    
    /** Creates a new instance of EmployeeViewController */
    public EmployeeViewController() {
    }
    
    /** 
     *method to set EmployeeView panel to recieve fresh values: in new mode
     */
    public JPanel getNewEmployeePanel() {
        employeeView = new EmployeeView();
        return employeeView;
    }
    
    /** 
     *method to set EmployeeView panel to
     *modify mode
     */
    public JPanel getModifyEmployeePanel() {
        employeeView = new EmployeeView();
        employeeView.setFormToModify();
        return employeeView;
    }  
    
    private EmployeeView employeeView = null;    
}
