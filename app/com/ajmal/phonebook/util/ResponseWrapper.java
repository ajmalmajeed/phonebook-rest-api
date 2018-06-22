package com.ajmal.phonebook.util;

public class ResponseWrapper<T> {
    private String message;
    private T data;

    public ResponseWrapper(String message, T data) {
        this.message = message;
        this.data = data;
    }

    public ResponseWrapper(Integer statusType, String message, T data) {
        this.message = statusType+": "+message;
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResponseWrapper{" +
                "message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}