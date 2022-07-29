package com.portfolio.backend.Response;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Response {
    private String Message;

    public Response(String message) {
        this.Message = message;
    }
}
