import core.system.Server;

import java.sql.SQLException;

public class App {

    public static void main(String[] args) throws SQLException {

//        ARGS: HOST

        if (args.length != 1) {
            System.out.println("You missed some params (You have to specify host)");
            System.exit(0);
        }

        String host = args[0];
        Server server = new Server(host);
        server.start();

    }


}
