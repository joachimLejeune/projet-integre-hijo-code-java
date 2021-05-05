package exception;

public class GetNextIdBillException extends Exception{
    public String getMessage(){
        return "Erreur d'une insertion dans la table Bill";
    }
}
