package exception;

public class EmailException extends Exception {
    private String email;

    public EmailException(String email){
        this.email = email;
    }
    public String getMessage(){
        return "La strucutre de l'email renseigné n'est pas correct";
    }
}
