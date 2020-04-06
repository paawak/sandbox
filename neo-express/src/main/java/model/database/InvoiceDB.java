/*
 * MonthRollMainDB.java
 *
 * Created on 06 April 2004, 04:00
 */

package model.database;

/**
 *
 * @author  paawak
 */
public interface InvoiceDB {
    
        public final String invoicenum = "invoicenum";
        public final String invoicedate = "invoicedate";
        public final String curmonth = "curmonth";
        public final String curyear = "curyear";
        public final String accnum = "accnum";
        public final String totalamt = "totalamt";
        public final String tax = "tax";        
        public final String tdsamount = "tdsamount";
        public final String invoiceamt = "invoiceamt";
        public final String bookadjamt = "bookadjamt";
        public final String remarks = "remarks";
        public final String netreceivable = "netreceivable";
        public final String recievedamt = "recievedamt";
        public final String ispartpayment = "ispartpayment";
        public final String haspaidfull = "haspaidfull";
        public final String employeeid = "employeeid";
        public final String entrytime = "entrytime";
        public final String entrydate = "entrydate";
    
        public final int fields = 18;
        
}
