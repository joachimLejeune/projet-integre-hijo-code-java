package controller;

import business.*;
import exception.AllEmployeesException;
import exception.EmailException;
import exception.NumEmployeeException;
import exception.PhoneNumberException;
import model.*;

import java.util.ArrayList;

public class ApplicationControler {
    private BillManager manager;

    public ApplicationControler(){
        // à compléter
        setManager(new BillManager());
    }

    public void setManager(BillManager manager) {
        this.manager = manager;
    }

    public ArrayList<Employee> getAllEmployees() throws PhoneNumberException, NumEmployeeException, EmailException, AllEmployeesException {
        return manager.getAllEmployees();
    }
}
