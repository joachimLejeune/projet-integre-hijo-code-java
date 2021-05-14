package dataAccess.dBInterface;

import exception.*;
import model.originalDBClasse.*;
import model.tableModelTool.SearchOne;
import model.tableModelTool.SearchThree;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public interface BillDataAccess {
    // getter
    ArrayList<Employee> getAllEmployees() throws PhoneNumberException, EmailException, NumPersonException, AllEmployeesException;
    ArrayList<Customer> getAllCustomers() throws PhoneNumberException, EmailException, NumPersonException, AllCustomersException;
    Integer getNextIdBill() throws GetNextIdBillException;
    ArrayList<Article> getAllArticles() throws GetAllArticlesException;
    int getIdArticle(String wordingArticle) throws IdArticleException;
    ArrayList<SearchOne> getSearchOne(GregorianCalendar firstDate, GregorianCalendar lastDate, Integer idCustomer) throws GetSearchOneException;
    ArrayList<SearchThree> getSearchThree(Integer idArticle);

    // setter
    void setBill(Bill bill);

    void setListings(ArrayList<Listing> listings) throws SetListingsException;

//    Integer getIdEmployee(String firstNameEmployee, String lastNameEmployee);
//
//    Integer getIdCustomer(String firstNameCustomer, String lastNameCustomer);
}
