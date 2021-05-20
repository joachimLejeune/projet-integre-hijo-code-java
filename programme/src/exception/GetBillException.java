package exception;

public class GetBillException extends Exception {
    @Override
    public String getMessage() {
        return "Le numéro de la facture n'a pas pu être récupérer en raison d'un soucis de connection à la base de donnée";
    }
}
