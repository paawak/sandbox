/*
 * EmployeeObject.java
 *
 * Created on March 31, 2004, 11:35 PM
 */

package model;

/**
 *
 * @author  paawak
 */
import java.util.*;

import model.database.*;

public class EmployeeObject implements EmployeeDB {
    
    private Object employeeid = null ;
    
    private Object firstname = null ;
    
    private Object middlename = null ;
    
    private Object lastname = null ;
    
    private Object telephone1 = null ;
    
    private Object telephone2 = null ;
    
    private Object mobile = null ;
    
    private Object fax = null ;
    
    private Object email = null ;
    
    private Object address1 = null ;

    private Object address2 = null ;
    
    private Object city = null ;
    
    private Object state = null ;
    
    private Object dateofbirth = null ;
    
    private Object startdate = null ;
    
    private Object enddate = null ;
    
    private Object status = null ;
        
    private Object[] objArray = null;
            
    private Vector vecArray = null;
        
    /** Creates a new instance of EmployeeObject */
    public EmployeeObject() {
    }
    
    public EmployeeObject(Object[] objArray) throws Exception {
        setEmployeeObject(objArray);
    }
    
    public EmployeeObject(Vector vecArray) throws Exception {
        setEmployeeObject(vecArray);
    }
    
    public Object[] getEmployeeObjectAsArray() {
         Object[] objArray = new Object[fields];         
         objArray[0] = employeeid;
         objArray[1] = firstname;
         objArray[2] = middlename;
         objArray[3] = lastname;
         objArray[4] = telephone1;
         objArray[5] = telephone2;
         objArray[6] = mobile;
         objArray[7] = fax;
         objArray[8] = email;
         objArray[9] = address1;
         objArray[10] = address2;
         objArray[11] = city;
         objArray[12] = state;
         objArray[13] = dateofbirth;
         objArray[14] = startdate;        
         objArray[15] = enddate;
         objArray[16] = status;                 
         return objArray;
    }
    
    /** method to set the class variables
     *
     */
    public Object getEmployeeId(){
        return employeeid;
    }
    public void setEmployeeId(Object employeeid){
        this.employeeid=employeeid;
    }

    public Object getFirstName(){
        return firstname;
    }
    public void setFirstName(Object firstname){
        this.firstname=firstname;
    }    
 
    public Object getMiddleName(){
        return middlename;
    }
    public void setMiddleName(Object middlename){
        this.middlename=middlename;
    }    
    
    public Object getLastName(){
        return lastname;
    }
    public void setLastName(Object lastname){
        this.lastname=lastname;
    }    
    
    public Object getTelephone1(){
        return telephone1;
    }
    public void setTelephone1(Object telephone1){
        this.telephone1=telephone1;
    }    
    
    public Object getTelephone2(){
        return telephone2;
    }
    
    public void setTelephone2(Object telephone2){
        this.telephone2=telephone2;
    }    
    
    public Object getMobile(){
        return mobile;
    }
    public void setMobile(Object mobile){
        this.mobile=mobile;
    }    
    
    public Object getFax(){
        return fax;
    }
    public void setFax(Object fax){
        this.fax=fax;
    }    
    
    public Object getEmail(){
        return email;
    }
    public void setEmail(Object email){
        this.email=email;
    }    
    
    public Object getAddress1(){
        return address1;
    }
    public void setAddress1(Object address1){
        this.address1=address1;
    }    
    
    public Object getAddress2(){
        return address2;
    }
    public void setAddress2(Object address2){
        this.address2=address2;
    }    
    
    public Object getState(){
        return state;
    }
    public void setState(Object state){
        this.state=state;
    }  
    
    public Object getCity(){
        return city;
    }
    public void setCity(Object city){
        this.city=city;
    }        
    
    public Object getDateOfBirth(){
        return dateofbirth;
    }
    public void setDateOfBirth(Object dateofbirth){
        this.dateofbirth=dateofbirth;
    }    
    
    public Object getStartDate(){
        return startdate;
    }
    public void setStartDate(Object startdate){
        this.startdate=startdate;
    }    
    
    public Object getEndDate(){
        return enddate;
    }
    public void setEndDate(Object enddate){
        this.enddate=enddate;
    }    
    
    public Object getStatus(){
        return status;
    }
    public void setStatus(Object status){
        this.status=status;
    }    
    
    public void setEmployeeObject(Object[] objArray) throws Exception {
        if(objArray.length!=fields)
            throw new Exception();
        
        this.objArray = objArray;
        
        employeeid = objArray[0];
        firstname = objArray[1];
        middlename = objArray[2];
        lastname = objArray[3];
        telephone1 = objArray[4];
        telephone2 = objArray[5];
        mobile = objArray[6];
        fax = objArray[7];
        email = objArray[8];
        address1 = objArray[9];
        address2 = objArray[10];
        city = objArray[11];
        state = objArray[12];
        dateofbirth = objArray[13];
        startdate = objArray[14];
        enddate = objArray[15];
        status = objArray[16];        
        
    }
    
    public void setEmployeeObject(Vector vecArray) throws Exception {
        int len = vecArray.size();
        if(len!=fields)
            throw new Exception();
        this.vecArray = vecArray;
        Object[] objArray = new Object[len];
        for(int i=0; i<len; i++)
            objArray[i] = vecArray.elementAt(i);
        setEmployeeObject(objArray);
    }
    
}
