package exception;

public class GetSearchThreeException extends Exception {
    @Override
    public String getMessage() {
        return "La recherche 3 n'a pas pu aboutir en raison d'un problème de connection à la base de données";
    }
}
