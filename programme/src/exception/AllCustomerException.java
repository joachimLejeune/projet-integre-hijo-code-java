package exception;

public class AllCustomerException extends Exception{
    public String getMessage(){
        return "Erreur lors de la récupération des clients";
    }
}
