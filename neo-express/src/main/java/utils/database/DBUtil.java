/*
 * DBUtil.java
 *
 * Modified on October 18, 2003, 1:47 AM
 *
 *This is a modified version of the class LinkDB
 *
 *Enhanced features: 1.javadoc comments for all public final  methods
 *                   2. provision to view the SQL-Statements
 *                   3. no need to perform queryRows() before quering the database for columns
 */

/**
 *Modified on December 11, 2003, 2:35 AM
 *this was made an abstract class to prevent from instantiating. Of course it has to be
 *sub-classed. The sub-class should have static variables containing DSN, UserName, Password,TableName
 *which are to be passed on to DBUtil.
 *This means that in a particular project, the programmer needs to specify these quantities 
 *only once. Also, two new methods have been added: setTable() and getTable(): for obvious
 *reasons.
 *all the methods have been made final to prevent overriding.
 */

/**
 *modified on December 18, 2003, 21:16 PM
 *modified to make it compliant with certain special characters that needed to be prefixed
 *with the escape character \ like ', ", \, etc. in certain RDBMS like MySQL.
 *Also has been added two utility methods queryMultipleElements_Vec and queryOneColumn_Vec
 *which returns Vector instead of Object[].
 */

/**
 *modifeied on February 8, 2004, 22:30 PM.
 *modified to include the MySQL-JDBC Class1 driver..
 *method accessDB() overloade..
 *constructor overloaded
 */

/**
 *major mistake in queryMultipleElements:
 *corrected on 27-04-04
 */

/**
 *major changes on 19-05-04: upgraded to include postgres database vendor
 *modified: accessDB and constructors
 */

/**
 *
 * @author  paawak
 */

package utils.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.swing.JOptionPane;

public abstract class DBUtil extends javax.swing.JFrame {

    private static final long serialVersionUID = 3413478837197896498L;

    private final String databaseDriver;

    private final String databaseUrl;

    private final String password;

    private final String tableName;

    private final String userName;

    /**
     * Holds the no. of columns in the table in the database
     */
    private int Columns = 0;

    /**
     * Holds the names of the columns of the table in the database
     */
    private String[] ColumnNames;

    private Statement stat;

    private Connection con;

    /**
     * displays all error messages when this is set to true
     */
    private boolean displayErrors = false;
    /**
     * holds the characters requiring escape character before them, in order to
     * be saved in the database. for example, mysql doesnot store ', " and \
     * characters unless they are preceeded by the escape character \ i.e., \',
     * \", \\, etc.
     */
    private String[] charsWithEscapeSeq = new String[1];

    /**
     * holds the SQL-Statement to be executed: chiefly used for debugging
     */
    private String SQLStatement = "";

    /**
     * 
     * Creates a new instance of DBUtil: public final DBUtil(String
     * DatabaseName, String DatabaseUserName, String DatabasePassword, String
     * DatabaseTableName) this is the default constructor and connects to a
     * mysql database using mysql jdbc class 4 driver
     */
    public DBUtil(String databaseDriver, String databaseUrl, String userName, String password, String tableName, boolean displayErrors,
            String[] charsWithEscapeSeq) {
        this.databaseDriver = databaseDriver;
        this.databaseUrl = databaseUrl;
        this.userName = userName;
        this.password = password;
        this.tableName = tableName;
        this.displayErrors = displayErrors;
        this.charsWithEscapeSeq = charsWithEscapeSeq;
        fillColumnNames();
    }

    /**
     * Method to connect to the Database this method uses the native jdbc-odbc
     * driver
     * 
     */
    private final void accessDB() {

        String fullUrl = null;

        try {

            // Class.forName(databaseDriver).newInstance();

            fullUrl = databaseUrl + "?user=" + userName + "&password=" + password + "&useSSL=false&allowPublicKeyRetrieval=true";

            con = DriverManager.getConnection(fullUrl);

            stat = con.createStatement();

        } catch (Exception e) {
            System.err.println("Trying to connect to: " + fullUrl);
            e.printStackTrace();
            if (displayErrors) {
                String title = "Error: DBUtil.accessDB() Table: " + tableName;
                StringBuffer error = new StringBuffer(e.toString());
                if (error.length() < title.length())
                    error.append("\t\t");
                JOptionPane.showMessageDialog(this, error.toString(), title, JOptionPane.ERROR_MESSAGE);
            }
        }

    }

