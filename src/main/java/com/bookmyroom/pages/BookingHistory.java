package com.bookmyroom.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import com.bookmyroom.util.Constants;
import com.bookmyroom.util.GetExcelData;
import com.bookmyroom.util.Reusable;

public class BookingHistory {
	
	static WebDriver driver;
	GetExcelData excelData=new GetExcelData();
	
	static org.apache.log4j.Logger log = Logger.getLogger(SignUpPage.class.getName());

	public BookingHistory(WebDriver driver)
	{
		BookingHistory.driver=driver;
	}
	
	public void historyCheck(String city,String from,String to,String type,String expected)
	{
		Reusable util=new Reusable(driver);
		ConfirmBooking confirm=new ConfirmBooking(driver);
		String data[][];
		
		data = excelData.getData(Constants.loginExcelPath, "BookingHistory");
		for(int i=0;i<data.length;i++)
        {
			System.out.println("----- Keyword: "+data[i][Constants.keyword_col]+" ------");
			switch(data[i][Constants.keyword_col])
	    	{
	    	
	    	case "confirmBooking":
	    		confirm.confirmBooking(city, from, to, type, "");
	    		break;
	    		
	    	case "click":
	    		util.implicitWait();
	    		//System.out.println("Click on: "+data[i][Constants.path_col]);
	    		util.click(data[i][Constants.pathType_col], data[i][Constants.path_col]);
	    		break;	
	    		
	    	case "navigateBack":
	    		util.navigateBack();
	    		break;
	    		
	    	case "checkHotel":
		    	//util.implicitWait();
		    	break;
		    	
	    	default:log.debug("Invalid Keyword");
	    	}
        }
	}

}
