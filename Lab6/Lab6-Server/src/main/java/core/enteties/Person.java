package core.enteties;

public class Person {

    /**
     * Persons name
     */
    private String name; //Поле не может быть null, Строка не может быть пустой
    /**
     * Persons height
     */
    private long height; //Значение поля должно быть больше 0
    /**
     * Location of the person
     */
    private Location location; //Поле может быть null

    /**
     * Constructor
     * @param name
     * @param height
     * @param location
     */
    public Person(String name, long height, Location location) {
        this.name = name;
        this.height = height;
        this.location = location;
    }

    /**
     * Getter for name
     * @return name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Getter for height
     * @return height
     */
    public long getHeight() {
        return this.height;
    }

    /**
     * Getter for location
     * @return location
     */
    public Location getLocation() {
        return this.location;
    }

}
