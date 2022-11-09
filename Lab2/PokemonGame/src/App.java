import ru.ifmo.se.pokemon.*;

public class App {
    public static void main(String[] args) {
        Battle battle = new Battle();

        battle.addAlly(new Budew("Pupa", 1));
        battle.addAlly(new Feebas("Lupa", 1));
        battle.addAlly(new Milotic("Chuvirla", 1));

        battle.addFoe(new Regice("Biba", 1));
        battle.addFoe(new Roselia("Boba", 1));
        battle.addFoe(new Roserade("Gadost", 1));

        battle.go();
    }
}