    /**
     * Method to execute a given SQL-Statement: public final void
     * executeSQLStatement(String SQL-Statement)throws java.sql.SQLException
     * 
     */
    public final void executeSQLStatement(String sql) throws java.sql.SQLException {
        accessDB();
        stat.execute(sql);
        con.close();
    }

    /**
     * Method to get the no. of columns and the column-names of the table in the
     * database.
     */
    private final void fillColumnNames() {
        try {

            accessDB();

            SQLStatement = "SELECT * FROM " + tableName;
            ResultSet res = stat.executeQuery(SQLStatement);

            ResultSetMetaData md = res.getMetaData();
            Columns = md.getColumnCount();
            ColumnNames = new String[Columns];

            for (int i = 0; i < Columns; i++)
                ColumnNames[i] = md.getColumnName(i + 1);
            con.close();
        } // end try
        catch (Exception e) {
            String title = "Error: DBUtil.fillColumnNames() Table: " + tableName;
            StringBuffer error = new StringBuffer(e.toString());
            if (error.length() < title.length())
                error.append("\t\t");
            JOptionPane.showMessageDialog(this, error.toString(), title, JOptionPane.ERROR_MESSAGE);
        } // end catch

    }

    /**
     * Method to construct the SQL-Statement to INSERT data in a database table:
     * it returns the SQL-Statement as String. public final String
     * getInsertStatement(String[] ColumnNames, Object[] Values)
     * 
     */
    public final String getInsertStatement(String[] col_Names, Object[] val) {

        if (col_Names.length != val.length)
            JOptionPane.showMessageDialog(this,
                    " Value-List not matching with the no. of Columns.No. of columns = " + col_Names.length + "\tNo. of values to be inserted = " + val.length
                            + ": Make sure that they are equal.",
                    "Error in DBUtil_01.getInsertStatement() using table " + tableName + ":", JOptionPane.ERROR_MESSAGE);
        else {

            String names = "", values = "";

            for (int i = 0; i < val.length; i++) {
                names += col_Names[i];
                values += "'" + prefixEscapeChars(val[i].toString()) + "'";
                if (i != val.length - 1) {
                    names += ",";
                    values += ",";
                } // end if
            } // end for

            SQLStatement = "INSERT INTO  " + tableName + " (" + names + ") VALUES " + " (" + values + ") ";

        }

        return SQLStatement;
    }

    /**
     * Method to construct the SQL-Statement to UPDATE data in a database table:
     * it returns the SQL-Statement as String. public final String
     * getUpdateStatement(String[] ColumnNames, Object[] Values, String
     * WhereCondition)
     * 
     */
    public final String getUpdateStatement(String[] col_Names, Object[] val, String whereClause) {

        if (col_Names.length != val.length)
            JOptionPane.showMessageDialog(this,
                    " Value-List not matching with the no. of Columns.No. of columns = " + col_Names.length + "\tNo. of values to be inserted = " + val.length
                            + ": Make sure that they are equal.",
                    "Error in the modifyData method of LinkDB class using Database Table " + tableName + ":", JOptionPane.ERROR_MESSAGE);
        else {// main else

            String ssM = "";
            for (int i = 0; i < val.length; i++) {
                ssM += col_Names[i] + "='" + prefixEscapeChars(val[i].toString()) + "'";
                if (i != val.length - 1)
                    ssM += ",";
            } // end for

            SQLStatement = "UPDATE " + tableName + " SET  " + ssM + " " + whereClause;
        } // end main else

        return SQLStatement;

    }

    /**
     * Method to insert data into a database table. Returns true if INSERTion is
     * successful else returns false. public final String insertData(String[]
     * ColumnNames, Object[] Values)
     * 
     */
    public final boolean insertData(String[] col_Names, Object[] val) {
        boolean inserted = false;
        SQLStatement = getInsertStatement(col_Names, val);
        try {
            executeSQLStatement(SQLStatement);
            inserted = true;
        } catch (Exception e) {
            inserted = false;
            // System.out.println("gfhgfhgfhgfhg");
            String title = "Error: DBUtil.insertData() Table: " + tableName;
            StringBuffer error = new StringBuffer(e.toString());
            if (error.length() < title.length())
                error.append("\t\t");
            JOptionPane.showMessageDialog(this, error.toString(), title, JOptionPane.ERROR_MESSAGE);

        }
        return inserted;
    }

    /**
     * inserts data in all columns of the database table except the autonumber
     * column, which is assumed to be the first column in the database table
     */
    public final boolean insertData_autoNum(Object[] val) {
        String[] cols = new String[ColumnNames.length - 1];
        for (int i = 0; i < ColumnNames.length - 1; i++)
            cols[i] = ColumnNames[i + 1];
        // System.out.println("eeeeeee");
        return insertData(cols, val);
    }

