package exception;

public class IdBillException extends Throwable {
    private Integer wrongIdBill;

    public IdBillException(Integer wrongIdBill){
        this.wrongIdBill = wrongIdBill;
    }
    public String getMessage(){
        return "La valeur " + wrongIdBill + " contient autre chose qu'une valeur numérique";
    }
}
