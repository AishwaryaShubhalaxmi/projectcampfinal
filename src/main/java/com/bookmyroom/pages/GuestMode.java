package com.bookmyroom.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import com.bookmyroom.util.Constants;
import com.bookmyroom.util.GetExcelData;
import com.bookmyroom.util.Reusable;

public class GuestMode {
	
	static WebDriver driver;
	GetExcelData excelData=new GetExcelData();
	
	static org.apache.log4j.Logger log = Logger.getLogger(SignUpPage.class.getName());

	public GuestMode(WebDriver driver)
	{
		GuestMode.driver=driver;
	}
	
	public void guestSearch(String city,String from,String to,String type,String expected)
	{
		Reusable util=new Reusable(driver);
		SearchHotels searchHotels=new SearchHotels(driver);
		String data[][];
		
		data = excelData.getData(Constants.loginExcelPath, "Guest");
		for(int i=0;i<data.length;i++)
        {
			System.out.println("----- Keyword: "+data[i][Constants.keyword_col]+" ------");
			switch(data[i][Constants.keyword_col])
	    	{
			
	    	case "navigateTo":
	    		System.out.println("Driver in SignUp: "+driver);
	    		System.out.println("----URL: "+data[i][Constants.parameter_col]);
	    		util.navigateTo(data[i][Constants.parameter_col]);
	    		util.implicitWait();
	    		break;
	    	
	    	case "search":
	    		util.implicitWait();
	    		System.out.println("------ Login ---------");
	    		searchHotels.guestSearch(city,from,to,type,expected);
	    		break;
	    		
	    	case "click":
	    		util.implicitWait();
	    		//System.out.println("Click on: "+data[i][Constants.path_col]);
	    		util.click(data[i][Constants.pathType_col], data[i][Constants.path_col]);
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
