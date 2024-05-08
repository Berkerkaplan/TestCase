package com.saf.framework;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Date;
import java.text.*;

public class DBReporting {
    private static Connection connectDB() {
        Connection con = null;
        try {
            String path = System.getProperty("user.dir") + "/Reports/DB_Reports/";
            File f = new File(path);
            if(!f.exists()) {
                f.mkdir();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return con;
    }




}
