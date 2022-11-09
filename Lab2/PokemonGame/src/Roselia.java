import ru.ifmo.se.pokemon.*;

public class Roselia extends Budew {
    public Roselia (String name, int level) {
        super(name, level);
        setType(Type.GRASS, Type.POISON);
        setMove(new Facade(), new Confide(), new GrassWhistle());
        //Stats: hp, att, def, sAtt, sDeff, speed
        setStats(50, 60, 45, 100, 80, 65);
    }
}
