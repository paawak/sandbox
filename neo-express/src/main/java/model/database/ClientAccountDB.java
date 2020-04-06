/*
 * ClientAccountDB.java
 *
 * Created on April 1, 2004, 12:20 AM
 */

package model.database;

/**
 *
 * @author  paawak
 */
public interface ClientAccountDB {
    public final int fields = 17;
    
    public final String accnum  = "accnum" ;
    
    public final String name = "name" ;
    
    public final String shortname = "shortname" ;
    
    public final String contactfirstname = "contactfirstname " ;
    
    public final String contactmiddlename = "contactmiddlename " ;
    
    public final String contactlastname = "contactlastname " ;
           
    public final String telephone1 = "telephone1" ;
    
    public final String telephone2 = "telephone2" ;    
    
    public final String mobile = "mobile" ;
    
    public final String fax = "fax" ;
    
    public final String email = "email" ;
    
    public final String address1 = "address1" ;
    
    public final String address2 = "address2" ;
    
    public final String city = "city" ;
    
    public final String state = "state" ;    
    
    public final String isorganization  = "isorganization" ;
            
    public final String status   = "status" ;    
    
}
