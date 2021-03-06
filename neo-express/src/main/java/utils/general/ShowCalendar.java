/*
 * ShowCalendar.java
 *
 * Created on September 28, 2003, 11:04 PM
 *Modified on October 14,2003, 11:52 PM
 *Has GridBagLayout instead of AbsoluteLayout
 */

/**
 *modified on December 11, 2003, 3:15 AM
 *all the days and dates have been centred and also the fonts of current month have been
 *made bold and the colours of the dates of previous and next month have been blurred
 *and italliced. the mnemonics in OK and Cancel buttons have been removed and unnecessary constructors commented out.
 also a convinience main method has been added for testing, which can be 
 *commented out while making the jar file.
 */

/**
 *modified on 05 April 2004, 22:08
 *a method added to get thge selected date, it returns the selected date as
 *a GregorianCalendar object
 */

package utils.general;

import java.io.*;
import java.util.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.text.DecimalFormat;

/**
 *
 * @author  paawak
 */
public class ShowCalendar extends javax.swing.JDialog implements MouseListener{
    
    private JTextField txtField ;
    
    private String selDateStr;
    
    private StringBuffer selDateBfr;
    
    /** Creates new form ShowCalendar */
    public ShowCalendar(java.awt.Frame parent, boolean modal, JTextField txtField) {
        
        super(parent, modal);
        setLookNFeel();
        this.txtField=txtField;
        
        //to initialise years
        
        for(int i=1900; i<=2100; i++)
            Years[i-1900]=Integer.toString(i);
        
        initComponents();
        displayCalendar();
    }
    
/*    public ShowCalendar(java.awt.Frame parent, boolean modal, JTextField txtField,String selDateStr) {
        super(parent, modal);
        this.txtField=txtField;
        
        setLookNFeel();
        
        //to initialise years
        
        for(int i=1900; i<=2100; i++)
            Years[i-1900]=Integer.toString(i);
        
        initComponents();
        displayCalendar();
        this.selDateStr=selDateStr;
    }
    
    public ShowCalendar(java.awt.Frame parent, boolean modal, JTextField txtField,StringBuffer selDateBfr) {
        super(parent, modal);
        this.txtField=txtField;
        
        setLookNFeel();
        
        //to initialise years
        
        for(int i=1900; i<=2100; i++)
            Years[i-1900]=Integer.toString(i);
        
        initComponents();
        displayCalendar();
        this.selDateBfr=selDateBfr;
    }  //*/  
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        cbYears = new JComboBox(Years);
        cbMonths = new JComboBox(Months);
        jPanel3 = new javax.swing.JPanel();
        lblSelectedDate = new javax.swing.JLabel();
        txtSelectedDate = new javax.swing.JTextField();
        btOK = new javax.swing.JButton();
        btCancel = new javax.swing.JButton();
        pnlCalendar = new javax.swing.JPanel();

        getContentPane().setLayout(new java.awt.GridBagLayout());

