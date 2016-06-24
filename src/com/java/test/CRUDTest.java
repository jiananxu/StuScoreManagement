package com.java.test;

import com.java.jdbc.JDBCConn;
import com.java.user.User;
import com.java.userdao.UserDAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Joe on 2016/6/6.
 */
public class CRUDTest {


    public static void main(String[] args) throws Exception {
        JDBCConn jdbcConn=new JDBCConn();
        jdbcConn.getConnection();
        UserDAO userDAO=new UserDAO();
        User user=new User();
        user.setId(2014211456);
        user.setName("李二");
        user.setScore1(86);
        user.setScore2(69);
//       userDAO.update(user);
//        userDAO.insert(user);
//    List<User> list=userDAO.findAll();
      //  System.out.println(list.size());
        //list输出1
//        Iterator iterator=list.iterator();
//        while (iterator.hasNext()){
//            System.out.println(iterator.next());
//        }
        //list输出2
//            for (User u:list){
//                System.out.println(u.getId()+" "+u.getName()+" "+u.getScore1()+" "+u.getScore2());
//            }
        //list输出3
        // System.out.println(list);
           userDAO.delete(43433);

//        ---------------------------
//        user=userDAO.findByID("2014211414");
//        System.out.println(user.getName());
//        System.out.println(user);

//        -----------------------
  //      userDAO.insert(user);

    }
}
