package com.java.userdao;

import com.java.jdbc.JDBCConn;
import com.java.user.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {


    private   JDBCConn jdbcConn=new JDBCConn();


    public UserDAO() throws SQLException, ClassNotFoundException {
    }

    //增加信息

    public int insert(User user) throws SQLException {
        String sql="insert into student(SID,name,score1,score2) values(?,?,?,?)";
        return jdbcConn.executeUpdate(sql,user.getId(),user.getName(),user.getScore1(),user.getScore2());
    }

    //修改信息
    public  int update(User user) throws SQLException {

        String sql="update student set name=?,score1=?,score2=? where SID=?";
        return jdbcConn.executeUpdate(sql,user.getName(),user.getScore1(),user.getScore2(),user.getId());
    }

    //删除信息
    public int delete(int id) throws SQLException {

        String sql="delete from student where SID=?";
        return jdbcConn.executeUpdate(sql,id);

    }

        //根据学号查询
    public User findByID(int id) throws SQLException {

        String sql = "SELECT  * FROM student WHERE SID= ?";
        Connection conn = null;
        PreparedStatement pstat = null;
        ResultSet rs = null;
        try {
            conn = jdbcConn.getConnection();
            pstat = conn.prepareStatement(sql);
            pstat.setInt(1, id);
            rs = pstat.executeQuery();
            User user ;
            if(rs.next()) {

                 user=new User();

                user.setName(rs.getString("name"));
                user.setScore1(rs.getInt("score1"));
                user.setScore2(rs.getInt("score2"));
                return  user;
            }else {
                return null;
            }

        }finally {
            if (rs != null) {
                rs.close();
            }
            if (pstat != null) {
                pstat.close();
            }
            if (conn != null) {
                conn.close();
            }
        }



    }

        //查询所有学生信息
    public List<User> findAll() throws Exception {
        String sql ="SELECT * FROM student";
        List<User> list=new ArrayList<>();
        Connection conn=null;
        PreparedStatement pstat=null;
        ResultSet rs=null;
        try {
            conn=jdbcConn.getConnection();
            pstat=conn.prepareStatement(sql);
            rs=pstat.executeQuery();

            while(rs.next()){
                User user=new User();

                user.setId(rs.getInt("SID"));
                user.setName(rs.getString("name"));
                user.setScore1(rs.getInt("score1"));
                user.setScore2(rs.getInt("score2"));
                list.add(user);
            }

        } finally {
            if (rs!=null){
                rs.close();
            }if(pstat!=null){
                pstat.close();
            }if(conn!=null){
                conn.close();
            }
        }

        return  list;

    }


}
