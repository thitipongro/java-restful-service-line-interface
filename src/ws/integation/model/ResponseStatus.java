package ws.integation.model;

public class ResponseStatus {
    String statusCode;
    String errorCode;
    String errorMessage;

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String data) {
        this.statusCode = data;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String data) {
        this.errorCode = data;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String data) {
        this.errorMessage = data;
    }
}
