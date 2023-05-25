package shared.enteties;

import java.io.Serializable;

public class Coordinates implements Serializable {

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


    /**
     * Getter for coords
     * @return array of coordinates [X,Y]
     */
    public long[] getCoords() {
        return new long[]{this.x, this.y};
    }

}