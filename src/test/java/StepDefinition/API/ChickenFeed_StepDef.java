package StepDefinition.API;

import PageObjectModel.API.FeedTheChicken_POM;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ChickenFeed_StepDef {
    FeedTheChicken_POM chickenFeedPom = new FeedTheChicken_POM();
    @When("Farmer feed the chicken")
    public void farmerFeedTheChicken()
    {
        chickenFeedPom.FeedTheChicken();
    }

    @Then("The chicken now is full and happy!")
    public void theChickenNowIsFullAndHappy()
    {
        chickenFeedPom.assertChickensAreFeeded();
    }
}
