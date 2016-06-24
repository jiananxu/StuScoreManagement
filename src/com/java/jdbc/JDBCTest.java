package com.java.jdbc;

import java.sql.*;

/**
 * Created by Joe on 2016/5/30.
 */
public class JDBCTest {
    public static void main(String[] args) {





        try {
            String sql="select * from students";
            String update="INSERT INTO students(sno,sname,sex) VALUES('s000007','12','男') ";
            String delete="delete from students where sname='12' ";

            Class.forName("com.mysql.jdbc.Driver");
            String url="jdbc:mysql://localhost:3306/test";
            String user="root";
            String  password="1025";

          Connection  conn= DriverManager.getConnection(url,user,password);

             Statement statement=conn.createStatement();
           ResultSet rs= statement.executeQuery(sql);
            while (rs.next()){
                String name=rs.getString(2);
                String sex=rs.getString(3);
                System.out.println(name+"\t"+sex);

            }
            System.out.println("-------增加--------");
            //增加
            Statement stmt=conn.createStatement();
            int s=stmt.executeUpdate(update);
            if (s==1){
                System.out.println("update   success");
            }
            System.out.println("-----删除-----");
            //删除
            Statement stmts=conn.createStatement();
            stmts.executeUpdate(delete);

            //    statement.executeUpdate();
            rs.close();
            statement.close();
            conn.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
