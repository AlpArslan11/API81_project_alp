package get_requests;

import base_urls.ReqresBaseUrlK;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Get05bk extends ReqresBaseUrlK {

       /*
        Given
          https://reqres.in/api/unknown/3
        When
            User send a GET request to the URL
        Then
            HTTP Status Code should be 200
        And
            Response content type is “application/json; charset=utf-8”
        And
            Response body should be like;(Soft Assertion)
        {
        "data": {
            "id": 3,
            "name": "true red",
            "year": 2002,
            "color": "#BF1932",
            "pantone_value": "19-1664"
        },
        "support": {
            "url": "https://reqres.in/#support-heading",
            "text": "To keep ReqRes free, contributions towards server costs are appreciated!"
        }
}
      */

    @Test
    public void get05bk() {
        spec.pathParams("first","unknown","second",3);
        //  https://reqres.in/api/unknown/3
        //  User send a GET request to the URL
        Response response= given().spec(spec).get("/{first}/{second}");

        //  HTTP Status Code should be 200
        response.then().assertThat().statusCode(200);

        //  Response content type is “application/json; charset=utf-8”
        response.then().assertThat().contentType("application/json; charset=utf-8");
        response.prettyPrint();
        //  Response body should be like;(Soft Assertion)
        //{
        //        "data": {
        //            "id": 3,
        //            "name": "true red",
        //            "year": 2002,
        //            "color": "#BF1932",
        //            "pantone_value": "19-1664"
        //        },
        //        "support": {
        //            "url": "https://reqres.in/#support-heading",
        //            "text": "To keep ReqRes free, contributions towards server costs are appreciated!"
        //        }
        //}


            response.then().assertThat().body("data.id",equalTo(3)
            ,"data.name",equalTo("true red")
            ,"data.year",equalTo(2002)
            ,"data.color",equalTo("#BF1932")
            ,"data.pantone_value",equalTo("19-1664")
            ,"support.url",equalTo("https://reqres.in/#support-heading")
            ,"support.text",equalTo("To keep ReqRes free, contributions towards server costs are appreciated!"));

            //-->***** SOFT ASSERT DEDİGİ İCİN 2.yontem
        SoftAssert softAssert = new SoftAssert();
        JsonPath jsonPath = response.jsonPath();
        softAssert.assertEquals(response.statusCode(),200,"status code hatali");
        softAssert.assertEquals(response.contentType(),"application/json; charset=utf-8","contentType dogru degil");

        softAssert.assertEquals(jsonPath.getInt("data.id"),3,"data.id hatali");
        softAssert.assertEquals(jsonPath.get("data.name"),"true red","data name dogru degil");
        softAssert.assertEquals(jsonPath.getInt("data.year"),2002,"data.year biglisi hatali");
        softAssert.assertEquals(jsonPath.getString("data.color"),"#BF1932","data.color hatali");
        softAssert.assertEquals(jsonPath.getString("data.pantone_value"),"19-1664","data.pantone_value hatali");
        softAssert.assertEquals(jsonPath.getString("support.url"),"https://reqres.in/#support-heading");
        softAssert.assertEquals(jsonPath.getString("support.text"),"To keep ReqRes free, contributions towards server costs are appreciated!");
        softAssert.assertAll();

    }
}
