import ru.ifmo.se.pokemon.*;

public class Feebas extends Pokemon{
    public Feebas (String name, int level) {
        super(name, level);
        setType(Type.WATER);
        setMove(new Scald(), new IceBeam(), new Swagger());
        //Stats: hp, att, def, sAtt, sDeff, speed
        setStats(20, 15, 20, 10, 55, 80);
    }
}
