package com.example.volleygsonjson;

import com.google.gson.annotations.SerializedName;

public class App {
    @SerializedName("name")
    private String factionName;
    @SerializedName("founded_year")
    private Integer foundedYear;
    @SerializedName("descrip")
    private String description;

    //Initializer for model
    public App(final String name, final Integer founded_year, final String descrip) {
        this.factionName = name;
        this.foundedYear = founded_year;
        this.description = descrip;
    }

    //Getters
    public String getName() {return factionName;}

    public Integer getYear() {return foundedYear;}

    public String getDescription() {return description;}


    //Setters
    public void setName(final String name) {this.factionName = name;}

    public void setYear(final Integer foundedYear) {this.foundedYear = foundedYear;}

    public void setDescription(final String description) {this.description = description;}
}