    public final Vector queryOneColumn_Vec(String colName, boolean distinct, String whereClause) throws Exception {

        if (distinct)
            SQLStatement = "SELECT DISTINCT " + colName + "  FROM " + tableName + " " + whereClause;
        else
            SQLStatement = "SELECT " + colName + "  FROM " + tableName + " " + whereClause;
        accessDB();
        ResultSet res = stat.executeQuery(SQLStatement);
        Vector temp = new Vector();
        for (int i = 0; res.next(); i++)
            temp.addElement(res.getString(1));
        con.close();
        return temp;
    }

    /**
     * Method to QUERY a single column from a database table: public final
     * Object[] queryOneColumn(String ColumnNameToBeQueried, boolean
     * RowsDistinct, String WhereCondition)throws Exception
     * 
     */
    public final Object[] queryOneColumn(String colName, boolean distinct, String whereClause) throws Exception {
        Vector temp = queryOneColumn_Vec(colName, distinct, whereClause);
        int l = temp.size();
        Object[] result = new Object[l];
        for (int i = 0; i < l; i++)
            result[i] = temp.elementAt(i);
        return result;

    }

    /**
     * Method to QUERY all the columns from a database table: public final
     * Object[][] queryAllColumns(boolean RowsDistinct, String
     * WhereCondition)throws Exception
     * 
     */
    public final Object[][] queryAllColumns(boolean distinct, String whereClause) throws Exception {

        accessDB();
        if (distinct)
            SQLStatement = "SELECT DISTINCT *  FROM " + tableName + " " + whereClause;
        else
            SQLStatement = "SELECT *  FROM " + tableName + " " + whereClause;

        ResultSet res = stat.executeQuery(SQLStatement);

        Vector temp = new Vector();

        for (int i = 0; res.next(); i++)
            for (int j = 0; j < Columns; j++)
                temp.addElement(res.getString(j + 1));

        int rows = temp.size() / Columns;

        Object result[][] = new Object[rows][Columns];

        int k = 0;

        for (int i = 0; i < rows; i++)
            for (int j = 0; j < Columns; j++, k++) {
                result[i][j] = temp.elementAt(k);
            }

        con.close();

        return result;

    }

