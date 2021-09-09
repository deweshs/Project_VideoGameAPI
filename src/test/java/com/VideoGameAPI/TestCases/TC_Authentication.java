package com.VideoGameAPI.TestCases;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class TC_Authentication extends TestBaseClass{
	
	@Test
	public void test_Authentication() 
	{
		given()
		 .auth()
		 .preemptive()
		 .basic("ToolsQA","TestPassword")
		 
		.when()
		  .get("http://restapi.demoqa.com/authentication/CheckForAuthentication/") //This URL is not working
		  
		.then() 
		  .statusCode(200);
	}
	
	/* If you don't want to pass credentials again and again:
	 * Create one setUp method in TestBaseClass
	 * 
	 * @BeforeClass
	 * public void setUp()
	 * {
	 *   RestAssured.authentication = RestAssured.preemptive().basic("username","password");
	 *   }
	 * and extends TestBaseClass to your test class
	 * TestClass extends BaseTestClass{
	 * 
	 *   public void test_Authentication()
	 *   {
	 *   
	 *     RestAssured.given()
	 *              
	 *              .when()
	 *                 .get("")
	 *                 
	 *              .then()
	 *                 .sourceCode(200);
	 *  }                 
	 */

	/*You can also define baseURI and proxy in TestBaseClass
	 * @BeforeClass
	 * public void setUp()
	 * {
	 *   RestAssured.authentication = RestAssured.preemptive().basic("username","password");
	 *   RestAssured.baseURI = "http://restapi.demoqa.com/authentication/CheckForAuthentication/";
	 *   }
	 *    and extends TestBaseClass to your test class
	 * TestClass extends BaseTestClass{
	 * 
	 *   public void test_Authentication()
	 *   {
	 *   
	 *     RestAssured.given()
	 *              
	 *              .when()
	 *                 .get()
	 *                 
	 *              .then()
	 *                 .sourceCode(200);
	 *  }     
	 * 
	 */
}
