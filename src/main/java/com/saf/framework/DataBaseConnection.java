package com.saf.framework;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DataBaseConnection {

    public enum DBType
    {
        ORACLE, MYSQL, MSACCESS;
    }

    DBType oDBType;

    public DataBaseConnection(DBType oDBType) {
        this.oDBType = oDBType;
    }

    public Statement DataBaseConn(String connectionDb, String userName, String password){

        switch(oDBType)
        {
            case ORACLE:
                try {

                    //Load the driver class
                    String DBClass = "oracle.jdbc.driver.OracleDriver";
                    Class.forName(DBClass).newInstance();

                    //Create the connection object
                    Connection con = DriverManager.getConnection(connectionDb,userName,password);
                    if (!con.getAutoCommit()) {
                        con.commit();
                    }

                    //Create the statement object
                    Statement stmt = con.createStatement();
                    return stmt;

                    //con.close();
                }

                catch(Exception e){
                    e.printStackTrace();
                }
                break;

            case MYSQL:
                try {

                    //Load the driver class
                    String DBClass = "com.mysql.jdbc.Driver";
                    Class.forName(DBClass).newInstance();

                    //Create the connection object
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:port/ServiceName", "UserID", "Password");

                    //Create the statement object
                    Statement stmt = con.createStatement();
                    return stmt;

                }

                catch(Exception e){
                    e.printStackTrace();
                }
                break;

            case MSACCESS:
                try {

                    //Load the driver class
                    String DBClass = "oracle.jdbc.driver.OracleDriver";
                    Class.forName(DBClass).newInstance();

                    //Create the connection object
                    Connection con = DriverManager.getConnection("connection details","UserId","Password");

                    //Create the statement object
                    Statement stmt = con.createStatement();
                    return stmt;

                }
                catch(Exception e){
                    e.printStackTrace();
                }
                break;
        }
        return null;
    }
}
