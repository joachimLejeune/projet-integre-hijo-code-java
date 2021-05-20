package exception;

public class VATException extends Throwable{
    private Double wrongVAT;

    public VATException(Double VAT){
        this.wrongVAT = wrongVAT;
    }
    public VATException(){
        this.wrongVAT = -1.0;
    }
    public String getMessage(){
        return "La valeur " + wrongVAT + " n'est pas une valeur correct pour une TVA";
    }
}

