package get_requests;

import base_urls.ReqresBaseUrlK;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;


public class Get02bk extends ReqresBaseUrlK {
     /*
        Given
            https://reqres.in/api/users/23
        When
            User send a GET Request to the url
        Then
            HTTP Status code should be 404
        And
            Status Line should be HTTP/1.1 404 Not Found
        And
            Server is "cloudflare"
        And
            Response body should be empty
     */

    @Test
    public void get02k() {

        spec.pathParams("first", "users", "second", 23);

        Response response = given().spec(spec).when().get("/{first}/{second}");
        // response.prettyPrint();

        //  HTTP Status code should be 404
       assertEquals(404, response.statusCode());

        //Status Line should be HTTP/1.1 404 Not Found
        assertEquals("HTTP/1.1 404 Not Found", response.statusLine());

        // Server is "cloudflare"

        assertEquals("cloudflare", response.getHeader("Server"));

        // Response body should be empty
        assertEquals(2, response.asString().replaceAll("\\s", "").length());


    }
}
