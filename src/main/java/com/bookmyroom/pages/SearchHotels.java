package com.bookmyroom.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import com.bookmyroom.util.Constants;
import com.bookmyroom.util.GetExcelData;
import com.bookmyroom.util.Reusable;

public class SearchHotels {
	
	static WebDriver driver;
	GetExcelData excelData=new GetExcelData();
	
	static org.apache.log4j.Logger log = Logger.getLogger(SignUpPage.class.getName());

	public SearchHotels(WebDriver driver)
	{
		SearchHotels.driver=driver;
	}
	
	public void search(String city,String from,String to,String type,String expected)
	{
		Reusable util=new Reusable(driver);
		LoginPage loginPage=new LoginPage(driver);
		String data[][];
		
		data = excelData.getData(Constants.loginExcelPath, "Search");
		int length=data.length;
		int j=0;
		if(expected.equals("")) {
			length=length-1;}
		else if(expected.equals("Guest")) {
			j=2;}
		for(int i=j;i<length;i++)
        {
			System.out.println("----- Keyword: "+data[i][Constants.keyword_col]+" ------");
			switch(data[i][Constants.keyword_col])
	    	{
	    	
	    	case "login":
	    		util.implicitWait();
	    		System.out.println("------ Login ---------");
	    		loginPage.login("Nikhil4","Nikhil@6","");
	    		break;
	    		
	    	case "click":
	    		util.implicitWait();
	    		//System.out.println("Click on: "+data[i][Constants.path_col]);
	    		util.click(data[i][Constants.pathType_col], data[i][Constants.path_col]);
	    		break;	    
	    		
	    	case "enterText":
	    		util.implicitWait();
	    		switch(data[i][Constants.parameter_col])
	    		{
	    		case"city":
	    			System.out.println("--- In City ---");
	    			util.enterText(data[i][Constants.pathType_col], data[i][Constants.path_col], city);
	    			break;
	    		case"from":
	    			util.enterText(data[i][Constants.pathType_col], data[i][Constants.path_col], from);
	    			break;
	    		case"to":
	    			util.enterText(data[i][Constants.pathType_col], data[i][Constants.path_col], to);
	    			break;
	    		}
	    		break;
	    		
	    	case "select":
	    		util.implicitWait();
	    		util.selectFromDropDownBox(data[i][Constants.pathType_col],data[i][Constants.path_col] ,type);
	    		util.implicitWait();
	    	    break;
	    	case "verifyTitle":
	    	util.verifyTitle(data[i][Constants.parameter_col]);
	    	util.implicitWait();
	    	break;
	    	//util.verify(data[i][Constants.pathType_col],data[i][Constants.path_col], data[i][Constants.parameter_col]);
	    	
	    	case "verifyPresence":
		    	util.verifyPresence(data[i][Constants.pathType_col], data[i][Constants.path_col],data[i][Constants.parameter_col],expected);
		    	//util.implicitWait();
		    	break;
	    	default:log.debug("Invalid Keyword");
	    	
	    	}
        }
		
		
	}
	
	public void guestSearch(String city,String from,String to,String type,String expected)
	{
		Reusable util=new Reusable(driver);
		LoginPage loginPage=new LoginPage(driver);
		String data[][];
		
		data = excelData.getData(Constants.loginExcelPath, "Search");
		
		for(int i=2;i<data.length;i++)
        {
			System.out.println("----- Keyword: "+data[i][Constants.keyword_col]+" ------");
			switch(data[i][Constants.keyword_col])
	    	{
	    	
	    	case "login":
	    		util.implicitWait();
	    		System.out.println("------ Login ---------");
	    		loginPage.login("Nikhil4","Nikhil@6","");
	    		break;
	    		
	    	case "click":
	    		util.implicitWait();
	    		//System.out.println("Click on: "+data[i][Constants.path_col]);
	    		util.click(data[i][Constants.pathType_col], data[i][Constants.path_col]);
	    		break;	    
	    		
	    	case "enterText":
	    		util.implicitWait();
	    		switch(data[i][Constants.parameter_col])
	    		{
	    		case"city":
	    			System.out.println("--- In City ---");
	    			util.enterText(data[i][Constants.pathType_col], data[i][Constants.path_col], city);
	    			break;
	    		case"from":
	    			util.enterText(data[i][Constants.pathType_col], data[i][Constants.path_col], from);
	    			break;
	    		case"to":
	    			util.enterText(data[i][Constants.pathType_col], data[i][Constants.path_col], to);
	    			break;
	    		}
	    		break;
	    		
	    	case "select":
	    		util.implicitWait();
	    		util.selectFromDropDownBox(data[i][Constants.pathType_col],data[i][Constants.path_col] ,type);
	    		util.implicitWait();
	    	    break;
	    	case "verifyTitle":
	    	util.verifyTitle(data[i][Constants.parameter_col]);
	    	util.implicitWait();
	    	break;
	    	//util.verify(data[i][Constants.pathType_col],data[i][Constants.path_col], data[i][Constants.parameter_col]);
	    	
	    	case "verifyPresence":
		    	util.verifyPresence(data[i][Constants.pathType_col], data[i][Constants.path_col],data[i][Constants.parameter_col],expected);
		    	//util.implicitWait();
		    	break;
	    	default:log.debug("Invalid Keyword");
	    	
	    	}
        }
		
		
	}
}
