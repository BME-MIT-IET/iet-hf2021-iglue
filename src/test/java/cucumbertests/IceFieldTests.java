package cucumbertests;

import Field.IceBlock;
import Player.Eskimo;
import Player.Researcher;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.fail;

public class IceFieldTests {
    IceBlock field = new IceBlock();
    List<Eskimo> eskimos = new ArrayList<>();
    List<Researcher> researchers = new ArrayList<>();
    
    @io.cucumber.java.en.Given("^a stable ice field$")
    public void aStableIceField() {
        field.setCapacity(-1);
    }

    @io.cucumber.java.en.Given("^(\\d+) eskimos$")
    public void eskimos(int numOfEskimos) {
        for (int i = 0; i < numOfEskimos; i++) {
            eskimos.add(new Eskimo());
        }
    }

    @io.cucumber.java.en.Given("^(\\d+) researchers$")
    public void researchers(int numOfResearchers) {
        for (int i = 0; i < numOfResearchers; i++) {
            researchers.add(new Researcher());
        }
    }

    @io.cucumber.java.en.When("^the eskimos move to the field$")
    public void theEskimosMoveToTheField() {
        for (Eskimo eskimo: eskimos) {
            field.Accept(eskimo);
        }
    }

    @io.cucumber.java.en.And("^the researchers move to the field$")
    public void theResearchersMoveToTheField() {
        for (Researcher researcher: researchers) {
            field.Accept(researcher);
        }
    }

    @Then("none of the eskimos should be in water")
    public void noneOfTheEskimosShouldBeInWater() {
        for (Eskimo e : eskimos) {
            if (e.isInWater()) fail();
        }
    }

    @And("none of the researchers should be in water")
    public void noneOfTheResearchersShouldBeInWater() {
        for (Researcher r : researchers) {
            if (r.isInWater()) fail();
        }
    }


    @Given("an unstable ice field with a limit of {int} and snow level of {int}")
    public void anUnstableIceFieldWithALimitOfAndSnowLevelOf(int capacity, int snow) {
        field.setCapacity(capacity);
        field.setLayerOfSnow(snow);
    }

    @Then("all of the researchers should be in water")
    public void allOfTheResearchersShouldBeInWater() {
        for (Researcher r : researchers) {
            if (!r.isInWater()) fail();
        }
    }

    @And("the snow level should be {int}")
    public void theSnowLevelShouldBe(int snowLevel) {
        if (field.getLayerOfSnow() != snowLevel) fail();
    }
}
