package com.models;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

/**
 * Counter Operation Request Model
 * WebSocket Request DTO for Counter operations
 */
public class CounterOperationRequest {

    private CounterOperations operation;

    public CounterOperations getOperation() {
        return operation;
    }

    public void setOperation(CounterOperations operation) {
        this.operation = operation;
    }

    /**
     * Object Deserializer
     * Deserializes incoming String to this Object
     * @param json String with serialized object
     * @return deserialized Object
     */
    public static CounterOperationRequest toObject(String json) {
        try {
            return new Gson().fromJson(json, CounterOperationRequest.class);
        } catch (JsonSyntaxException e) {
            return null;
        }
    }
}
