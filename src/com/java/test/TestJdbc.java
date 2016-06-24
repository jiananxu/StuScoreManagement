package com.java.test;

import com.java.jdbc.JDBCConn;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Joe on 2016/5/31.
 */
public class TestJdbc {

    public static void main(String[] args) {

        JDBCConn conn=new JDBCConn();
        try {
            Connection connection=conn.getConnection();
            Statement statement=connection.createStatement();
            ResultSet rs=statement.executeQuery("SELECT  * FROM student");
            while (rs.next()){

                String  name=rs.getString(1);
                String id=rs.getString(2);
                System.out.println("name"+"\t"+"  id");
                System.out.print(name+"\t"+id);
            }
            rs.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
