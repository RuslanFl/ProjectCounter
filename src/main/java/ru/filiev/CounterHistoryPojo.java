package ru.filiev;

import java.io.Serial;
import java.io.Serializable;

public class CounterHistoryPojo implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private int value;

    public CounterHistoryPojo(int value) {
        this.value = value;
    }

    public int getValue() {

        return value;
    }

    public void incrementValue() {
        value++;
    }

    public void resetValue() {
        value = 0;
    }
}