package exception;

public class QuantityException extends Throwable {
    private Integer wrongQuantity;

    public QuantityException(Integer wrongQuantity){
        this.wrongQuantity = wrongQuantity;
    }
    public String getMessage(){
        return "La valeur " + wrongQuantity + " n'est pas une valeur corrent";
    }
}
