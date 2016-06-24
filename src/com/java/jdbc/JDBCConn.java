package com.java.jdbc;

import java.sql.*;

/**
 * Created by Joe on 2016/5/31.
 */
public class JDBCConn {
    public  Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return  DriverManager.getConnection("" +
                "jdbc:mysql://localhost:3306/StuScoreManagement?useUnicode=true&characterEncoding=utf8","root","1025"
        );

    }

    public  void setParams(PreparedStatement preStmt, Object... params)
            throws SQLException {
        if (params == null || params.length == 0)
            return;
        for (int i = 1; i <= params.length; i++) {
            Object param = params[i - 1];
            if (param == null) {
                preStmt.setNull(i, Types.NULL);
            } else if (param instanceof Integer) {
                preStmt.setInt(i, (Integer) param);
            } else if (param instanceof String) {
                preStmt.setString(i, (String) param);
            } else if (param instanceof Double) {
                preStmt.setDouble(i, (Double) param);
            } else if (param instanceof Long) {
                preStmt.setDouble(i, (Long) param);
            } else if (param instanceof Timestamp) {
                preStmt.setTimestamp(i, (Timestamp) param);
            } else if (param instanceof Boolean) {
                preStmt.setBoolean(i, (Boolean) param);
            } else if (param instanceof Date) {
                preStmt.setDate(i, (Date) param);
            }
        }
    }
    public  int executeUpdate(String sql) throws SQLException {

        return executeUpdate(sql,new Object[]{});
    }


    public  int executeUpdate(String sql, Object... param) throws SQLException {

        Connection conn=null;
        PreparedStatement psate=null;
        try {

            conn=getConnection();
            psate=conn.prepareStatement(sql);
            setParams(psate,param);
            return psate.executeUpdate();
        }finally {
            if(psate!=null){
                psate.close();
            }if (conn!=null){
                psate.close();
            }
        }

    }
   }

