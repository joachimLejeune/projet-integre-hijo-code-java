package controller;

import exception.*;
import model.Customer;
import model.Employee;

import java.util.ArrayList;

public interface BillDataAccess {
    ArrayList<Employee> getAllEmployees() throws PhoneNumberException, EmailException, NumPersonException, AllEmployeesException;
    ArrayList<Customer> getAllCustomers() throws PhoneNumberException, EmailException, NumPersonException, AllCustomersException;
    Integer getLastIdBill();
}
