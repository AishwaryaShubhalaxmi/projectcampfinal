package com.bookmyroom.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import com.bookmyroom.util.Constants;
import com.bookmyroom.util.GetExcelData;
import com.bookmyroom.util.Reusable;

public class ConfirmBooking {
	
	static WebDriver driver;
	GetExcelData excelData=new GetExcelData();
	
	static org.apache.log4j.Logger log = Logger.getLogger(SignUpPage.class.getName());

	public ConfirmBooking(WebDriver driver)
	{
		ConfirmBooking.driver=driver;
	}
	
	public void confirmBooking(String city,String from,String to,String type,String expected)
	{
		System.out.println("xxxxxxxxxxxxx ConfirmBooking xxxxxxxx");
		Reusable util=new Reusable(driver);
		SearchHotels searchHotels=new SearchHotels(driver);
		String data[][];
		data = excelData.getData(Constants.loginExcelPath, "Confirm");
		int length=0;
		if(expected.equals(""))
			length=(data.length)-1;
		else
			length=data.length;
		for(int i=0;i<length;i++)
        {
			System.out.println("----- Keyword: "+data[i][Constants.keyword_col]+" ------");
			switch(data[i][Constants.keyword_col])
	    	{
	    	
	    	case "search":
	    		util.implicitWait();
	    		System.out.println("------ Search in Confirm Booking ---------");
	    		searchHotels.search(city,from,to,type,"");
	    		break;
	    		
	    	case "click":
	    		util.implicitWait();
	    		//System.out.println("Click on: "+data[i][Constants.path_col]);
	    		util.click(data[i][Constants.pathType_col], data[i][Constants.path_col]);
	    		break;
	    		
	    	case "verifyURL":
	    		util.implicitWait();
	    		System.out.println("Verify: "+expected);
	    		util.verifyURL(expected);
	    		break;
	    	
	    	case "verifyPresence":
		    	util.verifyPresence(data[i][Constants.pathType_col], data[i][Constants.path_col],data[i][Constants.parameter_col],expected);
		    	//util.implicitWait();
		    	break;
	    	default:log.debug("Invalid Keyword");
	    	
	    	}
        }
	}

}
