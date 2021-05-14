package model.tableModelTool;

import java.util.Date;
import java.util.GregorianCalendar;

public class SearchOne {
    private String firstNameCustomer;
    private String lastNameCustomer;
    private Date dateBill;

    public SearchOne(String firstNameCustomer, String lastNameCustomer, Date dateBill){
        this.firstNameCustomer = firstNameCustomer;
        this.lastNameCustomer = lastNameCustomer;
        this.dateBill = dateBill;
    }
    public String getFirstNameCustomer() {
        return firstNameCustomer;
    }
    public String getLastNameCustomer() {
        return lastNameCustomer;
    }
    public Date getDateBill() {
        return dateBill;
    }
    public String toString(){
        return this.firstNameCustomer + " " + this.lastNameCustomer + " (date de facture :" + this.dateBill.getTime() + " )";
    }
}
