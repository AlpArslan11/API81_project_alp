package get_requests;

import base_urls.RestfulBaseUlrK;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Get04bkk extends RestfulBaseUlrK {
      /*
        Given
            https://restful-booker.herokuapp.com/booking?firstname=Almedin&lastname=Alikadic
        When
            User sends get request to the URL
        Then
            Status code is 200
	  	And
	  		Among the data there should be someone whose firstname is "Almedin" and lastname is "Alikadic"

     */

    @Test
    public void get04bkk() {
        spec.pathParams("first", "booking")
                .queryParams("firstname", "Jim",
                        "lastname", "Brown");

        Response response = given().spec(spec).when().get("/{first}");
        response.prettyPrint();

        // Status code is 200
        assertEquals(200, response.statusCode());
        assertTrue(response.asString().contains("bookingid"));

    }


}
