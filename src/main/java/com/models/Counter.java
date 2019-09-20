package com.models;

/**
 * Counter Model
 * Current Counter DTO
 */
public class Counter {

    private static Integer counter = 0;

    /**
     * Get Counter
     * Returns current Counter value
     * @return Current Counter value
     */
    public static synchronized Integer getCounter() {
        return counter;
    }

    /**
     * Increment Counter
     * Increments current Counter value to the maximum allowed by Integer class
     */
    public static synchronized void incrementCounter() {
        if (counter < Integer.MAX_VALUE) {
            counter++;
        }
    }
}
