package com.ulacit.matriculas.matriculasulacit.Modelos;


public class Response {
    
    private Object request;

    private Object response;

    private String message;

    private int httpStatus = Constante.ok;

    public Response() {
        super();
    }

    public Object getRequest() {
        return request;
    }
    public void setRequest(Object request) {
        this.request = request;
    }
    public Object getResponse() {
        return response;
    }
    public void setResponse(Object response) {
        this.response = response;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public int getHttpStatus() {
        return httpStatus;
    }
    public void setHttpStatus(int httpStatus) {
        this.httpStatus = httpStatus;
    }
}
