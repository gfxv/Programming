package core.enteties;


import core.exceptions.InvalidInputException;
import core.validators.CoordsValidator;

public class Coordinates {

    private Integer x; //Значение поля должно быть больше -327, Поле не может быть null
    private Long y; //Поле не может быть null

    /**
     * Constructor
     * @param x X Position
     * @param y Y Position
     */
    public Coordinates(Integer x, Long y) {
        this.x = x;
        this.y = y;
    }

    public static Coordinates arrayToCoords(String[] args) throws InvalidInputException {
        CoordsValidator.validate(args[0],args[1]);
        return new Coordinates(Integer.parseInt(args[0]), Long.parseLong(args[1]));
    }

    /**
     * Getter for coords
     * @return array of coordinates [X,Y]
     */
    public long[] getCoords() {
        return new long[]{this.x, this.y};
    }

}