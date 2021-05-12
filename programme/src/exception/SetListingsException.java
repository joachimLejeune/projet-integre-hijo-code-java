package exception;

public class SetListingsException extends Exception {

    public String getMessage() {
        return "Erreur lors de l'accès en écriture de la base de donnée pour le listing";
    }
}
