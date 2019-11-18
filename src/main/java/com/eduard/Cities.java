package com.eduard;

public enum Cities {
    KIEV("Kiev"),
    LONDON("London"),
    NEW_YORK("New-York"),
    ODESSA("Odessa"),
    PARIS("Paris"),
    MADRID("Madrid"),
    DONETSK("Donetsk"),
    SHEPETOVKA("Shepetovka");

    private final String city;

    Cities(String city) {
        this.city = city;
    }

    public String getCity() {
        return city;
    }
}
