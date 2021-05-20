package dataAccess.dBInterface;

import exception.*;
import model.originalDBClasse.*;
import model.tableModelTool.SearchOne;
import model.tableModelTool.SearchThree;
import model.tableModelTool.SearchTwo;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public interface BillDataAccess {
    // getter
    ArrayList<Employee> getAllEmployees() throws PhoneNumberException, EmailException, NumPersonException, AllEmployeesException;
    ArrayList<Customer> getAllCustomers() throws PhoneNumberException, EmailException, NumPersonException, AllCustomersException;
    Integer getNextIdBill() throws GetNextIdBillException;
    ArrayList<Article> getAllArticles() throws GetAllArticlesException, IdArticleException, VATException, PriceException, NumAisleException;
    int getIdArticle(String wordingArticle) throws IdArticleException;
    ArrayList<SearchOne> getSearchOne(GregorianCalendar firstDate, GregorianCalendar lastDate, Integer idCustomer) throws GetSearchOneException;
    ArrayList<SearchTwo> getSearchTwo(GregorianCalendar firstDateRead, GregorianCalendar lastDateRead);
    ArrayList<SearchThree> getSearchThree(Integer idArticle) throws GetSearchThreeException;
    ArrayList<Bill> getBill(Integer idBill) throws NumPersonException, GetBillException, IdBillException;
    ArrayList<Listing> getListings(Integer idBill);
    Boolean deleteBill(Integer idBill) throws DeleteBillException;

    // setter
    void setBill(Bill bill) throws IdBillException;
    void setListings(ArrayList<Listing> listings) throws SetListingsException;
}
