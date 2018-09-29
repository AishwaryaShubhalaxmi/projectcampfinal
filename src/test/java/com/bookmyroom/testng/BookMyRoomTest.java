package com.bookmyroom.testng;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.bookmyroom.pages.BookingHistory;
import com.bookmyroom.pages.ConfirmBooking;
import com.bookmyroom.pages.GuestMode;
import com.bookmyroom.pages.LoginPage;
import com.bookmyroom.pages.SearchHotels;
import com.bookmyroom.pages.SignUpPage;
import com.bookmyroom.util.Constants;
import com.bookmyroom.util.GetExcelData;
import com.bookmyroom.util.MyListener;
import com.bookmyroom.util.ProgressBar;
import com.bookmyroom.util.SendMail;
import com.relevantcodes.extentreports.ExtentReports;


@Listeners(com.bookmyroom.util.MyListener.class)
public class BookMyRoomTest extends MyListener {


	public static WebDriver driver;
	//Reusable util;
	String reportFile1;
	ProgressBar progress;
	ProgressBar progress1;
	ProgressBar progress2;
	GetExcelData excelData=new GetExcelData();

	static org.apache.log4j.Logger log = Logger.getLogger(BookMyRoomTest.class.getName());

	//SignUp
	@DataProvider(name = "SignUpSheetData")	  
	public Object[][] signUpData() throws IOException 
	{	
		progress1=new ProgressBar();
		String[][] data=excelData.getData(Constants.signUpExcelPath, "SignUpData");
		progress1.setVisible(true);
		progress1.setStep(data.length);
		return data;
	}

	@Test(dataProvider="SignUpSheetData",groups = {"User"})
	public void signUpTest(String testCaseName,String name,String email,String userName,String password,String gender,String age,String phone,String expected) throws IOException
	{
		log.info("------ SignUp - "+testCaseName+" -------");
		test = reports.startTest(testCaseName);
		progress1.setProgress();
		System.out.println("Driver in AppTest: "+driver);
		SignUpPage signUpPage=new SignUpPage(driver);
		signUpPage.signUp(name, email,userName, password, gender, age, phone,expected);

	}	

	//Login
	@DataProvider(name = "LoginSheetData")	  
	public Object[][] loginData() throws IOException 
	{	
		progress2=new ProgressBar();
		String[][] data=excelData.getData(Constants.signUpExcelPath, "LoginData");
		progress2.setVisible(true);
		progress2.setStep(data.length);
		return data;
	}

	@Test(dataProvider="LoginSheetData",groups = {"User"})
	public void loginTest(String testCaseName,String username,String password,String expected) throws IOException
	{
		log.info("------ Login - "+testCaseName+" -------");
		test = reports.startTest(testCaseName);
		progress2.setProgress();
		LoginPage loginPage=new LoginPage(driver);
		loginPage.login(username, password,expected);
	}	


	 //HotelSearch
	@DataProvider(name = "SearchSheetData")	  
	public Object[][] searchData() throws IOException 
	{	
		progress=new ProgressBar();
		String[][] data=excelData.getData(Constants.signUpExcelPath, "SearchData");
		progress.setVisible(true);
		progress.setStep(data.length);
		return data;
	}

	@Test(dataProvider="SearchSheetData",groups = {"User"})
	public void searchTest(String testCaseName,String city,String from,String to,String type,String expected) throws IOException
	{
		log.info("------ Search - "+testCaseName+" -------");
		test = reports.startTest(testCaseName);
		progress.setProgress();
		SearchHotels searchHotel=new SearchHotels(driver);
		searchHotel.search(city,from,to,type,expected);
	}	

	//ConfirmBooking
	@DataProvider(name = "ConfirmBookingData")	  
	public Object[][] confirmBookingData() throws IOException 
	{	
		progress=new ProgressBar();
		String[][] data=excelData.getData(Constants.signUpExcelPath, "ConfirmData");
		progress.setVisible(true);
		progress.setStep(data.length);
		return data;
	}
	@Test(dataProvider="ConfirmBookingData",groups = {"User"})
	public void confirmBookingTest(String testCaseName,String city,String from,String to,String type,String expected) throws IOException
	{
		log.info("------ Search - "+testCaseName+" -------");
		test = reports.startTest(testCaseName);
		progress.setProgress();
		ConfirmBooking confirmBooking=new ConfirmBooking(driver);
		confirmBooking.confirmBooking(city,from,to,type,expected);
	}	
	
//GuestSearch
		@DataProvider(name = "GuestSheetData")	  
		public Object[][] guestData() throws IOException 
		{	
			progress=new ProgressBar();
			String[][] data=excelData.getData(Constants.signUpExcelPath, "GuestData");
			progress.setVisible(true);
			progress.setStep(data.length);
			return data;
		}

		@Test(dataProvider="GuestSheetData",groups = {"User"})
		public void guestTest(String testCaseName,String city,String from,String to,String type,String expected) throws IOException
		{
			log.info("------ Search - "+testCaseName+" -------");
			test = reports.startTest(testCaseName);
			progress.setProgress();
			GuestMode guestMode=new GuestMode(driver);
			guestMode.guestSearch(city,from,to,type,expected);
		}
		
/*		//BookingHistory
		
		@Test(dataProvider="ConfirmBookingData",groups = {"User"})
		public void bookingHistoryTest(String testCaseName,String city,String from,String to,String type,String expected) throws IOException
		{
			log.info("------ Search - "+testCaseName+" -------");
			test = reports.startTest(testCaseName);
			progress.setProgress();
			BookingHistory bookingHistory=new BookingHistory(driver);
			bookingHistory.historyCheck(city,from,to,type,expected);
		}
*/
	@BeforeTest
	public void beforeTest() {	
		System.out.println("--------************---------");
		String driverPath=Constants.chromeDriverPath;
		System.setProperty("webdriver.chrome.driver", driverPath);
		driver= new ChromeDriver();
		MyListener.driver=driver;
		reportFile1="C:\\Users\\M1047105\\Desktop\\images\\"+new SimpleDateFormat("yyyy-MM-dd hh-mm-ss-ms").format(new Date()) + "SignUpreports.html";
		reports = new ExtentReports(reportFile1);
		//String driverPath=Constants.chromeDriverPath;
		// System.setProperty("webdriver.chrome.driver", driverPath);
		//driver = new ChromeDriver();
		// driver.get("https://bookmyroomangulardevdotnet.azurewebsites.net/Home");
		// util=new Reusable(driver);
	}

	@AfterTest
	public void afterTest() {	
		progress.setVal();
		//progress1.setVal();
		//progress2.setVal();
		driver.close();
	}

	@AfterSuite
	public void afterSuite()
	{
		SendMail.sendMail(reportFile1);
	}


}
