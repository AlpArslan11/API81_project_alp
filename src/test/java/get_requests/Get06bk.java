package get_requests;

import base_urls.ReqresBaseUrlK;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get06bk extends ReqresBaseUrlK {

    /*
       Given
              https://reqres.in/api/unknown/
       When
            I send GET Request to the URL
       Then

            1)Status code is 200
            2)Print all pantone_values
            3)Print all ids greater than 3 on the console
              Assert that there are 3 ids greater than 3
            4)Print all names whose ids are less than 3 on the console
              Assert that the number of names whose ids are less than 3 is 2
    */

    @Test
    public void get06bk() {
        spec.pathParam("first", "unknown");
        Response response = given().spec(spec).when().get("/{first}");

        //   1)Status code is 200
        response.then().assertThat().statusCode(200);
       //   response.prettyPrint();
       // 2)Print all pantone_values
        JsonPath jsonPath = response.jsonPath();
        System.out.println(jsonPath.getList("data.pantone_value"));

        //   3)Print all ids greater than 3 on the console
        List<Integer> ids = jsonPath.getList("data.findAll{it.id>3}.id");
        System.out.println(ids);

        //   Assert that there are 3 ids greater than 3
        assertEquals("3ten büyük 3 id yok", 3, ids.size());

        //   4)Print all names whose ids are less than 3 on the console
        List<String> names = jsonPath.getList("data.findAll{it.id<3}.name");
        System.out.println(names);
        //   Assert that the number of names whose ids are less than 3 is 2
        assertEquals("3'ten kucuk 2 id yok",2,names.size());

    }
}
