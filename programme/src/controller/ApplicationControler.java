package controller;

import business.*;
import exception.AllEmployeesException;
import exception.EmailException;
import exception.NumPersonException;
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

    public ArrayList<Employee> getAllEmployees() throws PhoneNumberException, NumPersonException, EmailException, AllEmployeesException {
        return manager.getAllEmployees();
    }
}
