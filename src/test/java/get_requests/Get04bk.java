package get_requests;

import base_urls.JsonPlaceHolderBaseUrlK;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;

public class Get04bk extends JsonPlaceHolderBaseUrlK {

      /*
        Given
            https://jsonplaceholder.typicode.com/todos
        When
          I send a GET request to the Url
       And
           Accept type is "application/json"
       Then
           HTTP Status Code should be 200
       And
           Response format should be "application/json"
       And
           There should be 200 todos
       And
           "quis eius est sint explicabo" should be one of the todos title
       And
           2, 7, and 9 should be among the userIds
     */


    @Test
    public void get04bk() {
        spec.pathParam("first","todos");
        Response response = given().spec(spec).when().accept(ContentType.JSON).get("/{first}");
       response.prettyPrint();

       // ******* BU SEKİLDE DE YAPILABİLİR AMA BU HARD ASSERT HATA VERİDİGİNDE CODE DURUR. BANA KOLAY GELEN
        //HTTP Status Code should be 200
       response.then().assertThat().statusCode(200);
        //           Response format should be "application/json"
        response.then().assertThat().contentType(ContentType.JSON);
        //           There should be 200 todos
        response.then().assertThat().body("id",hasSize(200));

        //           "quis eius est sint explicabo" should be one of the todos title
        response.then().assertThat().body("title",Matchers.hasItem("quis ut nam facilis et officia qui"));
        //           2, 7, and 9 should be among the userIds

        response.then().assertThat().body("userId",Matchers.hasItems(2,7,9));


        //           HTTP Status Code should be 200
        //           Response format should be "application/json"
        //           There should be 200 todos
        //           "quis eius est sint explicabo" should be one of the todos title
        //           2, 7, and 9 should be among the userIds

        // ********* DOGRU OLAN BU SEKİLDE YAPMAK. SOFT ASSERT BU. BODY İCİNDE BİRİSİ FAİL OLSA BİLE
        // DOGRULAMAYA DEVAM EDER......
       response.then().assertThat()
                .statusCode(200).
                contentType(ContentType.JSON)
                .body("id",hasSize(200),
                        "title",hasItem("quis eius est sint explicabo")
                        ,"userId",Matchers.hasItems(2,7,9));

    }
}
