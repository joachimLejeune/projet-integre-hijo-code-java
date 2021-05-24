package controller;

import business.*;
import exception.*;
import model.originalDBClasse.*;
import model.tableModelTool.SearchOne;
import model.tableModelTool.SearchThree;
import model.tableModelTool.SearchTwo;

import javax.swing.*;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class ApplicationControler {
    private BillManager manager;
    private JSpinnerManager jSpinnerManger;

    public ApplicationControler(){
        // à compléter
        setManager(new BillManager());
        setJSPinnermanager(new JSpinnerManager());
    }

    private void setJSPinnermanager(JSpinnerManager jSpinnerManager) {
        this.jSpinnerManger = jSpinnerManager;
    }
    public void setManager(BillManager manager) {
        this.manager = manager;
    }

    public ArrayList<Employee> getAllEmployees() throws PhoneNumberException, NumPersonException, EmailException, AllEmployeesException {
        return manager.getAllEmployees();
    }
    public ArrayList<Customer> getAllCustomers() throws PhoneNumberException, EmailException, NumPersonException, AllCustomersException{
        return manager.getAllCustomers();
    }
    public Integer getNextIdBill() throws GetNextIdBillException {
        return manager.getNextIdBill();
    }
    public ArrayList<Article> getAllArticles() throws GetAllArticlesException, VATException, IdArticleException, PriceException, NumAisleException {
        return manager.getAllArticles();
    }

    public int getYearJSPinner(JSpinner dateSpinner) {
        return jSpinnerManger.getYEARJSPinner(dateSpinner);
    }
    public int getMonthJSPinner(JSpinner dateSpinner) {
        return jSpinnerManger.getMonthJSPinner(dateSpinner);
    }
    public int getDayOfTheMonthJSPinner(JSpinner dateSpinner) {
        return jSpinnerManger.getDayOfTheMonthJSPinner(dateSpinner);
    }

    public Integer getIdEmployee(String idFirstAndLastNameEmployee) {
        return manager.getIdEmployee(idFirstAndLastNameEmployee);
    }

    public Integer getIdCustomer(String idFirstAndLastNameCustomer) {
        return manager.getIdCustomer(idFirstAndLastNameCustomer);
    }

    public void setBill(Bill bill) throws IdBillException {
        manager.setBill(bill);
    }

    public Integer getIdArticle(String wordingArticle) throws IdArticleException {
        return manager.getIdArticle(wordingArticle);
    }

    public void setListings(ArrayList<Listing> listings) throws SetListingsException {
        manager.setListings(listings);
    }

    public ArrayList<SearchOne> getSearchOne(GregorianCalendar firstDate, GregorianCalendar lastDate, Integer idCustomer) throws GetSearchOneException {
        return manager.getSearchOne(firstDate,lastDate,idCustomer);
    }

    public ArrayList<SearchThree> getSearchThree(Integer idArticle) throws GetSearchThreeException {
        return manager.getSearchThree(idArticle);
    }

    public ArrayList<SearchTwo> getSearchTwo(GregorianCalendar firstDateRead, GregorianCalendar lastDateRead) {
        return manager.getSearchTwo(firstDateRead,lastDateRead);
    }

    public ArrayList<Bill> getBill(Integer idBill) throws NumPersonException, IdBillException, GetBillException {
        return manager.getBill(idBill);
    }

    public ArrayList<Listing> getListings(Integer idBill) {
        return manager.getListings(idBill);
    }

    public Boolean deleteBill(Integer idBill) throws DeleteBillException {
        return manager.deleteBill(idBill);
    }

    public ArrayList<Integer> getAllIdBill() throws GetAllIdBillsException {
        return manager.getAllIdBill();
    }
}
