package com.example.hackro.myapplication.models.Generos;


public class Genre {

    private int id;
    private String name;

    /**
     * No args constructor for use in serialization
     */
    public Genre() {
    }

    /**
     * @param id
     * @param name
     */
    public Genre(int id, String name) {
        super();
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
