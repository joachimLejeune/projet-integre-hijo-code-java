package dataAccess;

import exception.*;
import model.Article;
import model.Bill;
import model.Customer;
import model.Employee;

import java.util.ArrayList;

public interface BillDataAccess {
    ArrayList<Employee> getAllEmployees() throws PhoneNumberException, EmailException, NumPersonException, AllEmployeesException;
    ArrayList<Customer> getAllCustomers() throws PhoneNumberException, EmailException, NumPersonException, AllCustomersException;
    Integer getNextIdBill() throws GetNextIdBillException;
    ArrayList<Article> getAllArticles();

    void setBill(Bill bill);

//    Integer getIdEmployee(String firstNameEmployee, String lastNameEmployee);
//
//    Integer getIdCustomer(String firstNameCustomer, String lastNameCustomer);
}
