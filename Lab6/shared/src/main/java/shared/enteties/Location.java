package shared.enteties;

import java.io.Serializable;

public class Location implements Serializable {

    /**
     * X Position
     */
    private int x;
    /**
     * Y Position
     */
    private int y;
    /**
     * Name of the location
     */
    private String name; //Длина строки не должна быть больше 564, Поле может быть null

    /**
     *
     * @param x X Position
     * @param y Y Position
     * @param name Name of the location
     */
    public Location(int x, int y, String name) {
        this.x = x;
        this.y = y;
        this.name = name;
    }

    /**
     * Getter for coords
     * @return array of coordinates [X,Y]
     */
    public int[] getCoords() {
        return new int[]{this.x, this.y};
    }

    /**
     * Getter for name field
     * @return name
     */
    public String getName() {
        return this.name;
    }
}
