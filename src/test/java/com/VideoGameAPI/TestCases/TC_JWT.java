package com.VideoGameAPI.TestCases;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.util.HashMap;

import org.apache.http.HttpStatus;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class TC_JWT {
	
	/* 1. Register the user with the end point https://jobapplicationjwt.herokuapp.com/users/sign-up
	 * 2. Authenticate and generate the token https://jobapplicationjwt.herokuapp.com/users/authenticate
	 * 3. Extract the token using json path
	 * 4. Send the request with jwt token https://jobapplicationjwt.herokuapp.com/auth/webapi/all
	 */
	
	private String data = "{ \"password\": \"Guns and Bikes\", \"username\": \"John Wick\"}";
	protected String generatedToken = ""; //This would hold the generated token
	
	@BeforeClass
	public void setUp() {			 
				
		//1. To register the user		
		given()
		  .contentType("application/json")
		  .body(data)
		  
		.when()
		  .post("https://jobapplicationjwt.herokuapp.com/users/sign-up")
		  
		.then()
		  .statusCode(200);
		
		//2. Generate the token
		Response response=
		given()
		  .contentType("application/json")
		  .body(data)
		  
		.when()
		  .post("https://jobapplicationjwt.herokuapp.com/users/authenticate")
		
		.then()
		  .statusCode(200)
		  .extract().response(); //Here response is in JSON
		
		String jsonString = response.asString(); //Convert JSON response to string
		generatedToken = JsonPath.from(jsonString).get("token");  //Fetch value of token node from string response , using JsonPath
		  
		
	}
	
	@Test
	public void test_getWithJWT() {
		//1. We need to attach the header with token information along with request.
		//2. This header will have information about JWT token
		HashMap headers = new HashMap();
		headers.put("Accept", "application/json");
		headers.put("Authorization", "Bearer" +generatedToken);			     
		
		given()
		  .headers(headers)
		.when()
		  .get("https://jobapplicationjwt.herokuapp.com/auth/webapi/all")
		  
		.then()
		  .assertThat()
		  .statusCode(HttpStatus.SC_OK);
	}
	
	@Test
	public void test_getWithoutJWT() {
		//1. We need to attach the header with token information along with request.
		//2. This header will have information about JWT token
		HashMap headers = new HashMap();
		headers.put("Accept", "application/json"); 
		
		given()
		  .headers(headers)
		.when()
		  .get("https://jobapplicationjwt.herokuapp.com/auth/webapi/all")
		  
		.then()
		  .assertThat()
		  .statusCode(HttpStatus.SC_UNAUTHORIZED);
	}
	
	
	

}
