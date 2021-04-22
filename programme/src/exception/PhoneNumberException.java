package exception;

public class PhoneNumberException extends Exception{
    private Integer wrongPhoneNumber;

    public PhoneNumberException(Integer wrongPhoneNumber){
        this.wrongPhoneNumber = wrongPhoneNumber;
    }
    public String getMessage(){
        return "La valeur " + wrongPhoneNumber + " n'est pas une valeur admise comme numéro de téléphone";
    }
}
