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
import buihanh.projects.pages.DashboardPage;
import buihanh.projects.pages.SigninPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class SigninSteps{
	WebDriver driver;
	private SigninPage signinPage;
	private DashboardPage dashboardPage;
	private String email, password, expResult;

	@Given("User navigate to url")
	public void user_navigate_to_url() {
		driver = DriverFactory.getDriver();
		signinPage = new SigninPage(driver);
	}

	@When("User enters vaild email {string} into email field")
	public void user_enters_vaild_email_into_email_field(String vaildEmailText) {
	    signinPage.enterEmail(vaildEmailText);
	}
	
	@And("User enters valid password {string} into password field")
	public void user_enters_valid_password_into_password_field(String vaildPasswordText) {
		signinPage.enterPassword(vaildPasswordText);
	}
	
	@When("User dont enter any details into fields in the signin form")
	public void user_dont_enter_any_details_into_fields_in_the_signin_form() {
		signinPage.enterEmail("");
		signinPage.enterPassword("");
	}
	
	@Then("User is redirected to the Dashboard page")
    public void theUserRedirectToDashboardPage() {
		dashboardPage = new DashboardPage(driver);
		WebUI.verifyEquals(dashboardPage.getDoashboardTitle(), "Dashboard | RISE - Ultimate Project Manager and CRM");
    }

	@When("User fill the form sign in from given sheetname {string} and rowNumber {int}")
	public void user_fill_the_form_signin_from_given_sheetname_and_rownumber(String sheetname, Integer rowNumber) throws InvalidFormatException, IOException{
		ExcelHelpers reader = new ExcelHelpers();
		String excelpath = Helpers.getCurrentDir()+"src\\test\\resources\\data\\data.xlsx";
		List<Map<String, String>> testdata = reader.getData(excelpath, sheetname);
		email = testdata.get(rowNumber).get("email");
		password = testdata.get(rowNumber).get("password");
		expResult = testdata.get(rowNumber).get("exception_result");
		signinPage.fillSignInForm(email, password);
	}
	
	@And("User clicks on Sign in button")
	public void user_clicks_on_sign_in_button() {
	    signinPage.clickOnSigninButton();
	}
	
	@Then("User receives a formatting error message right under the email field")
	public void user_receives_a_formatting_error_message_right_under_the_email_field() {
		WebUI.verifyEquals(signinPage.getWarningMessageEmail(), expResult);
	}
	
	@Then("User should get an alert message about incorrect credentials")
	public void user_should_get_an_alert_message_about_incorrect_credentials() {
		WebUI.verifyEquals(signinPage.getAlertMessage(), expResult);
	}
	
	@Then("User should get error messages for every field in the signin form")
	public void user_should_get_error_messages_for_every_field_in_the_signin_form() {
		WebUI.verifyEquals(signinPage.getWarningMessageEmail(), "This field is required.");
		WebUI.verifyEquals(signinPage.getWarningMessagePassword(), "This field is required.");
	}
	
}
