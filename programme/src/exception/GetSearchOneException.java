package exception;

public class GetSearchOneException extends Exception {
    public String getMessage() {
        return "Erreur lors de la tentative pour la recherche 1";
    }
}
