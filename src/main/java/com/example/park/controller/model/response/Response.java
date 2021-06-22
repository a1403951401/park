package com.example.park.controller.model.response;

import lombok.Data;

@Data
public class Response<T> {
    private static final int CODE_SUCCESS = 200;
    private static final int CODE_Error = 500;

    private int code;

    private String message;

    private T data;

    public Response(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.setData(data);
    }

    public Response(T data) {
        this.setData(data);
    }

    public static <T> Response<T> success(T data) {
        return new Response<T>(CODE_SUCCESS, "ok", data);
    }

    public static <T> Response<T> error(String message) {
        return new Response<T>(CODE_Error, message, null);
    }

    public static <T> Response<T> error(T data) {
        return new Response<T>(CODE_Error, "Internal Server Error", data);
    }

    public static <T> Response<T> error(String message, T data) {
        return new Response<T>(CODE_Error, message, data);
    }
}
