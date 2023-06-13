package buihanh.projects.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import buihanh.keywords.WebUI;

public class SignupPage {
	private WebDriver driver;

	private By linkSignup = By.xpath("//a[contains(text(),'Sign up')]");
	private By firstnameField = By.id("first_name");
	private By lastnameField = By.id("last_name");
	private By companynameField = By.id("company_name");
	private By emailField = By.id("email");
	private By passwordField = By.id("password");
	private By retypepassField = By.id("retype_password");
	private By buttonSignUp = By.id("signup-form");
	private By organizationType = By.xpath("//label[contains(text(),'Organization')]");
	private By individualType = By.xpath("//label[contains(text(),'Individual')]");
	private By labelFirstnameError = By.id("first_name-error");
	private By labelLastnameError = By.id("last_name-error");
	private By labelRetypePass = By.id("retype_password-error");
	private By labelEmailError = By.id("email-error");
	private By labelPasswordError = By.id("password-error");

	public SignupPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public String SignupTitle() {
		String signintitile = driver.getTitle();
		return signintitile;
	}

	public void enterFirstName(String firstnameText) {
		WebUI.clearText(firstnameField);
		WebUI.setText(firstnameField, firstnameText);
	}

	public void enterLastName(String lastnameText) {
		WebUI.clearText(lastnameField);
		WebUI.setText(lastnameField, lastnameText);
	}

	public void enterCompanyName(String companynameText) {
		WebUI.clearText(companynameField);
		WebUI.setText(companynameField, companynameText);
	}

	public void enterEmail(String emailText) {
		WebUI.clearText(emailField);
		WebUI.setText(emailField, emailText);
	}

	public void enterPassword(String passwordText) {
		WebUI.clearText(passwordField);
		WebUI.setText(passwordField, passwordText);
	}

	public void enterRetypePass(String retypepasswordText) {
		WebUI.clearText(retypepassField);
		WebUI.setText(retypepassField, retypepasswordText);
	}

	public void selectOrganizationType() {
		WebUI.clickElement(organizationType);
	}

	public void selectIndividualType() {
		WebUI.clickElement(individualType);
	}

	public void clickOnSignupButton() {
		WebUI.clickElement(buttonSignUp);
	}

	public String getWarningMessageEmail() {
		return WebUI.getTextElement(labelEmailError);
	}

	public String getWarningMessagePassword() {
		return WebUI.getTextElement(labelPasswordError);
	}

	public String getWarningMessageFirstName() {
		return WebUI.getTextElement(labelFirstnameError);
	}

	public String getWarningMessageLastName() {
		return WebUI.getTextElement(labelLastnameError);
	}

	public String getWarningMessageRetypePass() {
		return WebUI.getTextElement(labelRetypePass);
	}
	public void moveToSignUp() {
		WebUI.clickElement(linkSignup);
	}
	public void fillSignUpForm(String firstnameText, String lastnameText, String companynameText, String emailText, String passwordText, String retypepasswordText ) {
		WebUI.clearAndFillText(firstnameField, firstnameText);
		WebUI.clearAndFillText(lastnameField, lastnameText);
		//selectOrganizationType();
		WebUI.clearAndFillText(companynameField, companynameText);
		WebUI.clearAndFillText(emailField, emailText);
		WebUI.clearAndFillText(passwordField, passwordText);
		WebUI.clearAndFillText(retypepassField, retypepasswordText);
	}
}
