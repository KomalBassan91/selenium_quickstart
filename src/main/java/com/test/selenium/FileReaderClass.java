package com.test.selenium;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class FileReaderClass {

    /*File file = new File("D:/Selenium/workspace/src/excelExportAndFileIO/w1.xlsx");
    FileInputStream iFile = new FileInputStream(file);

    XSSFWorkbook wb = new XSSFWorkbook(iFile);
    XSSFSheet sheet = wb.getSheet("Sheet1");*/

    public XSSFSheet FileReader(String filepath,String sheetname) throws IOException {
        File file= new File(filepath);
        FileInputStream iFile = new FileInputStream(file);

        XSSFWorkbook wb = new XSSFWorkbook(iFile);
        XSSFSheet sheet = wb.getSheet(sheetname);
        return sheet;
    }

    public String fetchUserName(XSSFSheet sheet){
        int rowCount = sheet.getLastRowNum();
        System.out.println("the no of rows are : " + rowCount);
        String uname = sheet.getRow(1).getCell(0).getStringCellValue();
        System.out.println(uname);

        return uname;

    }
    public String fetchPwd(XSSFSheet sheet){
        int rowCount = sheet.getLastRowNum();
        System.out.println("the no of rows are : " + rowCount);
        String pwd = sheet.getRow(1).getCell(1).getStringCellValue();
        System.out.println(pwd);

        return pwd;

    }

}
