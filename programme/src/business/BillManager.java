package business;

import dataAccess.*;
import exception.*;
import model.*;

import java.util.ArrayList;

public class BillManager {
    private BillDBAccess dao;

    public BillManager(){
        // à compléter
        setDao(new BillDBAccess());
    }

    public void setDao(BillDBAccess dao) {
        this.dao = dao;
    }

    public ArrayList<Employee> getAllEmployees() throws AllEmployeesException, PhoneNumberException, EmailException, NumPersonException {
        ArrayList<Employee> employeeList = dao.getAllEmployees();
        return employeeList;
    }

    public ArrayList<Customer> getAllCustomers() throws PhoneNumberException, AllCustomersException, NumPersonException, EmailException {
        ArrayList<Customer> customersList = dao.getAllCustomers();
        return customersList;
    }
    public Integer getLastIdBill(){
        return dao.getLastIdBill();
    }
}
