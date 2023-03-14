package core.managers;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import java.io.FileWriter;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class FileManager {

    /**
     * Filename
     */
    private Path filename;

    /**
     * Constructor;
     * Creates csv file if it not exists with all necessary fields(columns)
     * @param filename
     * @throws Exception
     */
    public FileManager(String filename) throws Exception {
        this.filename = Paths.get(filename);
        if (this.getAll().size() == 0) {
            this.append(new String[]{
                    "id", "name", "creationDate", "oscarCount", "goldenPalmCount", "totalBoxOffice", "mpaaRating", "coords_x", "coords_y",
                    "director_name", "director_height", "location_x", "location_y", "location_name"
            });
        }
    }

    /**
     * Method to get all rows from csv file
     * @return List of String[] with all rows
     * @throws Exception
     */
    public List<String[]> getAll() throws Exception {
        try (Reader reader = Files.newBufferedReader(this.filename)) {
            try (CSVReader csvReader = new CSVReader(reader)) {
                return csvReader.readAll();
            }
        }
    }

    /**
     * Method to add new row to csv file
     * @param line
     * @throws Exception
     */
    public void append(String[] line) throws Exception {
        List<String[]> rows = this.getAll();
        rows.add(line);

        try (CSVWriter writer = new CSVWriter(new FileWriter(this.filename.toString()))) {
            writer.writeAll(rows);
        }
    }


}
