//package core.system;
//
//import core.commands.base.Command;
//import core.exceptions.InvalidInputException;
//
//import java.util.Map;
//import java.util.NoSuchElementException;
//import java.util.Scanner;
//
//public class Client {
//
//    public Client() {
////        Invoker.init();
////        Storage.loadMovies();
//    }
//
//    public void run() {
//        Scanner scanner = new Scanner(System.in);
////        Map<String, Command> commands = Invoker.getCommands();
//
////        while(true) {
////            System.out.println("Type \"help\" to list all available commands");
////            System.out.print(">>> ");
////            String[] input;
////            try {
////                input = scanner.nextLine().split(" ");
////            } catch (NoSuchElementException ignored) {
////                break;
////            }
////            String userCommand = input[0];
////            String userArgs = "";
////            if (input.length == 2) userArgs = input[1];
////            if (commands.containsKey(userCommand)) {
////                try {
////                    History.addCommandToHistory(userCommand);
////                    commands.get(userCommand).execute(userArgs);
////                } catch (InvalidInputException e) {
////                    System.out.println(e.getMessage());
////                } catch (NoSuchElementException e) {
////                    System.out.println("Bye!");
////                    System.exit(0);
////                } catch (Exception ignore) {}
////            } else {
////                System.out.println("I don't know such command :(");
////            }
////            System.out.println();
////        }
//    }
//
//}
