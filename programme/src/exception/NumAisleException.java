package exception;

public class NumAisleException extends Throwable{
    private Integer wrongNumAisle;

    public NumAisleException(Integer wrongNumAisle){
        this.wrongNumAisle = wrongNumAisle;
    }
    public String getMessage(){
        return "La valeur " + wrongNumAisle + " contient autre chose qu'une valeur numérique";
    }
}