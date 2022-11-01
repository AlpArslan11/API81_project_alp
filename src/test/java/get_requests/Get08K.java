package get_requests;

import base_urls.JsonPlaceHolderBaseUrlK;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Get08K extends JsonPlaceHolderBaseUrlK {

    //De-Serialization: Json datayı Java objesine çevirme
    //Serialization: Java objesini Json formatına çevirme.
    //De-Serialization: Iki türlü yapacağız.
    //Gson: Google tarafından üretilmiştir.
    //Object Mapper: Daha popüler...

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
    public void get08k() {
        spec.pathParams("first","todos","second",2);
        Response response=given().spec(spec).when().get("/{first}/{second}");

        response.then().assertThat().statusCode(200).body("userId",equalTo(1)
        ,"title",equalTo("quis ut nam facilis et officia qui")
        ,"completed",equalTo(false));

        response.header("Via").equals("1.1 vegur");
        response.header("Server").equals("cloudflare");

        response.prettyPrint();



    }
}
