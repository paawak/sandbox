/*
 * DailyOrderBookDB.java
 *
 * Created on March 30, 2004, 10:43 PM
 */

package model.database;

/**
 *
 * @author  paawak
 */
public interface DailyOrderBookDB {
public final String consignmentnum = "consignmentnum" ;
public final String consignmentdate  = "consignmentdate" ;
public final String consignmenttime  = "consignmenttime" ;
public final String accnum = "accnum" ;
public final String destination = "destination" ;
public final String type = "type" ;
public final String weightkg = "weightkg" ;
public final String weightgram = "weightgram" ;
public final String packets = "packets" ;
public final String amount = "amount" ;
public final String iscredit = "iscredit" ;
public final String employeeid = "employeeid" ;
public final String entrydate = "entrydate" ;
public final String deliverydate = "deliverydate" ;
public final String deliverytime = "deliverytime" ;
    
public final int fields = 15;

}
