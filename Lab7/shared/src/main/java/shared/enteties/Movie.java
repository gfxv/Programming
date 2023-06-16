package shared.enteties;

import java.io.Serializable;

import java.time.LocalDate;
import java.util.UUID;

public class Movie implements Serializable, Comparable<Movie> {

    private static final long serialVersionUID = 5;

    private Long id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private LocalDate creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private int oscarsCount; //Значение поля должно быть больше 0
    private long goldenPalmCount; //Значение поля должно быть больше 0
    private float totalBoxOffice; //Значение поля должно быть больше 0
    private MpaaRating mpaaRating; //Поле может быть null
    private Person director; //Поле может быть null

    private String[] headers = {
            "id", "name", "creationDate", "oscarCount", "goldenPalmCount", "totalBoxOffice", "mpaaRating", "coords_x", "coords_y",
            "director_name", "director_height", "location_x", "location_y", "location_name"
    };


    /**
     * Constructor
     *
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

        this.id = genId();
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = LocalDate.now();
        this.oscarsCount = oscarsCount;
        this.goldenPalmCount = goldenPalmCount;
        this.totalBoxOffice = totalBoxOffice;
        this.mpaaRating = mpaaRating;
        this.director = director;
    }

    private long genId() {

        return UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;
    }

    /**
     * Returns movie object as array
     *
     * @return array of movie values
     */
    public String[] toArray() {

        long[] coords = this.coordinates.getCoords();
        Location dir_loc = this.director.getLocation();


        return new String[]{
                String.valueOf(this.id), this.name, String.valueOf(this.creationDate), String.valueOf(this.oscarsCount),
                String.valueOf(this.goldenPalmCount), String.valueOf(this.totalBoxOffice), String.valueOf(mpaaRating),
                String.valueOf(coords[0]), String.valueOf(coords[1]), this.director.getName(),
                String.valueOf(this.director.getHeight()),
                String.valueOf(dir_loc.getCoords()[0]), String.valueOf(dir_loc.getCoords()[1]), dir_loc.getName()
        };

    }

    public String getName() {
        return this.name;
    }

    /**
     * Getter for ID
     *
     * @return id
     */
    public Long getId() {
        return this.id;
    }

    /**
     * Setter for id
     *
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Getter for oscarsCount
     *
     * @return oscarsCount
     */
    public int getOscarsCount() {
        return this.oscarsCount;
    }

    /**
     * Setter for oscarsCount
     *
     * @param oscar
     */
    public void setOscarsCount(int oscar) {
        this.oscarsCount = oscar;
    }

    /**
     * Getter for goldenPalmCount
     *
     * @return goldenPalmCount
     */
    public long getGoldenPalmCount() {
        return this.goldenPalmCount;
    }

    /**
     * Getter for totalBoxOffice
     *
     * @return totalBoxOffice
     */
    public float getTotalBoxOffice() {
        return this.totalBoxOffice;
    }

    /**
     * Getter for MPAA Rating
     *
     * @return mpaaRating
     */
    public MpaaRating getMpaaRating() {
        return this.mpaaRating;
    }

    public String[] getHeaders() {
        return headers;
    }

    public Coordinates getCoordinates() {
        return this.coordinates;
    }

    public Person getDirector() {
        return this.director;
    }

    @Override
    public String toString() {
        String result = "";
        String[] attrs = this.toArray();
        int i = 0;
        for (String s : attrs) {
            result += headers[i] + ": " + s + "\t";
            i++;
        }
        return result;
    }

    @Override
    public int compareTo(Movie m) {
        if ((this.getGoldenPalmCount() + this.getOscarsCount()) == (m.getGoldenPalmCount() + m.getOscarsCount()))
            return 0;
        return (this.getGoldenPalmCount() + this.getOscarsCount()) >
                (m.getGoldenPalmCount() + m.getOscarsCount()) ? 1 : -1;
    }
}