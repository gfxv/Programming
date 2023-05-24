package core.managers;

import shared.exceptions.InvalidInputException;
import shared.enteties.*;
import shared.validators.*;

import java.util.Scanner;

public class MovieInputManager {

    /**
     * Movie, that we get after user input
     */
    private Movie movie;

    /**
     * Scanner
     */
    private Scanner input;

    /**
     * Script Mode (reading from terminal or from file)
     */
    private static boolean scriptMode = false;

    /**
     * Constructor
     * @throws InvalidInputException
     */
    public MovieInputManager(Scanner sc) throws InvalidInputException {
        this.input = sc;
        this.readInput();
    }

    /**
     * Reads user input and validates it
     * @throws InvalidInputException
     */
    private void readInput() throws InvalidInputException {


        if (!scriptMode) System.out.println("Please, input movie name: ");
        String movieName = input.nextLine();

        MovieNameValidator.validate(movieName);

        if (!scriptMode) System.out.println("Please, input amount of oscars(int)");
        String oscar = input.nextLine();
        OscarValidator.validate(oscar);

        if (!scriptMode) System.out.println("Please, input amount of golden palms(int)");
        String goldenPalm = input.nextLine();
        GoldenPalmValidator.validate(goldenPalm);

        if (!scriptMode) System.out.println("Please, input total box office(int/float)");
        String tbo = input.nextLine();
        TBOValidator.validate(tbo);

//        PrimitivesValidator.validate(primitives);

        if (!scriptMode) System.out.println("Please, input X Coordinate(int):");
        String coords_x = input.nextLine();
        if (!scriptMode) System.out.println("Please, input Y Coordinate(int/long):");
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
        if (!scriptMode) System.out.println("Please, input Directors' height(int/long): ");
        String dir_height = input.nextLine();

        DirectorValidator.validate(dir_name, dir_height);

        if (!scriptMode) System.out.println("Please, input name of the Location:");
        String loc_name = input.nextLine();
        if (!scriptMode) System.out.println("Please, input Loc X(int) coordinate");
        String _loc_x = input.nextLine();
        if (!scriptMode) System.out.println("Please, input Loc Y(int) coordinate");
        String _loc_y = input.nextLine();

        LocationValidator.validate(loc_name, new String[]{_loc_x, _loc_y});


        int oscarCounter = Integer.parseInt(oscar);
        long palmCounter = Long.parseLong(goldenPalm);
        float totalBox = Float.parseFloat(tbo);
        Integer x = Integer.parseInt(coords_x);
        Long y = Long.parseLong(coords_y);
        long height = Long.parseLong(dir_height);
        int loc_x = Integer.parseInt(_loc_x);
        int loc_y = Integer.parseInt(_loc_y);

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
     * Setter for script mode
     * @param mode
     */
    public static void setScriptMode(boolean mode) {
        scriptMode = mode;
    }

}
