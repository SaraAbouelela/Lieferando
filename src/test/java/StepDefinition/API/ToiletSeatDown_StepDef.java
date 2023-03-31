
package StepDefinition.API;

import PageObjectModel.API.ToiletSeatDown_POM;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static io.restassured.RestAssured.given;

public class ToiletSeatDown_StepDef {

    ToiletSeatDown_POM toiletSeatDownPom = new ToiletSeatDown_POM();

    @When("Farmer use the toilet and seat it down")
    public void farmerUseTheToiletAndSeatItDown()
    {
        toiletSeatDownPom.putSeatDown();

    }

    @Then("Tell him encouragement words")
    public void tellHimEncouragementWords()
    {
        toiletSeatDownPom.encouragementWords();
    }
}
