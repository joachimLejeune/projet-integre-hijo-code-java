package model.originalDBClasse;

import exception.IdBillException;
import exception.NumPersonException;

import java.util.Date;
import java.util.GregorianCalendar;

public class Bill {
    private Integer idBill;
    private GregorianCalendar dateBill;
    private Boolean isDiscount;
    private Double discount;
    private Integer discountCoupon;
    private String remarks;
    private Integer employee;
    private Integer customer;

    public Bill(Integer idBill, GregorianCalendar dateBill, Boolean isDiscount, Double discount, Integer discountCoupon, String remarks, Integer employee, Integer customer) throws IdBillException, NumPersonException {
        setIdBill(idBill);
        this.dateBill = dateBill;
        this.isDiscount = isDiscount;
        this.discount = discount;
        this.discountCoupon = discountCoupon;
        this.remarks = remarks;
        setEmployee(employee);
        setCustomer(customer);
    }
    public Bill(Integer idBill, Integer employee, Integer customer) throws IdBillException, NumPersonException {
        this(idBill,null,null,null,null,null,employee,customer);
    }

    // getter
    public Integer getIdBill(){
        return this.idBill;
    }
    public GregorianCalendar getDateBill() {
        return dateBill;
    }
    public Date getDateBillOtherDateFormat(){
        return dateBill.getTime();
    }
    public Boolean isDiscountBeforeDeadLine() {
        return isDiscount;
    }
    public Double getDiscount() {
        return discount;
    }
    public Integer getDiscountCoupon() {
        return discountCoupon;
    }
    public String getRemarks() {
        return remarks;
    }
    public Integer getEmployee() {
        return employee;
    }
    public Integer getCustomer() {
        return customer;
    }

    // setter
    public void setIdBill(Integer idBill) throws IdBillException {
        if (idBill instanceof Integer) {
            this.idBill = idBill;
        } else {
            throw new IdBillException(idBill);
        }
    }
    public void setDateBill(GregorianCalendar dateBill) {
        if(dateBill != null){
            this.dateBill = dateBill;
        }
        else{
            this.dateBill = new GregorianCalendar();
        }
    }
    public void setDiscount(Boolean discount) {
        if(discount!= null){
            this.isDiscount = discount;
        }
        else{
            this.isDiscount = false;
        }
    }
    public void setDiscount(Double discount) {
        if(discount!=null){
            this.discount = discount;
        }
        else{
            this.discount = 0.0;
        }
    }
    public void setDiscountCoupon(Integer discountCoupon) {
        if(discountCoupon!=null){
            this.discountCoupon = discountCoupon;
        }
        else{
            this.discountCoupon = 0;
        }
    }
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
    public void setEmployee(Integer employee) throws NumPersonException {
        if (employee instanceof Integer) {
            this.employee = employee;
        } else {
            throw new NumPersonException(employee);
        }
    }
    public void setCustomer(Integer customer) throws NumPersonException {
        if (customer instanceof Integer) {
            this.customer = customer;
        } else {
            throw new NumPersonException(customer);
        }
    }

    public String getPossibleRemarks() {
        return remarks;
    }
}
