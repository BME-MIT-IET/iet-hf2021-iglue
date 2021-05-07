package Game;

import Player.Player;

/**
 * Minden szereplo ezt az interface-t valositja meg, így lesz sajat koruk, ahol ok cselekednek
 */
public interface Actor {
    /**
     * actor actor talalkozas
     * @param a
     */
    void Meet(Actor a);

    /**
     * megmondja egy aktornak hogy o a soros
     */
    void HaveTurn() throws InterruptedException;

    /**
     * actor interakcioja playerrel
     * @param p a player akivel az aktor interakcioba lep
     */
    void InteractWith(Player p);

    /**
     * actor interakcioja jegesmedvevel
     * @param p a jegesmedve akivel interaktol az aktor
     */
    void InteractWith(PolarBear p);
}
