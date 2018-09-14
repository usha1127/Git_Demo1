package interviewPrep;

import org.testng.annotations.Test;

import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.path.json.JsonPath;
import static com.jayway.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;



public class LandlordAPI_TestCases {
	/*Testcase 1
		Fetch all landlord data
		Validate for empty body and status code is 200
	*/
	@Test(enabled = false)
	public void getLandlordData1()
	{
		when()
		.get("http://localhost:8888/landlords")
		.then()
		.statusCode(200)
		.body("$", is(empty()));	
	}
	
	/*Testcase 2: Add two landlords 
	 * Validate status code is 201
	Validate firstname is <firstname>,
	lastname is <lastname>,
	trusted id <trusted>,
	apartment is empty
    Extract the <id generated> and store it
	 */
	@Test (enabled = false)
    public void postLandlord1()
    {
		LandlordTestPOJO landlord1 = new LandlordTestPOJO("Alex","Fruz",false);
		/* Converted the request body into POJO and passing the object of POJO class
		in request body
		*/
		String response =
		given()
		.contentType(ContentType.JSON)
		.body(landlord1)
		.when()
		.post("http://localhost:8888/landlords")
		.then()
		.statusCode(201)
		.contentType(ContentType.JSON)
		.body("firstName",equalTo("Alex"))
		.body("lastName",equalTo("Fruz"))
		.body("apartments", is(empty()))
		.extract().response().body().prettyPrint();
		JsonPath path = new JsonPath(response);
		
		boolean trustedResp = path.getBoolean("trusted");
		if (trustedResp == false)
		{
			System.out.println("PASS");
		}
		else
		{
			System.out.println("FAIL");
		}
		
		String id = path.get("id");
		System.out.println("Id for Landlord1:" + id);
		
		/* Test Case 4: Update a landlord data
		 * Use landlord id to which other data to be updated
		 * Validate for the new updated data success message and status code is 200
		 * Validate the updated data using testcase 3 steps
		 */
		// PUT Method (Updating the last name of Landlord1)
		LandlordTestPOJO landlord1mod = new LandlordTestPOJO("Alex","Carter",false);
		given()
		.contentType(ContentType.JSON)
		.pathParam("id", id)
		.body(landlord1mod)
		.when()
		.put("http://localhost:8888/landlords/{id}")
		.then()
		.statusCode(200)
		.extract().response().body().prettyPrint();	
    }
	
	@Test (enabled = false)
    public void postLandlord2()
    {
		LandlordTestPOJO landlord2 = new LandlordTestPOJO("Alex1","Fruz1",true);
		/* Converted the request body into POJO and passing the object of POJO class
		in request body
		*/
		String response =
		given()
		.contentType(ContentType.JSON)
		.body(landlord2)
		.when()
		.post("http://localhost:8888/landlords")
		.then()
		.statusCode(201)
		.contentType(ContentType.JSON)
		.body("firstName",equalTo("Alex1"))
		.body("lastName",equalTo("Fruz1"))
		.body("apartments", is(empty()))
		.extract().response().body().prettyPrint();
		
		JsonPath path = new JsonPath(response);
		
		boolean trustedResp = path.getBoolean("trusted");
		if (trustedResp == true)
		{
			System.out.println("PASS");
		}
		else
		{
			System.out.println("FAIL");
		}
		
		String id = path.get("id");
		System.out.println("Id for Landlord2:" + id);
		
    
	
	/* TestCase 3: Fetch single landlord data by "id"
	 * Use the generated id (response of add landlord request) to fetch particular 
	 * landlord data. 
	 * Validate corresponding data(firstname, lastname, apartments(empty array)) 
	 * and Status code is 200
	 */
	  // Same as "getLandlord by id"
	
		given()
		.pathParam("id", id)
		.when()
		.get("http://localhost:8888/landlords/{id}")
		.then()
		.statusCode(200)
		.body("firstName",equalTo("Alex1"))
		.body("lastName",equalTo("Fruz1"))
		.body("apartments", is(empty()));
		System.out.println("Validation of the (getLandord by Id) request is PASS");
		
    }
	/* TestCase 5: Delete particular landlord using id
	 * Validate for the successful deleted message and status code is 200
	 */
	
	@Test(enabled = true)
	public static void deleteLandlord()
	{
		LandlordTestPOJO landlordnew = new LandlordTestPOJO("Mark","Henderson",true);
		/* Converted the request body into POJO and passing the object of POJO class
		in request body
		*/
		String response =
		given()
		.contentType(ContentType.JSON)
		.body(landlordnew)
		.when()
		.post("http://localhost:8888/landlords")
		.then()
		.statusCode(201)
		.extract().response().body().prettyPrint();
		
		JsonPath path = new JsonPath(response);

		String id = path.get("id");
		System.out.println("Id for Landlordnew:" + id);
		
		given()
		.pathParam("id", id)
		.when()
		.delete("http://localhost:8888/landlords/{id}")
		.then()
		.statusCode(200)
		.extract().response().body().prettyPrint();
				
		
		
	}
	



}
