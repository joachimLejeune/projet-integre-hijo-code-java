package business;

import dataAccess.*;
import exception.*;
import model.*;

import javax.swing.*;
import java.util.ArrayList;

public class BillManager {
    private BillDBAccess dao;
    private int posFirstSpaceEmployee = 0;
    private int posFirstSpaceCustomer = 0;
    Boolean find = false;
    private Integer idEmployee;
    private Integer idCustomer;

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
    public Integer getNextIdBill() throws GetNextIdBillException {
        return dao.getNextIdBill();
    }

    public ArrayList<Article> getAllArticles() {
        return dao.getAllArticles();
    }

    public Integer getIdEmployee(String idFfirstAndLastNameEmployee) {
        int idFirsAndLastNameEmployeeLength = idFfirstAndLastNameEmployee.length();
        while(posFirstSpaceEmployee < idFirsAndLastNameEmployeeLength && idFfirstAndLastNameEmployee.charAt(posFirstSpaceEmployee)!=' '){
            posFirstSpaceEmployee++;
        }
        idEmployee = Integer.valueOf(idFfirstAndLastNameEmployee.substring(0,posFirstSpaceEmployee));
        posFirstSpaceEmployee = 0;
        return idEmployee;
    }

    public Integer getIdCustomer(String idFirstAndLastNameCustomer) {
        int idFirstAndLastNameCustomerLength = idFirstAndLastNameCustomer.length();
        while(posFirstSpaceCustomer < idFirstAndLastNameCustomerLength && idFirstAndLastNameCustomer.charAt(posFirstSpaceCustomer)!=' '){
            posFirstSpaceCustomer++;
        }
        idCustomer = Integer.valueOf(idFirstAndLastNameCustomer.substring(0,posFirstSpaceCustomer));
        posFirstSpaceCustomer = 0;
        return idCustomer;
    }

    public void setBill(Bill bill) {
        dao.setBill(bill);
    }
}