        setTitle("Click to select:");
        setModal(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                closeDialog(evt);
            }
        });

        jPanel1.setLayout(new java.awt.GridBagLayout());

        jPanel1.setMinimumSize(new java.awt.Dimension(200, 25));
        jPanel1.setPreferredSize(new java.awt.Dimension(200, 25));
        cbYears.setMinimumSize(new java.awt.Dimension(25, 25));
        cbYears.setPreferredSize(new java.awt.Dimension(25, 25));
        cbYears.setSelectedIndex(year-1900);
        cbYears.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbYearsItemStateChanged(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 10;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 10);
        jPanel1.add(cbYears, gridBagConstraints);

        cbMonths.setMinimumSize(new java.awt.Dimension(25, 25));
        cbMonths.setPreferredSize(new java.awt.Dimension(25, 25));
        cbMonths.setSelectedIndex(month);
        cbMonths.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbMonthsItemStateChanged(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 10;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(3, 10, 3, 3);
        jPanel1.add(cbMonths, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        getContentPane().add(jPanel1, gridBagConstraints);

        jPanel3.setLayout(new java.awt.GridBagLayout());

        jPanel3.setMinimumSize(new java.awt.Dimension(200, 70));
        jPanel3.setPreferredSize(new java.awt.Dimension(200, 70));
        lblSelectedDate.setFont(new java.awt.Font("Dialog", 0, 12));
        lblSelectedDate.setText("Selected Date:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        jPanel3.add(lblSelectedDate, gridBagConstraints);

        txtSelectedDate.setBackground(new java.awt.Color(204, 255, 255));
        txtSelectedDate.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        jPanel3.add(txtSelectedDate, gridBagConstraints);

        btOK.setFont(new java.awt.Font("Dialog", 0, 12));
        btOK.setText("Ok");
        btOK.setToolTipText("Ok");
        btOK.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btOKMouseReleased(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        jPanel3.add(btOK, gridBagConstraints);

        btCancel.setFont(new java.awt.Font("Dialog", 0, 12));
        btCancel.setText("Cancel");
        btCancel.setToolTipText("Cancel");
        btCancel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btCancelMouseReleased(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        jPanel3.add(btCancel, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.ipadx = 10;
        gridBagConstraints.ipady = 30;
        getContentPane().add(jPanel3, gridBagConstraints);

        pnlCalendar.setMinimumSize(new java.awt.Dimension(150, 175));
        pnlCalendar.setPreferredSize(new java.awt.Dimension(150, 175));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 10;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 10, 20);
        getContentPane().add(pnlCalendar, gridBagConstraints);

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-218)/2, (screenSize.height-361)/2, 218, 361);
    }//GEN-END:initComponents

    private void cbMonthsItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbMonthsItemStateChanged
        // Add your handling code here:
        displayCalendar();
    }//GEN-LAST:event_cbMonthsItemStateChanged

    private void cbYearsItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbYearsItemStateChanged
        // Add your handling code here:
        displayCalendar();
    }//GEN-LAST:event_cbYearsItemStateChanged

    private void btCancelMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btCancelMouseReleased
        // Add your handling code here:
        setVisible(false);
        dispose();
    }//GEN-LAST:event_btCancelMouseReleased

    private void btOKMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btOKMouseReleased
        // Add your handling code here:
        txtField.setText(selectedDate);
        selDateStr=selectedDate;
        selDateBfr=new StringBuffer(selectedDate);
        setVisible(false);
        dispose();
    }//GEN-LAST:event_btOKMouseReleased
    
    /** Closes the dialog */
    private void closeDialog(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_closeDialog
        setVisible(false);
        dispose();
    }//GEN-LAST:event_closeDialog
    
    private void displayCalendar() {
        
        int panelComponents = pnlCalendar.getComponentCount();
        
        
        
        if(panelComponents!=0){
            pnlCalendar.removeAll();
            
            
        }//*/
        else
            pnlCalendar.setLayout(new java.awt.GridLayout(7, 7));
        
        String[] days ={"Su","Mo","Tu","We","Th","Fr","Sa"};
        
        //to fill the days
        
        for(int i=0 ; i<7; i++){
            javax.swing.JLabel label = new javax.swing.JLabel(days[i]);
            label.setHorizontalAlignment(label.CENTER);
            label.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
            label.setOpaque(true);
            label.setBackground(new java.awt.Color(153, 153, 153));
            pnlCalendar.add(label);
            
        }
        
        year = cbYears.getSelectedIndex()+1900;
        month = cbMonths.getSelectedIndex();
        int date=1;
        
        if(month==0)
            cal.set(year, 11,date);
        else
            cal.set(year, month-1,date);
        
        int lastDateOfLastMonth = cal.getActualMaximum(cal.DATE);
        
        
        cal.set(year, month,date);
        
        int firstDayOfThisMonth = cal.get(cal.DAY_OF_WEEK);
        
        
        //to fill the previous month dates
        
        for(int i=1,j=lastDateOfLastMonth-(firstDayOfThisMonth-2) ; i<firstDayOfThisMonth; i++,j++){
            javax.swing.JLabel label = new javax.swing.JLabel(Integer.toString(j));
            label.setFont(new java.awt.Font("Dialog", 2, 12));
            label.setHorizontalAlignment(label.CENTER);
            label.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
            label.setOpaque(true);
            label.setBackground(new java.awt.Color(204,204,204));
            label.setForeground(new java.awt.Color(102,102,102));
            pnlCalendar.add(label);
            
            label.addMouseListener(this);
        }
        
        int lastDateOfThisMonth = cal.getActualMaximum(cal.DATE);
        
        //to fill current month dates
        
        for(int i=1 ; i<=lastDateOfThisMonth; i++){
            javax.swing.JLabel label = new javax.swing.JLabel(Integer.toString(i));
            label.setFont(new java.awt.Font("Dialog", 1, 12));
            label.setHorizontalAlignment(label.CENTER);
            label.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
            label.setOpaque(true);
            cal.set(year,month,i);
            if(cal.get(cal.DAY_OF_WEEK)==cal.SATURDAY||cal.get(cal.DAY_OF_WEEK)==cal.SUNDAY)
                label.setBackground(new java.awt.Color(203,255,51));
            else
                label.setBackground(new java.awt.Color(102,102,255));
            if(i==dateToday && year==thisYear && month==thisMonth)
                label.setForeground(new java.awt.Color(204,0,51));
            pnlCalendar.add(label);
            label.addMouseListener(this);
        }
        
        cal.set(year, month,lastDateOfThisMonth);
        
        int lastDayOfThisMonth = cal.get(cal.DAY_OF_WEEK);
        
        int j=1;
        
        //to fill next month dates upto the last week of current month
        
        for(int i=lastDayOfThisMonth+1;i<=7;i++,j++){
            javax.swing.JLabel label = new javax.swing.JLabel(Integer.toString(j)); 
            label.setFont(new java.awt.Font("Dialog", 2, 12));
            label.setHorizontalAlignment(label.CENTER);
            label.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
            label.setOpaque(true);
            label.setBackground(new java.awt.Color(204,204,204));
            label.setForeground(new java.awt.Color(102,102,102));
            pnlCalendar.add(label);
            label.addMouseListener(this);
        }
        
        //to fill next month dates beyond the last week of current month such that a total of 7*7=49 labels are used
        
        panelComponents = pnlCalendar.getComponentCount();
        
        if(panelComponents<49){
            for(int i=1;i<=49-panelComponents;i++,j++){
                javax.swing.JLabel label = new javax.swing.JLabel(Integer.toString(j));
                label.setFont(new java.awt.Font("Dialog", 2, 12));
                label.setHorizontalAlignment(label.CENTER);
                label.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
                label.setOpaque(true);
                label.setBackground(new java.awt.Color(204,204,204));
                label.setForeground(new java.awt.Color(102,102,102));
                pnlCalendar.add(label);
                label.addMouseListener(this);
            }
        }
        
        pnlCalendar.doLayout();
        
    }    
    
    public GregorianCalendar getSelectedDate(){
        return selectedDate_dt;
    }
    
    /** Invoked when the mouse button has been clicked (pressed
     * and released) on a component.
     *
     *
     */
    public void mouseClicked(MouseEvent e) {
        
        try{
            javax.swing.JLabel label = (javax.swing.JLabel)e.getComponent();
            
            String text=label.getText();
            int date = Integer.parseInt(text);
            int month = cbMonths.getSelectedIndex();
            int year = cbYears.getSelectedIndex();
            Color backgroundColor=label.getBackground();
            
            //check whether user has clicked on a date of previous or next month : if so, change the months
            //if( (!backgroundColor.equals(new java.awt.Color(102,102,255))) || (!backgroundColor.equals(new java.awt.Color(203,255,51)))){
            if( backgroundColor.equals(new java.awt.Color(204,204,204))){
                if(date>=17){
                    if(month==0){
                        month=11;
                        year-=1;
                        cbYears.setSelectedIndex(year);
                    }
                    else
                        month-=1;
                    cbMonths.setSelectedIndex(month);
                }
                else{
                    if(month==11){
                        month=0;
                        year+=1;
                        cbYears.setSelectedIndex(year);
                    }
                    else
                        month+=1;
                    cbMonths.setSelectedIndex(month);
                }
            }
            
            selectedDate = (year+1900)+"/"+format.format(month+1)+"/"+format.format(date);
            selectedDate_dt.set(year+1900,month,date);
            txtSelectedDate.setText(selectedDate);
            
            
            
            /*for(int j=0;j<pnlCalendar.getComponentCount();j++){
                JLabel labels = (JLabel)pnlCalendar.getComponent(j);
                String txt = labels.getText();
                if(txt.equals(Integer.toString(date))){
                    java.awt.Point point =labels.getLocation();
             
                    break;
                }
            }//*/
            
            
        }
        catch(Exception ex){
        }
    }
    
    /** Invoked when the mouse enters a component.
     *
     *
     */
    public void mouseEntered(MouseEvent e) {
    }
    
    /** Invoked when the mouse exits a component.
     *
     *
     */
    public void mouseExited(MouseEvent e) {
    }
    
    /** Invoked when a mouse button has been pressed on a component.
     *
     *
     */
    public void mousePressed(MouseEvent e) {
    }
    
    /** Invoked when a mouse button has been released on a component.
     *
     *
     */
    public void mouseReleased(MouseEvent e) {
        
    }
    
    /** @param args the command line arguments
     *
     */
    private static void setLookNFeel() {
        
        try {
            //UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel" );
            //UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel" );
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            //UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
            //UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
            //UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel" );//*/
            
        } catch (Exception e) { }
        
        
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btCancel;
    private javax.swing.JButton btOK;
    private javax.swing.JComboBox cbMonths;
    private javax.swing.JComboBox cbYears;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel lblSelectedDate;
    private javax.swing.JPanel pnlCalendar;
    private javax.swing.JTextField txtSelectedDate;
    // End of variables declaration//GEN-END:variables

    private GregorianCalendar cal = new GregorianCalendar();    
    
    private GregorianCalendar selectedDate_dt = new GregorianCalendar();    
    
    private final int dateToday = cal.get(cal.DATE);
    
    private int month = cal.get(Calendar.MONTH);
    
    private final String[] Months = {"Jan","Feb","Mar","Apr",
    "May","Jun","Jul","Aug",
    "Sep","Oct","Nov","Dec",};
    
    private String selectedDate = new String();
    
    private final int thisMonth = cal.get(Calendar.MONTH);
    
    private final int thisYear = cal.get(Calendar.YEAR);
    
    private int year = cal.get(Calendar.YEAR);
    
    private final String[] Years = new String[201];
    
    private DecimalFormat format = new DecimalFormat("00");
    
    //convinience method for testing 
    
    /*public static void main(String args[]){
        
        new ShowCalendar(new java.awt.Frame(),true,new JTextField()).show();
        
    }//*/
    
}
