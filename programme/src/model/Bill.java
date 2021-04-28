package model;

import exception.IdBillException;
import exception.NumPersonException;

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

    public void setIdBill(Integer idBill) throws IdBillException {
        if (idBill instanceof Integer) {
            this.idBill = idBill;
        } else {
            throw new IdBillException(idBill);
        }
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
}
