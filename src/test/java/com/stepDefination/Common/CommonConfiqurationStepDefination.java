package com.stepDefination.Common;

import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import com.generics.BaseTest;
import com.generics.Pojo;
import com.pageFactory.common.SAUCE.LoginPage;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.it.Date;

public class CommonConfiqurationStepDefination {

	private Pojo objPojo;
	private String testData;
	private LoginPage objLoginPage;

	public CommonConfiqurationStepDefination(BaseTest pojo) throws Exception {
		objPojo = pojo.initializeWebEnvironment();
		objLoginPage = new LoginPage(objPojo);
	}

	@Before
	public void initializeScenario(Scenario scenario) {
		objPojo.setTestCaseID(scenario.getName());
	}

	@After
	public void tearDown(Scenario scenario) {
		((BaseTest) objPojo).tearDownWebEnvironment(scenario);
	}

	@AfterStep
	public void AddScreenshot(Scenario scenario) throws IOException {
		byte[] screenshotAs = ((TakesScreenshot) objPojo.getDriver()).getScreenshotAs(OutputType.BYTES);
		scenario.attach(screenshotAs, "image/png", scenario.getName());
	}

	/**
	 * @Method : Load URL
	 * @Description : Use Following method to Navigate to Specific URL
	 * @param : moduleName : Name of app path to be navigated
	 */
	@When("Navigate To Specific Portal : {string}")
	public void navigate_To_Specific_Portal(String string) {
		objPojo.getObjWrapperFunctions().loadBaseURL();
	}

	/**
	 * @throws IOException
	 * @Method : Load Data Provider
	 * @Description : Use Following method to Get excel data
	
	 */
	@Given("Load {string} TestData in Specific Excel Sheet{string}.")
	public void load_TestData_in_Specific_Excel_Sheet(String string, String SheetName) throws IOException {
		objPojo.getObjUtilities().loadDataProvider(SheetName);
		System.err.println("Test data Load for execution>>>>" + objPojo.getObjHashTable());
	}

	/**
	 * @ScriptName : Method Login user name and password
	 * @Description: Username and password credentials for Login
	
	 **/

	@Then("Fill {string} , {string} and click on {string} button in Sauce Login Page.")
	public void fill_and_click_on_button_in_uti_login_page(String UserName, String Password, String Login) {

		
		testData = objPojo.getObjUtilities().dpString("USERNAME");
		if (!testData.equals("")) {
			objLoginPage.setUsername(testData);

		}

		
		testData = objPojo.getObjUtilities().dpString("PASSWORD");
		if (!testData.equals("")) {
			objLoginPage.setPassword(testData);
		}

	
		By locator = By.xpath("//input[@id='login-button']");
		objPojo.getObjUtilities().logReporter("Click Login Button to get logged in. ",
				objPojo.getObjWrapperFunctions().click(locator));
		objPojo.getObjWrapperFunctions().waitFor(2);


		By locator1 = By.xpath("//div[@id='root']");
		objPojo.getObjUtilities().logReporter("Click ok on Pop after Login. ",
				objPojo.getObjWrapperFunctions().click(locator1));
		objPojo.getObjWrapperFunctions().waitFor(2);

	}

	@Then("Fill {string} , {string} and click on {string} button in Sauce Login Page using Scenario Outline.")
	public void fill_and_click_on_button_in_sauce_login_page_using_scenario_outline(String Username, String Password,
			String string3) {

		if (!Username.equals("")) {
			objLoginPage.setUsername(Username);
		}
	}
	@Then("Fill {string} , {string} Login Page using Scenario Outline.")
	public void fill_login_page_using_scenario_outline(String string, String string2, io.cucumber.datatable.DataTable dataTable) {
		if (!string.equals("")) {
			objLoginPage.setUsername(string);
		}
	}
}
