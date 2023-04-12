package fr.nelson.apibasetemplate.exceptions;

public class HttpException extends Exception {

    private int statusCode;

    public HttpException(String errorMessage, int statusCode) {
        super(errorMessage);
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
