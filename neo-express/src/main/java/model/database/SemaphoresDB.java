/*
 * SemaphoresDB.java
 *
 * Created on April 1, 2004, 11:50 PM
 */

package model.database;

/**
 *
 * @author  paawak
 */
public interface SemaphoresDB {
    public static final String tablename="tablename";
    public static final String counterformat="counterformat";    
    public static final String prefix="prefix";
    public static final String maxcount="maxcount";
    public static final String suffix="suffix";
    public static final String remarks="remarks";    
    
    public final int fields = 6;
    
}
