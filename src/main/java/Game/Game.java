package Game;

import Field.Field;

import java.lang.reflect.Constructor;
import java.security.SecureRandom;
import java.util.*;

import Item.*;
import Field.*;
import views.*;

/**
 * Inicializalja a jegtablakat es a jatekot.
 */
public final class Game {
    private final View view = new View();
    private static boolean gameWon = false;
    private static boolean gameLost = false;
    private static final SecureRandom random = new SecureRandom();
    public static ArrayList<Field> getFields() {
        return fields;
    }
    /**
     * A jatekteren talalhato osszes mezo
     */
    private static ArrayList<Field> fields;
    /**
     * a statikus valtozo amin hivjuk a fuggvenyeket
     */
    private static Game INSTANCE;
    public static void Reset(){
        gameWon = false;
        gameLost = false;
    }
    /**
     * @return visszakuldi a statikus valtozot ha nem ures, amugy pedig peldanyosit
     */
    public static Game getInstance(){
        // Ha meg egyszer sem keszitettunk el Game objektumot akkor most lesz elkeszitve
        if(INSTANCE == null)
            INSTANCE = new Game();
        return INSTANCE;
    }
    /**
     * Megnyert-e a jatek
     * @return hogy megnyert-e a jatek
     */
    public static boolean isGameWon(){ return gameWon; }
    /**
     * Elvesztett-e a jatek
     * @return hogy elveszett-e a jatek
     */
    public static boolean isGameLost(){ return gameLost; }
    /**
     * Ha privat a konstruktor senki sem tudja osszekeverni a dolgokat
     * es mindenki helyesen a getInstance fuggvenyt fogja hasznali
     */
    private Game(){}
    /**
     * elinditja a jatekot
     */
    public List<Object> InitMap(){
        int WIDTH = 14;
        int HEIGHT = 14;

        List<Object> newObjects = new ArrayList<>();
        fields = new ArrayList<>();

        InitFields(WIDTH, HEIGHT);

        InitItems(WIDTH * HEIGHT);

        //polarbear
        PolarBear pb = PolarBear.getInstance();
        int randField = random.nextInt(fields.size());
        pb.setField(fields.get(randField));
        view.AddView(new PolarBearView(pb));

        //weather es jegesmedve actorkent besorolasa
        Manager.getInstance().AddActor(Weather.getInstance());
        Manager.getInstance().AddActor(pb);

        for (Field f:fields) {
            newObjects.add(f);
            if (f.getItem() != null)
                newObjects.add(f.getItem());
        }
        return newObjects;
    }

    /**
     * Letrehozza a jatekter jegtablait, es beallitja rajtuk a megfelelo osszekotteteseket
     * @param WIDTH a jatekter szelessege
     * @param HEIGHT a jatekter magassaga
     */
    private void InitFields(int WIDTH, int HEIGHT) {
        // A ciklusok sorrendje nem mindegy, mivel az alapján kerülnek be a jegtablak a fields listaba
        for (int y = 0; y < HEIGHT; y++)
            for (int x = 0; x < WIDTH; x++)
                CreateField(x, y);
        ConnectFields(WIDTH, HEIGHT);
    }

    /**
     * Letrehoz egy jegtablat (ami lehet lyuk is) a megadott helyre a jatekteren,
     * majd hozzaadja a megfelelo listakhoz.
     * @param X a jegtabla X koordinataja
     * @param Y a jegtabla Y koordinataja
     */
    private void CreateField(int X, int Y) {
        double P_ICEFIELD = 0.9;
        int MAX_SNOW_THICKNESS = 3;
        int MAX_CAPACITY = 4;

        double prob = random.nextDouble();
        int thickness = random.nextInt(MAX_SNOW_THICKNESS);
        double snowProb = random.nextDouble();

        Field f;

        // f egy Hole
        if (prob > P_ICEFIELD){
            f = new Hole();

            view.AddView(new HoleView((Hole)f));
        }
        // f egy IceBlock
        else {
            f = new IceBlock();

            int capacity = random.nextInt(MAX_CAPACITY + 1);
            if (capacity >= MAX_CAPACITY) {
                f.setCapacity(-1);
            } else {
                f.setCapacity(capacity + 1);
            }


            view.AddView(new IceBlockView((IceBlock) f));
        }

        if(snowProb > 0.5)
            f.setLayerOfSnow(thickness);

        f.X = X;
        f.Y = Y;
        fields.add(f);
    }

