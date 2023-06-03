package core.system;

public class Config {

    /**
     * Path to .csv file
     */
    private static String DEFAULT_FILEPATH = "default.csv";

    /**
     * @return Current path to .csv file
     */
    public static String getFilepath() {
        return DEFAULT_FILEPATH;
    }

    /**
     * @param filepath Path to .csv file
     */
    public static void setFilepath(String filepath) {
        DEFAULT_FILEPATH = filepath;
    }


}
