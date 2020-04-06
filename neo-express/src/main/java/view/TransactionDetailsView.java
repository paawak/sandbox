/*
 * MonthRollView.java
 *
 * Created on 05 April 2004, 22:08
 */

package view;

/**
 *
 * @author  paawak
 */
import java.util.*;
import java.text.*;

import utils.general.*;
import model.*;
import controller.*;
import model.database.*;

public class TransactionDetailsView extends javax.swing.JPanel implements TableListDB, DailyOrderBookDB{
    
    /** Creates new form MonthRollView */
    public TransactionDetailsView() {
        initComponents(); 
        setDateFields();
        viewButtonPressed();
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        pnlTitle = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtFromDate = new javax.swing.JTextField();
        scrPnTable = new javax.swing.JScrollPane();
        tblDetails = new javax.swing.JTable();
        btClose = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtTotalAmount = new javax.swing.JTextField();
        txtTax = new javax.swing.JTextField();
        txtNetRecievable = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtToDate = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnlTitle.setLayout(new java.awt.BorderLayout());

        jLabel17.setFont(new java.awt.Font("Dialog", 1, 18));
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("Transactions Details");
        pnlTitle.add(jLabel17, java.awt.BorderLayout.CENTER);

        add(pnlTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 30, 440, 30));

        jLabel2.setText("From Date :");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 110, -1, -1));

        txtFromDate.setEditable(false);
        txtFromDate.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                txtFromDateMouseReleased(evt);
            }
        });

        add(txtFromDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 110, 230, -1));

        tblDetails.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        scrPnTable.setViewportView(tblDetails);

        add(scrPnTable, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 160, 1140, 220));

        btClose.setMnemonic('c');
        btClose.setText("Close");
        btClose.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                btCloseKeyReleased(evt);
            }
        });
        btClose.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btCloseMouseReleased(evt);
            }
        });

        add(btClose, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 630, -1, -1));

        jLabel3.setText("Gross Amount :");
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 420, -1, -1));

        jLabel4.setText("Gross Tax :");
        add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 420, -1, -1));

        jLabel6.setText("Gross Recievable Amount :");
        add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 420, -1, -1));

        txtTotalAmount.setEditable(false);
        txtTotalAmount.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        add(txtTotalAmount, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 420, 200, -1));

        txtTax.setEditable(false);
        txtTax.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        add(txtTax, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 420, 200, -1));

        txtNetRecievable.setEditable(false);
        txtNetRecievable.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        add(txtNetRecievable, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 420, 200, -1));

        jLabel7.setText("To Date :");
        add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 110, -1, -1));

        txtToDate.setEditable(false);
        txtToDate.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                txtToDateMouseReleased(evt);
            }
        });

        add(txtToDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 110, 230, -1));

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel1.setText("<html>\n<p><font face=\"Verdana, Arial, Helvetica, sans-serif\" color=\"#FF0000\" size=\"5\">**To \n  get the Table Data to an Excel file:</font><font face=\"Verdana, Arial, Helvetica, sans-serif\"><br>\n  <font size=\"3\"><font color=\"#0033CC\">1.)Select the entire Table by first clicking \n  on the Table<br>\n  and then pressing<i><b> <font color=\"#006600\">CTRL+A</font></b></i>. The colour \n  of the entire table <br>\n  becomes blue: which means the entire table is selected.<br>\n  2.)Now press <i><b><font color=\"#006600\">CTRL+C</font></b></i> for copying the \n  data from table.<br>\n  3.)Open a new Excel file.<br>\n  4.)Press <i><b><font color=\"#006600\">CTRL+V</font></b></i> for pasting the data \n  from table. Alternatively,<br>\n  you can go to <i><b><font color=\"#006600\">Edit-&gt;Paste</font></b></i>.<br>\n  5.)Save the Excel file.</font></font></font></p>\n<p>&nbsp; </p>\n</html>\n");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 450, 750, 170));

    }//GEN-END:initComponents

    private void txtToDateMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtToDateMouseReleased
        // Add your handling code here:
        ShowCalendar cal = new ShowCalendar(new java.awt.Frame(), true,  txtToDate);
        cal.show();
        viewButtonPressed();        
    }//GEN-LAST:event_txtToDateMouseReleased

    private void btCloseKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btCloseKeyReleased
        // Add your handling code here:
        closeButtonPressed();
    }//GEN-LAST:event_btCloseKeyReleased

    private void btCloseMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btCloseMouseReleased
        // Add your handling code here:
        closeButtonPressed();
    }//GEN-LAST:event_btCloseMouseReleased

    private void txtFromDateMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtFromDateMouseReleased
        // Add your handling code here:
        ShowCalendar cal = new ShowCalendar(new java.awt.Frame(), true,  txtFromDate);
        cal.show();
        viewButtonPressed();
    }//GEN-LAST:event_txtFromDateMouseReleased

    private void viewButtonPressed(){
        setTable(cntrl.getAllClientDataByDate(this,txtFromDate.getText(),txtToDate.getText()), TABLECOLHEADS);
    }
    
    public void setTable(Object[][] TableData, String[] TableColHeads){
        final int TableColumns = TableColHeads.length;
        tblDetails.setModel(new javax.swing.table.DefaultTableModel(TableData,TableColHeads){
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return false;
            }
        });
        
    }
    
    public void setNumberFields(String totalAmt, String tax, String netRecievable){
        txtTotalAmount.setText(totalAmt);
        txtTax.setText(tax);
        txtNetRecievable.setText(netRecievable);        
    }
    
    private void closeButtonPressed(){
        this.setVisible(false);
    }    
    
    /**
     *to set the txtFromDate to the 1st of the month and
     *txtToDate to the current date
     *
     */
    private void setDateFields(){
        //set date fields
        GregorianCalendar gCal = new GregorianCalendar();
        gCal.set(gCal.DATE,gCal.getMinimum(gCal.DATE));
        DateTimeUtils dt = new DateTimeUtils();
        txtFromDate.setText(dt.getFormattedDate(gCal.getTime(),"yyyy/MM/dd"));
        txtToDate.setText(dt.getMySqlDate());
    }
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btClose;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel pnlTitle;
    private javax.swing.JScrollPane scrPnTable;
    private javax.swing.JTable tblDetails;
    private javax.swing.JTextField txtFromDate;
    private javax.swing.JTextField txtNetRecievable;
    private javax.swing.JTextField txtTax;
    private javax.swing.JTextField txtToDate;
    private javax.swing.JTextField txtTotalAmount;
    // End of variables declaration//GEN-END:variables
    
    private TransactionDetailsController cntrl = new TransactionDetailsController();    
    
    /**
     *holds the headings of the JTable
     */
    private final String[] TABLECOLHEADS = {"Sl No.",  "Consignment No.", "Booking Date",    "Dst.",   "Weight(Kg.grms)", "Amount(Rs.ps)"};
}
