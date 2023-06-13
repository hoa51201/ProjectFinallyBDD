package buihanh.runner;

import org.junit.runner.RunWith;
import org.testng.annotations.AfterSuite;

import buihanh.hooks.CucumberListener;
import buihanh.listeners.*;
import buihanh.utils.Log;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.testng.AbstractTestNGCucumberTests;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/Features", glue = {
		        "buihanh/projects/steps", "buihanh/hooks" }, 
plugin = {        		
		"pretty","html:target/cucumber-html-report",
		"html:target/cucumber-reports/cucumber-reports.html",
        "json:target/cucumber-reports/cucumber-reports.json",
        "rerun:target/fail_scenario.txt",
        "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm",
        "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"}, 
         tags = "@success or @failure",
         monochrome = true, 
		 publish = true)
public class TestRunnerForImport {
	
}
