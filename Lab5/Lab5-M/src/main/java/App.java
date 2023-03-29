import core.system.Client;
import core.system.Config;


public class App {

    public static void main(String[] args) {

        // Small args validation
        if (args.length > 1) {
            System.out.println("Кол-во аргументов > 1");
            System.exit(0);
        } if (args.length == 0) {
            System.out.println("Вы не передали имя файла");
            System.exit(0);
        } else {
            String newValue = args[0];
            if (newValue.endsWith(".csv")) {
                Config.setFilepath(newValue);
            }
        }

        Client client = new Client();
        client.run();
    }
}
