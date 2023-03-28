package core.managers;

import core.enteties.*;
import core.exceptions.InvalidInputException;
import core.validators.*;

import java.util.Scanner;

public class InputManager {

    /**
     * Movie, that we get after user input
     */
    private Movie movie;

    /**
     * Scanner
     */
    private static Scanner input;

    /**
     * Script Mode (reading from terminal or from file)
     */
    private static boolean scriptMode = false;

    /**
     * Constructor
     * @throws InvalidInputException
     */
    public InputManager () throws InvalidInputException {
        this.readInput();
    }

    /**
     * Reads user input and validates it
     * @throws InvalidInputException
     */
    private void readInput() throws InvalidInputException {


        if (input == null) {
            setScanner(new Scanner(System.in));
        }

        if (!scriptMode) System.out.println("Please, input movie name: ");
        String movieName = input.nextLine();

        MovieNameValidator.validate(movieName);

        if (!scriptMode) System.out.println("Please, input amount of oscars, amount of golden palm and total box office \n" +
                "(write 3 values, using space as separator)");
        String[] primitives = input.nextLine().split(" "); // length = 3

        PrimitivesValidator.validate(primitives);

        if (!scriptMode) System.out.println("Please, input X Coordinate:");
        String coords_x = input.nextLine();
        if (!scriptMode) System.out.println("Please, input Y Coordinate:");
        String coords_y = input.nextLine();

        CoordsValidator.validate(coords_x, coords_y);


        if (!scriptMode) {
            System.out.println("Please, choose Mpaa Rating:");
            for (MpaaRating rating : MpaaRating.values()) {
                System.out.print(rating + " ");
            }
        }

        if (!scriptMode) System.out.println();
        String mpaa = input.nextLine();

        MpaaValidator.validate(mpaa);

        if (!scriptMode) System.out.println("Please, input directors' name: ");
        String dir_name = input.nextLine();
        if (!scriptMode) System.out.println("Please, input Directors' height: ");
        String dir_height = input.nextLine();

        DirectorValidator.validate(dir_name, dir_height);

        if (!scriptMode) System.out.println("Please, input name of the Location:");
        String loc_name = input.nextLine();
        if (!scriptMode) System.out.println("Please, input X and Y");
        String[] loc_coords = input.nextLine().split(" ");

        LocationValidator.validate(loc_name, loc_coords);


        int oscarCounter = Integer.parseInt(primitives[0]);
        long palmCounter = Long.parseLong(primitives[1]);
        float totalBox = Float.parseFloat(primitives[2]);
        Integer x = Integer.parseInt(coords_x);
        Long y = Long.parseLong(coords_y);
        long height = Long.parseLong(dir_height);
        int loc_x = Integer.parseInt(loc_coords[0]);
        int loc_y = Integer.parseInt(loc_coords[1]);

        this.movie = new Movie(
                movieName, new Coordinates(x, y), oscarCounter, palmCounter, totalBox,
                MpaaRating.valueOf(mpaa), new Person(
                    dir_name, height, new Location(
                        loc_x, loc_y, loc_name
                )
            )
        );


    }

    /**
     * Getter for movie
     * @return movie
     */
    public Movie getMovie() {
        return this.movie;
    }

    /**
     * Setter for scanner
     * @param scanner
     */
    public static void setScanner(Scanner scanner) {
        input = scanner;
    }

    /**
     * Setter for script mode
     * @param mode
     */
    public static void setScriptMode(boolean mode) {
        scriptMode = mode;
    }

}
