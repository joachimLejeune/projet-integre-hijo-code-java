package model;

import exception.EmailException;
import exception.NumPersonException;
import exception.PhoneNumberException;

import java.util.GregorianCalendar;

public class Employee {
    private Integer numEmployee;
    private String firstName;
    private String lastName;
    private GregorianCalendar birthDate;
    private String address;
    private Integer phoneNumber;
    private String email;

    public Employee(Integer numEmployee, String firstName, String lastName, GregorianCalendar birthDate, String address, Integer phoneNumber, String email) throws NumPersonException, PhoneNumberException{
        setNumEmployee(numEmployee);
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.address = address;
        setPhoneNumber(phoneNumber);
        this.email = email;
    }
    public Employee(Integer numEmployee, String firstName, String lastName, GregorianCalendar birthDate, String address, Integer phoneNumber) throws NumPersonException, PhoneNumberException{
        this(numEmployee,firstName,lastName,birthDate,address,phoneNumber," ");

    }
    public String getLastName() {
        return lastName;
    }
    public String getFirstName() {
        return firstName;
    }

    public void setNumEmployee(Integer numEmployee) throws NumPersonException {
        if (numEmployee instanceof Integer) {
            this.numEmployee = numEmployee;
        } else {
            throw new NumPersonException(numEmployee);
        }
    }
    public void setPhoneNumber(Integer phoneNumber) throws PhoneNumberException{
        if(phoneNumber instanceof Integer){
            this.phoneNumber = phoneNumber;
        }
        else{
            throw new PhoneNumberException(phoneNumber);
        }
    }
}

// "(?:\\w|[-])+(?:.(?:\\w|[-])+)@(?:\\w|[-])+(?:.(?:\\w|[-])+)*"