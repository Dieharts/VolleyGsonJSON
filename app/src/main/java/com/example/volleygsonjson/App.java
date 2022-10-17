package com.example.volleygsonjson;

import com.google.gson.annotations.SerializedName;

public class App {
    @SerializedName("name")
    private String companyName;
    @SerializedName("year")
    private Integer releaseYear;
    @SerializedName("recentConsole")
    private String recentConsole;

    //Initializer for model
    public App(final String name, final Integer year, final String latestConsole) {
        this.companyName = name;
        this.releaseYear = year;
        this.recentConsole = latestConsole;
    }

    //Getters
    public String getName() {return companyName;}

    public Integer getYear() {return releaseYear;}

    public String getRecentConsole() {return recentConsole;}

    //Setters
    public void setName(final String name) {this.companyName = name;}

    public void setYear(final Integer year) {this.releaseYear = year;}

    public void setRecentConsole(final String recentConsole) {this.recentConsole = recentConsole;}
}
