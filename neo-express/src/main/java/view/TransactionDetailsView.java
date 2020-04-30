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
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

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

        pnlTitle.setLayout(new java.awt.BorderLayout());

        jLabel17.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("Transactions Details");
        pnlTitle.add(jLabel17, java.awt.BorderLayout.CENTER);

        jLabel2.setText("From Date :");

        txtFromDate.setEditable(false);
        txtFromDate.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                txtFromDateMouseReleased(evt);
            }
        });

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

        jLabel3.setText("Gross Amount :");

        jLabel4.setText("Gross Tax :");

        jLabel6.setText("Gross Recievable Amount :");

        txtTotalAmount.setEditable(false);
        txtTotalAmount.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        txtTax.setEditable(false);
        txtTax.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        txtNetRecievable.setEditable(false);
        txtNetRecievable.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        jLabel7.setText("To Date :");

        txtToDate.setEditable(false);
        txtToDate.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                txtToDateMouseReleased(evt);
            }
        });

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel1.setText("<html>\n<p><font face=\"Verdana, Arial, Helvetica, sans-serif\" color=\"#FF0000\" size=\"5\">**To \n  get the Table Data to an Excel file:</font><font face=\"Verdana, Arial, Helvetica, sans-serif\"><br>\n  <font size=\"3\"><font color=\"#0033CC\">1.)Select the entire Table by first clicking \n  on the Table<br>\n  and then pressing<i><b> <font color=\"#006600\">CTRL+A</font></b></i>. The colour \n  of the entire table <br>\n  becomes blue: which means the entire table is selected.<br>\n  2.)Now press <i><b><font color=\"#006600\">CTRL+C</font></b></i> for copying the \n  data from table.<br>\n  3.)Open a new Excel file.<br>\n  4.)Press <i><b><font color=\"#006600\">CTRL+V</font></b></i> for pasting the data \n  from table. Alternatively,<br>\n  you can go to <i><b><font color=\"#006600\">Edit-&gt;Paste</font></b></i>.<br>\n  5.)Save the Excel file.</font></font></font></p>\n<p>&nbsp; </p>\n</html>\n");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(330, 330, 330)
                .addComponent(pnlTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 440, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(80, 80, 80)
                .addComponent(jLabel2)
                .addGap(40, 40, 40)
                .addComponent(txtFromDate, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(90, 90, 90)
                .addComponent(jLabel7)
                .addGap(59, 59, 59)
                .addComponent(txtToDate, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addComponent(scrPnTable, javax.swing.GroupLayout.PREFERRED_SIZE, 1140, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(100, 100, 100)
                        .addComponent(txtTotalAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel3))
                .addGap(20, 20, 20)
                .addComponent(jLabel4)
                .addGap(5, 5, 5)
                .addComponent(txtTax, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(170, 170, 170)
                        .addComponent(txtNetRecievable, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel6)))
            .addGroup(layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 750, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(460, 460, 460)
                .addComponent(btClose))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(pnlTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(txtFromDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(txtToDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addComponent(scrPnTable, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTotalAmount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(txtTax, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNetRecievable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(11, 11, 11)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(btClose))
        );
    }// </editor-fold>//GEN-END:initComponents

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