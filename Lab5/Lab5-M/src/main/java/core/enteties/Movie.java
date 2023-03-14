package core.enteties;

import java.time.LocalDate;

public class Movie {

    /**
     * Movie ID
     */
    private Long id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    /**
     * Movie Name
     */
    private String name; //Поле не может быть null, Строка не может быть пустой
    /**
     * Movie Coordinates
     */
    private Coordinates coordinates; //Поле не может быть null
    /**
     * Date of creation
     */
    private LocalDate creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    /**
     * Amount of oscars movie has
     */
    private int oscarsCount; //Значение поля должно быть больше 0
    /**
     * Amount of golden palms movie has
     */
    private long goldenPalmCount; //Значение поля должно быть больше 0
    /**
     * Total box office movie has
     */
    private float totalBoxOffice; //Значение поля должно быть больше 0
    /**
     * MPAA Rating
     */
    private MpaaRating mpaaRating; //Поле может быть null
    /**
     * Director of the movie
     */
    private Person director; //Поле может быть null


    /**
     * Constructor
     * @param name
     * @param coordinates
     * @param oscarsCount
     * @param goldenPalmCount
     * @param totalBoxOffice
     * @param mpaaRating
     * @param director
     */
    public Movie(String name, Coordinates coordinates, int oscarsCount, long goldenPalmCount,
                 float totalBoxOffice, MpaaRating mpaaRating, Person director) {

        this.id = System.currentTimeMillis();
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = LocalDate.now();
        this.oscarsCount = oscarsCount;
        this.goldenPalmCount = goldenPalmCount;
        this.totalBoxOffice = totalBoxOffice;
        this.mpaaRating = mpaaRating;
        this.director = director;

    }

    /**
     * Returns movie object as array
     * @return array of movie values
     */
    public String[] toArray() {

        long[] coords = this.coordinates.getCoords();
        Location dir_loc = this.director.getLocation();


        return new String[]{
            String.valueOf(this.id),  this.name, String.valueOf(this.creationDate), String.valueOf(this.oscarsCount),
            String.valueOf(this.goldenPalmCount), String.valueOf(this.totalBoxOffice), String.valueOf(mpaaRating),
            String.valueOf(coords[0]), String.valueOf(coords[1]), this.director.getName(),
            String.valueOf(this.director.getHeight()),
            String.valueOf(dir_loc.getCoords()[0]), String.valueOf(dir_loc.getCoords()[1]), dir_loc.getName()
        };

    }

    /**
     * Getter for ID
     * @return id
     */
    public Long getId() {
        return this.id;
    }

    /**
     * Setter for id
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Getter for oscarsCount
     * @return oscarsCount
     */
    public int getOscarsCount() {
        return this.oscarsCount;
    }

    /**
     * Setter for oscarsCount
     * @param oscar
     */
    public void setOscarsCount(int oscar) {
        this.oscarsCount = oscar;
    }

    /**
     * Getter for goldenPalmCount
     * @return goldenPalmCount
     */
    public long getGoldenPalmCount() {
        return this.goldenPalmCount;
    }

    /**
     * Getter for totalBoxOffice
     * @return totalBoxOffice
     */
    public float getTotalBoxOffice() {
        return this.totalBoxOffice;
    }

    /**
     * Getter for MPAA Rating
     * @return mpaaRating
     */
    public MpaaRating getMpaaRating() {
        return this.mpaaRating;
    }

}
