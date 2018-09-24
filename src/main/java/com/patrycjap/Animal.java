package com.patrycjap;

public class Animal {

    private String name;
    private String type;
    private String description;

    public Animal(String name, String type, String description) {
        this.name = name;
        this.type = type;
        this.description = description;
    }

    public Animal() {
        this.name = "";
        this.type = "";
        this.description = "";

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
