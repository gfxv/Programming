import cli.Client;

import java.io.IOException;


public class App {

    public static void main(String[] args) throws IOException, ClassNotFoundException {

//         Small args validation
//        if (args.length > 1) {
//            System.out.println("Кол-во аргументов > 1");
//            System.exit(0);
//        } if (args.length == 0) {
//            System.out.println("Вы не передали имя файла");
//            System.exit(0);
//        } else {
//            String newValue = args[0];
//            if (newValue.endsWith(".csv")) {
//                Config.setFilepath(newValue);
//            } else {
//                System.out.println("Invalid file type!");
//                System.exit(0);
//            }
//        }


        Client client = new Client();
        client.startConnection("localhost", 9999);

    }

}
