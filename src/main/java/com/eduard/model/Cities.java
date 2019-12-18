package com.eduard.model;

public enum Cities {
    KIEV("Kiev"),
    LONDON("London"),
    NEW_YORK("New-York"),
    ODESSA("Odessa"),
    PARIS("Paris"),
    MADRID("Madrid"),
    DONETSK("Donetsk"),
    SHEPETOVKA("Shepetovka"),
    TOKYO("Tokyo"),
    HONG_KONG("Hong Kong"),
    OTTAWA("Ottawa"),
    MUMBAI("Mumbai"),
    GLASGOW("Glasgow"),
    WASHINGTON("Washington");

    private final String city;

    Cities(String city) {
        this.city = city;
    }

    public String getCity() {
        return city;
    }
}
