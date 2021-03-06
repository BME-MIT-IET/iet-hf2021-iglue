package Player;

import Field.Field;

/**
 * Egy jatekos altal iranyitott karaktert reprezental, aki meg tudja nezni, hogy egy szomszedos jegtabla hany jatekost bir el.
 */
public class Researcher extends Player {
    static final int MAX_HEALTH = 4;
    static final int MAX_WORK_UNIT = 4;

    public Researcher(Field field) {
        super(MAX_HEALTH, MAX_WORK_UNIT, MAX_HEALTH, field);
    }

    public Researcher() {
        super(MAX_HEALTH, MAX_WORK_UNIT, MAX_HEALTH);
    }

    /**
     * @param field A mezo amire a kepesseget hasznalja majd a player (Oda epit Iglut vagy deriti fel)
     * @return visszater a mezo teherbiro kepessegevel
     */
    @Override
    public int UseAbility(Field field) {
        if (getActualWorkUnit() > 0 && !isInWater()) {
            setActualWorkUnit(getActualWorkUnit() - 1);
            return field.getCapacity();
        }
        return -1;
    }

    @Override
    public String getType() {
        return "researcher";
    }

    /**
     * toString hivasra az osztaly nevevel ter vissza
     */
    @Override
    public String toString() {
        return "researcher";
    }
}
