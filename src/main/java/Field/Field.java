package Field;

import Coverable.Coverable;
import Coverable.NoCover;
import Game.Entity;

import Game.OutputToString;
import Item.Item;
import Prototype.Test;
import views.Direction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;


/**
 * Absztrakt alaposztaly. Ebbol oroklodik az IceBlock és a Hole.
 * Egy mezot reprezentál. Minden mezot boríthat ho (tobb retegben is),
 * egyes mezok csak bizonyos szamu jatekost birnak el,
 * ezt a limitet tullepve a mezorol a jatekosok a vizbe kerulnek.
 */
public abstract class Field implements OutputToString {
    /** A mezon allo playerek listaja **/
    protected List<Entity> entities = new ArrayList<>();
    /** A szomszedos mezok listaja**/
    private final List<Field> neighbours = new ArrayList<>();
    public HashMap<Direction, Field> getNeighboursWithDir() {
        return neighboursWithDir;
    }
    private final HashMap<Direction,Field> neighboursWithDir = new HashMap<>();
    public Coverable getCover(){return cover;}
    /** A mezo strategyje, alapertelmezetten minden mezo fedettlen **/
    protected Coverable cover = new NoCover();
    public boolean isInspected = false;
    /**
     * A horetegek szama a mezon
     */
    protected int layerOfSnow = 0;
    /**
     * Ennyi jatekost bir el a mezo
     */
    private int capacity = 0;
    public int X;
    public int Y;

    /**
     * neighbours adattag gettere
     * @return a szomszedos jegtablak
     */
    public List<Field> getNeighbours() { return neighbours; }

    /**
     * hozzaad a szomszedokhoz egy fieldet
     * @param e a hozzaadott szomszed
     */
    public void AddNeighbour(Direction dir, Field e) {
        neighboursWithDir.put(dir, e);
        neighbours.add(e);}

    /**
     * abstract, iceblockban van megvalositva
     * @return az open adattag
     */
    public abstract boolean isOpen();

    /**
     * Ezzel a setterrel lehet itemet adni a mezonek
     * @param item az item amit elfogad
     */
    public abstract void setItem(Item item);
    public abstract Item getItem();

    /**
     * Visszaad egy mar kiasott targyat es eltavolitja azt a mezobol.
     * @return a visszaadott item
     */
    public abstract Item RemoveItem();

    /**
     * Uj jatekos erkezik a mezore. Ha meg elbirja a mezo, akkor a jatekos ezentul ezen a mezon áll.
     * Ha nem birja el, akkor a jatekos a vizbe esik.
     * @param entity az entity aki a mezore lep
     */
    public abstract void Accept(Entity entity);

    /**
     *A parameterkent kapott jatekos elhagyja a mezot.
     * @param e az entity aki tavozik a mezorol
     */
    public void Remove(Entity e){
        entities.remove(e);
    }

    /**
     * Visszaadja a mezon tartozkodo entity-ket.
     * @return a visszaadott jatekosok
     */
    public List<Entity> getEntites() {
        return entities;
    }

    /**
     * Megnoveli a mezon levo horetegek szamat.
     */
    public void IncrLayerOfSnow(){
        layerOfSnow++;
    }

    /**
     * Csokkenti a mezon levo horetegek szamat.
     * @param n hany horeteget tavolitsunk el
     */
    public void DecrLayerOfSnow(int n) {
        if (n <= getLayerOfSnow())
            setLayerOfSnow(getLayerOfSnow()-n);
        else
            setLayerOfSnow(0);
    }

    /**
     * Visszaadja, hogy a mezo hany jatekost bir el.
     * @return kapacitas
     */
    public int getCapacity(){
       return capacity;
    }

    /**
     * Beallitja a capacity-t a megadott ertekre
     *
     */
    public void setCapacity(int Capacity) {
        capacity = Capacity;
    }

    /**
     *  Beallitja az fedettseg strategiat.
     * @param c a strategy amit beallit
     */
    public abstract void Cover(Coverable c);

    /**
     * Meghivja a strategiajanak az IsCovered() fuggvenyet,
     * vagyis megmondja, hogy fedett-e a mezo vagy sem.
     * @return fedett-e
     */
    public boolean IsCovered(){ return cover.IsCovered(); }

    /**
     * Visszater azzal, hogy megveallo-e a mezo
     *
     */
    public boolean IsBearProof(){
        return cover.IsBearProof();
    }

    /**
     * Visszater a horeteg szamaval, ami a mezon talalhato
     *
     */
    public int getLayerOfSnow() {
        return layerOfSnow;
    }

    /**
     *
     * @param LayerOfSnow beallitja a mezon levo ho mennyiseget a kapott ertekre
     */
    public void setLayerOfSnow(int LayerOfSnow) {
        layerOfSnow = LayerOfSnow;
    }

    /**
     * A kimeneti nyelv miatt szukseges fuggveny, egy field szomszedos fieldjeibol csinal egy string-et
     * ami a field-ek id-jet felsorolja egymas utan space-el elvalasztva
     * @param nbs szomszedos field-ek
     * @param objects hashmap ami tarolja az id-ket
     * @return az osszefuzott string
     */
    protected String concatNeighbours(List<Field> nbs, HashMap<String,Object> objects){
        StringBuilder result = new StringBuilder();
        List<String> strs = new ArrayList<>();
        for (Field nb: nbs ){
            strs.add(Test.getKeyByValue(objects, nb));
        }
        Collections.sort(strs);
        for(String s : strs){
            result.append(s).append(" ");
        }
        if(result.lastIndexOf(" ") >= 0)
            result.deleteCharAt(result.lastIndexOf(" "));
        return result.toString();
    }
}