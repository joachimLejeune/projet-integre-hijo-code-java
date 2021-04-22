package exception;

public class NumEmployeeException extends Exception{
    private Integer wrongNumEmployee;

    public NumEmployeeException(Integer wrongNumEmployee){
        this.wrongNumEmployee = wrongNumEmployee;
    }
    public String getMessage(){
        return "La valeur " + wrongNumEmployee + " contient autre chose qu'une valeur numérique";
    }
}
