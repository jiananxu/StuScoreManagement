package com.java.test;


import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Joe on 2016/6/10.
 */
public class PoiXssfExcel {
    public static void main(String[] args) {

        String[] title={"id","name","sex"};

        //创建工作薄
        XSSFWorkbook hssfWorkbook=new XSSFWorkbook();
        //创建工作表sheet
        XSSFSheet hssfSheet=hssfWorkbook.createSheet();
        //创建第一行
        XSSFRow Row=hssfSheet.createRow(0);
        //创建cell
        XSSFCell cell=null;
        //插入第一行数据
        for (int i=0;i<title.length;i++){
            cell=Row.createCell(i);
            cell.setCellValue(title[i]);
        }
        for (int i=1;i<=10;i++) {
            XSSFRow nextrow=hssfSheet.createRow(i);
            XSSFCell cell2=nextrow.createCell(0);
            cell2.setCellValue("a"+i);
            cell2=nextrow.createCell(1);
            cell2.setCellValue("user"+i);
            cell2=nextrow.createCell(2);
            cell2.setCellValue("女");
        }
        File file=new File("C:\\Users\\11701\\Desktop\\poi_test.xlsx");
        try {
            file.createNewFile();
            FileOutputStream fileOutputStream= FileUtils.openOutputStream(file);
            hssfWorkbook.write(fileOutputStream);
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