    /**
     * Method to QUERY more than one columns from a database table: public final
     * Object[][] queryMultipleColumns(String[] ColumnNames, boolean
     * RowsDistinct,String WhereCondition)throws Exception
     * 
     */
    public final Object[][] queryMultipleColumns(String[] ColumnNames, boolean distinct, String whereClause) throws Exception {

        int Columns = ColumnNames.length;

        String names = "";
        for (int i = 0; i < ColumnNames.length; i++) {
            names += ColumnNames[i];
            if (i != ColumnNames.length - 1) {
                names += ",";
            } // end if

        } // end for

        accessDB();

        if (distinct)
            SQLStatement = "SELECT DISTINCT " + names + "  FROM " + tableName + " " + whereClause;
        else
            SQLStatement = "SELECT " + names + "  FROM " + tableName + " " + whereClause;

        ResultSet res = stat.executeQuery(SQLStatement);

        Vector temp = new Vector();

        for (int i = 0; res.next(); i++) {
            for (int j = 0; j < ColumnNames.length; j++) {

                String val = "";

                try {
                    val = res.getString(j + 1);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                temp.addElement(val);
            }
        }

        int rows = temp.size() / Columns;

        Object result[][] = new Object[rows][Columns];

        int k = 0;

        for (int i = 0; i < result.length; i++)
            for (int j = 0; j < ColumnNames.length; j++, k++)
                result[i][j] = temp.elementAt(k);
        con.close();

        return result;

    }

    public final Vector queryMultipleElements_Vec(String[] ColumnNames, String whereClause) throws Exception {
        Vector vec = new Vector();
        String names = "";
        for (int i = 0; i < ColumnNames.length; i++) {
            names += ColumnNames[i];
            if (i != ColumnNames.length - 1) {
                names += ",";
            } // end if

        } // end for

        accessDB();
        SQLStatement = "SELECT " + names + "  FROM " + tableName + " " + whereClause;

        ResultSet res = stat.executeQuery(SQLStatement);

        while (res.next())
            for (int i = 0; i < ColumnNames.length; i++) {
                // System.out.println("obj["+i+"] "+res.getString(i+1));
                vec.addElement(res.getString(i + 1));
            }

        con.close();
        return vec;
    }

    /**
     * Method to QUERY more than one columns from a database table, but which
     * comes in a single row: public final Object[]
     * queryMultipleElements(String[] ColumnNames, String WhereCondition)throws
     * Exception
     * 
     */
    public final Object[] queryMultipleElements(String[] ColumnNames, String whereClause) throws Exception {
        Vector vec = queryMultipleElements_Vec(ColumnNames, whereClause);
        Object result[] = new Object[vec.size()];
        for (int i = 0; i < result.length; i++)
            result[i] = vec.elementAt(i);
        return result;

    }

    /**
     * Method to QUERY one element from a database table: public final Object
     * queryOneElement(String ColumnName, String WhereCondition)throws Exception
     * 
     */
    public final Object queryOneElement(String colName, String whereClause) throws Exception {

        Object result = "";

        accessDB();

        SQLStatement = "SELECT " + colName + "  FROM " + tableName + " " + whereClause;

        ResultSet res = stat.executeQuery(SQLStatement);

        if (res.next())
            result = "" + res.getString(1);

        con.close();

        return result;

    }

    /**
     * Method to QUERY the no. of rows in a database table: public final int
     * queryRows(String WhereCondition,boolean RowsDistinct)throws Exception
     * 
     */
    public final int queryRows(String whereClause, boolean distinct) throws Exception {

        int rowTotal = 0;

        accessDB();
        if (distinct)
            SQLStatement = " SELECT DISTINCT COUNT(*) FROM " + tableName + " " + whereClause;
        else
            SQLStatement = " SELECT COUNT(*) FROM " + tableName + " " + whereClause;
        ResultSet res = stat.executeQuery(SQLStatement);
        if (res.next())
            rowTotal = Integer.parseInt(res.getString(1));
        con.close();
        return rowTotal;
    }

    /**
     * Method to update data into a database table. Returns true if UPDATion is
     * successful else returns false. public final String updateData(String[]
     * ColumnNames, Object[] Values, String WhereCondition)
     * 
     */
    public final boolean updateData(String[] col_Names, Object[] val, String whereClause) {

        boolean updated = true;
        SQLStatement = getUpdateStatement(col_Names, val, whereClause);

        try {
            executeSQLStatement(SQLStatement);
        } catch (Exception e) {
            updated = false;
            String title = "Error: DBUtil.updateData() Table: " + tableName;
            StringBuffer error = new StringBuffer(e.toString());
            if (error.length() < title.length())
                error.append("\t\t");
            JOptionPane.showMessageDialog(this, error.toString(), title, JOptionPane.ERROR_MESSAGE);

        }
        return updated;
    }

    /**
     * returns the no. of coluns in the database table
     */
    public final int getNoOfColumns() {
        return Columns;
    }

    /**
     * returns the names of the columns in the database table
     */
    public final String[] getColumnNames() {
        return ColumnNames;
    }

    /**
     * returns the SQL Statement
     */
    public final String getSQLStatement() {
        return SQLStatement;
    }

    /**
     * prints the SQL statement to the console
     */
    public final void printSQLStatement() {
        System.out.println(SQLStatement);
    }

    /*
     * public final void setTable(String tableName) { this.tableName =
     * tableName; fillColumnNames(); }
     */

    public final String getTable() {
        return tableName;
    }

    /**
     * utility to check for the characters in charsWithEscapeSeq array in the
     * input Object and replace them with the escape characters
     */
    private final Object prefixEscapeChars(String modify) {
        int l = charsWithEscapeSeq.length;
        if (l > 0) {
            String modifiedString = "";
            String delimiters = "";
            for (int i = 0; i < l; i++)
                delimiters += charsWithEscapeSeq[i];
            try {
                StringTokenizer st = new StringTokenizer(modify, delimiters, true);
                while (st.hasMoreTokens()) {
                    String temp = st.nextToken();
                    if (delimiters.indexOf(temp) != -1)
                        temp = "\\" + temp;
                    modifiedString += temp;
                } // end while

            } catch (Exception e) {
                String title = "Error: DBUtil.prefixEscapeChar() Table: " + tableName;
                StringBuffer error = new StringBuffer(e.toString());
                if (error.length() < title.length())
                    error.append("\t\t");
                JOptionPane.showMessageDialog(this, error.toString(), title, JOptionPane.ERROR_MESSAGE);
            } // end catch
            return modifiedString;
        } // end if
        else
            return modify;
    }// end method

}
