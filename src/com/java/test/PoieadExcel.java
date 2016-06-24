package com.java.test;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.File;
import java.io.IOException;

/**
 * Created by Joe on 2016/6/10.
 */
public class PoieadExcel {

    public static void main(String[] args) {

        File file=new File("C:\\Users\\11701\\Desktop\\poi_test.xls");

        try {
            HSSFWorkbook hssfWorkbook=new HSSFWorkbook(FileUtils.openInputStream(file));
           // HSSFSheet sheet0=hssfWorkbook.getSheet("sheet0");
            HSSFSheet sheet0=hssfWorkbook.getSheetAt(0);
            int firstRowNum=0;
            int lastRowNum=sheet0.getLastRowNum();

            for (int i=firstRowNum;i<=lastRowNum;i++){

                HSSFRow row=sheet0.getRow(i);
                int lastCellNum =row.getLastCellNum();
                for (int j=0;j<lastCellNum;j++){
                    HSSFCell cell=row.getCell(j);
                    String value=cell.getStringCellValue();
                    System.out.print(value+"  ");

                }
                System.out.println();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
