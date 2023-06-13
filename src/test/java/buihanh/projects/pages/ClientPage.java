package buihanh.projects.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import buihanh.enums.FailureHandling;
import buihanh.keywords.WebUI;

public class ClientPage {
	private WebDriver driver;

	//--Add client
	private By buttonAddClient = By.xpath("//a[normalize-space()='Add client']");
	private By selectOrganizationType = By.id("type_organization");
	private By selectPersonType = By.id("type_person");
	private By inputCompanyName = By.xpath("//input[@id='company_name']");
	private By selectOwner = By.xpath("//div[@id='s2id_created_by']");
	private By inputSearchOwner = By.xpath("//div[@id='select2-drop']//input");
	private By selectFirstItemOwner = By.xpath("(//div[contains(@id,'select2-result-label')])[1]");
	private By inputAddress = By.xpath("//textarea[@id='address']");
	private By inputCity = By.xpath("//input[@id='city']");
	private By inputState = By.xpath("//input[@id='state']");
	private By inputZip = By.xpath("//input[@id='zip']");
	private By inputCountry = By.xpath("//input[@id='country']");
	private By inputPhone = By.xpath("//input[@id='phone']");
	private By inputWebsite = By.xpath("//input[@id='website']");
	private By inputVat = By.xpath("//input[@id='vat_number']");
	private By inputClientGroups = By.xpath("(//label[normalize-space()='Client groups']/following-sibling::div//input)[1]");
	private By spanFirstItemClientGroups = By.xpath("//span[@class='select2-match']");
	private By checkDisableOnlinePayment = By.id("disable_online_payment");
	private By buttonSaveOnDialog = By.xpath("//div[@id='ajaxModalContent']//button[normalize-space()='Save']");
	private By inputSearch = By.xpath("//input[@placeholder='Search']");
	private By itemClientFirstRow = By.xpath("//table[@id='client-table']//tbody/tr[1]/td[2]/a");
	private By tabClientInfo = By.xpath("//a[normalize-space()='Client info']");
	private By ownerDetail = By.xpath("//div[@id='s2id_created_by']//a[@class='select2-choice']/span[1]");
	private By radioOrganization = By.xpath("//input[@id='type_organization']");
	private By labelOnClientPage = By.xpath("//span[normalize-space()='Total clients']");
	private By mnClient = By.xpath("//span[contains(text(),'Clients')]");
	private By tabClient = By.xpath("//a[contains(text(),'Clients')]");
	private By labelClientGroups = By.xpath("//li[@class='select2-search-choice']/div");
	private By labelCompanyNameError = By.id("company_name-error");
	private By labelWrong = By.xpath("/html[1]/body[1]/div[4]/div[1]/div[1]/div[2]/form[1]/div[1]/div[1]/div[1]");
 
	//--Search
	private By dropdownClientGroups = By.xpath("(//span[normalize-space()='- Client groups -'])[1]");
	private By totalItemOnOnePage = By.xpath("//div[@id='client-table_length']//a");
	private By itemAll = By.xpath("//div[@id='select2-drop']//ul//li[normalize-space()='All']");

	//--Delete
	private By titleModel = By.id("confirmationModalTitle");
	private By contentModel = By.id("confirmationModalContent");
	private By deleteButton = By.id("confirmDeleteButton");
	private By cancelButton = By.xpath("//div[@id='confirmationModal']/div/div/div[3]/button[2]");
	private By msgDeleteSucc = By.xpath("//div[contains(text(),'The record has been deleted.')]");

	public ClientPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	public void openClientTabPage() {
		WebUI.sleep(2);
		WebUI.clickElement(mnClient);
		WebUI.sleep(2);
		WebUI.clickElement(tabClient);
		WebUI.waitForPageLoaded();
	}

	public void selectOrganizationType() {
		WebUI.clickElement(selectOrganizationType);
	}

	public void selectPersonType() {
		WebUI.clickElement(selectPersonType);
	}

	public void checkDisableOnlinePayment() {
		WebUI.clickElement(checkDisableOnlinePayment);
	}

