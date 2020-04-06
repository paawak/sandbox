/*
 * InvoiceController.java
 *
 * Created on 26 April 2004, 22:47
 */

package controller;

/**
 *
 * @author  paawak
 */
import model.InvoiceObject;
import model.database.ClientAccountDB;
import model.database.InvoiceDB;
import model.database.TableListDB;

public class InvoiceController implements TableListDB, InvoiceDB,
        ClientAccountDB {

    /** Creates a new instance of InvoiceController */
    public InvoiceController() {
    }

    /**
     *gets an array containing client names between the given dates whose records are present in invoice table
     */
    public Object[] getClientNames(String frmDate, String toDate) {
        Object[] obj = null;
        try {
            String where = "WHERE " + InvoiceDB.invoicedate + ">='" + frmDate
                    + "' AND " + InvoiceDB.invoicedate + "<='" + toDate + "'";
            obj = invDB.queryOneColumn(InvoiceDB.accnum, true, where
                    + " ORDER BY " + InvoiceDB.accnum);
        } catch (Exception e) {
        }
        for (int i = 0; i < obj.length; i++)
            try {
                obj[i] = clAccDb.queryOneElement(ClientAccountDB.name, "WHERE "
                        + ClientAccountDB.accnum + "='" + obj[i] + "'");
            } catch (Exception e) {
            }
        return obj;
    }

    /**
     *gets the invoice nums between frm date and to date
     */
    public Object[] getInvoiceNums(String frmDate, String toDate) {
        Object[] obj = null;
        try {
            String where = "WHERE " + InvoiceDB.invoicedate + ">='" + frmDate
                    + "' AND " + InvoiceDB.invoicedate + "<='" + toDate + "'";
            obj = invDB.queryOneColumn(InvoiceDB.invoicenum, true, where
                    + " ORDER BY " + InvoiceDB.invoicenum);
        } catch (Exception e) {
        }
        return obj;
    }

    /**
     *gets the invoice nums between frm date and to date and the given client name: which is converted to accnum
     */
    public Object[] getInvoiceNums(String frmDate, String toDate,
            Object clientName) {
        Object clientAcc = null;
        try {
            clientAcc = clAccDb.queryOneElement(ClientAccountDB.accnum,
                    "WHERE " + ClientAccountDB.name + "='" + clientName + "'");
        } catch (Exception e) {
        }
        Object[] obj = null;
        try {
            String where = "WHERE " + InvoiceDB.invoicedate + ">='" + frmDate
                    + "' AND " + InvoiceDB.invoicedate + "<='" + toDate
                    + "' AND " + InvoiceDB.accnum + "='" + clientAcc + "'";
            obj = invDB.queryOneColumn(InvoiceDB.invoicenum, true, where
                    + " ORDER BY " + InvoiceDB.invoicenum);
        } catch (Exception e) {
        }
        return obj;
    }

    /**
     *gets the records based on an invoice number returns an instance of InvoiceObject
     */
    public InvoiceObject getInvoiceRecord(Object invNum) {
        InvoiceObject obj = null;
        try {
            String where = "WHERE " + InvoiceDB.invoicenum + "='" + invNum
                    + "'";
            obj = new InvoiceObject(invDB.queryMultipleElements(invDB
                    .getColumnNames(), where));
            invDB.printSQLStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return obj;
    }

    /**
     *this update the bookadjamt in invoice
     */
    public boolean updateBookAdj(Object[] obj, Object invNum) {
        try {
            String where = "WHERE " + InvoiceDB.invoicenum + "='" + invNum
                    + "'";
            return invDB.updateData(new String[] { InvoiceDB.bookadjamt,
                    InvoiceDB.remarks, InvoiceDB.netreceivable,
                    InvoiceDB.recievedamt, InvoiceDB.ispartpayment,
                    InvoiceDB.haspaidfull, InvoiceDB.employeeid,
                    InvoiceDB.entrydate, InvoiceDB.entrytime }, obj, where);
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    private Database clAccDb = new Database(clientaccount);
    private Database invDB = new Database(invoice);

}
