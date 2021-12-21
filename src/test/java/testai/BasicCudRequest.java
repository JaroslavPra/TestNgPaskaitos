package testai;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class BasicCudRequest {

    String authToken = "a124354ae6d269d1a033d7fa84b8f21f17a067fcc50f67739781c17cc47d172f";


    @Test
    public void crudTest() {


            RequestSpecification reqSpec = new RequestSpecBuilder().
                    setBaseUri("https://gorest.co.in/").
                    setContentType(ContentType.JSON).
                    addHeader("Authorization", "Bearer " + authToken).
                    addFilters(Arrays.asList(new RequestLoggingFilter(), new ResponseLoggingFilter())).
                    build();

        String endPointUserDelete = "/public-api/users/{userId}";
        String endPostUserPost = "/public-api/users/";

        Map user = new HashMap<>();
        user.put("name", "Userfor Test");
        user.put("email", "testing0009000@email.com");
        user.put("gender", "male");
        user.put("status", "inactive");

       int userId = given().spec(reqSpec).
               body(user).
       when().
               post(endPostUserPost).
       then().
               assertThat().
               //statusCode(201).
               body("code", is(201)).
               extract().path("data.id");


       given().spec(reqSpec).
               pathParam("userId", userId).
               log().all().
       when().
               get(endPointUserDelete).
       then().
               assertThat().
               //statusCode(200).
               body("code", is(200)).
               body("data.name", is("Userfor Test")).
               body("data.email",is("testing0009000@email.com")).
               body("data.gender", is("male")).
               body("data.status", is("inactive")).
               log().all();

       given().spec(reqSpec).
               pathParam("userId", userId).
       when().
               delete(endPointUserDelete).
       then().
               log().all().
               assertThat().
               body("code", is(204));
               //statusCode(204);

       given().spec(reqSpec).
               pathParam("userId", userId).
               log().all().
       when().
               get(endPointUserDelete).
       then().
               assertThat().
               //statusCode(200).
               body("code", is(404)).
                log().all();


    }




}

