package dataAccess;

import java.sql.*;
import model.*;

import javax.swing.*;
import java.util.ArrayList;

public class BillDBAcces {

    public BillDBAcces(){
        try{
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/library","root","Hijo");
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Aucune connexion possible avec la DB");
        }
    }

    public ArrayList<Employee> getAllEmployees(){
        return ?????;
    }
}
