package model;

import exception.NumPersonException;
import exception.PhoneNumberException;

import java.util.GregorianCalendar;

public class Customer {
    private Integer numCustomer;
    private String firstName;
    private String lastName;
    private GregorianCalendar birthDate;
    private String address;
    private Integer phoneNumber;
    private String email;

    public Customer(Integer numCustomer, String firstName, String lastName, GregorianCalendar birthDate, String address, Integer phoneNumber, String email) throws PhoneNumberException, NumPersonException {
        setNumCustomer(numCustomer);
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.address = address;
        setPhoneNumber(phoneNumber);
        this.email = email;
    }
    public Integer getNumCustomer() {
        return numCustomer;
    }
    public String getLastName() {
        return lastName;
    }
    public String getFirstName() {
        return firstName;
    }

    public void setNumCustomer(Integer numEmployee) throws NumPersonException {
        if (numEmployee instanceof Integer) {
            this.numCustomer = numEmployee;
        } else {
            throw new NumPersonException(numEmployee);
        }
    }
    public void setPhoneNumber(Integer phoneNumber) throws PhoneNumberException {
        if(phoneNumber instanceof Integer){
            this.phoneNumber = phoneNumber;
        }
        else{
            throw new PhoneNumberException(phoneNumber);
        }
    }
}
