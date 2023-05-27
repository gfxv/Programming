import core.system.Config;
import core.system.Server;

import java.io.IOException;

public class App {

    public static void main(String[] args) throws IOException {

        /*
        ARGS: HOST FILE

         */

        if (args.length < 2) {
            System.out.println("You missed some params (You have to specify host and file)");
            System.exit(0);
        } else {
            String newValue = args[1];
            if (newValue.endsWith(".csv")) {
                Config.setFilepath(newValue);
            } else {
                System.out.println("Invalid file type!");
                System.exit(0);
            }
        }

        String host = args[0];
        Server server = new Server(host);
        server.start();

    }


}
