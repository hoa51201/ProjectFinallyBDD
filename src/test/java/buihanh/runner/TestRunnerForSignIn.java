package buihanh.runner;
import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/Features/Signin.feature", glue = {
		        "buihanh/projects/steps", "buihanh/hooks" }, 
                plugin = {
                		"pretty","html:target/cucumber-html-report","summary",
                		"html:target/cucumber-reports/cucumber-reports.html",
                        "json:target/cucumber-reports/cucumber-reports.json",
                        "rerun:target/fail_scenario.txt",
                        "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm",
                        "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},
                tags = "@success",
                monochrome = true, 
				publish = true)
public class TestRunnerForSignIn {
	
}
