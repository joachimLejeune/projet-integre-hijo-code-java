package exception;

public class NumPersonneException extends Exception{
    private Integer wrongNum;

    public NumPersonneException(Integer wrongNumEmployee){
        this.wrongNum = wrongNum;
    }
    public String getMessage(){
        return "La valeur " + wrongNum + " contient autre chose qu'une valeur numérique";
    }
}
