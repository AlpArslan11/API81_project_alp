package get_requests;

import base_urls.JsonPlaceHolderBaseUrlK;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.Jsonplaceholder_testData_K;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;


public class Get08K_testdataMethodCallIle_K extends JsonPlaceHolderBaseUrlK {

    /*
         Given
            https://jsonplaceholder.typicode.com/todos/2
        When
            I send GET Request to the URL
        Then
            Status code is 200
            And "completed" is false
            And "userId" is 1
            And "title" is "quis ut nam facilis et officia qui"
            And header "Via" is "1.1 vegur"
            And header "Server" is "cloudflare"
            {
                "userId": 1,
                "id": 2,
                "title": "quis ut nam facilis et officia qui",
                "completed": false
            }
     */


    @Test
    public void get08kk() {
        spec.pathParams("first", "todos", "second", 2);
        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.then().assertThat().statusCode(200);


        Jsonplaceholder_testData_K jsonplaceholder_testData_K = new Jsonplaceholder_testData_K();
        Map<String, Object> expectedData = jsonplaceholder_testData_K.expectedDataMethod(1, "quis ut nam facilis et officia qui", false);

        Map<String, Object> actualData = response.as(HashMap.class);


        assertEquals("id hatalli", expectedData.get("userId"), actualData.get("userId"));
        assertEquals("completed hatali", expectedData.get("completed"), actualData.get("completed"));
        assertEquals("title hatali", expectedData.get("title"), expectedData.get("title"));

    }
}
