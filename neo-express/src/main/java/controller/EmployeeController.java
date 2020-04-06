/*
 * EmployeeController.java
 *
 * Created on 04 May 2004, 20:53
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
import utils.database.*;

public class EmployeeController implements TableListDB,EmployeeDB{
    
    /** Creates a new instance of EmployeeController */
    public EmployeeController() {
    }
    
    /**
     *saves data to the database
     */
    public boolean saveInDB(EmployeeObject obj){
        try{
            boolean saved=empDB.insertData(empDB.getColumnNames(),obj.getEmployeeObjectAsArray());
            //insert into the user table: username=firstname, password=emplid
            usrDB.insertData(usrDB.getColumnNames(),new Object[]{obj.getFirstName(),obj.getEmployeeId(),"3"});
            return saved;
        }catch (Exception e){
            System.out.println(e);
            return false;
        }
    }
    
    /**
     *updates the databse
     *only single record specified by the employeeid
     *is changed 
     *returns true/false
     */
    public boolean modifyRecord(EmployeeObject obj, String emplID){
        try{
            String where = "WHERE "+EmployeeDB.employeeid+"='"+emplID+"'";
            boolean updated = empDB.updateData(empDB.getColumnNames(),obj.getEmployeeObjectAsArray(),where);
            //update only the firstname
            usrDB.updateData(new String[]{"username"},new Object[]{obj.getFirstName()},"WHERE password='"+obj.getEmployeeId()+"'");
            return updated;
        }catch (Exception e){
            System.out.println(e);
            return false;
        }        
    }
    
    /**
     *returns a 2*rows array containing empl names and ids
     *these are to be used in the search panel
     */
    public Object[][] getEmplName_ID(){
        Object[][] obj = null;
        try{
            obj = empDB.queryMultipleColumns(new String[]{EmployeeDB.firstname,EmployeeDB.employeeid},true,"");
        }catch (Exception e){
        }
        return obj;
    }
    
    /**
     *queries the employee table by the given firstname or employeeid
     *if boolean id==true, search by emplid
     *if multiple record exists, returns null
     *else returns an instance of Employee Object
     */
    public EmployeeObject getEmployeeRecord(String val, boolean id){
        EmployeeObject obj = null;
        try{
            String col;
            if(id)
                col=EmployeeDB.employeeid;
            else
                col=EmployeeDB.firstname;
            String where = "WHERE "+col+"='"+val+"'";
            Object ob[]=empDB.queryMultipleElements(empDB.getColumnNames(),where);             
            obj = new EmployeeObject(ob);
        }catch (Exception e){
            System.out.println(e);
        }
        return obj;
    }    

    private Database empDB = new Database(employee);    
    private Database usrDB = new Database(user); 
}
