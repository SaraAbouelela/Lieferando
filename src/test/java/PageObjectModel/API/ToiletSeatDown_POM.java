package PageObjectModel.API;

import Lieferando.common.EndPoints;
import Lieferando.common.SharedValues;
import io.restassured.response.Response;
import org.testng.Assert;
import static io.restassured.RestAssured.given;
public class ToiletSeatDown_POM
{
	Response res;
	Boolean toiletSeatDown_FirstTime = false;
	Boolean toiletSeatAlreadyDown = false;
	public Response putSeatDown()
	{
		String baseURI = "http://coop.apps.symfonycasts.com";
		res= given()
				.contentType("application/x-www-form-urlencoded; charset=utf-8")
				.header("Authorization","Bearer "+ SharedValues.getToken())
				.when()
				.post(baseURI.concat(EndPoints.ToiletSeatDown));
		if(!toiletSeatDown_FirstTime)
			toiletSeatDown_FirstTime = true;

		else
			toiletSeatAlreadyDown= true;

		return res;
	}

	public void encouragementWords() {
		Assert.assertEquals(putSeatDown().statusCode(), 200);
		Assert.assertEquals(putSeatDown().body().jsonPath().get("action"), "toiletseat-down");
		Assert.assertTrue(putSeatDown().body().jsonPath().get("success"));
		// We need to know after how long the variables are reset
		if (toiletSeatDown_FirstTime && !toiletSeatAlreadyDown)
		{
			Assert.assertEquals(putSeatDown().body().jsonPath().get("message"), "You just put the toilet seat down. You're a wonderful roommate!");
			toiletSeatAlreadyDown = true;
		}
		else
			Assert.assertEquals(putSeatDown().body().jsonPath().get("message"),"Yea, the toilet seat is already down... you slob!");
	}
}
