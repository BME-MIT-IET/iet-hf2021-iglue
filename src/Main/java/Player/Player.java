package Player;

import ClothesEquipped.ClothesEquipped;
import ClothesEquipped.NoClothesEquipped;
import Field.Field;
import Game.*;
import Item.Item;
import Prototype.Test;
import views.GameplayFrame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * Absztrakt alaposztaly, a konkret peldanyai az Eszkimo (Eskimo) vagy a Sarkkutato (Researcher).
 */

public abstract class Player extends Entity implements OutputToString {
    private volatile int actualHealth;
    private volatile int actualWorkUnit;
    private int maxHealth;
    private List<Item> items = new ArrayList<>();
    private boolean inWater = false;
    private ClothesEquipped clothes = new NoClothesEquipped();
    private boolean endTurn = false;

    protected Player(int actualHealth, int actualWorkUnit, int maxHealth, Field field) {
        this(actualHealth,actualWorkUnit,maxHealth);
        setField(field);
    }

    protected Player(int actualHealth, int actualWorkUnit, int maxHealth) {
        this.actualHealth = actualHealth;
        this.actualWorkUnit = actualWorkUnit;
        this.maxHealth = maxHealth;
        Manager.AddPlayer(this);
    }

    public boolean isInWater() {
        return inWater;
    }

    public void setInWater(boolean inWater) {
        this.inWater = inWater;
        Manager.Update(this);
    }

    public void setEndTurn(boolean endTurn) {
        this.endTurn = endTurn;
    }

    public synchronized int getActualWorkUnit() {
        return actualWorkUnit;
    }

    public synchronized void setActualWorkUnit(int actualWorkUnit) {
        this.actualWorkUnit = actualWorkUnit;
    }

    public synchronized int getActualHealth() {
        return actualHealth;
    }

    /**
     * Növeli a jatekos munkaegysegeit eggyel
     */
    public synchronized void IncreaseWorkUnit() {
        actualWorkUnit++;
    }

    /**
     * Csokkenti a jatekos munkaegysegeit eggyel
     */
    public synchronized void DecreaseWorkUnits() {
        actualWorkUnit--;
    }

    public List<Item> getItems() {
        return items;
    }

    /**
     * @param field A mezo amire a kepesseget hasznalja majd a player (Oda epit Iglut vagy deriti fel)
     * @return Ha Researcher hivja meg akkor ter vissza fontos szammal, ha Eszkimo akkor 0 a visszateresi ertek
     */
    public abstract int UseAbility(Field field);

    /**
     * Noveli a player HP-jat 1 el
     */
    public synchronized void IncreaseHp() {
        if (actualHealth + 1 <= maxHealth) actualHealth++;
    }

    /**
     * Csokkenti a player HP-jat  1 el
     */
    public synchronized void DecreaseHp() {
        actualHealth--;
        if (actualHealth <= 0) {
            Manager.Lose();
        }
    }

    @Override
    public synchronized void Step(Field field) {
        if (actualWorkUnit > 0 && field != null && (!inWater || isWaterproof())) {
            this.field.Remove(this);
            this.field = field;
            field.Accept(this);
            DecreaseWorkUnits();
        }
    }

    /**
     * Ezzel a metodussal a player ellapatol egy kevés havat a mezorol amin all
     */
    public void Dig() {
        if (actualWorkUnit > 0)
            field.DecrLayerOfSnow(1);
        DecreaseWorkUnits();
    }

    /**
     * @param item      az item amit a player hasznalni fog, meghivja  az itemre a use()-t
     * @param target a player amin az item hasznalva lesz (pl rope)
     */
    public void UseItem(Item item, Player target) {
        if (actualWorkUnit > 0 && item != null)
            item.Use(target);
    }

    /**
     * A player ezzel a metodussal felveszi a mezojen talalhato itemet
     */
    public void PickUpItem() {
        Item item = field.RemoveItem();
        if (item != null && actualWorkUnit > 0) {
            items.add(item);
            item.setHolder(this);
            this.DecreaseWorkUnits();
        }
    }

    /**
     * @param clothes a ruha amit a player felvesz magara
     */
    public void setClothes(ClothesEquipped clothes) {
        this.clothes = clothes;
    }

    @Override
    public void Meet(Actor actor) {
        actor.InteractWith(this);
    }

    /**
     * A menedzser kozli a playerrel, hogy az o kore kovetkezik
     */
    public synchronized void HaveTurn() throws InterruptedException {
        GameplayFrame.currentPlayer = this;
        actualWorkUnit = 4;
        endTurn = false;
        while (!endTurn && actualWorkUnit > 0) {
            synchronized (this) {
                this.wait(100);
            }
        }
        // "Ez a resz majd tenyleges jatekmenetkor lesz lenyeges, tesztelesnel, amikor a prototipust hasznaljuk meg nincs ra szukseg"
        // Mondta a csapat majd szenvedett 3 orat 4 ember ennek a resznek a megirasaval

    }

    public synchronized void EndTurn() {
        endTurn = true;
        this.notifyAll();
    }

    @Override
    public void InteractWith(PolarBear polarBear) {
        this.EndTurn();
        Manager.Lose();
    }

    @Override
    public void InteractWith(Player player) {
    }

    /**
     * Elveszi a playertol az itemet amit parameterkent megkap
     *
     * @param item az item amit elvesznek
     */
    public void RemoveItem(Item item) {
        items.remove(item);
    }

    /**
     * @return visszater azzal, hogy a player vizbe van-e esve
     */
    public boolean isWaterproof() {

        return clothes.isWaterproof();
    }

    /**
     * Elfogad a player egy itemet, igy lehet neki adni pl asot
     *
     * @param item az item amit elfogad
     */
    public void AcceptItem(Item item) {
        items.add(item);
        item.setHolder(this);
    }

    /**
     * kimeneti nyelv a playerre
     *
     * @param objects a hashmap ami tarolja az objektum-id-ket es a hozza tartozo objektumokat
     * @return
     */
    public String toString(HashMap<String, Object> objects) {
        return "player:\n" +
                "\tID: " + Test.getKeyByValue(objects, this) + "\n" +
                "\tTYPE: " + this.getType() + "\n" +
                "\tactualHealth: " + this.actualHealth + "\n" +
                "\titems: " + concatItems(items, objects) + "\n" +
                "\tinWater: " + this.inWater + "\n" +
                "\tactualWorkUnit: " + this.actualWorkUnit + "\n" +
                "\tfield: " + Test.getKeyByValue(objects, this.field) + "\n" +
                "\tclothes: " + this.clothes.getType();
    }


    public abstract String getType();

    /**
     * A kimeneti nyelv miatt szukseges fuggveny, egy jatekos inventory-jabol csinal egy string-et
     * ami az item-ek id-jet felsorolja egymas utan space-el elvalasztva
     *
     * @param items   egy jatekos inventory-ja
     * @param objects a hashmap amiben tarolva vannak az id-k objectekkel parositva
     * @return az eloallitott string
     */

    private String concatItems(List<Item> items, HashMap<String, Object> objects) {
        StringBuilder result = new StringBuilder();
        List<String> strs = new ArrayList<>();
        for (Item i : items) {
            strs.add(Test.getKeyByValue(objects, i));
        }
        Collections.sort(strs);
        for (String s : strs) {
            result.append(s).append(" ");
        }
        return result.toString();
    }


}
