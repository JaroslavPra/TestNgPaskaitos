package testai;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.hamcrest.Matcher.*;

import java.util.Arrays;

public class BasicApiExerciseTest {
    @Test
    public void testUser101(){

        RequestSpecification reqSpec = new RequestSpecBuilder().
                setBaseUri("https://gorest.co.in/").
                setContentType(ContentType.JSON).
                addFilters(Arrays.asList(new RequestLoggingFilter(), new ResponseLoggingFilter())).
                build();

        String userId = "101";
        String endPointUser = "/public-api/users/{userId}";

        given().
                spec(reqSpec).
                pathParam("userId", userId).
                log().all().
        when().
                get(endPointUser).
        then().
                assertThat().
                statusCode(200).
                body("data.gender", is("male")).
                body("data.status",is("inactive")).
                log().all();

    }

/*    @Test
    public void testUserCount(){

        given().pathParam("users", "page=5").
                log().all().
        when().
                get("https://gorest.co.in/public-api/users?page=5")

    }*/


}
