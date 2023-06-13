package buihanh.projects.steps;
import org.openqa.selenium.WebDriver;
import buihanh.driver.DriverFactory;
import buihanh.keywords.WebUI;
import buihanh.projects.pages.ClientPage;
import buihanh.projects.pages.SigninPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class DeleteClientSteps {

	WebDriver driver;
	private SigninPage signinPage;
	private ClientPage clientPage;
	
	@Given("User logged in with email {string} and password {string}")
	private void user_logged_in_page_to_upload(String vaildEmailText, String vaildPasswordText) {
		driver = DriverFactory.getDriver();
		signinPage = new SigninPage(driver);
		signinPage.enterEmail(vaildEmailText);
		signinPage.enterPassword(vaildPasswordText);
		signinPage.clickOnSigninButton();
	}
	@Given("User navigates to client page")
	public void user_navigates_to_client_page() {
		clientPage = new ClientPage(driver);
		clientPage.openClientTabPage();
	}
	@When("User searches with value {string}")
	public void user_searches_with_value(String keyword) {
		clientPage.selectListbox();
	    clientPage.enterDataSearchClient(keyword);
	}
	@When("User click the icon Delete next to the record with name {string}")
	public void user_click_the_icon_delete_next_to_the_record_with_name(String value) {
		WebUI.clickDeleteTableByColumn(2, 9, value);
	    WebUI.verifyEquals(clientPage.getModelTitle(), "Delete?");
		WebUI.verifyEquals(clientPage.getModelText(), "Are you sure? You won't be able to undo this action!");
	}

	@When("User click Cancel button in the confirmation modal to delete record")
	public void user_click_cancel_button_in_the_confirmation_modal_to_delete_record() {
		clientPage.modalCancelDelete();
	}

	@Then("The record with name {string} is not removed from the list")
	public void the_record_with_name_is_not_removed_from_the_list(String value) {
		WebUI.checkDataTableByColumn(2, value);
	}
	@When("User click Delete button in the confirmation modal to delete record")
	public void user_click_delete_button_in_the_confirmation_modal_to_delete_record() {
		clientPage.modaltAcceptDelete();
	}

	@Then("User receives a alert message {string} of successful record deletion")
	public void user_receives_a_alert_message_of_successful_record_deletion(String message) {
		WebUI.verifyEquals(clientPage.getMsgDeleteSucc(), message);
	}
	
}
