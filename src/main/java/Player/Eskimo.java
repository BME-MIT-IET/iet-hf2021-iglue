package Player;

import Coverable.IglooCover;
import Field.Field;
import Game.Game;
import views.IglooCoverView;

/**
 * Egy jatekos altal iranyitott karaktert reprezental, aki iglu epitesere kepes, annak
 * erdekeben, hogy a jatekosok at tudjak veszelni a hovihart.
 */
public class Eskimo extends Player {
    static final int MAX_HEALTH = 5;
    static final int MAX_WORK_UNIT = 4;

    public Eskimo(Field field) {
        super(MAX_HEALTH, MAX_WORK_UNIT, MAX_HEALTH, field);
    }

    public Eskimo() {
        super(MAX_HEALTH, MAX_WORK_UNIT, MAX_HEALTH);
    }

    /**
     * @param field A mezo amire a kepesseget hasznalja majd a player (Oda epit Iglut vagy deriti fel)
     * @return visszater nullaval
     */
    @Override
    public int UseAbility(Field field) {
        if (getActualWorkUnit() > 0 && !isInWater()) {
            getField().Cover(new IglooCover());
            setActualWorkUnit(getActualWorkUnit() - 1);
            Game.getInstance().getView().AddView(new IglooCoverView(this.getField()));
        }
        return -1;
    }

    @Override
    public String getType() {
        return "eskimo";
    }

    /**
     * toString hivasra az osztaly nevevel ter vissza
     */
    @Override
    public String toString() {
        return "eskimo";
    }
}
