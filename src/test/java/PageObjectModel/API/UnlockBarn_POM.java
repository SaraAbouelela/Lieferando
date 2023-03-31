package PageObjectModel.API;

import Lieferando.common.EndPoints;
import Lieferando.common.SharedValues;
import io.restassured.response.Response;
import org.testng.Assert;

import static io.restassured.RestAssured.given;

public class UnlockBarn_POM
{
	Response res;
	Boolean openedFirstTime = false;
	Boolean alreadyOpened = false;
	public Response unlockBarn()
	{
        String baseURI = "http://coop.apps.symfonycasts.com";

		res= given()
				.contentType("application/x-www-form-urlencoded; charset=utf-8")
				.header("Authorization","Bearer "+ SharedValues.getToken())
				.when()
				.post(baseURI.concat(EndPoints.UnlockBarn));
		if(!openedFirstTime)
			openedFirstTime = true;
		else
			alreadyOpened= true;
		return res;
	}

	public void assertResponseCode(int expectedStatusCode)
	{
		Assert.assertEquals(res.statusCode(),expectedStatusCode);
	}

	public void assertBarnIsUnlocked()
	{
		Assert.assertEquals(unlockBarn().statusCode(),200);
		Assert.assertEquals(unlockBarn().body().jsonPath().get("action"),"barn-unlock");
		Assert.assertTrue(unlockBarn().body().jsonPath().get("success"));
		// We need to know after how long the variables are reset
		if (openedFirstTime && !alreadyOpened)
			Assert.assertEquals(unlockBarn().body().jsonPath().get("message"),"You just unlocked your barn! Watch out for strangers!");
		else
			Assert.assertEquals(unlockBarn().body().jsonPath().get("message"),"The barn is already wide open! Let's throw a party!");

	}
	public void ValidateInvalidKeyResponse()
	{
		Assert.assertEquals(unlockBarn().body().jsonPath().get("error"),"invalid_token");
		Assert.assertEquals(unlockBarn().body().jsonPath().get("error_description"),"The access token provided is invalid");
	}
}
