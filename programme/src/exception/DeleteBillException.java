package exception;

public class DeleteBillException extends Exception {
    @Override
    public String getMessage() {
        return "La suppression n'a pas pu être faite en raison d'un soucis de connection à la base de donnée";
    }
}