	public void clickOnSaveButton() {
		WebUI.clickElement(buttonSaveOnDialog);
	}

	public String getWarningMessageCompany() {
		return WebUI.getTextElement(labelCompanyNameError);
	}
	public String getWarningWrong() {
		return WebUI.getTextElement(labelWrong);
	}

	public String getWarningMessageName() {
		return WebUI.getTextElement(labelCompanyNameError);
	}

	public void enterSearch(String keyword) {
		WebUI.setText(inputSearch, keyword);
	}

	public void addClient(String companyText, String searchOwnerText, String addressText, String cityText,
			String stateText, String zipText, String countryText, String phoneText, String websiteText, String vatText,
			String clientsgroupText) {
		WebUI.sleep(4);
		WebUI.clickElement(buttonAddClient);
		WebUI.setText(inputCompanyName, companyText);
		WebUI.clickElement(selectOwner);
		WebUI.setText(inputSearchOwner, searchOwnerText);
		WebUI.clickElement(selectFirstItemOwner);
		WebUI.setText(inputAddress, addressText);
		WebUI.setText(inputCity, cityText);
		WebUI.setText(inputState, stateText);
		WebUI.setText(inputZip, zipText);
		WebUI.setText(inputCountry, countryText);
		WebUI.setText(inputPhone, phoneText);
		WebUI.setText(inputWebsite, websiteText);
		WebUI.setText(inputVat, vatText);
		WebUI.setText(inputClientGroups, clientsgroupText);
		WebUI.clickElement(spanFirstItemClientGroups);

	}

	public void checkClientDetail(String companyText, String searchOwnerText, String addressText, String cityText,
			String stateText, String zipText, String countryText, String phoneText, String websiteText, String vatText,
			String clientsgroupText) {
		WebUI.sleep(1);
		WebUI.verifyElementChecked(radioOrganization, "Type off Client is not Organization");
		WebUI.verifyElementAttributeValue(inputCompanyName, "value", companyText);
		WebUI.verifyElementTextEquals(ownerDetail, searchOwnerText);
		WebUI.verifyElementTextEquals(inputAddress, phoneText, FailureHandling.CONTINUE_ON_FAILURE);
		WebUI.verifyElementAttributeValue(inputCity, "value", cityText);
		WebUI.verifyElementAttributeValue(inputState, "value", stateText);
		WebUI.verifyElementAttributeValue(inputZip, "value", zipText);
		WebUI.verifyElementAttributeValue(inputCountry, "value", countryText);
		WebUI.verifyElementAttributeValue(inputPhone, "value", phoneText);
		WebUI.verifyElementAttributeValue(inputWebsite, "value", websiteText);
		WebUI.verifyElementAttributeValue(inputVat, "value", vatText);
		WebUI.verifyElementTextEquals(labelClientGroups, clientsgroupText);
	}

	public void movetoTabClients() {
		WebUI.sleep(3);
		WebUI.clickElement(tabClientInfo);
		WebUI.waitForPageLoaded();
		WebUI.sleep(1);
	}

	public void enterDataSearchClient(String value) {
		WebUI.sleep(2);
		WebUI.moveToElement(inputSearch);
		WebUI.clearText(inputSearch);
		WebUI.setText(inputSearch, value);
		WebUI.waitForPageLoaded();
		WebUI.sleep(2);
	}

	public void selectListbox() {
		WebUI.sleep(4);
		WebUI.clickElement(totalItemOnOnePage);
		WebUI.clickElement(itemAll);
		WebUI.waitForPageLoaded();

	}

	public String getModelTitle() {
		WebUI.waitForPageLoaded();
		return WebUI.getTextElement(titleModel);
	}

	public String getModelText() {
		return WebUI.getTextElement(contentModel);
	}

	public void modaltAcceptDelete() {
		WebUI.clickElement(deleteButton);
	}

	public void modalCancelDelete() {
		WebUI.clickElement(cancelButton);
	}

	public String getMsgDeleteSucc() {
		return WebUI.getTextElement(msgDeleteSucc);
	}

}
