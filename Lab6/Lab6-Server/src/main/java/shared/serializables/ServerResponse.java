package shared.serializables;

public class ServerResponse {

    private boolean errors = false;
    private String errorMessage;
    private String[] response;

    public ServerResponse(String[] response) {
        this.response = response;
    }

    public ServerResponse(String errorMessage) {
        this.errors = true;
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return this.errorMessage;
    }

    public String[] getResponse() {
        return this.response;
    }

    public boolean hasErrors() {
        return this.errors;
    }

}
