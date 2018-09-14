package interviewPrep;
import org.testng.annotations.Test;

import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.response.Response;

import static com.jayway.restassured.RestAssured.when;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.assertNotEqualsDeep;

import java.util.HashMap;
import java.util.Map;



public class Mock2RestQ {
	@ Test (enabled = false)
	public void getResponseBody()
	{
		Response response =
		when()
		.get("http://jsonplaceholder.typicode.com/posts");
		// returns the completed body of the response, as a string, and this is printed to the console
		System.out.println("Response Body = " +response.getBody().asString());
		//System.out.println("Value of 4th record: " + response.body().jsonPath().);	
		
	}
	//Total # of records in the response is 100
	@Test(enabled = false)
	public void getResponseBody1()
	{
		when()
		.get("http://jsonplaceholder.typicode.com/posts")
		    //.get("http://services.groupkt.com/state/get/{countryCode}/{stateCode}")
		.then()
		.contentType(ContentType.JSON)
		.body("list.size()", is(100));
	}
	@Test(enabled = false)
	public void getResponseBody2()
	{
		int sizeOfList = 
				when()
				.get("http://jsonplaceholder.typicode.com/posts")
				.then()
				.contentType(ContentType.JSON)
				.extract().path("list.size()");
		System.out.println("Total Records in response is: " + sizeOfList);
	}
	
	// Validate the value of the 4th record
	@Test (enabled = false)
	public void getNodeDetails()
	{
		when()
		.get("http://jsonplaceholder.typicode.com/posts")
		.then()
		.assertThat().body("[3].userId", is(1))
		.and()
		.assertThat().body("[3].id", is(4))
		.and()
		.assertThat().body("[3].title", equalTo("eum et est occaecati"))
		.and()
		.assertThat().body("[3].body", equalToIgnoringCase("ullam et saepe reiciendis voluptatem adipisci\nsit amet autem assumenda provident rerum culpa\nquis hic commodi nesciunt rem tenetur doloremque ipsam iure\nquis sunt voluptatem rerum illo velit"));
	}
	
	@Test (enabled = true)
	public void getNodeDetails1()
	{
		String response =
		when()
		.get("http://jsonplaceholder.typicode.com/posts")
		.then()
		.contentType(ContentType.JSON)
		.extract().response().body().asString();
		   
		   JsonPath path = new JsonPath(response);
		   System.out.println(" Json Object in result node: " +path.getMap("[3]"));
		   			
	}
	
}
