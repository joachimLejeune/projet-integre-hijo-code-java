package business;

import dataAccess.*;
import model.*;

import java.util.ArrayList;

public class BillManager {
    private BillDBAcces dao;

    public BillManager(){
        // à compléter
    }
    public ArrayList<Employee> getAllEmployees(){
        return dao.getAllEmployees();
    }
}
