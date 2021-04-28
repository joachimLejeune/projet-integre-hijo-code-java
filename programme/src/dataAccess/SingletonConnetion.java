package dataAccess;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SingletonConnetion {
    private static Connection connexionUnique;


    public static Connection getInstance(){
        if(connexionUnique == null){
            try{
                connexionUnique = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_projet_hijo","root","Hijo");
            }
            catch(SQLException e) {
                JOptionPane.showMessageDialog(null, "Problème initial lors de la connection à la base de donnée : " + e);
            }
        }
        return connexionUnique;
    }
}
