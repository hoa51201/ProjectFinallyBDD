package buihanh.projects.steps;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.WebDriver;
import buihanh.driver.DriverFactory;
import buihanh.helpers.ExcelHelpers;
import buihanh.helpers.Helpers;
import buihanh.keywords.WebUI;
import buihanh.projects.pages.SigninPage;
import buihanh.projects.pages.SignupPage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class SignupSteps {
	WebDriver driver;
	private SignupPage signupPage;
	private SigninPage signinPage;
	private String firstname,lastname,companyname,email,password,retypepass, expResult;

	@Given("User navigates to Sign up page")
	public void user_navigates_to_sign_up_page() {
		driver = DriverFactory.getDriver();
		signinPage = new SigninPage(driver);
		signupPage = new SignupPage(driver);
		signupPage.moveToSignUp();
	}

	@When("User dont enter any details into fields")
	public void user_dont_enter_any_details_into_fields() {
		signupPage.enterFirstName("");
		signupPage.enterLastName("");
		signupPage.enterCompanyName("");
		signupPage.enterEmail("");
		signupPage.enterPassword("");
		signupPage.enterRetypePass("");
	}

	@When("User clicks on Sign up button")
	public void user_clicks_on_sign_up_button() {
		signupPage.clickOnSignupButton();
	}

	@When("User enter vaild into fields in the signin form")
	public void user_enter_vaild_into_fields_in_the_signin_form(DataTable dataTable) {
		List<Map<String,String>>datalist=dataTable.asMaps(String.class,String.class);
        for (Map<String,String> dataMap:datalist)
        {
        	signupPage.enterFirstName(dataMap.get("firstname"));
    		signupPage.enterFirstName(dataMap.get("lastname"));
    		signupPage.enterFirstName(dataMap.get("email"));
    		signupPage.enterFirstName(dataMap.get("password"));
    		signupPage.enterFirstName(dataMap.get("retypepassword"));
        }	
	}
	@When("User fill the form sign up from given sheetname {string} and rowNumber {int}")
	public void user_fill_the_form_signup_from_given_sheetname_and_rownumber(String sheetname, Integer rowNumber) throws InvalidFormatException, IOException{
		ExcelHelpers reader = new ExcelHelpers();
		String excelpath = Helpers.getCurrentDir()+"src\\test\\resources\\data\\data.xlsx";
		List<Map<String, String>> testdata = reader.getData(excelpath, sheetname);
		//lấy kết quả mong đợi trong execl
		expResult = testdata.get(rowNumber).get("exception_result");
		//nhập dữ liệu từ trong excel
		firstname=testdata.get(rowNumber).get("first_name");
		lastname=testdata.get(rowNumber).get("last_name");
		companyname=testdata.get(rowNumber).get("company_name");
		email=testdata.get(rowNumber).get("email");
		password=testdata.get(rowNumber).get("password");
		retypepass=testdata.get(rowNumber).get("retype_password");
		signupPage.fillSignUpForm(firstname, lastname, companyname, email, password, retypepass);
		
	}

	@When("User selects Type Organization")
	public void user_selects_type_organization() {
		signupPage.selectOrganizationType();
	}
	@Given("User selects Type Individual")
	public void user_selects_type_individual() {
	    signupPage.selectIndividualType();
	}
	@Then("Display an error message right below the password field")
	public void display_an_error_message_right_below_the_password_field() {
		WebUI.verifyEquals(signupPage.getWarningMessagePassword(), expResult);
	}
	@Then("User account should get created successfully")
	public void user_account_should_get_created_successfully() {
	    WebUI.verifyEquals(signinPage.SigninTitle(),"Sign in | RISE - Ultimate Project Manager and CRM");
	}
	@Then("Display an error message right below the invaild field")
	public void display_an_error_message_right_below_the_invaild_field() {
		
//		WebUI.verifyEquals(signupPage.getWarningMessageFirstName(), expResult);
//		WebUI.verifyEquals(signupPage.getWarningMessageLastName(), "This field is required.");
//		WebUI.verifyEquals(signupPage.getWarningMessageEmail(), "This field is required.");
//		WebUI.verifyEquals(signupPage.getWarningMessagePassword(), "This field is required.");
	}
	@Then("User should get proper warning messages for every mandatory field")
	public void user_should_get_proper_warning_messages_for_every_mandatory_field() {
		WebUI.verifyEquals(signupPage.getWarningMessageFirstName(), "This field is required.");
		WebUI.verifyEquals(signupPage.getWarningMessageLastName(), "This field is required.");
		WebUI.verifyEquals(signupPage.getWarningMessageEmail(), "This field is required.");
		WebUI.verifyEquals(signupPage.getWarningMessagePassword(), "This field is required.");
	}
}
