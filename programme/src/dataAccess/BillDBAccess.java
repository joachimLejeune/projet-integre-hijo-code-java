package dataAccess;

import exception.*;
import model.Article;
import model.Bill;
import model.Customer;
import model.Employee;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class BillDBAccess  implements BillDataAccess {
    private Connection connection;
    private Integer idEmployee;
    private Integer idCustomer;
    Integer lastIdBill = 0;
    public BillDBAccess(){
        connection = SingletonConnetion.getInstance();
    }

    public ArrayList<Employee> getAllEmployees() throws PhoneNumberException, EmailException, NumPersonException, AllEmployeesException {
        ArrayList<Employee> employeesList = new ArrayList<>();
        Employee employee;

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
                                data.getInt("phone_number"));
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
    public Integer getNextIdBill() throws GetNextIdBillException { // pas sur qu'un ArrayList soit pertinent

        if(connection!=null){
            try{
                String sqlInstruction = "select max(id_bill) from bill";
                PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
                ResultSet data = preparedStatement.executeQuery();
                data.next();
                lastIdBill = data.getInt("max(id_bill)");
                //connection.close();
            }
            catch(SQLException e){
                throw new GetNextIdBillException();
            }
        }
        return lastIdBill+1;
    }
    public ArrayList<Article> getAllArticles(){
        ArrayList<Article> articlesList = new ArrayList<>();
        Article article;
        if(connection!=null){
            try{
                // pour les articles
                String sqlInstruction = "select * from article";
                PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
                ResultSet data = preparedStatement.executeQuery();
                while(data.next()){
                    article = new Article(data.getInt("id_article"),data.getString("wording"),data.getDouble("price"),
                            data.getDouble("vat"),data.getInt("aisle"));
                    articlesList.add(article);
                }
            }
            catch(SQLException e){
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Problème lors de la connection à la base de donnée");
            } catch (IdArticleException e) {
                e.printStackTrace();
            } catch (VATException e) {
                e.printStackTrace();
            } catch (PriceException e) {
                e.printStackTrace();
            } catch (NumAisleException e) {
                e.printStackTrace();
            }
        }
        return articlesList;
    }
//    public Integer getIdEmployee(String firstNameEmployee, String lastNameEmployee){
//        try{
//            String sqlInstruction = "select num_employee from employee where first_name = ? and last_name = ?";
//            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
//            preparedStatement.setString(1,firstNameEmployee);
//            preparedStatement.setString(2,lastNameEmployee);
//            ResultSet data = preparedStatement.executeQuery();
//            if(data.next()){
//                idEmployee =  data.getInt("num_employee");
//            }
//        }
//        catch(SQLException e){
//            e.printStackTrace();
//        }
//        return idEmployee;
//    }
//    public Integer getIdCustomer(String firstNameCustomer, String lastNameCustomer){
//        try{
//            String sqlInstruction = "select num_customer from customer where first_name = ? and last_name = ?";
//            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
//            preparedStatement.setString(1,firstNameCustomer);
//            preparedStatement.setString(2,lastNameCustomer);
//            ResultSet data = preparedStatement.executeQuery();
//            if(data.next()){
//                idCustomer = data.getInt("num_customer");
//            }
//        }
//        catch(SQLException e){
//            e.printStackTrace();
//        }
//        return idCustomer;
//    }


    public void setBill(Bill bill){
        if(connection != null){
            try{
                String sqlInstruction = "insert into bill value(?,?,?,?,?,?,?,?)";
                PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
                preparedStatement.setInt(1,bill.getIdBill());
                preparedStatement.setDate(2, new java.sql.Date(bill.getDateBill().getTimeInMillis()));
                preparedStatement.setBoolean(3,bill.getIsDiscount());
                preparedStatement.setDouble(4,bill.getDiscount());
                preparedStatement.setInt(5,bill.getDiscountCoupon());
                preparedStatement.setString(6,bill.getRemarks());
                preparedStatement.setInt(7,bill.getEmployee());
                preparedStatement.setInt(8,bill.getCustomer());
                preparedStatement.executeUpdate();
            }
            catch(SQLException e){
                e.printStackTrace();
            }
        }
    }
}
