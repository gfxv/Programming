import ru.ifmo.se.pokemon.*;


class RockSlide extends PhysicalMove {

    protected RockSlide () {
        super(Type.ROCK, 75, 90);
    }

    @Override
    protected void applyOppEffects (Pokemon p) {
        if (Math.random() <= 0.3) Effect.flinch(p);
        p.setMod(Stat.ACCURACY, 0);
    }

    @Override
    protected String describe () {
        return "имеет 30% шанс заставить цель дрогнуть";
    }

}


class Bulldoze extends PhysicalMove {
    
    protected Bulldoze () {
        super(Type.GROUND, 60, 100);
    }

    @Override
    protected void applySelfEffects (Pokemon p) {
        p.setMod(Stat.SPEED, 1);
    }

    @Override
    protected String describe () {
        return "уменьшает скорость использующего на 1";
    }

}


class Thunder extends SpecialMove {

    protected Thunder () {
        super(Type.ELECTRIC, 110, 70);
    }

    @Override
    protected void applyOppEffects (Pokemon p) {
        if (Math.random() <= 0.3) Effect.paralyze(p);
        p.setMod(Stat.ACCURACY, 0);
    }

    @Override
    protected String describe () {
        return "имеет 30% шанс парализовать цель";
    }

}


class ZapCannon extends SpecialMove {

    protected ZapCannon () {
        super(Type.ELECTRIC, 120, 50);
    }

    @Override
    protected void applyOppEffects (Pokemon p) {
        if (p.getCondition() == Status.PARALYZE) {
            double speed = p.getStat(Stat.SPEED) * 0.5;
            p.setMod(Stat.SPEED, (int) speed);
        }
    }

    @Override
    protected void applyOppDamage (Pokemon def, double damage) {
        def.setMod(Stat.HP, (int) Math.round(damage));
        Effect.paralyze(def);
    }

    @Override
    protected String describe () {
        return "у парализованного противника скорость снижается на 50%";
    }

}


class Scald extends SpecialMove {

    protected Scald () {
        super(Type.WATER, 80, 100);
    }

    @Override
    protected void applyOppEffects (Pokemon p) {
        if (Math.random() <= 0.3) Effect.burn(p);
        p.setMod(Stat.ACCURACY, 0);
    }

    @Override
    protected String describe () {
        return "имеет 30% шанс поджечь цель";
    }

}


class IceBeam extends SpecialMove {

    protected IceBeam () {
        super(Type.ICE, 90, 100);
    }

    @Override
    protected void applyOppEffects (Pokemon p) {
        if (Math.random() <= 0.1) Effect.freeze(p);
        p.setMod(Stat.ACCURACY, 0);
    }

    @Override
    protected String describe () {
        return "имеет 10% шанс заморозить цель";
    }

}


class Swagger extends StatusMove {

    protected Swagger () {
        super(Type.NORMAL, 0, 85);
    }

    @Override
    protected void applySelfEffects (Pokemon p) {
        p.setMod(Stat.ATTACK, -2);
    }

    @Override
    protected void applyOppEffects (Pokemon p) {
        Effect.confuse(p);
        p.setMod(Stat.ACCURACY, 0);
    }

    @Override
    protected String describe () {
        return "вызывает растерянность у врага и повышает атаку на 2 у использующего";
    }

}


class DisarmingVoice extends SpecialMove {

    protected DisarmingVoice () {
        super(Type.FAIRY, 40, 999999999);
    }

    @Override
    protected String describe () {
        return "игнорирует изменеие атаки и уклонения";
    }

}


class Facade extends PhysicalMove {

    protected Facade () {
        super(Type.NORMAL, 70, 100);
    }

    @Override
    protected void applyOppDamage (Pokemon def, double damage) {

        if (def.getCondition() == Status.PARALYZE || 
            def.getCondition() == Status.POISON) {

            def.setMod(Stat.HP, (int) Math.round(damage) * 2);
        }
    }

    @Override
    protected String describe () {
        return "наносит двойной урон, если противник отравлен или паразилован";
    }

}


class Confide extends StatusMove {

    protected Confide () {
        super(Type.NORMAL, 0, 0);
    }

    @Override
    protected void applyOppEffects (Pokemon p) {
        p.setMod(Stat.SPECIAL_ATTACK, 1);
    }

    @Override
    protected String describe () {
        return "понижает специальную атаку на 1";
    }
}


class GrassWhistle extends StatusMove {

    protected GrassWhistle () {
        super(Type.GRASS, 0, 55);
    }

    @Override
    protected void applyOppEffects (Pokemon p) {
        Effect.sleep(p);
        p.setMod(Stat.ACCURACY, 0);
    } 

    @Override
    protected String describe () {
        return "усыпляет противника";
    }

}


class SludgeBomb extends SpecialMove {

    protected SludgeBomb () {
        super(Type.POISON, 90, 100);
    }

    @Override
    protected void applyOppEffects (Pokemon p) {
        if (Math.random() <= 0.3) Effect.poison(p);
        p.setMod(Stat.ACCURACY, 0); 
    }

    @Override
    protected String describe () {
        return "имеет 30% шанс отравить противника";
    }
}