package com.eduard;

public enum Commands {
    RACES_TABLE("1"),
    GET_RACE_BY_ID("2"),
    SEARCH_AND_RESERVE("3"),
    CANCEL_RESERVE("4"),
    MY_RACES("5"),
    EXIT("0");

    private final String value;

    Commands(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
