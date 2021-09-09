package com.VideoGameAPI.TestCases;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class TC_GET_AllVideoGames extends TestBaseClass{
	
	@Test
	public void test_getAllVideoGames()
	{
		logger.info("****Execution of test_getAllVideoGames started****");
		given()  // If there is any prerequisite
		
		.when()  //Request URL
		   .get("http://localhost:8080/app/videogames") 
		
		.then()  //Validations
		   .statusCode(200)
		   .header("content-type","application/xml")
		   .header("Transfer-Encoding", "chunked");
		logger.info("****Execution of test_getAllVideoGames executed successfully****");
  		
	}


}
