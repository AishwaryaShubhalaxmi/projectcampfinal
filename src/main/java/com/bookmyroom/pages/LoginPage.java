package com.bookmyroom.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import com.bookmyroom.util.Constants;
import com.bookmyroom.util.GetExcelData;
import com.bookmyroom.util.Reusable;

public class LoginPage {
	
	static WebDriver driver;
	GetExcelData excelData=new GetExcelData();
	static org.apache.log4j.Logger log = Logger.getLogger(SignUpPage.class.getName());
	
	public LoginPage(WebDriver driver){
		LoginPage.driver=driver;
	}
	
	public void login(String username,String password,String expected)
	{
		Reusable util=new Reusable(driver);
		String data[][];
		
		data = excelData.getData(Constants.loginExcelPath, "Login");
		int length=data.length;
		if(expected.equals(""))
			length=length-1;
		for(int i=0;i<length;i++)
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
	    		
	    	case "click":
	    		util.click(data[i][Constants.pathType_col], data[i][Constants.path_col]);
	    		util.implicitWait();
	    		break;	    
	    		
	    	case "enterText":
	    		
	    		switch(data[i][Constants.parameter_col])
	    		{
	    		case"username":
	    			util.enterText(data[i][Constants.pathType_col], data[i][Constants.path_col], username);
	    			break;
	    		case"password":
	    			util.enterText(data[i][Constants.pathType_col], data[i][Constants.path_col], password);
	    			break;
	    		}
	    		break;
	    		//util.enterText(data[i][Constants.pathType_col], data[i][Constants.path_col], name);
	    	    
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
		
		util.click("xpath", "//button[@class='swal2-confirm swal2-styled']");
	
	}

}
