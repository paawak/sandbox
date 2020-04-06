/*
 * DailyOrderBookObject.java
 *
 * Created on March 30, 2004, 10:41 PM
 */

package model;

import java.util.*;

import model.database.*;

/**
 *
 * @author  paawak
 */
public class DailyOrderBookObject implements DailyOrderBookDB{
    
    /** Creates a new instance of DailyOrderBookObject */
    public DailyOrderBookObject() {
    }
    
    public DailyOrderBookObject(Object[] objArray)throws Exception{
        setDailyOrderBookObject(objArray);
    }
    
    public DailyOrderBookObject(Vector vecArray)throws Exception{
        setDailyOrderBookObject(vecArray);
    }    
    
    /**
     *method to set the class variables
     */
    public void setDailyOrderBookObject(Object[] objArray)throws Exception{
        if(objArray.length!=fields)
            throw new Exception();
           
   consignmentnum = objArray[0];
   consignmentdate = objArray[1];
   consignmenttime = objArray[2];
   accnum = objArray[3];
   destination = objArray[4];
   type = objArray[5];
   weightkg = objArray[6];
   weightgram = objArray[7];
   packets = objArray[8];
   amount = objArray[9];
   iscredit = objArray[10];
   employeeid = objArray[11];
   entrytime = objArray[12];
   deliverydate = objArray[13];
   deliverytime = objArray[14];
            
    }
    
    
    
    public void setDailyOrderBookObject(Vector vecArray)throws Exception{
        int len = vecArray.size();
        if(len!=fields)
            throw new Exception();        
        Object[] objArray = new Object[len];
        for(int i=0; i<len; i++)
            objArray[i] = vecArray.elementAt(i);
        setDailyOrderBookObject(objArray);
    }
    
    public Object[] getDailyOrderBookObjectAsArray(){
         Object[] objArray = new Object[fields];         
         objArray[0] = consignmentnum;
         objArray[1] = consignmentdate;
         objArray[2] = consignmenttime;
         objArray[3] = accnum;
         objArray[4] = destination;
         objArray[5] = type;
         objArray[6] = weightkg;
         objArray[7] = weightgram;
         objArray[8] = packets;
         objArray[9] = amount;
         objArray[10] = iscredit;
         objArray[11] = employeeid;
         objArray[12] = entrytime;
         objArray[13] = deliverydate;
         objArray[14] = deliverytime;        
         return objArray;
    }    
 
    public Object getConsignmentNum(){
        return consignmentnum;
    }
    
    public void setConsignmentNum(Object consignmentnum){
        this.consignmentnum=consignmentnum;
    }
    
    public Object getConsignmentDate(){
        return consignmentdate;
    }
    
    public void setConsignmentDate(Object consignmentdate){
        this.consignmentdate=consignmentdate;
    }
    
    public Object getConsignmentTime(){
        return consignmenttime;
    }
    
    public void setConsignmentTime(Object consignmenttime){
        this.consignmenttime= consignmenttime;
    }
    
    public Object getAccNum(){
        return accnum;
    }
    
    public void setAccNum(Object accnum){
        this. accnum=accnum;
    }
    
    public Object getDestination(){
        return destination;
    }
    
    public void setDestination(Object destination){
        this.destination=destination;
    }    
    
    public Object getType(){
        return type;
    }
    
    public void setType(Object type){
        this.type=type;
    }        

    public Object getWeightKg(){
        return weightkg;
    }
    
    public void setWeightKg(Object weightkg){
        this.weightkg=weightkg;
    }
 
    public Object getWeightGram(){
        return weightgram;
    }
    
    public void setWeightGram(Object weightgram){
        this.weightgram=weightgram;
    }
    
    public Object getPackets(){
        return packets;
    }
    
    public void setPackets(Object packets){
        this.packets=packets;
    }
    
    public Object getAmount(){
        return amount;
    }
    
    public void setamount(Object amount){
        this.amount=amount;
    }
    
    public Object getIsCredit(){
        return iscredit;
    }
    
    public void setIsCredit(Object iscredit){
        this. iscredit=iscredit;
    }
    
    public Object getEmployeeId(){
        return employeeid;
    }
    
    public void setEmployeeId(Object employeeid){
        this.employeeid=employeeid;
    }    

    public Object getEntryTime(){
        return entrytime;
    }
    
    public void setEntryTime(Object entrytime){
        this.entrytime=entrytime;
    }
    
    public Object getDeliveryDate(){
        return deliverydate;
    }
    
    public void setDeliveryDate(Object deliverydate){
        this. deliverydate=deliverydate;
    }
    
    public Object getDeliveryTime(){
        return deliverytime;
    }
    
    public void setDeliveryTime(Object deliverytime){
        this.deliverytime=deliverytime;
    }    
    
    /*
        //*/
      //member variables

private  Object consignmentnum = null ;
private  Object consignmentdate = null ;
private  Object consignmenttime = null ;
private  Object accnum = null ;
private  Object destination = null ;
private Object type = null;
private  Object weightkg = null ;
private  Object weightgram = null ;
private  Object packets = null ;
private  Object amount = null ;
private  Object iscredit = null ;
private  Object employeeid = null ;
private  Object entrytime = null ;
private  Object deliverydate = null ;
private  Object deliverytime = null ;
    
    
}
