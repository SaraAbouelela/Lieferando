package PageObjectModel.API;

import Lieferando.common.EndPoints;
import Lieferando.common.SharedValues;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import static io.restassured.RestAssured.given;

public class GetToken_POM
{
	Response res;
	public Response getToken(String clientSecret)
	{
        String baseURI = "http://coop.apps.symfonycasts.com";

		 res =given()
			.contentType("application/x-www-form-urlencoded; charset=utf-8")
			.formParam("grant_type", "client_credentials")
			.formParam("client_secret", clientSecret)
			.formParam("client_id", "Lieferando technical task")
			.when()
			.post(baseURI.concat(EndPoints.Get_Token));
		JsonPath JPath = res.jsonPath();
		SharedValues.setToken(JPath.get("access_token"));
		return res;
	}
	public void assertResponseCode(int expectedStatusCode)
	{
			Assert.assertEquals(res.statusCode(),expectedStatusCode);
	}
}
