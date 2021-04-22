package controller;

import business.*;
import model.*;

import java.util.ArrayList;

public class ApplicationControler {
    private BillManager manager;

    public ApplicationControler(){
        // à compléter
    }
    public ArrayList<Employee> getAllEmployees(){
        return manager.getAllEmployees();
    }
}
