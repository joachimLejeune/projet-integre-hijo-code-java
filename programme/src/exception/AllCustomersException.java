package exception;

public class AllCustomersException extends Exception{
    public String getMessage(){
        return "Erreur lors de la récupération des clients";
    }
}
