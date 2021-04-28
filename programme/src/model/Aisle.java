package model;


import exception.NbShelvesException;
import exception.NumAisleException;


public class Aisle {
    private Integer numAisle;
    private String wording;
    private Integer nbShelves;

    public Aisle(Integer numAisle, String wording, Integer nbShelves) throws NumAisleException, NbShelvesException {
        setNumAisle(numAisle);
        this.wording = wording;
        setNbShelves(nbShelves);
    }

    public void setNumAisle(Integer numAisle) throws NumAisleException {
        if (numAisle instanceof Integer) {
            this.numAisle = numAisle;
        } else {
            throw new NumAisleException(numAisle);
        }
    }

    public void setNbShelves(Integer nbShelves) throws NbShelvesException {
        if((nbShelves >= 0) && (nbShelves instanceof Integer)){
            this.nbShelves = nbShelves;
        }
        else{
            throw new NbShelvesException(nbShelves);
        }
    }

}