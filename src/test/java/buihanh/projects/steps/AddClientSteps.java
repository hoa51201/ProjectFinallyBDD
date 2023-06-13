package buihanh.projects.steps;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.util.Assert;

import buihanh.driver.DriverFactory;
import buihanh.helpers.ExcelHelpers;
import buihanh.helpers.Helpers;
import buihanh.keywords.WebUI;
import buihanh.projects.pages.ClientPage;
import buihanh.projects.pages.SigninPage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AddClientSteps {
	WebDriver driver;
	private SigninPage signinPage;
	private ClientPage clientPage;
	private String CompanyName, SearchOwner, Address, City, State, Zip, Country, Phone, Website, Vat, ClientGroups, expResult;
	//private String expResult;
	
	@Given("User logged in with email {string} and password {string}")
	public void user_logged_in_page_to_client_page(String vaildEmailText, String vaildPasswordText) {
		driver = DriverFactory.getDriver();
		signinPage = new SigninPage(driver);
		signinPage.fillSignInForm(vaildEmailText, vaildPasswordText);		
		signinPage.clickOnSigninButton();	}

	@Given("User navigates to Add client page")
	public void user_navigates_to_add_client_page() {
		clientPage = new ClientPage(driver);
		clientPage.openClientTabPage();
	}
	@When("User searches for the newly added and move to tab Client info")
	public void user_searches_for_the_newly_added_and_move_to_tab_client_info() {
		//WebUI.disableinfobars();
		clientPage.selectListbox();
		clientPage.enterDataSearchClient(CompanyName);
		WebUI.checkAndClickDataTable(2, CompanyName);
		clientPage.movetoTabClients();
	}

	
	@When("User fill the form add client with valid inputs")
	public void user_fill_the_from_add_client_with_vaild_inputs(DataTable dataTable) {
		List<Map<String,String>>datalist=dataTable.asMaps(String.class,String.class);
        for (Map<String,String> dataMap:datalist)
        {
        	CompanyName = dataMap.get("company_name");
        	SearchOwner = dataMap.get("owner");
        	Address = dataMap.get("address");
        	City = dataMap.get("city");
        	State = dataMap.get("state");
        	Zip = dataMap.get("zip");
        	Country = dataMap.get("country");
        	Phone = dataMap.get("phone");
        	Website = dataMap.get("website");
        	Vat = dataMap.get("vat");
        	ClientGroups = dataMap.get("client_group");	
    		clientPage.addClient(CompanyName, SearchOwner, Address, City, State, Zip, Country, Phone, Website, Vat, ClientGroups);
    		
        }			

	}
	@Then("Display client info and check values detail")
	public void display_client_info_and_check_values_detail() {
		clientPage.checkClientDetail(CompanyName, SearchOwner, Address, City, State, Zip, Country, Phone, Website, Vat, ClientGroups);
	}
	@When("User fill the form add client from given sheetname {string} and rownumber {int}")
	public void user_fill_the_from_add_client_from_given_sheetname_and_rownumber(String sheetname, Integer rownumber)throws InvalidFormatException, IOException {
		ExcelHelpers reader = new ExcelHelpers();
		String excelpath = Helpers.getCurrentDir()+"src\\test\\resources\\data\\data.xlsx";
		List<Map<String, String>> testdata = reader.getData(excelpath, sheetname);
		CompanyName = testdata.get(rownumber).get("company_name");
		SearchOwner = testdata.get(rownumber).get("owner");
		Address = testdata.get(rownumber).get("address");
		City = testdata.get(rownumber).get("city");
		State = testdata.get(rownumber).get("state");
		Zip = testdata.get(rownumber).get("zip");
		Country = testdata.get(rownumber).get("country");
		Phone = testdata.get(rownumber).get("phone");
		Website = testdata.get(rownumber).get("website");
		Vat = testdata.get(rownumber).get("vat");
		ClientGroups = testdata.get(rownumber).get("client_group");
		expResult = testdata.get(rownumber).get("exception_result");
		clientPage.addClient(CompanyName, SearchOwner, Address, City, State, Zip, Country, Phone, Website, Vat, ClientGroups);

	}
	@Given("User clicks on Save in button")
	public void user_clicks_on_save_in_pbutton() {
		clientPage.clickOnSaveButton();
	}

	@Then("Display an error message right below the companyname field")
	public void display_an_error_message_right_below_the_companyname_field() {
		
		WebUI.verifyEquals(clientPage.getWarningMessageCompany(), expResult);
	}
}
