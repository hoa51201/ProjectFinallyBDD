package buihanh.runner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = "@target/fail_scenario.txt",
glue = { "buihanh/projects/steps", "buihanh/hooks" }, 
plugin = {
		"pretty", "html:target/cucumber-html-report", "html:target/cucumber-reports/cucumber-reports.html",
		"json:target/cucumber-reports/cucumber-reports.json", "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm",
		"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:" },
        dryRun=true, monochrome = true)
public class TestRunnerRerun {

}
