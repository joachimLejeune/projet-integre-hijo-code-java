package exception;

public class NumAisleException extends Throwable{
    private Integer wrongNumAisle;

    public NumAisleException(Integer wrongNumAisle){
        this.wrongNumAisle = wrongNumAisle;
    }
    public NumAisleException(){this.wrongNumAisle = -1;}
    public String getMessage(){
        return "La valeur " + wrongNumAisle + " contient autre chose qu'une valeur numérique";
    }
}