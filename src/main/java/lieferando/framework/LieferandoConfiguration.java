package lieferando.framework;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeMethod;

public class LieferandoConfiguration {
	@BeforeMethod(alwaysRun = true)
	public void configure() {
		RestAssured.baseURI = "http://coop.apps.symfonycasts.com";
	}
}
