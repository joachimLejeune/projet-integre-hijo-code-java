package business;

import dataAccess.*;
import exception.AllEmployeesException;
import exception.EmailException;
import exception.NumEmployeeException;
import exception.PhoneNumberException;
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

    public ArrayList<Employee> getAllEmployees() throws AllEmployeesException, PhoneNumberException, EmailException, NumEmployeeException {
        ArrayList<Employee> employeeList = dao.getAllEmployees();
        return employeeList;
    }
}
