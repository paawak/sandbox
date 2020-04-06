/*
 * ClientAccountObject.java
 *
 * Created on April 1, 2004, 12:48 AM
 */

package model;

/**
 *
 * @author  paawak
 */
import java.util.*;

import model.database.*;

public class ClientAccountObject implements ClientAccountDB{
    
    /** Creates a new instance of ClientAccountObject */
    public ClientAccountObject() {
    }
    
    public ClientAccountObject(Object[] objArray) throws Exception {
        setClientAccountObject(objArray);
    }
    
    public ClientAccountObject(Vector vecArray) throws Exception {
        setClientAccountObject(vecArray);
    }
    
    public Object[] getClientAccountObjectAsArray() {
        return objArray;
    }
    
    /** method to set the class variables
     *
     *
     */
    public void setClientAccountObject(Object[] objArray) throws Exception {
        if(objArray.length!=fields)
            throw new Exception();
        
        this.objArray = objArray;
        
         accnum = objArray[0];
        
         name = objArray[1];

         shortname = objArray[2];

         contactfirstname = objArray[3];

         contactmiddlename = objArray[4];

         contactlastname = objArray[5];

         telephone1 = objArray[6];

         telephone2 = objArray[7];

         mobile = objArray[8];

         fax = objArray[9];

         email = objArray[10];

         address1 = objArray[11];

         address2 = objArray[12];

         city = objArray[13];

         state = objArray[14];

         isorganization  = objArray[15];

         status  =  objArray[16];
     
    }
    
    public void setClientAccountObject(Vector vecArray) throws Exception {
        int len = vecArray.size();
        if(len!=fields)
            throw new Exception();
        this.vecArray = vecArray;
        Object[] objArray = new Object[len];
        for(int i=0; i<len; i++)
            objArray[i] = vecArray.elementAt(i);
        setClientAccountObject(objArray);
    }
    
    public Object getAccNum(){
        return accnum;
    }
    
    
    public Object getName(){
        return name;
    }    

    public Object getShortName(){
        return shortname;
    }
    
    
    public Object getContactFirstName(){
        return contactfirstname;
    }    
    
    public Object getContactMiddleName(){
        return contactmiddlename;
    }    

    public Object getContactLastName(){
        return contactlastname;
    }
    
    
    public Object getTelephone1(){
        return telephone1;
    }    
          
  
        public Object getTelephone2(){
        return telephone2;
    }
    
    
    public Object getMobile(){
        return mobile;
    }    

    public Object getFax(){
        return fax;
    }
    
    
    public Object getEmail(){
        return email;
    }    
    
    public Object getAddress1(){
        return address1;
    }    

    public Object getAddress2(){
        return address2;
    }
    
    
    public Object getCity(){
        return city;
    }    
        
     public Object getState(){
        return state;
    }    

    public Object getIsOrganization(){
        return isorganization;
    }
    
    
    public Object getStatus(){
        return status;
    }           
    
    public void setAccNum(Object accnum){
        this.accnum=accnum;
    }    
    
    public void setName(Object name){
        this.name=name;
    }        
    
    public void setShortName(Object shortname){
        this.shortname = shortname;
    }
    
    
    public void setContactFirstName(Object contactfirstname){
        this.contactfirstname=contactfirstname;
    }    
    
    public void setContactMiddleName(Object contactmiddlename ){
        this.contactmiddlename=contactmiddlename;
    }    

    public void setContactLastName(Object contactlastname){
        this.contactlastname=contactlastname;
    }
    
    
    public void setTelephone1(Object telephone1){
        this.telephone1=telephone1;
    }    
          
  
        public void setTelephone2(Object telephone2){
        this.telephone2=telephone2;
    }
    
    
    public void setMobile(Object mobile){
        this.mobile=mobile;
    }    

    public void setFax(Object fax){
        this.fax=fax;
    }
    
    
    public void setEmail(Object email){
        this.email=email;
    }    
    
    public void setAddress1(Object address1){
        this.address1=address1;
    }    

    public void setAddress2(Object address2){
        this.address2=address2;
    }
    
    
    public void setCity(Object city){
        this.city=city;
    }    
        
    public void setState(Object state){
        this.state=state;
    }    

    public void setIsOrganization(Object isorganization){
        this.isorganization=isorganization;
    }
    
    
    public void setStatus(Object status){
        this.status=status;
    }               
    
    private  Object accnum  = null ;
    
    private  Object name = null ;
    
    private  Object shortname = null ;
    
    private  Object contactfirstname = null  ;
    
    private  Object contactmiddlename = null  ;
    
    private  Object contactlastname = null  ;
           
    private  Object telephone1 = null ;
    
    private  Object telephone2 = null ;    
    
    private  Object mobile = null ;
    
    private  Object fax = null ;
    
    private  Object email = null ;
    
    private  Object address1 = null ;
    
    private  Object address2 = null ;
    
    private  Object city = null ;
    
    private  Object state = null ;    
    
    private  Object isorganization  = null ;
            
    private  Object status   = null ;   
    
    private Object[] objArray = null;
            
    private Vector vecArray = null;    
    
    
}
