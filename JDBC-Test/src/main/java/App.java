import java.sql.*;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Stream;


public class App {


    public static void main(String[] args) throws SQLException {
        Stream.of("kilo", "mega", "giga", "tera", "peta", "exa", "zetta", "yotta")
                .filter(s -> s.length() == 4)
                .map(s -> "11" + s)
                .skip(2)
                .sorted()
                .forEachOrdered(System.out::print);
    }

}
