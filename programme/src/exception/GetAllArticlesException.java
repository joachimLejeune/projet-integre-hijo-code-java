package exception;

public class GetAllArticlesException extends Exception {
    public String getMessage() {
        return "Erreur lors de la récupération de la récupération de tout les articles";
    }
}
