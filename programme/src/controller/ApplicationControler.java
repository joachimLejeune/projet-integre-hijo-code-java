package controller;

import business.*;
import exception.*;
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
    public ArrayList<Customer> getAllCustomers() throws PhoneNumberException, EmailException, NumPersonException, AllCustomersException{
        return manager.getAllCustomers();
    }
    public Integer getLastIdBill(){
        return manager.getLastIdBill();
    }

    public ArrayList<Article> getAllArticles() {
        return manager.getAllArticles();
    }
}
