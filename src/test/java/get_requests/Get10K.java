package get_requests;

import base_urls.GoRestCoInBaseUrlK;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.GoRest_testData_k;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get10K extends GoRestCoInBaseUrlK {
      /*
        Given
            https://gorest.co.in/public/v1/users/2989
        When
            User send GET Request to the URL
        Then
            Status Code should be 200
        And
            Response body should be like
            /*
  {
    "meta": null,
    "data": {
        "id": 2989,
        "name": "Prayag Dhawan",
        "email": "prayag_dhawan@mccullough.net",
        "gender": "male",
        "status": "inactive"
    }
}
     */

    @Test
    public void get10K() {
        spec.pathParams("first","users","second","2989");


        GoRest_testData_k obj=new GoRest_testData_k();

        Map<String,String> dataKeyMap= obj.dataKeyMap("Prayag Dhawan","prayag_dhawan@mccullough.net","male","inactive");

        Map<String,Object> expectedData= obj.expectedDataMethod(null,dataKeyMap);

        // biz simdi expected data yi hazirladik
        System.out.println(expectedData);

        //simdi actual, yani databaseden gelen kismi alalim get request kismi
        Response response =given().spec(spec).when().get("{first}/{second}");



        response.prettyPrint(); // bakalim gelen deger ne? ardindan gelen dataayi bir map'e cevirip karsilastiricam actual ile expected i

        Map<String, Object> actualData = response.as(HashMap.class);
        System.out.println("actual data -->  " +actualData);
        assertEquals(expectedData.get("meta"),actualData.get("meta")); // once distaki metayi kontrol ettik.
        assertEquals(dataKeyMap.get("name"),  ((Map)actualData.get("data")).get("name"));// simdi data key mapten alip gelen actualdan data içindeki name'i kıyaslayalim
        assertEquals(dataKeyMap.get("email"),  ((Map)actualData.get("data")).get("email"));
        assertEquals(dataKeyMap.get("gender"), ((Map)actualData.get("data")).get("gender"));
        assertEquals(dataKeyMap.get("status"), ((Map) actualData.get("data")).get("status"));
        assertEquals(200,response.statusCode());
        //ya da
        response.then().assertThat().statusCode(200); // bu sekilde




    }



}
