package core.system;

public class Config {

    /**
     * Path to .csv file
     */
    private static String DEFAULT_FILEPATH = "src/main/java/default.csv";

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

    /**
     * Command argument
     */
    private static String cmdParam = "";

    /**
     * Getter for command argument
     * @return command argument
     */
    public static String getCmdParam() {
        return cmdParam;
    }

    /**
     * Setter for command argument
     * @param param
     */
    public static void setCmdParam(String param) {
        cmdParam = param;
    }

}
