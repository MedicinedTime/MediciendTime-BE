package com.yagsog.api.response;

public class ApiResponse {
    private String message;
    private Object data;

    public ApiResponse(String message, Object data) {
        this.message = message;
        this.data = data;
    }

    public String getMessage() { return message; }
    public Object getData() { return data; }
}

