package get_requests;

import base_urls.RestfulBaseUlrK;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get09_K extends RestfulBaseUlrK {
     /*
        Given
            https://restful-booker.herokuapp.com/booking/91
        When
            I send GET Request to the url
        Then
            Response body should be like that;
             {
            "firstname": "Jim",
            "lastname": "Brown",
            "totalprice": 111,
            "depositpaid": true,
            "bookingdates": {
                "checkin": "2013-02-23",
                "checkout": "2014-10-23"
            },
            "additionalneeds": "Breakfast"
            }
     */

    @Test
    public void get09() {
        //--> set the url
        spec.pathParams("first","booking","second",91);

        //-->set the expected data
        Map<String,String> bookingdatesMap=new HashMap<>();
        bookingdatesMap.put("checkin","2018-01-01");
        bookingdatesMap.put("checkout","2019-01-01");

        Map<String,Object> expectedData=new HashMap<>();
        expectedData.put("firstname","Jim");
        expectedData.put("lastname","Brown");
        expectedData.put("totalprice",111);
        expectedData.put("depositpaid",true);
        expectedData.put("bookingdates",bookingdatesMap);
        expectedData.put("additionalneeds","Breakfast");

        System.out.println(expectedData);

        // send the request and get the response
      Response response =  given().spec(spec).when().get("/{first}/{second}");
      response.prettyPrint();

      //Do Assertion
        Map<String, Object> actualData = response.as(HashMap.class);
        assertEquals(expectedData.get("firstname"), (actualData.get("firstname")));
        assertEquals(expectedData.get("lastname"),(actualData.get("lastname")));
        assertEquals(expectedData.get("totalprice"),(actualData.get("totalprice")));
        assertEquals(expectedData.get("depositpaid"),(actualData.get("depositpaid")));
        assertEquals(bookingdatesMap.get("checkin"),((Map)(actualData.get("bookingdates"))).get("checkin"));
        assertEquals(bookingdatesMap.get("checkout"),((Map)(actualData.get("bookingdates"))).get("checkout"));
        assertEquals(expectedData.get("additionalneeds"),actualData.get("additionalneeds"));








    }
}
