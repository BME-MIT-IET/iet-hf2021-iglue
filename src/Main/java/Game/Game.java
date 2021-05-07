package Game;

import Field.Field;

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

        SpawnFields(WIDTH, HEIGHT);

        SpawnItems(WIDTH * HEIGHT);

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
    private void SpawnFields(int WIDTH, int HEIGHT) {
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
    private void SpawnItems(int NumberOfFields) {
        //Winning Item
        int cnt = 0;
        while (cnt < 3){
            Field field = fields.get(random.nextInt(NumberOfFields));
            if (field.getItem() == null && field instanceof IceBlock ){
                WinningItem winningItem = new WinningItem(cnt);
                field.setItem(winningItem);
                winningItem.setField((IceBlock)field);
                view.AddView(new WinningItemView(winningItem));
                cnt++;
            }
        }
        // Tent
        for (int i = 0; i < NumberOfFields /15; i++ ){
            int randomField = random.nextInt(fields.size());
            Field field = fields.get(randomField);
            if (field.getItem() == null && field instanceof IceBlock ){
                Tent tent = new Tent();
                field.setItem(tent);
                tent.setField((IceBlock)field);
                view.AddView(new TentView(tent));
            }
        }
        // Swimsuit
        for (int i = 0; i < NumberOfFields /15; i++ ) {
            int randomField = random.nextInt(fields.size());
            Field field = fields.get(randomField);
            if (field.getItem() == null && field instanceof IceBlock) {
                Swimsuit swimsuit = new Swimsuit();
                field.setItem(swimsuit);
                swimsuit.setField((IceBlock)field);
                view.AddView(new SwimsuitView(swimsuit));
            }
        }
        // Spade
        for (int i = 0; i < NumberOfFields /15; i++ ){
            int randomField = random.nextInt(fields.size());
            Field field = fields.get(randomField);
            if (field.getItem() == null && field instanceof IceBlock) {
                Spade spade = new Spade(3);
                field.setItem(spade);
                spade.setField((IceBlock)field);
                view.AddView(new SpadeView(spade));
            }
        }
        // Rope
        for (int i = 0; i < NumberOfFields /15; i++ ){
            int randomField = random.nextInt(fields.size());
            Field field = fields.get(randomField);
            if (field.getItem() == null && field instanceof IceBlock) {
                Rope rope = new Rope();
                field.setItem(rope);
                rope.setField((IceBlock)field);
                view.AddView(new RopeView(rope));
            }
        }
        // Food
        for (int i = 0; i < NumberOfFields /7; i++ ){
            int randomField = random.nextInt(fields.size());
            Field field = fields.get(randomField);
            if (field.getItem() == null && field instanceof IceBlock) {
                Food food = new Food();
                field.setItem(food);
                food.setField((IceBlock)field);
                view.AddView(new FoodView(food));
            }
        }

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