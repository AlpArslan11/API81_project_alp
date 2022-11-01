package base_urls;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;

import java.net.URI;
import java.net.URISyntaxException;

public class ReqresBaseUrlK {

    protected RequestSpecification spec;

    @Before
  public void setUp() throws URISyntaxException {
      URI uri = new URI("https://reqres.in/api");
      spec = new RequestSpecBuilder().setBaseUri(uri).build();
  //  spec = new RequestSpecBuilder().setBaseUri("https://reqres.in/api").build();



  }



}
