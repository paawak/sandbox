/*
 * InvoiceObject.java
 *
 * Created on 06 April 2004, 04:17
 */

package model;

/**
 *
 * @author  paawak
 */
import java.text.DecimalFormat;
import java.util.Vector;

import model.database.InvoiceDB;

public class InvoiceObject implements InvoiceDB {

    private Object invoicenum = null;

    private Object invoicedate = null;

    private Object curmonth = null;

    private Object curyear = null;

    private Object accnum = null;

    private Object totalamt = null;

    private Object tax = null;

    private Object invoiceamt = null;

    private Object tdsamount = null;

    private Object bookadjamt = null;

    private Object remarks = null;

    private Object netreceivable = null;

    private Object recievedamt = null;

    private Object ispartpayment = null;

    private Object haspaidfull = null;

    private Object employeeid = null;

    private Object entrytime = null;

    private Object entrydate = null;

    private float fuelCharge;

    /** Creates a new instance of InvoiceObject */
    public InvoiceObject() {
    }

    public InvoiceObject(Object[] objArray) throws Exception {
        setInvoiceObject(objArray);
    }

    public InvoiceObject(Vector vecArray) throws Exception {
        setInvoiceObject(vecArray);
    }

    public void setInvoiceObject(Vector vecArray) throws Exception {
        int len = vecArray.size();
        if (len != fields)
            throw new Exception();
        Object[] objArray = new Object[len];
        for (int i = 0; i < len; i++)
            objArray[i] = vecArray.elementAt(i);
        setInvoiceObject(objArray);
    }

    public void setInvoiceObject(Object[] objArray) throws Exception {
        if (objArray.length != fields)
            throw new Exception();

        invoicenum = objArray[0];
        invoicedate = objArray[1];
        // format it to show a leading zero
        DecimalFormat frm = new DecimalFormat("00");
        try {
            curmonth = frm.format(Integer.parseInt(objArray[2].toString()));
        } catch (Exception e) {
        }
        curyear = objArray[3];
        accnum = objArray[4];
        totalamt = objArray[5];
        tax = objArray[6];
        tdsamount = objArray[7];
        invoiceamt = objArray[8];
        bookadjamt = objArray[9];
        remarks = objArray[10];
        netreceivable = objArray[11];
        recievedamt = objArray[12];
        ispartpayment = objArray[13];
        haspaidfull = objArray[14];
        employeeid = objArray[15];
        entrytime = objArray[16];
        entrydate = objArray[17];

    }

    public Object[] getInvoiceObjectAsArray() {
        Object[] objArray = null;
        Vector objVec = getInvoiceObjectAsVector();
        if (objVec.size() != fields)
            return objArray;
        objArray = new Object[fields];
        for (int i = 0; i < fields; i++)
            objArray[i] = objVec.elementAt(i);
        return objArray;
    }

    public Vector getInvoiceObjectAsVector() {
        Vector objVec = new Vector(fields);
        objVec.addElement(invoicenum);
        objVec.addElement(invoicedate);
        objVec.addElement(curmonth);
        objVec.addElement(curyear);
        objVec.addElement(accnum);
        objVec.addElement(totalamt);
        objVec.addElement(tax);
        objVec.addElement(tdsamount);
        objVec.addElement(invoiceamt);
        objVec.addElement(bookadjamt);
        objVec.addElement(remarks);
        objVec.addElement(netreceivable);
        objVec.addElement(recievedamt);
        objVec.addElement(ispartpayment);
        objVec.addElement(haspaidfull);
        objVec.addElement(employeeid);
        objVec.addElement(entrytime);
        objVec.addElement(entrydate);

        return objVec;
    }

    /**
     * method to set the class variables
     * 
     */

    public Object getInvoiceNum() {
        return invoicenum;
    }

    public void setInvoiceNum(Object invoicenum) {
        this.invoicenum = invoicenum;
    }

    public Object getInvoiceDate() {
        return invoicedate;
    }

    public void setInvoiceDate(Object invoicedate) {
        this.invoicedate = invoicedate;
    }

    public Object getCurMonth() {
        return curmonth;
    }

    public void setCurMonth(Object curmonth) {
        this.curmonth = curmonth;
    }

    public Object getCurYear() {
        return curyear;
    }

    public void setCurYear(Object curyear) {
        this.curyear = curyear;
    }

    public Object getAccNum() {
        return accnum;
    }

    public void setAccNum(Object accnum) {
        this.accnum = accnum;
    }

    public Object getTotalAmt() {
        return totalamt;
    }

    public void setTotalAmt(Object totalamt) {
        this.totalamt = totalamt;
    }

    public Object getTax() {
        return tax;
    }

    public void setTax(Object tax) {
        this.tax = tax;
    }

    public Object getInvoiceAmt() {
        return invoiceamt;
    }

    public void setInvoiceAmt(Object invoiceamt) {
        this.invoiceamt = invoiceamt;
    }

    public Object getTdsAmount() {
        return tdsamount;
    }

    public void setTdsAmount(Object tdsamount) {
        this.tdsamount = tdsamount;
    }

    public Object getBookAdjAmt() {
        return bookadjamt;
    }

    public void setBookAdjAmt(Object bookadjamt) {
        this.bookadjamt = bookadjamt;
    }

    public Object getRemarks() {
        return remarks;
    }

    public void setRemarks(Object remarks) {
        this.remarks = remarks;
    }

    public Object getNetReceivable() {
        return netreceivable;
    }

    public void setNetReceivable(Object netreceivable) {
        this.netreceivable = netreceivable;
    }

    public Object getRecievedAmt() {
        return recievedamt;
    }

    public void setRecievedAmt(Object recievedamt) {
        this.recievedamt = recievedamt;
    }

    public Object getIsPartPayment() {
        return ispartpayment;
    }

    public void setIsPartPayment(Object ispartpayment) {
        this.ispartpayment = ispartpayment;
    }

    public Object getHasPaidFull() {
        return haspaidfull;
    }

    public void setHasPaidFull(Object haspaidfull) {
        this.haspaidfull = haspaidfull;
    }

    public Object getEmployeeId() {
        return employeeid;
    }

    public void setEmployeeId(Object employeeid) {
        this.employeeid = employeeid;
    }

    public Object getEntryTime() {
        return entrytime;
    }

    public void setEntryTime(Object entrytime) {
        this.entrytime = entrytime;
    }

    public Object getEntryDate() {
        return entrydate;
    }

    public void setEntryDate(Object entrydate) {
        this.entrydate = entrydate;
    }

    public float getFuelCharge() {
        return fuelCharge;
    }

    public void setFuelCharge(float fuelCharge) {
        this.fuelCharge = fuelCharge;
    }

}
