package Item;

import Field.Field;
import Player.*;

/**
 * A kotelet reprezentalja a jatekban. Hasznalataval a jatekos kihuz magahoz egy szomszedos vizbe esett jatekost.
 */
public class Rope extends Item {
    /**
     *
     * @param player a player akin az item hasznalva lesz (megegyezhet a haszan,lojaval is)
     */
    @Override
    public void Use(Player player){
        if(player.isInWater() && player.getField().getNeighboursWithDir().containsValue(this.getHolder().getField())) {
            this.getHolder().decreaseWorkUnits();
            Field target = this.getHolder().getField();
            player.setInWater(false);
            player.setField(target);
        }
    }

    /**
     * toString hivasra az osztaly nevevel ter vissza
     *
     */
    @Override
    public String toString(){
        return "rope";
    }
}
