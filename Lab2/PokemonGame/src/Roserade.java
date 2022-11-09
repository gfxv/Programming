import ru.ifmo.se.pokemon.*;

public class Roserade extends Roselia {
    public Roserade (String name, int level) {
        super(name, level);
        setType(Type.GRASS, Type.POISON);
        setMove(new Facade(), new Confide(), new GrassWhistle(), new SludgeBomb());
        //Stats: hp, att, def, sAtt, sDeff, speed
        setStats(60, 70, 65, 125, 105, 90);
    } 
}
