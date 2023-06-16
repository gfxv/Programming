package shared.serializables;

import shared.enteties.Movie;

import java.io.Serializable;
import java.util.HashSet;

public class ResponseBody implements Serializable {
    private static final long serialVersionUID = 4;

    private String[] pResponse;
    private HashSet<Movie> cResponse;
    private String errorMessage;

    public ResponseBody(String[] response) {
        this.pResponse = response;
    }

    public ResponseBody(HashSet<Movie> response) {
        this.cResponse = response;
    }

    public ResponseBody(String err) {
        this.errorMessage = err;
    }

    public String[] getpResponse() {
        return pResponse;
    }

    public HashSet<Movie> getcResponse() {
        return cResponse;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
