package PageObjectModel.API;

import Lieferando.common.EndPoints;
import Lieferando.common.SharedValues;
import io.restassured.response.Response;
import org.testng.Assert;

import static io.restassured.RestAssured.given;

public class FeedTheChicken_POM
{
	Response res;
	Boolean feededFirstTime = false;
	Boolean alreadyFull = false;
	public Response FeedTheChicken()
	{
		String baseURI = "http://coop.apps.symfonycasts.com";

		res= given()
				.contentType("application/x-www-form-urlencoded; charset=utf-8")
				.header("Authorization","Bearer "+ SharedValues.getToken())
				.when()
				.post(baseURI.concat(EndPoints.ChickenFeed));

		if(!feededFirstTime)
			feededFirstTime = true;

		else
			alreadyFull= true;
		return res;
	}

	public void assertChickensAreFeeded()
	{
		Assert.assertEquals(FeedTheChicken().statusCode(),200);
		Assert.assertEquals(FeedTheChicken().body().jsonPath().get("action"),"chickens-feed");
		Assert.assertTrue(FeedTheChicken().body().jsonPath().get("success"));
		// We need to know after how long the variables are reset
		if (feededFirstTime && !alreadyFull)
		{
			Assert.assertEquals(FeedTheChicken().body().jsonPath().get("message"),"Your chickens are now full and happy");
		    alreadyFull = true;
		}
		else
			Assert.assertEquals(FeedTheChicken().body().jsonPath().get("message"),"You just fed them! Do you want them to explode??");
	}
}
