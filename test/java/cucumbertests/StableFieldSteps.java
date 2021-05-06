package cucumbertests;

import Field.IceBlock;
import Player.Eskimo;
import Player.Researcher;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.fail;

public class StableFieldSteps {
    IceBlock field = new IceBlock();
    List<Eskimo> eskimos = new ArrayList<>();
    List<Researcher> researchers = new ArrayList<>();
    
    @io.cucumber.java.en.Given("^a stable ice field$")
    public void aStableIceField() {
        field.setCapacity(Integer.MAX_VALUE);
    }

    @io.cucumber.java.en.Given("^(\\d+) eskimo$")
    public void eskimo(int arg0) {
        for (int i = 0; i < arg0; i++) {
            eskimos.add(new Eskimo());
        }
    }

    @io.cucumber.java.en.Given("^(\\d+) researcher$")
    public void researcher(int arg0) {
        for (int i = 0; i < arg0; i++) {
            researchers.add(new Researcher());
        }
    }

    @io.cucumber.java.en.When("^the eskimos move to the field$")
    public void theEskimosMoveToTheField() {
        for (var eskimo: eskimos) {
            field.Accept(eskimo);
        }
    }

    @io.cucumber.java.en.And("^the researchers move to the field$")
    public void theResearchersMoveToTheField() {
        for (var researcher: researchers) {
            field.Accept(researcher);
        }
    }

    @io.cucumber.java.en.Then("^none of them should be in water$")
    public void noneOfThemShouldBeInWater() throws Exception {
        for (var e : eskimos) {
            if (e.IsInWater()) fail();
        }
        for (var r : researchers) {
            if (r.IsInWater()) fail();
        }
    }
}
