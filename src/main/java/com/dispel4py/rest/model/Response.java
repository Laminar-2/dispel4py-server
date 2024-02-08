package com.dispel4py.rest.model;

import java.io.Serializable;

public class Response implements Serializable {
    String response;

    public Response(String response) {
        this.response = response;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    @Override
    public String toString() {
        return "Response(" + response + ")";
    }
}
