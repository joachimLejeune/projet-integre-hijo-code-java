package exception;

public class VATException extends Throwable{
    private Double wrongVAT;

    public VATException(Double VAT){
        this.wrongVAT = wrongVAT;
    }
    public String getMessage(){
        return "La valeur " + wrongVAT + " n'est pas une correct TVA";
    }
}

