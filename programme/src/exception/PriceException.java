package exception;

public class PriceException extends Throwable {
    private Double wrongPrice;

    public PriceException(Double wrongPrice){
        this.wrongPrice = wrongPrice;
    }
    public PriceException(){this.wrongPrice = -1.0;}
    public String getMessage(){
        return "La valeur " + wrongPrice + " est une valeur negative";
    }
}
