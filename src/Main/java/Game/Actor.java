package Game;

import Player.Player;

/**
 * Minden szereplo ezt az interface-t valositja meg, így lesz sajat koruk, ahol ok cselekednek
 */
public interface Actor {
    /**
     * actor actorral valo talalkozasa
     * @param actor az Actor akivel talalkozik
     */
    void Meet(Actor actor);

    /**
     * actor sorra kerulese
     */
    void HaveTurn() throws InterruptedException;

    /**
     * actor interakcioja playerrel
     * @param player a player akivel az aktor interakcioba lep
     */
    void InteractWith(Player player);

    /**
     * actor interakcioja jegesmedvevel
     * @param polarBear a jegesmedve akivel interaktol az aktor
     */
    void InteractWith(PolarBear polarBear);
}
