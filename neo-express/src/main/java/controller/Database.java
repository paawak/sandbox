/*
 * Database.java
 *
 * Created on September 22, 2003, 12:39 AM
 */

package controller;

import utils.database.DBUtil;

/**
 * 
 * @author paawak
 */
public final class Database extends DBUtil {

    private static final DbDetails dbDetails = new DbDetails();

    public Database(String TableName) {

        super(dbDetails.getDriver(), dbDetails.getUrl(), dbDetails.getUser(),
                dbDetails.getPassword(), TableName, true, new String[] { "\\",
                        "\"", "'" });

    }

}
