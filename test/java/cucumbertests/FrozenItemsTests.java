package cucumbertests;

import Field.IceBlock;
import Item.Item;
import Item.Food;
import Item.Swimsuit;
import Item.Tent;
import Item.Spade;
import Player.Player;
import Player.Eskimo;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.fail;

public class FrozenItemsTests {
    IceBlock field;
    Item item;
    Item anotherItem;
    Player actor;

    @Given("a stable ice block with snow level of {int}")
    public void aStableIceBlockWithSnowLevelOf(int snowLevel) {
        field = new IceBlock();
        field.setCapacity(-1);
        field.setLayerOfSnow(snowLevel);
        if (snowLevel == 0)
            field.setIsOpen(true);
    }

    @And("a spade is frozen into the ice block")
    public void aSpadeIsFrozenIntoTheIceBlock() {
        item = new Spade(5);
        field.setItem(item);
        item.setField(field);
    }

    @And("an eskimo is standing on the block")
    public void anEskimoIsStandingOnTheBlock() {
        actor = new Eskimo();
        actor.setField(field);
    }

    @When("the eskimo tries to dig out the item")
    public void theEskimoTriesToDigOutTheItem() {
        actor.PickUpItem();
    }

    @Then("the eskimo should have the item")
    public void theEskimoShouldHaveTheItem() {
        if (item.getHolder() != actor)
            fail();
        if (!actor.getItems().contains(item))
            fail();
    }

    @And("a food is frozen into the ice block")
    public void aFoodIsFrozenIntoTheIceBlock() {
        item = new Food();
        field.setItem(item);
        item.setField(field);
    }

    @And("a tent is frozen into the ice block")
    public void aTentIsFrozenIntoTheIceBlock() {
        item = new Tent();
        field.setItem(item);
        item.setField(field);
    }

    @And("a swimsuit is frozen into the ice block")
    public void aSwimsuitIsFrozenIntoTheIceBlock() {
        item = new Swimsuit();
        field.setItem(item);
        item.setField(field);
    }

    @When("a tent freezes into that block")
    public void aTentFreezesIntoThatBlock() {
        anotherItem = new Tent();
        field.setItem(anotherItem);
        anotherItem.setField(field);
    }

    @Then("the tent should be in the block")
    public void theTentShouldBeInTheBlock() {
        if (field.getItem() != anotherItem)
            fail();
        if (anotherItem.getField() != field)
            fail();
    }

    @And("the swimsuit should not be in the block")
    public void theSwimsuitShouldNotBeInTheBlock() {
        if (field.getItem() == item)
            fail();
    }

    @Then("the eskimo should not have the item")
    public void theEskimoShouldNotHaveTheItem() {
        if (item.getHolder() == actor)
            fail();
        if (actor.getItems().contains(item))
            fail();
    }

    @And("an eskimo is standing on the block with energy level of {int}")
    public void anEskimoIsStandingOnTheBlockWithEnergyLevelOf(int energy) {
        actor = new Eskimo();
        actor.setField(field);
    }

    @When("the eskimo cleans the block")
    public void theEskimoCleansTheBlock() {
        while (!field.IsOpen())
            actor.Dig();
    }

    @Then("the eskimo should have the energy level of {int}")
    public void theEskimoShouldHaveTheEnergyLevelOf(int level) {
        if (actor.getActualWorkUnit() != level)
            fail();
    }
}
