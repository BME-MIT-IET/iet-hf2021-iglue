package Item;

import Field.Field;
import Player.*;
import Prototype.Test;

import java.util.HashMap;

/**
 * Asot reprezentalja a jatekban, hasznalataval 2 egysegnyi havat tud eltakarítani egy munkaegység felhasznalasaval a jatekos, aki hasznalja.
 */
public class Spade extends Item {
    /**
     * @param p a player akin az item hasznalva lesz (megegyezhet a haszan,lojaval is)
     */
    private int durability;

    public Spade(int durability){
        this.durability = durability;
    }

    @Override
    public void Use(Player player) {
        this.getHolder().DecreaseWorkUnits();
        Field field = player.getField();
        field.DecrLayerOfSnow(2);
        if(durability > 0){
            if(durability - 1 == 0){
                this.getHolder().RemoveItem(this);
            }else{
            durability--;
            }
        }
    }

    /**
     * spade-nek a kimeneti nyelvre valo forditasa
     * @param objects hashmap ami tarolja a letrehozott objektumokat az id-jukkel parositva
     * @return
     */
    @Override
    public String toString(HashMap<String,Object> objects){
        return "item:\n" +
                "\tID: " + Test.getKeyByValue(objects,this) + "\n" +
                "\ttype: " + this.toString() + "\n" +
                "\tholder: " + Test.getKeyByValue(objects,this.getHolder()) + "\n" +
                "\tdurability: " + this.durability + "\n";
    }

    /**
     * toString hivasra az osztaly nevevel ter vissza
     *
     */
    @Override
    public String toString(){
        return "spade";
    }
}
