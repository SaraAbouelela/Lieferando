package Lieferando.common;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeSuite;

public class RestAssuredConfiguration {

    @BeforeSuite(alwaysRun = true)
    public void configure()
    {
        RestAssured.baseURI = "http://coop.apps.symfonycasts.com";
    }

    public RequestSpecification getRequestSpecification()
    {
        return RestAssured.given().contentType(ContentType.JSON);
    }
}
