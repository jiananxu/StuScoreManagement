package com.java.service;

import com.java.user.User;
import com.java.userdao.UserDAO;
import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.junit.Test;

import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Joe on 2016/6/3.
 */
public class UserService {

    private UserDAO userDAO = new UserDAO();
    private String[] title = {"学号", "姓名", "成绩1", "成绩2"};
    //创建工作薄
    HSSFWorkbook Workbook = new HSSFWorkbook();
    //创建工作表sheet
    HSSFSheet Sheet = Workbook.createSheet();
    //创建第一行
    HSSFRow Row = Sheet.createRow(0);
    //创建cell
    HSSFCell cell = null;
    File file;

    public UserService() throws SQLException, ClassNotFoundException {
    }

    //显示【所有学生信息】
    public void getStudent() throws Exception {
        List<User> user = userDAO.findAll();
        for (int i = 0; i < title.length; i++) {
            cell = Row.createCell(i);
            cell.setCellValue(title[i]);
        }
        for (int j = 1; j <= user.size(); j++) {

            HSSFRow nextrow = Sheet.createRow(j);

            HSSFCell cell1 = nextrow.createCell(0);
            cell1.setCellValue(user.get(j - 1).getId());

            cell1 = nextrow.createCell(1);
            cell1.setCellValue(user.get(j - 1).getName());

            cell1 = nextrow.createCell(2);
            cell1.setCellValue(user.get(j - 1).getScore1());

            cell1 = nextrow.createCell(3);
            cell1.setCellValue(user.get(j - 1).getScore2());

            FileSystemView fsv = FileSystemView.getFileSystemView();

            file = new File(fsv.getHomeDirectory() + "\\student.xls");
            file.createNewFile();
            FileOutputStream stream = FileUtils.openOutputStream(file);
            Workbook.write(stream);
            stream.close();
        }
    }
}

