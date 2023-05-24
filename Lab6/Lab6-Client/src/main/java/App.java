import core.system.Client;

public class App {

    public static void main(String[] args) {

        /*
        ARGS: HOST PORT
         */

        String HOST = null;
        int PORT = 0;

        if (args.length < 2) {
            System.out.println("You missed some params (You have to specify HOST and PORT)");
            System.exit(0);
        }
        try {
            HOST = args[0];
            PORT = Integer.parseInt(args[1]);
        } catch (NumberFormatException ignored) {
            System.out.println("PORT have to be integer");
            System.exit(0);
        }

        Client client = new Client(HOST, PORT);
        client.startConnection();

    }

}
