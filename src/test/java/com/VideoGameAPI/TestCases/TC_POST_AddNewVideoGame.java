package com.VideoGameAPI.TestCases;

import java.util.HashMap;

import org.testng.annotations.Test;

import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

import org.testng.Assert;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class TC_POST_AddNewVideoGame extends TestBaseClass{
	
	// private String id = randomString();
	private HashMap data;
	
	@Test
	public void Test_AddNewGame() {
		
		String id = randomString();
		
		data = new HashMap();
		data.put("id", id);
		data.put("name", "jugG");
		data.put("releaseDate", "2021-09-04T07:39:03.687Z");
		data.put("reviewScore", "100");
		data.put("category", "Militery");
		data.put("rating", "5");
		
		Response res=
		given()
		  .contentType("application/json")
		  .body(data)
		
		.when()
		  .post("http://localhost:8080/app/videogames")
		  
		.then()
		  .statusCode(200)
		  .header("content-length","39")
		  .log().body()  //This will display response body in console
		  .extract().response();		  
		
		String resString = res.asString();
		Assert.assertEquals(resString.contains("Record Added Successfully"), true);
		
		logger.info("****Test_AddNewGame is executed successfully****");
		
		
			
	}
	
	   @Test(priority=1)
       public void Test_AddNewGameWithDuplicateId() {		
		String id = randomString();	
		data = new HashMap();
		data.put("id", id);
		data.put("name", "jugG");
		data.put("releaseDate", "2021-09-04T07:39:03.687Z");
		data.put("reviewScore", "100");
		data.put("category", "Militery");
		data.put("rating", "5");
		
		Response res=
		given()
		  .contentType("application/json")
		  .body(data)
		
		.when()
		  .post("http://localhost:8080/app/videogames")
		  
		.then()
		  .statusCode(200)
		  .header("content-type","application/xml")
		  .log().body()  //This will display response body in console
		  .extract().response();	
		
		given()
		  .contentType("application/json")
		  .body(data)
		
		.when()
		  .post("http://localhost:8080/app/videogames")
		  
		.then()
		  .statusCode(500)
		  .header("content-type","application/xml;charset=UTF-8")
		  .log().body();  //This will display response body in console
		logger.info("****Test_AddNewGameWithDuplicateId is executed successfully****");
		
	   }
	   
	   @Test (priority=2)
	   public void test_getVideoGameOfSpecificId() {
		    String id = randomString();
		    data = new HashMap();
		    data.put("id", id);
			data.put("name", "jugG");
			data.put("releaseDate", "2021-09-04T07:39:03.687Z");
			data.put("reviewScore", "100");
			data.put("category", "Militery");
			data.put("rating", "5");
			
			given()
			  .contentType("application/json")
			  .body(data)
			
			.when()
			  .post("http://localhost:8080/app/videogames")
			
			.then()
			  .statusCode(200)
			  .header("content-type","application/xml")
			  .log().body(); // to view the response body
			logger.info("****New Video Game is added****");
			
			given()
			
			.when()
			.get("http://localhost:8080/app/videogames/"+id)
			
			.then()
			.statusCode(200)
			.body("videoGame.id", equalTo(id))  //Validation on response body. This validation is needed while response body in xml format. here "videoGame" is starting and end node of xml <videoGame> </videoGame>
			.body("videoGame.name", equalTo("jugG"))
			.header("content-type", "application/xml")
			.log().body();
			
			logger.info("*****test_getVideoGameOfSpecificId is executed successfully****");
	   
	   }
	   
	   //PUT Request
	   @Test(priority=3)
	   public void test_UpdateVideoGame() {
		    String id = randomString();
		    data = new HashMap();
		    data.put("id", id);
			data.put("name", "jugG");
			data.put("releaseDate", "2021-09-04T07:39:03.687Z");
			data.put("reviewScore", "100");
			data.put("category", "Militery");
			data.put("rating", "5");
			System.out.println(id);
			
			given()
			  .contentType("application/json")
			  .body(data)
			
			.when()
			  .post("http://localhost:8080/app/videogames")
			
			.then()
			  .statusCode(200)
			  .header("content-type","application/xml")
			  .log().body();  // to view the response body
			  
			  logger.info("****New Video Game is added****");
			
			    data = new HashMap();
			    data.put("id", id);
				data.put("name", "ThugG");
				data.put("releaseDate", "2021-09-04T07:39:03.687Z");
				data.put("reviewScore", "200");
				data.put("category", "Militery");
				data.put("rating", "5");
			
			given()
			  .contentType("application/json")
			  .body(data)
			
			.when()
              .put("http://localhost:8080/app/videogames/"+id)
              
            .then()
              .statusCode(200)
              .body("videoGame.name", equalTo("ThugG"))
              .body("videoGame.reviewScore", equalTo("200"))
			  .log().body();
			
			logger.info("****test_UpdateVideoGame is executed successfully****");
     }
	   
	   @Test(priority=4)
	   public void test_deleteVideoGame() {
		   String id = randomString();
		   data = new HashMap();
		   data.put("id", id);
			data.put("name", "jugG");
			data.put("releaseDate", "2021-09-04T07:39:03.687Z");
			data.put("reviewScore", "100");
			data.put("category", "Militery");
			data.put("rating", "5");
			
			Response res =
			given()
			  .contentType("application/json")
			  .body(data)
			  
			.when()
			  .post("http://localhost:8080/app/videogames")
			  
			.then()
			  .statusCode(200)
			  .log().body()
			  .extract().response();
			
			String resString = res.asString();
			Assert.assertEquals(resString.contains("Record Added Successfully"), true);
			  
			
			logger.info("****Video Game is added****");
			
			Response response = 
			given()
			
			.when()
			  .delete("http://localhost:8080/app/videogames/"+id)
			  
			.then()
			  .statusCode(200)
			  .header("content-type", "application/xml")
			  .header("content-length", "41")
			  .extract().response();
			
			String responseString = response.asString();
			Assert.assertEquals(responseString.contains("Record Deleted Successfully"), true);
			
			logger.info("****Video Game record is deleted****");
		   
	   }

}
