package testdata;

//import java.lang.reflect.Method;

//import org.testng.annotations.DataProvider;

import base.BaseHooks;
//import utils.ExcelUtility;
public class TestDataProvider extends BaseHooks{

/*@DataProvider(name = "Createtask")
	public static String [][] getExcelData2(){
		return ExcelUtility.readExcelValue("S10_34CreateTask");
	}

@DataProvider(name="Verifyalertworktype")
public String [][] getExcelData1(){
	return ExcelUtility.readExcelValue("S10_42VerifyAlertForWorkType");
}  */





/*@DataProvider(name="gettestdata")
public String [][] getExcelData(){
	    System.out.print(filename);
	    return ExcelUtility.readExcelValue(filename);
} */


/*@DataProvider(name="gettestdata")
public String [][] getExcelData(Method m){
	switch(m.getName())
	{
	case "CreateTask":
	    return ExcelUtility.readExcelValue("S10_34CreateTask");
	case "MandatoryFieldForWorkType":
		return ExcelUtility.readExcelValue("S10_42VerifyAlertForWorkType");
	case "CreateNewLeads":
		return ExcelUtility.readExcelValue("S10_93CreateNewLeads");
	case "VerifyOpportunities":
		return ExcelUtility.readExcelValue("S10_5Verifyoppurtunitessort");
	case "EditTask":
		return ExcelUtility.readExcelValue("S10_31EditCase");
	}
	return null;
}*/


}
