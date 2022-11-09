import ru.ifmo.se.pokemon.*;

public class Milotic extends Pokemon {
    public Milotic (String name, int level) {
        super(name, level);
        setType(Type.WATER);
        setMove(new Scald(), new IceBeam(), new Swagger(), new DisarmingVoice());
        //Stats: hp, att, def, sAtt, sDeff, speed
        setStats(95, 60, 79, 100, 125, 81);
    }
}
