package buihanh.projects.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import buihanh.keywords.WebUI;

public class DashboardPage {
	private WebDriver driver;
	// private WebUI webUI;


	private String pageText = "Dashboard";
	private String pageUrl = "/dashboard";
	private By menuDashboard = By.xpath("//span[normalize-space()='Dashboard']");
	private By menuClients = By.xpath("//span[normalize-space()='Clients']");
	private By menuProjects = By.xpath("//span[normalize-space()='Projects']");
	private By menuTasks = By.xpath("//span[normalize-space()='Tasks']");
    
    public DashboardPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

    public DashboardPage openDashboardPage() {
        WebUI.clickElement(menuDashboard);
        return this;
    }

//    public ClientPage openClientPage() {
//        WebUI.clickElement(menuClients);
//        return new ClientPage();
//    }


	public String getDoashboardTitle() {
		String DoashboardTitle = driver.getTitle();
		return DoashboardTitle;
	}

}
