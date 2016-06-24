package com.java.test;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Joe on 2016/6/10.
 */
public class PoiExcel {

    public static void main(String[] args) {

        String[] title={"id","name","sex"};

        //创建工作薄
        HSSFWorkbook hssfWorkbook=new HSSFWorkbook();
       //创建工作表sheet
        HSSFSheet hssfSheet=hssfWorkbook.createSheet();
        //创建第一行
        HSSFRow Row=hssfSheet.createRow(0);
        //创建cell
        HSSFCell cell=null;
        //插入第一行数据
        for (int i=0;i<title.length;i++){
            cell=Row.createCell(i);
            cell.setCellValue(title[i]);
        }
        for (int i=1;i<=10;i++) {
            HSSFRow nextrow=hssfSheet.createRow(i);
            HSSFCell cell2=nextrow.createCell(0);
            cell2.setCellValue("a"+i);
            cell2=nextrow.createCell(1);
            cell2.setCellValue("user"+i);
            cell2=nextrow.createCell(2);
            cell2.setCellValue("女");
        }
        File file=new File("C:\\Users\\11701\\Desktop\\poi_test.xls");
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
