package com.java.test;

import com.java.service.UserService;
import com.java.userdao.UserDAO;
import org.junit.Test;

import java.sql.SQLException;

/**
 * Created by Joe on 2016/6/5.
 */
public class ServiceTest {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        UserService userService=new UserService();

        UserDAO userDAO=new UserDAO();
   //     userService.SUMScore("2014211413");
    }


}
