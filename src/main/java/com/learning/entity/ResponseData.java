package com.learning.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseData<T> {

    @AllArgsConstructor
    public enum CodeEnum {

        SUCCESS_CODE(200, "success"),

        CLIENT_ERROR_CODE(400, "client error"),

        SERVER_ERROR_CODE(500, "server error");

        private final int code;

        private final String msg;
    }

    private int code;

    private String message;

    private T data;


    public static <T> ResponseData<T> SUCCESS() {
        return new ResponseData<T>(CodeEnum.SUCCESS_CODE.code, CodeEnum.SUCCESS_CODE.msg, null);
    }

    public static <T> ResponseData<T> SUCCESS(T data) {
        return new ResponseData<T>(CodeEnum.SUCCESS_CODE.code, CodeEnum.SUCCESS_CODE.msg, data);
    }

    public static <T> ResponseData<T> CLIENT_ERROR() {
        return new ResponseData<>(CodeEnum.CLIENT_ERROR_CODE.code, CodeEnum.CLIENT_ERROR_CODE.msg, null);
    }

    public static <T> ResponseData<T> CLIENT_ERROR(T data) {
        return new ResponseData<>(CodeEnum.CLIENT_ERROR_CODE.code, CodeEnum.CLIENT_ERROR_CODE.msg, data);
    }

    public static <T> ResponseData<T> SERVER_ERROR() {
        return new ResponseData<>(CodeEnum.CLIENT_ERROR_CODE.code, CodeEnum.SERVER_ERROR_CODE.msg, null);
    }

    public static <T> ResponseData<T> SERVER_ERROR(T data) {
        return new ResponseData<>(CodeEnum.CLIENT_ERROR_CODE.code, CodeEnum.SERVER_ERROR_CODE.msg, data);
    }


}
