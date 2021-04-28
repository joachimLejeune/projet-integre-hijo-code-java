package exception;

public class PriceException extends Throwable {
    private Double wrongPrice;

    public PriceException(Double wrongPrice){
        this.wrongPrice = wrongPrice;
    }
    public String getMessage(){
        return "La valeur " + wrongPrice + " est une valeur negative";
    }
}
