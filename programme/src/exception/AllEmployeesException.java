package exception;

public class AllEmployeesException extends Exception{


    public String getMessage(){
        return "Erreur lors de la récupération des employés";
    }
}
