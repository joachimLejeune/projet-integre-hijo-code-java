package controller;

import exception.AllEmployeesException;
import exception.EmailException;
import exception.NumPersonException;
import exception.PhoneNumberException;
import model.Employee;

import java.util.ArrayList;

public interface BillDataAccess {
    ArrayList<Employee> getAllEmployees() throws PhoneNumberException, EmailException, NumPersonException, AllEmployeesException;
}
