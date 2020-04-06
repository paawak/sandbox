/*
 * MonthRollController.java
 *
 * Created on 06 April 2004, 03:49
 */

package controller;

/**
 *
 * @author  paawak
 */
import java.text.DecimalFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Properties;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.tree.DefaultMutableTreeNode;

import model.ClientAccountObject;
import model.InvoiceObject;
import model.database.ClientAccountDB;
import model.database.ConstantsDB;
import model.database.DailyOrderBookDB;
import model.database.InvoiceDB;
import model.database.SemaphoresDB;
import model.database.TableListDB;
import utils.general.DateTimeUtils;
import utils.general.DigitsToFigure;
import utils.general.ReadPropertyFile;
import view.MonthRollView;

import com.swayam.reusable.utils.writer.TagElement;
import com.swayam.reusable.utils.writer.TaggedFileWriter;
import com.swayam.reusable.utils.writer.rtf.RTFConstants;

public class MonthRollController implements TableListDB, SemaphoresDB,
        InvoiceDB, ClientAccountDB, DailyOrderBookDB, ConstantsDB, RTFConstants {

    /** Creates a new instance of MonthRollController */
    public MonthRollController() {
        for (int i = 1; i < cellPosData.length; i++) {
            cellPosData[i] = cellPosData[i - 1] + cellPosData[i];
        }
        setConstants();
        System.out.println("**************** maxLines = " + maxLines);
    }

    public void roll(MonthRollView monthRollView, int year, int month) {
        // check whether record exists
        String where = "WHERE " + InvoiceDB.curmonth + "='" + month + "' AND "
                + InvoiceDB.curyear + "='" + year + "'";
        Object[][] invNos = null;
        invProps = new Properties();
        try {
            deleteInvRec(year, month);
            invNos = invDB.queryMultipleColumns(new String[] {
                    InvoiceDB.accnum, InvoiceDB.invoicenum }, false, where);
            invDB.printSQLStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (invNos.length == 0) {
            // begin rollign
            startRoll(monthRollView, year, month, false);
        } else {
            int option = JOptionPane
                    .showConfirmDialog(
                            monthRollView,
                            "Records for this month already exists: Do you want to ovverwrite?",
                            "Record exists", JOptionPane.YES_NO_OPTION);
            if (option == JOptionPane.YES_OPTION) {
                deleteInvRec(year, month);
                // store accnum - inv num in invProps

                for (int i = 0; i < invNos.length; i++)
                    invProps.put(invNos[i][0], invNos[i][1]);

                startRoll(monthRollView, year, month, false);
            }
        }
    }

    private void startRoll(MonthRollView monthRollView, int year, int month,
            boolean recordExists) {
        // initialise the output stream to write to the file

        Object[][] clientDet = null;
        String where = "WHERE MONTH(" + DailyOrderBookDB.consignmentdate
                + ")='" + month + "' AND YEAR("
                + DailyOrderBookDB.consignmentdate + ")='" + year
                + "' GROUP BY " + DailyOrderBookDB.accnum;

        try {
            clientDet = dlOrBkDb.queryMultipleColumns(new String[] {
                    DailyOrderBookDB.accnum,
                    "SUM(" + DailyOrderBookDB.amount + ")" }, false, where);
            dlOrBkDb.printSQLStatement();
        } catch (Exception e) {
            e.printStackTrace();
            dlOrBkDb.printSQLStatement();
        }
        for (int i = 0; i < clientDet.length; i++) {
            // set the invoice object and popualte the database
            Vector invVec = new Vector(InvoiceDB.fields);
            // check whether record exists
            if (recordExists) {
                String invNum = invProps
                        .getProperty(clientDet[i][0].toString());// accnum
                if (invNum == null)
                    invNum = smCntr.getID();
                invVec.addElement(invNum);// inv num
            } else
                invVec.addElement(smCntr.getID());// inv num
            invVec.addElement(dt.getMySqlDate());
            invVec.addElement(new Integer(month));
            invVec.addElement(new Integer(year));
            invVec.addElement(clientDet[i][0]);// accnum
            invVec.addElement(clientDet[i][1]);// total amt
            // cal culate tax , tds
            float tax = 0, tds = 0, invAmt = 0, fuelCharge = 0;

            try {
                float amt = Float.parseFloat(clientDet[i][1].toString());
                fuelCharge = fuelChargePercent * amt / 100;
                tax = taxPercent * (amt + fuelCharge) / 100;

                if (amt >= tdsApplyAmt) {
                    tds = tdsPercent * amt / 100;
                }

                invAmt = amt + fuelCharge + tax - tds;
            } catch (Exception e) {
                e.printStackTrace();
            }
            invVec.addElement(new Float(tax));// tax
            invVec.addElement(new Float(tds));// tds
            // format inv amt to round to the nearest rupee
            DecimalFormat formatter = new DecimalFormat("0");
            String invAmtFrm = formatter.format(invAmt);
            invVec.addElement(invAmtFrm);// inv amt
            invVec.addElement("0");// bookadjustment amount
            invVec.addElement("");// remarks
            invVec.addElement(invAmtFrm);// netrecievable
            invVec.addElement("0");// recieved amt
            invVec.addElement("0");// is part payment
            invVec.addElement("0");// has paid full
            invVec.addElement(new DailyOrderBookController()
                    .getEmplIDLoggedIn());// empl. id
            invVec.addElement(dt.getMySqlTime());
            invVec.addElement(dt.getMySqlDate());

            // cast the invVec into Invoice object

            InvoiceObject invObj = null;
            try {
                invObj = new InvoiceObject(invVec);
                invObj.setFuelCharge(fuelCharge);
                writeInvoiceDetailsToFile(invObj);
            } catch (Exception e) {
                e.printStackTrace();
            }

            // add to invoice table: databse
            try {
                if (recordExists) {
                    if (invDB.updateData(invDB.getColumnNames(), invObj
                            .getInvoiceObjectAsArray(), "WHERE "
                            + InvoiceDB.invoicenum + "='"
                            + invObj.getInvoiceNum() + "'")) {
                        String details = "Records rolled successfully for client "
                                + clientDet[i][0] + "\n";
                        monthRollView.txtArDetails.append(details);
                    } else {
                        String details = "Records could not be rolled for client "
                                + clientDet[i][0] + "\n";
                        monthRollView.txtArDetails.append(details);
                    }
                } else {
                    if (invDB.insertData(invDB.getColumnNames(), invObj
                            .getInvoiceObjectAsArray())) {
                        smCntr.updateMaxCount();
                        String details = "Records rolled successfully for client "
                                + clientDet[i][0] + "\n";
                        monthRollView.txtArDetails.append(details);
                    } else {
                        String details = "Records could not be rolled for client "
                                + clientDet[i][0] + "\n";
                        monthRollView.txtArDetails.append(details);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }// end for
    }

    /**
     *method to write the INVOICE bill for each client in a file so that a
     * printout can be taken
     */
    public void writeInvoiceDetailsToFile(InvoiceObject invObj) {
        // date pattern
        String pattern = "dd/MM/yyyy";
        Object invnum = invObj.getInvoiceNum();
        Object accnum = invObj.getAccNum();
        // cur year
        int curyear = Integer.parseInt(invObj.getCurYear().toString());
        // cur month
        int curmonth = Integer.parseInt(invObj.getCurMonth().toString());
        // get formatted date
        String invdate = dt.getFormattedDate(
                invObj.getInvoiceDate().toString(), pattern);
        // to get from date and to date
        GregorianCalendar gCal = new GregorianCalendar(curyear, curmonth - 1, 1);
        Date date = gCal.getTime();
        String fromDate = dt.getFormattedDate(date, pattern);
        gCal.set(GregorianCalendar.DATE, gCal
                .getActualMaximum(GregorianCalendar.DAY_OF_MONTH));
        date = gCal.getTime();
        String toDate = dt.getFormattedDate(date, pattern);
        Object totalAmt = invObj.getTotalAmt();
        Object tax = invObj.getTax();
        // Object tds = invObj.getTdsAmount();
        Object netRecievable = invObj.getNetReceivable();
        ClientAccountObject clObj = null;

        String where = "WHERE MONTH(" + DailyOrderBookDB.consignmentdate
                + ")='" + curmonth + "' AND YEAR("
                + DailyOrderBookDB.consignmentdate + ")='" + curyear + "' AND "
                + DailyOrderBookDB.accnum + "='" + accnum + "' AND "
                + DailyOrderBookDB.iscredit + "='1' ORDER BY "
                + DailyOrderBookDB.consignmentdate;
        Object[][] obj = null;

        try {
            obj = dlOrBkDb.queryMultipleColumns(dlOrBkDb.getColumnNames(),
                    false, where);
            // init ClientAccountObject
            where = "WHERE " + ClientAccountDB.accnum + "='" + accnum + "'";
            Object[] clObjArr = clAccDb.queryMultipleElements(clAccDb
                    .getColumnNames(), where);
            clAccDb.printSQLStatement();
            clObj = new ClientAccountObject(clObjArr);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (clObj == null) {
            System.out
                    .println("Cannot create an instance of ClientAccountObject");
            return;
        }

        DefaultMutableTreeNode mainNode = new DefaultMutableTreeNode(
                new TagElement(DOCUMENT_HEADER, "\n}\n"));
        // {\fonttbl\f0\fnil Times New Roman;}
        DefaultMutableTreeNode fontTable = new DefaultMutableTreeNode(
                new TagElement(FONT_TBL, "}\n"));
        // \f0\fnil Times New Roman;
        DefaultMutableTreeNode f0 = new DefaultMutableTreeNode(new TagElement(
                FONT + FONT_SIZE + FNIL + " " + FONT_FAMILY, ";"));
        fontTable.add(f0);
        mainNode.add(fontTable);
        // {\colortbl\red0\green0\blue0;\red255\green200\blue0;}
        DefaultMutableTreeNode colorTable = new DefaultMutableTreeNode(
                new TagElement(COLOR_TBL, "}\n"));
        // \red0\green0\blue0;
        DefaultMutableTreeNode color1 = new DefaultMutableTreeNode(
                new TagElement(RED + "0" + GREEN + "0" + BLUE + "0", ";"));
        colorTable.add(color1);
        // \red255\green255\blue255;
        DefaultMutableTreeNode color2 = new DefaultMutableTreeNode(
                new TagElement(RED + "255" + GREEN + "255" + BLUE + "255", ";"));
        colorTable.add(color2);
        mainNode.add(colorTable);

        // {\header \qr{\f0\fs24 Invoice No.: XXXX}\par}
        DefaultMutableTreeNode header = new DefaultMutableTreeNode(
                new TagElement(HEADER, "}\n"));
        header.add(getRighted("Invoice No.: " + invnum));
        // getRighted()/getCentred() etc. increments lineCount by 1. but in
        // header/footer,
        // no new lines are added. so decrement lineCount
        lineCount--;
        mainNode.add(header);
        // {\footer \qr{\f0\fs24 Contd...}\par}
        DefaultMutableTreeNode footer = new DefaultMutableTreeNode(
                new TagElement(FOOTER, "}\n"));
        footer.add(getCentred(COMPANY_NAME + ", " + COMPANY_ADDRESS + ", "
                + COMPANY_CITY));
        lineCount--;
        // page numbers
        footer.add(getRighted("Page " + INSERT_PAGE_NUMS));
        lineCount--;
        mainNode.add(footer);

        printInvoiceHeader(mainNode, invnum, invdate, fromDate, toDate, clObj);

        // print the table headers
        printTableHeader(mainNode, cellPosData);

        String[] cellAlignment = { ALIGN_CENTER, ALIGN_LEFT, ALIGN_CENTER,
                ALIGN_LEFT, ALIGN_RIGHT, ALIGN_RIGHT };

        for (int i = 0; i < obj.length; i++) {
            Vector cells = new Vector();
            // sl no.
            cells.addElement(Integer.toString(i + 1));
            // consignment no.
            cells.addElement(obj[i][0]);

            // consignment date
            cells
                    .addElement(dt.getFormattedDate(obj[i][1].toString(),
                            pattern));
            // destination
            cells.addElement(obj[i][4]);
            // weight kg, grms
            DecimalFormat fm3 = new DecimalFormat("0.000");
            float wt = Float.parseFloat(obj[i][6] + "." + obj[i][7]);
            cells.addElement(fm3.format(wt));
            // amt
            cells.addElement(obj[i][9].toString());
            amtTillPageEnd += Float.parseFloat(obj[i][9].toString());

            printTableRow(mainNode, cells, cellPosData, cellAlignment);
        }

        mainNode.add(getCentred(DOTTED_LINE));
        // total amt
        mainNode.add(getRighted("Total Amount: " + totalAmt));
        mainNode.add(getCentred(DOTTED_LINE));

        // fuel charge
        mainNode.add(getRighted("(+) Fuel Charge @ "
                + moneyFormat.format(fuelChargePercent) + "%: "
                + moneyFormat.format(invObj.getFuelCharge())));
        mainNode.add(getCentred(DOTTED_LINE));

        // tax
        mainNode.add(getRighted("(+) Service Tax @ "
                + moneyFormat.format(taxPercent) + "%: "
                + moneyFormat.format(tax)));
        mainNode.add(getCentred(DOTTED_LINE));
        // tds
        // grnd total: net recievable amt
        mainNode.add(getAligned("Grand Total: " + netRecievable, ALIGN_RIGHT,
                BOLD));
        mainNode.add(getCentred(DOTTED_LINE));
        mainNode.add(getAligned("Rupees "
                + DigitsToFigure.toFigures("" + netRecievable) + " only.",
                ALIGN_RIGHT, ITALIC));
        mainNode.add(getLefted("Reg. No.: (COU)AAXPA4617J ST001 "));

        // TODO: print this as footer: "For Mach Express"

        StringBuffer sb = new StringBuffer(fileName);
        int index = sb.indexOf(".");
        sb.insert(index, "_" + clObj.getName());
        String filePathAbs = filePath + sb.toString();
        new TaggedFileWriter(filePathAbs, mainNode).run();
        System.out.println("Written file: " + filePathAbs);
        // reset line and page counts
        lineCount = 0;
        amtTillPageEnd = 0;
    }

    private void printTableHeader(DefaultMutableTreeNode parent, int[] cellSizes) {
        String[] headerAlignment = new String[cellPosData.length];
        for (int i = 0; i < cellPosData.length; i++) {
            headerAlignment[i] = ALIGN_CENTER;
        }

        Vector headerVec = new Vector();

        for (int i = 0; i < headers.length; i++) {
            headerVec.addElement(headers[i]);
        }

        printTableRow(parent, headerVec, cellSizes, headerAlignment);
    }

    /** prints a single row in table */
    private void printTableRow(DefaultMutableTreeNode parent, Vector cells,
            int[] cellSizes, String[] cellAlignment) {
        // \trowd\trql\trhdr\clbrdrt\brdrs\brdrw1\brdrcf1\brsp0\clbrdrl\brdrs\brdrw1\brdrcf1\brsp0\clbrdrb\brdrs\brdrw1\brdrcf1\brsp0\cellx1728\clbrdrt\brdrs\brdrw1\brdrcf1\brsp0\clbrdrl\brdrs\brdrw1\brdrcf1\brsp0\clbrdrb\brdrs\brdrw1\brdrcf1\brsp0\cellx3456\clbrdrt\brdrs\brdrw1\brdrcf1\brsp0\clbrdrl\brdrs\brdrw1\brdrcf1\brsp0\clbrdrb\brdrs\brdrw1\brdrcf1\brsp0\cellx5184\clbrdrt\brdrs\brdrw1\brdrcf1\brsp0\clbrdrl\brdrs\brdrw1\brdrcf1\brsp0\clbrdrb\brdrs\brdrw1\brdrcf1\brsp0\cellx6912\clbrdrt\brdrs\brdrw1\brdrcf1\brsp0\clbrdrl\brdrs\brdrw1\brdrcf1\brsp0\clbrdrb\brdrs\brdrw1\brdrcf1\brsp0\clbrdrr\brdrs\brdrw1\brdrcf1\brsp0\cellx8640
        // The table row begins with the \trowd control word and ends with the
        // \row control word
        DefaultMutableTreeNode row = new DefaultMutableTreeNode(new TagElement(
                TABLE_ROW_START, TABLE_ROW_END + "\n"));

        for (int i = 0; i < cells.size(); i++) {
            // \cellx2314
            String endTag = "";
            if (i == cells.size() - 1)
                endTag = "\n";
            DefaultMutableTreeNode cellSize = new DefaultMutableTreeNode(
                    new TagElement(TABLE_CELL_X + cellSizes[i], endTag));
            row.add(cellSize);
        }

        for (int i = 0; i < cells.size(); i++) {
            // \pard\plain \intbl\qc\i\b {\f1\fs24 XXXXXXX}\cell
            DefaultMutableTreeNode cell = new DefaultMutableTreeNode(
                    new TagElement(TABLE_CELL_START + cellAlignment[i] + "{"
                            + FONT + FONT_SIZE + FONT_SIZE_POINTS * 2 + " "
                            + cells.elementAt(i) + "}", TABLE_CELL_END + "\n"));
            row.add(cell);
        }
        parent.add(row);

        lineCount++;

        if (lineCount == maxLines - 4) {
            printPageBreak(parent, moneyFormat.format(amtTillPageEnd));
        }
    }

    /**
     *prints the total amount of the page, at the end of it
     */
    private void printPageBreak(DefaultMutableTreeNode parent,
            String pageTotalAmount) {
        parent.add(getCentred(DOTTED_LINE));
        parent.add(getRighted(pageTotalAmount));
        parent.add(getCentred(DOTTED_LINE));
        parent.add(getRighted("Contd..."));
        System.out.println("*************** NEW PAGE");
        lineCount = 0;
        // print new page
        parent.add(getLefted(""));
        parent.add(getCentred(DOTTED_LINE));
        parent.add(getRighted("Balance brought forward: " + pageTotalAmount));
        parent.add(getCentred(DOTTED_LINE));
        // print the table headers
        printTableHeader(parent, cellPosData);
    }

    /**
     *sets tdspercent, tax, etc
     */
    private void setConstants() {
        Database constDB = new Database(constants);
        try {
            taxPercent = Float.parseFloat(constDB.queryOneElement(value,
                    "WHERE " + ConstantsDB.name + "='TaxPercent'").toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            tdsPercent = Float.parseFloat(constDB.queryOneElement(value,
                    "WHERE " + ConstantsDB.name + "='TDSPercent'").toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            fuelChargePercent = Float.parseFloat(constDB.queryOneElement(value,
                    "WHERE " + ConstantsDB.name + "='FuelCharge'").toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            tdsApplyAmt = Float.parseFloat(constDB.queryOneElement(value,
                    "WHERE " + ConstantsDB.name + "='TDSApplyAmt'").toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            maxLines = Integer.parseInt(constDB.queryOneElement(value,
                    "WHERE " + ConstantsDB.name + "='MaxLines'").toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // set the folder in which the invoice files will be dumped
        ReadPropertyFile readMainProps = new ReadPropertyFile(
                "controller/props/CommonProps");
        filePath = readMainProps.getVal("InvoiceFilesDumpDirectory");
        fileName = readMainProps.getVal("InvoiceFileName");

        if (readMainProps.getVal("IncludeDateTimeWithInvoiceFileName").equals(
                "true")) {
            fileName += "_"
                    + dt.getFormattedDate(new Date(), "dd-MM-yyyy_HH-mm-ss");
        }

        fileName += ".rtf";

        int dottedLineLength = Integer.parseInt(readMainProps
                .getVal("DottedLineLength"));

        String dottedLine = "-";
        for (int i = 0; i < dottedLineLength; i++) {
            dottedLine += "-";
        }

        DOTTED_LINE = dottedLine;
    }

    /**
     *deletes a record in the invoice table
     */
    private void deleteInvRec(int year, int month) {
        try {
            String sql = "DELETE FROM " + invoice + " WHERE "
                    + InvoiceDB.curmonth + "='" + month + "' AND "
                    + InvoiceDB.curyear + "='" + year + "'";
            invDB.executeSQLStatement(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *method that attaches the header, i.e, address of the client, invoice num,
     * etc..
     */
    private void printInvoiceHeader(DefaultMutableTreeNode parent,
            Object invnum, Object invdate, Object fromDate, Object toDate,
            ClientAccountObject clObj) {
        parent.add(getCentred(COMPANY_NAME));
        parent.add(getCentred(COMPANY_ADDRESS));
        parent.add(getCentred(COMPANY_CITY));
        parent.add(getCentred("INVOICE"));
        parent.add(getLefted("To"));
        Vector cellVec = new Vector();
        int[] cellSizes = new int[2];
        cellSizes[0] = 5500;
        cellSizes[1] = 9240;
        String[] cellAlignment = { ALIGN_LEFT, ALIGN_LEFT };
        // print client details
        // if its a company, print m/s. , else print Mr.
        String line = "Mr. ";
        if (clObj.getIsOrganization().equals("1"))
            line = "M/s. ";

        cellVec.addElement(line + clObj.getName());
        cellVec.addElement("Invoice No.: " + invnum);
        printTableRow(parent, cellVec, cellSizes, cellAlignment);

        if (!clObj.getAddress1().toString().trim().equals(""))
            line = clObj.getAddress1() + ",";
        else
            line = "";

        cellVec.removeAllElements();
        cellVec.addElement(line);
        cellVec.addElement("Invoice Date: " + invdate);
        printTableRow(parent, cellVec, cellSizes, cellAlignment);

        if (!clObj.getAddress2().toString().trim().equals(""))
            line = clObj.getAddress2() + ",";
        else
            line = "";

        cellVec.removeAllElements();
        cellVec.addElement(line);
        cellVec.addElement("Period From: " + fromDate);
        printTableRow(parent, cellVec, cellSizes, cellAlignment);

        cellVec.removeAllElements();
        cellVec.addElement(clObj.getCity() + ",");
        cellVec.addElement("Period To: " + toDate);
        printTableRow(parent, cellVec, cellSizes, cellAlignment);

        cellVec.removeAllElements();
        cellVec.addElement(clObj.getState().toString());
        cellVec.addElement("");
        printTableRow(parent, cellVec, cellSizes, cellAlignment);

        parent.add(getLefted(""));

    }

    private DefaultMutableTreeNode getCentred(String text) {
        // \par \pard\plain \qc {\f0 XXXXXXXXXXX}
        return getAligned(text, ALIGN_CENTER);
    }

    private DefaultMutableTreeNode getLefted(String text) {
        // \par \pard\plain {\f0 XXXXXXXXXXX}
        return getAligned(text, "");
    }

    private DefaultMutableTreeNode getRighted(String text) {
        // \par \pard\plain {\f0 XXXXXXXXXXX}
        return getAligned(text, ALIGN_RIGHT);
    }

    private DefaultMutableTreeNode getAligned(String text, String align) {
        return getAligned(text, align, "");
    }

    /**
     * style accepts bold(\\b), italic(\\i), underline(\\u)
     */
    private DefaultMutableTreeNode getAligned(String text, String align,
            String style) {
        lineCount++;
        // \par \pard\plain \qc {\f0\fs24 XXXXXXXXXXX}
        return new DefaultMutableTreeNode(new TagElement(PARD_PLAIN + " "
                + align + style + "{" + FONT + FONT_SIZE + FONT_SIZE_POINTS * 2
                + " " + text, "}" + PARA + "\n"));
    }

    private Database clAccDb = new Database(clientaccount);
    private Database dlOrBkDb = new Database(dailyorderbook);
    private Database invDB = new Database(invoice);
    private SemaphoresController smCntr = new SemaphoresController(invoice);
    /**
     *holds the tax percentage
     */
    private float taxPercent = 0;

    private float fuelChargePercent;

    /**
     *holds the tds percentage
     */
    private float tdsPercent = 0;

    /**
     *holds the amount, exceeding which tds is charged
     */
    private float tdsApplyAmt = 0;

    // /**
    // *holds the output strem for the file of invoice
    // */
    // private FileOutputStream fos = null;

    private final DateTimeUtils dt = new DateTimeUtils();
    private final DecimalFormat moneyFormat = new DecimalFormat("0.00");

    /**
     *holds the headers
     */
    private final String[] headers = { "SL. ", "C.NOTE No. ", "DATE ",
            "DEST'N ", "WT(Kg.gms)", "AMOUNT" };

    /**
     *holds the file path
     */
    private String filePath = null;

    /**
     *holds the file name
     */
    private String fileName = null;

    /**
     *holds the max. no. of lines in a single page in the Invoice file
     */
    private int maxLines = 46;// WeirdUnsocialRTFUnits.DEFAULT_PAGE_HEIGHT_TWIPS/(FONT_SIZE_POINTS*WeirdUnsocialRTFUnits.TWIPS_IN_POINT);
    /**
     * keeps the count of lines in a page. this is reset in every new page
     */
    private int lineCount = 0;
    /**
     * keeps track of the total amount for a given page: should be reset after
     * every page, as well as new file
     */
    private float amtTillPageEnd = 0;

    /**
     *holds the existing invoice nos. for a given month/year if record exists
     */
    private Properties invProps = null;

    /**
     *This contains(all units are in twips): x of 1st cell, width of 1st cell,
     * width of 2nd cell and so on.... Usage: 2nd cell loc = cellPosData[0] +
     * cellPosData[1] 3rd cell loc = 2nd cell loc + cellPosData[2] this is done
     * in the constructor
     */
    private final int[] cellPosData = { 600, 1728, 1728, 1728, 1728, 1728 };
    private final static String FONT_FAMILY = "Times New Roman";
    /**
     * this is in points. so this has to be doubled when passed to rtf as rtf
     * uses double-point format. the page breaks are calculated on this basis.
     */
    private static final int FONT_SIZE_POINTS = 12;

    private String DOTTED_LINE;

    private final static String COMPANY_NAME = "MACH EXPRESS";
    private final static String COMPANY_ADDRESS = "No. 58, 3rd Main, Vayalikaval";
    private final static String COMPANY_CITY = "Bangalore - 560 003";

    // /**
    // *this interface contains the unwholesome, idiotic, unscientific,
    // godforsaken,
    // *bloody units of measure understood and used only by the MS bastards
    // *and Bill Gates and the sons of bachelors who wrote the RTF specs!!
    // */
    // private interface WeirdUnsocialRTFUnits {
    // /**used as the default unit of measure throughout the RTF*/
    // int TWIPS_IN_INCH = 1440;
    // /**
    // * used only for specifying font sizes.
    // * please note that RTF uses a double-point standard, i.e,
    // * for displaying a font of size 12, this command must be used:
    // * \fs24.
    // */
    // int POINTS_IN_INCH = 72;
    // int TWIPS_IN_POINT = 20;
    // int DEFAULT_PAGE_WIDTH_TWIPS = 12240;
    // int DEFAULT_PAGE_HEIGHT_TWIPS = 15840;
    // }

}
