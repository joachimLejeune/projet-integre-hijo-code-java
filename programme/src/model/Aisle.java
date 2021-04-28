package model;

public class Aisle {
    private Integer numAisle;
    private String wording;
    private Integer nbShelves;

    public Aisle(Integer numAisle, String wording, Integer nbShelves){
        this.numAisle = numAisle;
        this.wording = wording;
        this.nbShelves = nbShelves;
    }
}
