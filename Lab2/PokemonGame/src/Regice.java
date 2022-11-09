import ru.ifmo.se.pokemon.*;

public class Regice extends Pokemon{
    public Regice (String name, int level) {
        super(name, level);
        setType(Type.ICE);
        setMove(new RockSlide(), new Bulldoze(), new Thunder(), new ZapCannon());
        //Stats: hp, att, def, sAtt, sDeff, speed
        setStats(80, 50, 100, 100, 200, 50);
    }
}
