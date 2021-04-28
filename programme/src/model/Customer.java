package model;

import exception.NumPersonneException;
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

    public Customer(Integer numCustomer, String firstName, String lastName, GregorianCalendar birthDate, String address, Integer phoneNumber, String email) throws PhoneNumberException, NumPersonneException {
        setNumCustomer(numCustomer);
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.address = address;
        setPhoneNumber(phoneNumber);
        this.email = email;
    }
    public String getLastName() {
        return lastName;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setNumCustomer(Integer numEmployee) throws NumPersonneException {
        if (numEmployee instanceof Integer) {
            this.numCustomer = numEmployee;
        } else {
            throw new NumPersonneException(numEmployee);
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
