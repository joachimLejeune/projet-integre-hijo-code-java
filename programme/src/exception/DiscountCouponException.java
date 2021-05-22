package exception;

public class DiscountCouponException extends Exception {
    @Override
    public String getMessage() {
        return "La valeur entrée doit être composée de chiffres";
    }
}
