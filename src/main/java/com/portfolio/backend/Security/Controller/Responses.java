package com.portfolio.backend.Security.Controller;

public class Responses {
    private String response;
    
    //Constructor

    public Responses() {
    }
    public Responses(String message) {
        this.response = message;
    }

    //Getter & Setter

    public String getResponse() {
        return response;
    }

    public void setResponse(String message) {
        this.response = message;
    }
    
}
