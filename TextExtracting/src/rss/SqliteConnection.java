package rss;

import java.sql.Connection;
import java.sql.DriverManager;

public class SqliteConnection {
private static Connection c = null;
public static Connection getConn() throws Exception {
    if(c == null){
    Class.forName("org.sqlite.JDBC");
    c = DriverManager.getConnection("jdbc:sqlite:RSS_FEED_DB.db");
    }
    return c;
    }
}
