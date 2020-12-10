package com.test.selenium;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Test1 {

    public static void main(String[] args) throws IOException {

        WebDriver driver;
        String baseUrl = "https://www.makemytrip.com/";
        System.setProperty("webdriver.chrome.driver", "D:\\Selenium\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get(baseUrl);
        driver.manage().window().maximize();

        //Open Excel sheet and fetch values from it.
        FileReaderClass objFileReader = new FileReaderClass();
        String filepath = "D:/Selenium/workspace/src/excelExportAndFileIO/w1.xlsx";
        String sheetname = "flight";
        XSSFSheet xssfSheet = objFileReader.FileReader(filepath, sheetname);
        String fromFlightvalue;
        String toFlightvalue;

        fromFlightvalue = objFileReader.fetchUserName(xssfSheet);
        toFlightvalue = objFileReader.fetchPwd(xssfSheet);

        System.out.println("The value of From flight is :" + fromFlightvalue);

        //Select From Flight
        WebElement fromCityFlight = driver.findElement(By.id("fromCity"));
        fromCityFlight.sendKeys(fromFlightvalue, Keys.DOWN, Keys.ENTER);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        System.out.println("The value of to flight is :" + toFlightvalue);

        //Select To Flight
        // WebElement toCityFlight = driver.findElement(By.xpath("//*[@id='toCity']"));
        WebElement toCityFlight = driver.findElement(By.xpath("/html/body/div[1]/div/div[2]/div/div/div[2]/div[1]/div[2]/div[1]/div/div/div/input"));
        driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
        toCityFlight.sendKeys(toFlightvalue);
        driver.manage().timeouts().implicitlyWait(22, TimeUnit.SECONDS);
        toCityFlight.sendKeys(Keys.DOWN, Keys.ENTER);
        //Select Date
        String dateValue = "23/09/2020";
        //open calendar

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //move next in calendar
        WebElement nextLink = driver.findElement(By.xpath("/html/body/div[1]/div/div[2]/div/div/div[2]/div[1]/div[3]/div[1]/div/div/div/div[2]/div/div[1]/span[2]"));
        //nextLink.click();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //move previous in calendar
        WebElement prevLink = driver.findElement(By.xpath("/html/body/div[1]/div/div[2]/div/div/div[2]/div[1]/div[3]/div[1]/div/div/div/div[2]/div/div[1]/span[1]"));
        //prevLink.click();

        String date_dd_MM_yyyy[] = (dateValue.split(" ")[0]).split("/");

        int yearDiff = Integer.parseInt(date_dd_MM_yyyy[2]) - Calendar.getInstance().get(Calendar.YEAR);
        System.out.println("Year difference:"+yearDiff);
        int monthDiff = yearDiff*12 + Integer.parseInt(date_dd_MM_yyyy[1]) - Calendar.getInstance().get(Calendar.MONTH);
        System.out.println("Month Difference:"+monthDiff);
        if(monthDiff > 1){
            for (int i=0;i<monthDiff-1 ;i++){
                nextLink.click();
                driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            }
        }
        int dateParse = Integer.parseInt(date_dd_MM_yyyy[0]);
        System.out.println("Date"+  dateParse);

        //WebElement datepick = driver.findElement(By.xpath("//div[@class='dateInnerCell']//*[text()='12']"));
        WebElement datepick = driver.findElement(By.xpath("//div[@class='dateInnerCell']//*[text()='"+dateParse+"']"));
        datepick.click();

            System.out.println("The End");

        }

}
