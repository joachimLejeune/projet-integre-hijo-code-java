package dataAccess;

import controller.BillDataAccess;
import exception.*;
import model.Bill;
import model.Customer;
import model.Employee;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class BillDBAccess  implements BillDataAccess {

    public ArrayList<Employee> getAllEmployees() throws PhoneNumberException, EmailException, NumPersonException, AllEmployeesException {
        ArrayList<Employee> employeesList = new ArrayList<>();
        Employee employee;
        Connection connection = SingletonConnetion.getInstance();
        if(connection!=null){
            try{
                // pour les employés
                String sqlInstruction = "select * from employee";
                PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
                ResultSet data = preparedStatement.executeQuery();
                GregorianCalendar calendar = new GregorianCalendar();
                while(data.next()){
                    java.sql.Date birthDate = data.getDate("birth_date");
                    calendar.setTime(birthDate);
//                    employee = new Employee(data.getInt("num_employee"),data.getString("first_name"),
//                                data.getString("last_name"),calendar,data.getString("address"),
//                                data.getInt("phone_number"),data.getString("email"));
                    if(!data.wasNull()){
                        employee = new Employee(data.getInt("num_employee"),data.getString("first_name"),
                                data.getString("last_name"),calendar,data.getString("address"),
                                data.getInt("phone_number"),data.getString("email"));
                    }
                    else{
                        employee = new Employee(data.getInt("num_employee"),data.getString("first_name"),
                                data.getString("last_name"),calendar,data.getString("address"),
                                data.getInt("phone_number"),"inconnu");
                    }

                    employeesList.add(employee);
                }

            }
            catch(SQLException e){
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Problème lors de la connection à la base de donnée");
            }
        }
        return employeesList;
    }
    public ArrayList<Customer> getAllCustomers() throws PhoneNumberException, EmailException, NumPersonException, AllCustomersException {
        ArrayList<Customer> customersList = new ArrayList<>();
        Customer customer;
        Connection connection = SingletonConnetion.getInstance();
        if(connection!=null){
            try{
                // pour les clients
                String sqlInstruction = "select * from customer";
                PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
                ResultSet data = preparedStatement.executeQuery();
                GregorianCalendar calendar = new GregorianCalendar();
                while(data.next()){
                    java.sql.Date birthDate = data.getDate("birth_date");
                    calendar.setTime(birthDate);
                    customer = new Customer(data.getInt("num_customer"),data.getString("first_name"),
                                data.getString("last_name"),calendar,data.getString("address"),
                                data.getInt("phone_number"),data.getString("email"));

                    customersList.add(customer);
                }
                //connection.close();
            }
            catch(SQLException e){
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Problème lors de la connection à la base de donnée");
            }
        }
        return customersList;
    }
    public Integer getLastIdBill(){ // pas sur qu'un ArrayList soit pertinent
        Integer lastIdBill = 0;
        Connection connection = SingletonConnetion.getInstance();
        if(connection!=null){
            try{
                String sqlInstruction = "select max(id_bill) from bill";
                PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
                ResultSet data = preparedStatement.executeQuery();
                data.next();
                lastIdBill = data.getInt("max(id_bill)");
                connection.close();
            }
            catch(SQLException e){
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Problème lors de la connection à la base de donnée");
            }
        }
        return lastIdBill;
    }

}
