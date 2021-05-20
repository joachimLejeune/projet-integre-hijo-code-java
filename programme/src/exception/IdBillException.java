package exception;

public class IdBillException extends Exception {
    private Integer wrongIdBill;

    public IdBillException(Integer wrongIdBill){
        this.wrongIdBill = wrongIdBill;
    }
    public String getMessage(){
        return "Le numéro de la facture " + wrongIdBill + " eiste déjà en mémoire";
    }
}
