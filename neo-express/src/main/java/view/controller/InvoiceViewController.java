/*
 * InvoiceViewController.java
 *
 * Created on 26 April 2004, 23:35
 */

package view.controller;

/**
 *
 * @author  paawak
 */

import javax.swing.*;

import view.*;

public class InvoiceViewController {
    
    /** Creates a new instance of InvoiceViewController */
    public InvoiceViewController() {
    }
    
    public JPanel getNewInvoicePanel() {
        invoiceView = new InvoiceView();
        try{
        }catch(Exception e){
            System.out.println(e);
        }
        return invoiceView;
    }    
    
    private InvoiceView invoiceView = null;
    
}
