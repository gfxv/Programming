import ru.ifmo.se.pokemon.*;

public class Budew  extends Pokemon {
    public Budew (String name, int level) {
        super(name, level);
        setType(Type.GRASS, Type.POISON);
        setMove(new Facade(), new Confide());
        //Stats: hp, att, def, sAtt, sDeff, speed
        setStats(40, 30, 35, 50, 70, 55);
    }
}
