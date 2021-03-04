package org.geektimes.projects.user.sql;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
import java.io.PrintWriter;

import java.util.Properties;

// 数据库连接管理简单封装
public class DbConnectionManager {
    public Connection getConnection() throws SQLException {

        // JNDI方式获取数据源
        try {
            Context ctx = new InitialContext();
            Context envCtx = (Context)ctx.lookup("java:comp/env");
            DataSource ds = (DataSource) envCtx.lookup("jdbc/UserPlatformDB");
            return ds.getConnection();
        } catch (NamingException e) {
            e.printStackTrace();
            throw new RuntimeException("get connection fail!!!");
        }

//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//
//        DriverManager.setLogWriter(new PrintWriter(System.out));
//        Driver driver = DriverManager.getDriver("jdbc:mysql://192.168.3.144:3306/xiaomage?characterEncoding=UTF-8");
//
//        Properties properties = new Properties();
//        properties.setProperty("user", "root");
//        properties.setProperty("password", "123456");
//        return driver.connect("jdbc:mysql://192.168.3.144:3306/xiaomage?characterEncoding=UTF-8", properties);
    }

    public void releaseConnection(Connection connection) {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
