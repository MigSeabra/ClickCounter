package com.models;

import com.google.gson.Gson;

/**
 * Counter Response Model
 * WebSocket Response DTO containing Counter information
 */
public class CounterResponse {

    private String status;
    private Integer counter;

    public CounterResponse(String status, Integer counter) {
        this.status = status;
        this.counter = counter;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getCounter() {
        return counter;
    }

    public void setCounter(Integer counter) {
        this.counter = counter;
    }

    /**
     * Object Serializer
     * Serializes current object
     * @return String with serialized object
     */
    public String toString() {
        return new Gson().toJson(this);
    }
}
