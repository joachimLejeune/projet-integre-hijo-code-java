package model;

import exception.EmailException;
import exception.NumEmployeeException;
import exception.PhoneNumberException;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Employee {
    private Integer numEmployee;
    private String firstName;
    private String lastName;
    private GregorianCalendar birthDate;
    private String address;
    private Integer phoneNumber;
    private String email;

    public Employee(Integer numEmployee, String firstName, String lastName, GregorianCalendar birthDate, String address, Integer phoneNumber, String email) throws EmailException,NumEmployeeException, PhoneNumberException{
        setNumEmployee(numEmployee);
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.address = address;
        setPhoneNumber(phoneNumber);
        setEmail(email);
    }

    public void setNumEmployee(Integer numEmployee) throws NumEmployeeException {
        if (numEmployee instanceof Integer) {
            this.numEmployee = numEmployee;
        } else {
            throw new NumEmployeeException(numEmployee);
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
    public void setEmail(String email) throws EmailException {
        if(email == "(?:\\w|[-])+(?:.(?:\\w|[-])+)@(?:\\w|[-])+(?:.(?:\\w|[-])+)*"){
            this.email = email;
        }
        else{
            throw new EmailException(email);
        }
    }
}
