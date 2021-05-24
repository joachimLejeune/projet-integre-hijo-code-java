package exception;

public class GetAllIdBillsException extends Exception {
    @Override
    public String getMessage() {
        return "Problème de connexion à la base de données lors de la récupération des numéros de facture";
    }
}