    /**
     * A jatek mezoi kozotti szomszedsagi kapcsolatokat hozza letre.
     * @param WIDTH a jatekter szelessege mezok szamaban
     * @param HEIGHT a jatekter magassaga mezok szamaban
     */
    private void ConnectFields(int WIDTH, int HEIGHT) {
        for (int y = 0; y < HEIGHT; y++){
            for (int x = 0; x < WIDTH; x++){
                Field current = fields.get(x+y* WIDTH);
                if (y-1 >= 0)
                    current.AddNeighbour(Direction.UP, fields.get(x + (y-1) * WIDTH));
                if (y+1 < HEIGHT)
                    current.AddNeighbour(Direction.DOWN, fields.get(x + (y+1) * WIDTH));
                if (x-1 >= 0)
                    current.AddNeighbour(Direction.LEFT, fields.get(x-1 + y * WIDTH));
                if (x+1 < WIDTH)
                    current.AddNeighbour(Direction.RIGHT, fields.get(x+1 + y * WIDTH));
            }
        }
    }

    /**
     * Lehelyezi a jatekter mezoire a kulonbozo Itemeket
     * @param NumberOfFields a jatekter mezoinek a szama
     */
    private void InitItems(int NumberOfFields) {
        try {
            int i = 0;
            while (i < 3){
                Field field = fields.get(random.nextInt(NumberOfFields));
                if (field.getItem() == null && field instanceof IceBlock ){
                    SpawnSingleItem(WinningItem.class, WinningItemView.class, i, field);
                    i++;
                }
            }

            SpawnItems(NumberOfFields/15, Tent.class, TentView.class);
            SpawnItems(NumberOfFields/15, Swimsuit.class, SwimsuitView.class);
            SpawnItems(NumberOfFields/15, Spade.class, SpadeView.class, 3);
            SpawnItems(NumberOfFields/15, Rope.class, RopeView.class);
            SpawnItems(NumberOfFields/7, Food.class, FoodView.class);
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Adott mennyisegu Itemet hoz letre a jatekteren belul.
     * @param amount hany Itemet hozzon letre
     * @param ItemClass milyen Itemeket hozzon letre
     * @param ItemViewClass milyen ItemView tartozik az Itemekhez
     * @throws ReflectiveOperationException az adott Item es ItemView osztalyok nem elvart viselkedese eseten dobodik
     */
    private void SpawnItems(int amount, Class<? extends Item> ItemClass, Class<? extends IView> ItemViewClass) throws ReflectiveOperationException {
        SpawnItems(amount, ItemClass, ItemViewClass, null);
    }

    /**
     * Adott mennyisegu Itemet hoz letre a jatekteren belul, es a konstruktoruknak atad egy parametert.
     * @param amount hany Itemet hozzon letre
     * @param ItemClass milyen Itemeket hozzon letre
     * @param ItemViewClass milyen ItemView tartozik az Itemekhez
     * @param intArgOfItem az Item konstrukoktoranak a parametere (egy Integer, de a konstruktor int-kent kapja meg)
     * @throws ReflectiveOperationException az adott Item es ItemView osztalyok nem elvart viselkedese eseten dobodik
     */
    private void SpawnItems(int amount, Class<? extends Item> ItemClass, Class<? extends IView> ItemViewClass, Integer intArgOfItem) throws ReflectiveOperationException {
        for (int i = 0; i < amount; i++ ){
            int randomField = random.nextInt(fields.size());
            Field field = fields.get(randomField);
            if (field.getItem() == null && field instanceof IceBlock ){
                SpawnSingleItem(ItemClass, ItemViewClass, intArgOfItem, field);
            }
        }
    }

    /**
     * Konkret letrehozasa egy darab Itemnek a megfelelo jegtablan egy adott parameterrel.
     * @param ItemClass milyen Itemet hozzon letre
     * @param ItemViewClass miylen ItemView tartozik az Itemhez
     * @param intArgOfItem az Item konstrukoktoranak a parametere (egy Integer, de a konstruktor int-kent kapja meg)
     * @param field hol helyezze el az Itemet
     * @throws ReflectiveOperationException az adott Item es ItemView osztalyok nem elvart viselkedese eseten dobodik
     */
    private void SpawnSingleItem(Class<? extends Item> ItemClass, Class<? extends IView> ItemViewClass, Integer intArgOfItem, Field field) throws ReflectiveOperationException {
        Constructor<? extends Item> itemConstructor;
        if (intArgOfItem != null)
            itemConstructor = ItemClass.getDeclaredConstructor(int.class);
        else
            itemConstructor = ItemClass.getDeclaredConstructor();
        Constructor<? extends IView> viewConstructor = ItemViewClass.getDeclaredConstructor(ItemClass);

        Item item;
        if (intArgOfItem != null)
            item = itemConstructor.newInstance(intArgOfItem);
        else
            item = itemConstructor.newInstance();
        field.setItem(item);
        item.setField((IceBlock) field);
        view.AddView(viewConstructor.newInstance(item));
    }

    /**
     * Jatek megnyerese
     */
    public void Win(){ gameWon = true; }
    /**
     * Jatek elvesztese
     */
    public void Lose(){ gameLost = true; }
    public View getView() {
        return view;
    }
}