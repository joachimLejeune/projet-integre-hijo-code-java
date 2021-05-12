package dataAccess;

import exception.*;
import model.*;

import java.util.ArrayList;

public interface BillDataAccess {
    // getter
    ArrayList<Employee> getAllEmployees() throws PhoneNumberException, EmailException, NumPersonException, AllEmployeesException;
    ArrayList<Customer> getAllCustomers() throws PhoneNumberException, EmailException, NumPersonException, AllCustomersException;
    Integer getNextIdBill() throws GetNextIdBillException;
    ArrayList<Article> getAllArticles() throws GetAllArticlesException;
    int getIdArticle(String wordingArticle) throws IdArticleException;

    // setter
    void setBill(Bill bill);

    void setListings(ArrayList<Listing> listings) throws SetListingsException;

//    Integer getIdEmployee(String firstNameEmployee, String lastNameEmployee);
//
//    Integer getIdCustomer(String firstNameCustomer, String lastNameCustomer);
}
