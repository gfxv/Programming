import core.system.Client;
import core.system.Config;

import java.util.Comparator;
import java.util.TreeSet;


public class App {

    public static void main(String[] args) {

        // Small args validation
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
//
//        Client client = new Client();
//        client.run();


        class A implements Comparable<A>{
            public final int field;
            A (int f) {
                this.field = f;
            }

            @Override
            public int compareTo(A a) {

                if (this.field == a.field) {
                    return 0;
                }
                return this.field > a.field ? 1 : -1;
            }
        }

        TreeSet<A> ts = new TreeSet<>();
        ts.add(new A(1));
        ts.add(new A(2));
        ts.add(new A(3));

        for (A a : ts) {
            if (a.field == 2) {
                System.out.println(123);
            }
        }
    }

    /*
    * class A {
    *   public int field;
    * }
    *
    * TreeSet<A> ts = new TreeSet<>();
    * ts.add(new A(1));
    * ts.add(new A(2));
    * ts.add(new A(3));
    *
    * for (A a : ts) {
    *   if (a.field == 4) {
    *       sout(a);
    *   }
    * }
    * */

}
