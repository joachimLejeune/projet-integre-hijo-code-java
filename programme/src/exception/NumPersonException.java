package exception;

public class NumPersonException extends Exception{
    private Integer wrongNum;

    public NumPersonException(Integer wrongNumEmployee){
        this.wrongNum = wrongNum;
    }
    public String getMessage(){
        return "La valeur " + wrongNum + " contient autre chose qu'une valeur numérique";
    }
}
