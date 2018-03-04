package com.ulacit.matriculas.matriculasulacit.Modelos;

public class ResponseObject {

    /*@ApiModelProperty(notes="Parámetros recibidos como solicitud")*/
    private Object request;

    /*@ApiModelProperty(notes="Respuesta del servicio, este campo encapsula las respuestas")*/
    private Object response;

    /*@ApiModelProperty(notes="Mensaje de error en caso de que el servicio falle")*/
    private String message;

    /*@ApiModelProperty(notes="Estado de la solicitud: Satisfactorio 200 | Error de solicitud 400 | Error interno del servidor 500")*/
    private int httpStatus = Constants.ok;

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

    public ResponseObject() {
        super();
    }

    public ResponseObject(Object response) {
        super();
        this.response = response;
    }

    public ResponseObject(Object request, Object response, String message, int codeStatus) {
        super();
        this.request = request;
        this.response = response;
        this.message = message;
        httpStatus = codeStatus;
    }
}
