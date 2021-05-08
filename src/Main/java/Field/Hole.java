package Field;


import Coverable.*;
import Game.Entity;

import Item.Item;

import Prototype.Test;

import java.util.HashMap;


/**
 * A mezo egy tipusa. A jegtablak kozott levo lyukat reprezentalja a jatekban.
 * Nem lehet ra iglut epiteni. Teherbírása 0. Nem lehetnek rajta targyak.
 */
public class Hole extends Field {

    /**
     * hole implementacio miatt false
     * @return false
     */
    @Override
    public boolean IsOpen() {
        return false;
    }

    /**
     * hole implementacio miatt ures
     * @param item az item amit elfogad
     */
    @Override
    public void setItem(Item item)
    {
        // mivel a hole-on nem lehetnek targyak, nem csinal semmit
    }
    /**
     * hole-ban biztos nincs item igy null a visszateres
     * @return null
     */
    @Override
    public Item getItem(){return null;}
    /**
     * hole-ban biztos nincs item igy null a visszateres
     * @return null
     */
    @Override
    public Item RemoveItem() {
        return null;
    }

    /**
     * lyukba eses
     * @param e az entity aki a lyukba lep
     */
    @Override
    public void Accept(Entity e) {
        this.setLayerOfSnow(0);
        entities.add(e);
        e.setInWater(true);
        layerOfSnow = 0;
        for (Entity i: entities) {
            i.Meet(e);
        }
    }

    /**
     * Csokkenti a ho retegek szamat
     * @param n a reteggel valo csokkentes mennyisege
     */
    @Override
    public void DecrLayerOfSnow(int n) {
        if(n<=getLayerOfSnow()) setLayerOfSnow(getLayerOfSnow()-n);
        else{setLayerOfSnow(0);}
    }

    /**
     *  Beallitja a fedettseg strategiat.
     * @param c a strategy
     */
    public void Cover(Coverable c){ cover = new NoCover();
    }

    /**
     * hole kimeneti nyelvve valo atirasa
     * @param objects a tarolt objektumok az azonositashoz kell
     * @return a stringet
     */
    public String toString(HashMap<String,Object> objects){
        String result = "field:\n" +
                "\tID: " + Test.getKeyByValue(objects,this) + "\n" +
                "\ttype: " + "hole" + "\n" +
                "\tlayersOfSnow: " + this.getLayerOfSnow() + "\n" +
                "\tneighbours: " + concatNeighbours(getNeighbours(),objects) + "\n" +
                "\tlimit: " + this.getCapacity();
        return result;
    }

    /**
     * toString hivasra az osztaly nevevel ter vissza
     */
    @Override
    public String toString(){
        return "hole";
    }
}
