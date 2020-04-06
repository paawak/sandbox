/*
 * Semaphores.java
 *
 * Created on September 22, 2003, 9:38 PM
 */

package controller;

/**
 *
 * @author  paawak
 */

import utils.database.DBUtil;
import model.database.*;

import java.text.*;

public class SemaphoresController implements SemaphoresDB,TableListDB {
    
    private String TableName;
    
    private String Prefix;
    
    private String Suffix;
    
    private String CounterFormat;
    
    private long MaxCount=0;    
    
    private String Code;
    
    private Database db;    
    
    private String whereCondition;
    

    
    /** Creates a new instance of Semaphores */
    public SemaphoresController(String TableName) {
        
        this.TableName = TableName;
        
        db  = new Database(semaphores);
                
    }
    
    private void generateID() {
        try{
            initVariables();
        }catch(Exception e){
            System.out.println(e);
        }
        
        String count=new String();
        
           if(CounterFormat.equals(""))
                count+=MaxCount;
           else{
                DecimalFormat myFormatter = new DecimalFormat(CounterFormat);
                count=myFormatter.format(MaxCount);              
           }
        
               Code=Prefix+count+Suffix;

    }
    
    /**returns the ID formatted in due respect
     */
    public String getID(){
        generateID();
        return Code;
    }
    

    public boolean updateMaxCount(){
        
        try{
            initVariables();
        }catch(Exception e){
            System.out.println(e);
        }
        return db.updateData(new String[]{maxcount},new Object[]{Long.toString(MaxCount)},whereCondition);
        
    }
    
    /**initialises all the variables associated with the Semaphores
     */    
    private void initVariables()throws Exception{
        
        whereCondition = " WHERE "+tablename+" = '"+TableName+"'";
        
        Prefix = db.queryOneElement(prefix, whereCondition ).toString().trim();
        
        try{
            
            MaxCount = Integer.parseInt(db.queryOneElement(maxcount, whereCondition).toString().trim()) +1;
        }
        catch(Exception e){
        }
        
        Suffix = db.queryOneElement(suffix, whereCondition ).toString().trim();
        
        CounterFormat = db.queryOneElement(counterformat, whereCondition ).toString().trim();   
        
        
        
    }

    
}
