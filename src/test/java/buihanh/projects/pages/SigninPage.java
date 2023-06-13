package buihanh.projects.pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import buihanh.keywords.WebUI;

public class SigninPage {
	private WebDriver driver;
	private By emailField = By.id("email");
	private By passwordField = By.id("password");
	private By buttonSignIn = By.xpath("//button[normalize-space()='Sign in']");
	private By alertErrorMessage = By.xpath("//div[@role='alert']");
	private By linkForgotPassword = By.xpath("//a[normalize-space()='Forgot password?']");
	private By linkSignUp = By.xpath("//a[normalize-space()='Sign up']");
	private By labelEmailError = By.id("email-error");
	private By labelPasswordError = By.id("password-error");

	public SigninPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public String SigninTitle() {
		String signintitile = driver.getTitle();
		return signintitile;
	}

	public void enterEmail(String emailText) {
		WebUI.clearText(emailField);
		WebUI.setText(emailField, emailText);
	}

	public void enterPassword(String passwordText) {
		WebUI.clearText(passwordField);
		WebUI.setText(passwordField, passwordText);
	}

	public void clickOnSigninButton() {
		WebUI.clickElement(buttonSignIn);
	}

	public String getWarningMessageEmail() {
		return WebUI.getTextElement(labelEmailError);
	}

	public String getWarningMessagePassword() {
		return WebUI.getTextElement(labelPasswordError);
	}
	public String getAlertMessage() {
		return WebUI.getTextElement(alertErrorMessage);
	}

	public String Doashboard() {
		String DoashboardTitle = driver.getTitle();
		return DoashboardTitle;
	}
	public void fillSignInForm(String emailText, String passText) {
		WebUI.clearAndFillText(emailField, emailText);
		WebUI.clearAndFillText(passwordField, passText);		
	}
}
