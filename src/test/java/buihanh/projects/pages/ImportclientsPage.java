package buihanh.projects.pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import buihanh.keywords.WebUI;

public class ImportclientsPage {
	private WebDriver driver;
	private By mnClients = By.xpath("//span[contains(text(),'Clients')]");
	private By buttonImport = By.partialLinkText("Import clien");
	private By inputFileupload = By.id("file-upload-dropzone");
	private By labelNameFileBy = By.xpath("form[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[2]/p[1]");
	private By buttonNext = By.xpath("//button[@id='form-next']");
	private By buttonClose = By.id("form-next");
	private By buttonUpload = By.id("form-submit");
	private By lableFileError = By.xpath("//div[@id='uploaded-file-previews']/div/div[2]/strong");
	private By modalImport = By.id("ajaxModalTitle");
	private By labelFormatError = By.xpath("//tbody/tr[6]/td[1]");

	public ImportclientsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	

	public void openImportPage() {
		WebUI.sleep(1);
		WebUI.clickElement(mnClients);
		WebUI.clickElement(buttonImport);
		WebUI.waitForPageLoaded();	
	}

	public String ImportClientsTitle() {
		String importtitile = driver.getTitle();
		return importtitile;
	}
	public String importModalTitle() {		
		return WebUI.getTextElement(modalImport);
	}
	public void handleUploadFile(String path) {
		// mở form local máy nên file là trong ổ đĩa máy tính
		WebUI.uploadFileWithLocalForm(inputFileupload, path);
	}

	public String getWarningMessageFile() {
		return WebUI.getTextElement(lableFileError);
	}
	public String getWarningMessageTemplate() {
		return WebUI.getTextElement(labelFormatError);
	}
	public void clickOnNextButton() {
		WebUI.clickElement(buttonNext);
	}

}
