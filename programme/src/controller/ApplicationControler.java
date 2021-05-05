package controller;

import business.*;
import exception.*;
import model.*;

import javax.swing.*;
import java.util.ArrayList;

public class ApplicationControler {
    private BillManager manager;
    private JSpinnerManager jSpinnerManger;

    public ApplicationControler(){
        // à compléter
        setManager(new BillManager());
        setJSPinnermanager(new JSpinnerManager());
    }

    private void setJSPinnermanager(JSpinnerManager jSpinnerManager) {
        this.jSpinnerManger = jSpinnerManager;
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
    public Integer getNextIdBill() throws GetNextIdBillException {
        return manager.getNextIdBill();
    }
    public ArrayList<Article> getAllArticles() {
        return manager.getAllArticles();
    }

    public int getYearJSPinner(JSpinner dateSpinner) {
        return jSpinnerManger.getYEARJSPinner(dateSpinner);
    }
    public int getMonthJSPinner(JSpinner dateSpinner) {
        return jSpinnerManger.getMonthJSPinner(dateSpinner);
    }
    public int getDayOfTheMonthJSPinner(JSpinner dateSpinner) {
        return jSpinnerManger.getDayOfTheMonthJSPinner(dateSpinner);
    }

    public Integer getIdEmployee(String idFirstAndLastNameEmployee) {
        return manager.getIdEmployee(idFirstAndLastNameEmployee);
    }

    public Integer getIdCustomer(String idFirstAndLastNameCustomer) {
        return manager.getIdCustomer(idFirstAndLastNameCustomer);
    }

    public void setBill(Bill bill) {
        manager.setBill(bill);
    }
}
