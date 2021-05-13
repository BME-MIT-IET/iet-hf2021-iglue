package Field;

import Coverable.Coverable;
import Coverable.TentCover;
import Coverable.NoCover;
import Game.Entity;
import Game.Manager;
import Item.Item;
import Player.Player;
import Prototype.Test;

import java.util.HashMap;

/**
 * A mezo egy tipusa. A jegtablat reprezentalja a jatekban.
 * Az eszkimok tudnak ra iglut epiteni. Lehetnek targyak belefagyva, melyeket a jatekosok ki tudnak belole asni.
 */
public class IceBlock extends Field {
    /** A mezon talalhato item **/
    protected Item item = null;
    private boolean open = false;

    /**
     * visszaadja az open adattagot
     * @return az open adattag
     */
    @Override
    public boolean isOpen() {
        return open;
    }

    /**
     * beallitja a jegtablanak az itemjet
     * @param _item a beallitott item
     */
    @Override
    public void setItem(Item _item) {
            item = _item;
    }
    @Override
    public Item getItem(){return item;}

    /**
     * isOpen settere
     * @param b a logikai ertek
     */
    public void setIsOpen(boolean b){
        open = b;
    }

    /**
     * jegtablara lep az entity
     * @param entity az entity aki a jegtablara lep
     */
    @Override
    public void Accept(Entity entity) {
        getEntites().add(entity);

        for (Entity i: entities) {
            i.Meet(entity);
        }

        int numberOfPlayers = 0;
        for(int i = 0; i < getEntites().size(); i++)
            if(getEntites().get(i) instanceof Player)
                numberOfPlayers++;

        if (getCapacity() != -1 && getCapacity() < numberOfPlayers) {
            for (Entity i: getEntites())
                i.setInWater(true);
            Cover(new NoCover());
            setLayerOfSnow(0);
        }
    }

    /**
     * Csokkenti a ho retegek szamat
     * @param n a reteggel valo csokkentes szama
     */
    @Override
    public void DecrLayerOfSnow(int n) {
        super.DecrLayerOfSnow(n);
        if(getLayerOfSnow() == 0)
            open = true;
    }

    /**
     * Visszaad egy mar kiasott targyat es eltavolitja azt a mezobol.
     * @return az item
     */
    @Override
    public Item RemoveItem(){
        if(open) {
            Item i = item;
            item = null;
            return i;
        }
        return null;
    }

    /**
     *  Beallitja az fedettseg strategiat.
     * @param c a strategy
     */
    public void Cover(Coverable c){
        cover = c;
        Manager.Update(c instanceof TentCover,this);
    }

    /**
     * az iceblock kimeneti nyelvve valo alakitasa
     * @param objects a hashmap ami tarolja az objektumokat es a hozza tartozo
     *                id-ket
     * @return a stringet
     */
    public String toString(HashMap<String,Object> objects){
        String itemString = Test.getKeyByValue(objects,this.item) == null ? "" : Test.getKeyByValue(objects,this.item);
        return "field:\n" +
                "\tID: " + Test.getKeyByValue(objects, this) + "\n" +
                "\ttype: " + "iceblock" + "\n" +
                "\tlayersOfSnow: " + this.getLayerOfSnow() + "\n" +
                "\tneighbours: " + concatNeighbours(getNeighbours(),objects) + "\n" +
                "\tlimit: " + this.getCapacity() + "\n" +
                "\topen: " + this.open + "\n" +
                "\tcover: " + this.cover.toString() + "\n" +
                "\titem: " + itemString;
    }

    /**
     * toString hivasra az osztaly nevevel ter vissza
     */
    @Override
    public String toString(){
        return "iceblock";
    }
}
