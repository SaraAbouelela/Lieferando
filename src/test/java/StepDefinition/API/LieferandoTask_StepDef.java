package StepDefinition.API;

import PageObjectModel.API.GetToken_POM;
import Lieferando.common.EndPoints;
import Lieferando.common.SharedValues;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

import static io.restassured.RestAssured.given;

public class LieferandoTask_StepDef {
    GetToken_POM tokenPom = new GetToken_POM();
    @When("Call get token API")
    public void getTokenAPI()
    {
        tokenPom.getToken(SharedValues.getClientSecret());
    }

    @Then("Response Code is {int}")
    public void responseCodeIs(int statusCode)
    {
        tokenPom.assertResponseCode(statusCode);
    }

    @And("Token is Received successfully")
    public void tokenIsRecievedSuccessfully()
    {
        Assert.assertNotNull(SharedValues.getToken());
    }

    @And("Validate error msg")
    public void validateErrorMsg()
    {
       Assert.assertEquals(tokenPom.getToken(SharedValues.getClientSecret()).body().jsonPath().get("error"),"invalid_client");
       Assert.assertEquals(tokenPom.getToken(SharedValues.getClientSecret()).body().jsonPath().get("error_description"),"The client credentials are invalid");
    }

    @And("No Token is generated")
    public void noTokenIsGenerated()
    {
        Assert.assertNull(SharedValues.getToken());
    }
    @Given("User has {string}")
    public void userHas(String clientSecret)
    {
        SharedValues.setClientSecret(clientSecret);
    }

    @Given("Farmer has valid key")
    public void farmerHasValidKey()
    {
        String baseURI = "http://coop.apps.symfonycasts.com";

        SharedValues.setToken(given()
                .contentType("application/x-www-form-urlencoded; charset=utf-8")
                .formParam("grant_type", "client_credentials")
                .formParam("client_secret", "72eed00746ffa1ad5c909b7d0aa919db")
                .formParam("client_id", "Lieferando technical task")
                .when()
                .post(baseURI.concat(EndPoints.Get_Token)).body().jsonPath().get("access_token"));
    }
}
