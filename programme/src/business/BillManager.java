package business;

import dataAccess.*;
import dataAccess.dBInterface.BillDataAccess;
import exception.*;
import model.originalDBClasse.*;
import model.tableModelTool.SearchOne;
import model.tableModelTool.SearchThree;
import model.tableModelTool.SearchTwo;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class BillManager {
    private BillDataAccess dao;
    private int posFirstSpaceEmployee = 0;
    private int posFirstSpaceCustomer = 0;
    Boolean find = false;
    private Integer idEmployee;
    private Integer idCustomer;

    public BillManager(){
        setDao(new BillDBAccess());
    }

    // getter
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
    public ArrayList<Article> getAllArticles() throws GetAllArticlesException, VATException, IdArticleException, PriceException, NumAisleException {
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
    public Integer getIdArticle(String wordingArticle) throws IdArticleException {
        return dao.getIdArticle(wordingArticle);
    }
    public ArrayList<SearchOne> getSearchOne(GregorianCalendar firstDate, GregorianCalendar lastDate, Integer idCustomer) throws GetSearchOneException {
        return dao.getSearchOne(firstDate, lastDate, idCustomer);
    }
    public ArrayList<SearchThree> getSearchThree(Integer idArticle) throws GetSearchThreeException {
        return dao.getSearchThree(idArticle);
    }
    public ArrayList<SearchTwo> getSearchTwo(GregorianCalendar firstDateRead, GregorianCalendar lastDateRead) {
        return dao.getSearchTwo(firstDateRead,lastDateRead);
    }
    public ArrayList<Bill> getBill(Integer idBill) throws NumPersonException, IdBillException, GetBillException {
        return dao.getBill(idBill);
    }
    public ArrayList<Listing> getListings(Integer idBill) {
        return dao.getListings(idBill);
    }
    public ArrayList<Integer> getAllIdBill() throws GetAllIdBillsException {
        return dao.getAllIdBill();
    }


    // setter
    public void setDao(BillDBAccess dao) {
        this.dao = dao;
    }
    public void setBill(Bill bill) throws IdBillException {
        dao.setBill(bill);
    }
    public void setListings(ArrayList<Listing> listings) throws SetListingsException {
        dao.setListings(listings);
    }

    // méthodes
    public Boolean deleteBill(Integer idBill) throws DeleteBillException {
        return dao.deleteBill(idBill);
    }


}
