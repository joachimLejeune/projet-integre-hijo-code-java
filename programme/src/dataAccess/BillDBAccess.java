package dataAccess;

import controller.BillDataAccess;
import exception.AllEmployeesException;
import exception.EmailException;
import exception.NumPersonneException;
import exception.PhoneNumberException;
import model.Employee;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class BillDBAccess  implements BillDataAccess {

    public ArrayList<Employee> getAllEmployees() throws PhoneNumberException, EmailException, NumPersonneException, AllEmployeesException {
        ArrayList<Employee> employeesList = new ArrayList<>();
        Employee employee;
        Connection connection = SingletonConnetion.getInstance();
        if(connection!=null){
            try{
                String sqlInstruction = "select * from employee";
                PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
                ResultSet data = preparedStatement.executeQuery();
                GregorianCalendar calendar = new GregorianCalendar();
                while(data.next()){
                    java.sql.Date birthDate = data.getDate("birth_date");
                    calendar.setTime(birthDate);
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
}
