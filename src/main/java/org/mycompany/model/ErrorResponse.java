package org.mycompany.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ErrorResponse {

    private String descripcion;
    private String error;



    @JsonProperty("descripcion")
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @JsonProperty("error")
    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
