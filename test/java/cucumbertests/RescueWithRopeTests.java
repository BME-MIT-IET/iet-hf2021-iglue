package cucumbertests;

import Field.IceBlock;
import Item.Rope;
import Player.Player;
import Player.Researcher;
import Player.Eskimo;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import views.Direction;

import static org.junit.jupiter.api.Assertions.fail;

public class RescueWithRopeTests {
    IceBlock accident = new IceBlock();
    IceBlock escape = new IceBlock();
    Rope theRope = new Rope();
    Player sufferer;
    Player rescuer;

    @Given("an unstable ice field with a limit of {int}")
    public void anUnstableIceFieldWithALimitOf(int limit) {
        accident.setCapacity(0);
    }

    @And("a stable ice field to which the rescue should be made")
    public void aStableIceFieldToWhichTheRescueShouldBeMade() {
        escape.setCapacity(-1);
    }

    @And("these fields are neighboring")
    public void theseFieldsAreNeighboring() {
        accident.AddNeighbour(Direction.RIGHT, escape);
        escape.AddNeighbour(Direction.LEFT, accident);
    }

    @Given("a careless researcher")
    public void aCarelessResearcher() {
        sufferer = new Researcher();
    }

    @And("he moves to the unstable field")
    public void heMovesToTheUnstableField() {
        //accident.Accept(sufferer);
        sufferer.setField(accident);
    }

    @Given("a helpful eskimo")
    public void aHelpfulEskimo() {
        rescuer = new Eskimo();
    }

    @And("the eskimo has rope")
    public void theEskimoHasRope() {
        rescuer.AcceptItem(theRope);
    }

    @And("the eskimo is on the rescuer field")
    public void theEskimoIsOnTheRescuerField() {
        rescuer.setField(escape);
    }

    @And("the energy level of the eskimo is {int}")
    public void theEnergyLevelOfTheEskimoIs(int level) {
        rescuer.setActualWorkUnit(level);
    }

    @When("the eskimo tries to rescue his friend")
    public void theEskimoTriesToRescueHisFriend() {
        theRope.Use(sufferer);
    }

    @Then("both of them should be on the rescuer field")
    public void bothOfThemShouldBeOnTheRescuerField() {
        if (rescuer.getField() != sufferer.getField())
            fail();
    }

    @And("the energy level of the eskimo should be {int}")
    public void theEnergyLevelOfTheEskimoShouldBe(int level) {
        if (rescuer.getActualWorkUnit() != level)
            fail();
    }

    @And("an unstable ice field with a limit of {int} to which the rescue should be made")
    public void anUnstableIceFieldWithALimitOfToWhichTheRescueShouldBeMade(int limit) {
        escape.setCapacity(limit);
    }

    @Then("both of them should be in water")
    public void bothOfThemShouldBeInWater() {
        if (!rescuer.isInWater() || !sufferer.isInWater())
            fail();
    }

    @And("these fields are not neighboring")
    public void theseFieldsAreNotNeighboring() {
    }

    @Then("the eskimo should stay in water")
    public void theEskimoShouldStayInWater() {
        if (!sufferer.isInWater())
            fail();
    }
}
