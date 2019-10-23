package com.ccsu.cs530.successcentral.util;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;

public class DatabaseConnection {

    private DataSource ds;
    private static Connection con;
    private PreparedStatement pr;
    private InitialContext ic;

    public static Connection getConnection() {
        if (con != null) {
            return con;
        }

//        try {
//            HikariConfig config = new HikariConfig();

            // Configure which instance and what database user to connect with.
//            config.setJdbcUrl(System.getenv("JDBC_URL"));
//
//            DataSource pool = new HikariDataSource(config);
//            con = pool.getConnection();
//            return con;

//        } catch (Exception e) {
//            System.out.println("Could not connect Booya");
//            return null;
//        }
        try {
            return ((DataSource) InitialContext.doLookup("java:/SCDB")).getConnection();
        } catch (Exception e) {
            System.out.println("Hey CCSU team. Could not connect to DB");
            return null;
        }
    }

    public void open() throws NamingException, SQLException {
       // The configuration object specifies behaviors for the connection pool.
        HikariConfig config = new HikariConfig();

        // Configure which instance and what database user to connect with.
        config.setJdbcUrl(System.getenv("JDBC_URL"));


        // Initialize the connection pool using the configuration object.
        DataSource pool = new HikariDataSource(config);
        con = pool.getConnection();

//        ic = new InitialContext();
//        ds = (DataSource) ic.lookup("java:/SCDB");
//        con = ds.getConnection();
    }

    public void close() throws SQLException {
        if (pr != null){
            pr.close();
        }
        if (con != null) {
            con.close();
        }
    }

    public int executeUpdateCommand(String command) throws SQLException {
        pr = con.prepareStatement(command);
        int i = pr.executeUpdate();
        return i;
    }

    public ResultSet executeQueryCommand(String command) throws SQLException {
        pr = con.prepareStatement(command);
        ResultSet rs = pr.executeQuery();
        return rs;
    }

}
