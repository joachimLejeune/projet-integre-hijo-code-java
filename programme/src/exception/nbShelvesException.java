package exception;

public class NbShelvesException extends Throwable{
    private Integer wrongNbShelves;

    public NbShelvesException(Integer wrongNbShelves){
        this.wrongNbShelves = wrongNbShelves;
    }
    public String getMessage(){
        return "La valeur " + wrongNbShelves + " n'est pas une valeur corrent";
    }
}