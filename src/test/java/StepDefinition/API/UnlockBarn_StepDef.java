package StepDefinition.API;

import PageObjectModel.API.UnlockBarn_POM;
import Lieferando.common.SharedValues;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static io.restassured.RestAssured.given;

public class UnlockBarn_StepDef {
    UnlockBarn_POM unlockBarnPom = new UnlockBarn_POM();


    @When("Unlock Barn")
    public void unlockBarn()
    {
        unlockBarnPom.unlockBarn();
    }
    @Then("Barn is unlocked successfully")
    public void barnIsUnlockedSuccessfully()
    {
        unlockBarnPom.assertResponseCode(200);
        unlockBarnPom.assertBarnIsUnlocked();
    }

    @Given("Farmer has Invalid key")
    public void farmerHasInvalidKey()
    {
        SharedValues.setToken("3c996849e38a409954ba2cc6f5361743b311497v");
    }

    @Then("Validate Theft alarm ringing")
    public void validateTheftAlarmRinging()
    {
        unlockBarnPom.ValidateInvalidKeyResponse();
    }
}
