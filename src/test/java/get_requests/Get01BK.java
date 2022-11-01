package get_requests;

import base_urls.ReqresBaseUrlK;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class Get01BK extends ReqresBaseUrlK {
     /*
        Given
            https://reqres.in/api/users/3
        When
            User sends a GET Request to the url
        Then
            HTTP Status Code should be 200
        And
            Content Type should be JSON
        And
            Status Line should be HTTP/1.1 200 OK
     */


    @Test
    public void get01bk() {
        spec.pathParams("first","users","second",3);
        Response response= given().spec(spec).when().get("/{first}/{second}");

        //   HTTP Status Code should be 200
        response.then().assertThat().statusCode(200);
        //     Content Type should be JSON
        response.then().assertThat().contentType(ContentType.JSON);
        //     Status Line should be HTTP/1.1 200 OK
        response.then().assertThat().statusLine("HTTP/1.1 200 OK");


    }
}
