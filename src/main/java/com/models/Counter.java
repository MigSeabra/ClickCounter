package com.models;

public class Counter {

    private static Integer counter = 0;

    public static synchronized Integer getCounter() {
        return counter;
    }

    public static synchronized void incrementCounter() {
        counter++;
    }
}
