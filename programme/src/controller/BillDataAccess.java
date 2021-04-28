package controller;

import exception.AllEmployeesException;
import exception.EmailException;
import exception.NumPersonneException;
import exception.PhoneNumberException;
import model.Employee;

import java.util.ArrayList;

public interface BillDataAccess {
    ArrayList<Employee> getAllEmployees() throws PhoneNumberException, EmailException, NumPersonneException, AllEmployeesException;
}
