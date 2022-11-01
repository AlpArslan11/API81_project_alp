package get_requests;

import base_urls.ReqresBaseUrlK;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Get03bk extends ReqresBaseUrlK {
    
    /*
       Given
           https://reqres.in/api/users/2
       When
           User send GET Request to the URL
       Then
           HTTP Status Code should be 200
       And
           Response format should be “application/json”
       And
           “email” is “janet.weaver@reqres.in”,
       And
           “first_name” is "Janet"
       And
           “last_name” is "Weaver"
       And
           "text" is "To keep ReqRes free, contributions towards server costs are appreciated!"
    */

    @Test
    public void get03bk() {
        spec.pathParams("first", "users", "second", 2);

        Response response = given().spec(spec).when().get("/{first}/{second}");

        // HTTP Status Code should be 200
        assertEquals(200, response.statusCode());

        //           Response format should be “application/json”
        assertTrue(response.contentType().contains("application/json"));
        response.then().contentType(ContentType.JSON);

        //           “email” is “janet.weaver@reqres.in”,
        //--> JsonPath json = response.jsonPath();
        //--> Assert.assertEquals(json.getString("data.email"), "janet.weaver@reqres.in");
        response.then().assertThat().body("data.email", equalTo("janet.weaver@reqres.in"));

        // “first_name” is "Janet"
        response.then().assertThat().body("data.first_name", equalTo("Janet"));

        // “last_name” is "Weaver"
        response.then().assertThat().body("data.last_name", equalTo("Weaver"));

        // "text" is "To keep ReqRes free, contributions towards server costs are appreciated!"
        response.then().assertThat().body("support.text", equalTo("To keep ReqRes free, contributions towards server costs are appreciated!"));

        // response.prettyPrint();


        /*
        --> Bu şekilde çözersek yani her bir basamağı ayri ayri çözersek bu hard assertion olur. Yani eger ilk assertte doğrulayamazsa
        orda kalir. expception firlatir.
         response.
                then().
                statusCode(200).
                contentType(ContentType.JSON).
                body("data.email",equalTo("janet.weaver@reqres.in"),
                        "data.first_name",equalTo("Janet"),
                        "data.last_name",equalTo("Weaver"),
                        "support.text",equalTo("To keep ReqRes free, contributions towards server costs are appreciated!"));
    }           --> Bu sekilde yazarsak bize ilk assert hata doğrulamasa bile diğerlerinide çalıştırır. en fail verir ve hangisinde hata var gösterir.

         */




    }


}
