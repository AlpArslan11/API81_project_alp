package get_requests;

import base_urls.AutomationExerciseUrlK;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertTrue;
public class get_odev1 extends AutomationExerciseUrlK {
     /*
Given
    https://automationexercise.com/api/productsList
When
    User sends a GET Request to the url
Then
    HTTP Status Code should be 200
And
    Content Type should be "text/html; charset=utf-8"
And
    Status Line should be HTTP/1.1 200 OK
And
     There must be 12 Women, 9 Men, 13 Kids usertype in products
  */

    @Test
    public void get_odev1() {
        spec.pathParam("first", "productsList");
        Response response = given().spec(spec).when().get("/{first}");
        response.then().assertThat().statusCode(200);
        assertTrue(response.contentType().contains("text/html; charset=utf-8"));
        response.then().statusLine("HTTP/1.1 200 OK");
        //response.prettyPrint();
        JsonPath jsonPath = response.jsonPath();
        SoftAssert softAssert= new SoftAssert();
        List<String> women = jsonPath.getList("products.category.usertype.findAll{it.usertype=='Women'}.usertype");
        softAssert.assertTrue( women.size() == 12,"women sayisi 12 degil");

        List<String> men = jsonPath.getList("products.category.usertype.findAll{it.usertype=='Men'}.usertype");
        softAssert.assertTrue( men.size() == 9,"Men sayisi 9 degil");

        List<String> kids = jsonPath.getList("products.category.usertype.findAll{it.usertype=='Kids'}.usertype");
        softAssert.assertTrue( kids.size() == 13,"women sayisi 13 degil");


    }
}
