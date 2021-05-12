package dataAccess;

import exception.*;
import model.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class BillDBAccess  implements BillDataAccess {
    private Connection connection;
    Integer idArticle;
    Integer lastIdBill = 0;

    // constructeur
    public BillDBAccess(){
        connection = SingletonConnetion.getInstance();
    }

    // getter
    public ArrayList<Employee> getAllEmployees() throws PhoneNumberException, NumPersonException, AllEmployeesException {
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
                throw new AllEmployeesException();
            }
        }
        return employeesList;
    }
    public ArrayList<Customer> getAllCustomers() throws PhoneNumberException, NumPersonException, AllCustomersException {
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
                throw new AllCustomersException();
            }
        }
        return customersList;
    }
    public ArrayList<Article> getAllArticles() throws GetAllArticlesException{
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
                throw new GetAllArticlesException();
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

    public Integer getNextIdBill() throws GetNextIdBillException {
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
    public int getIdArticle(String wordingArticle) throws IdArticleException {
        if(connection!=null) {
            try {
                String sqlInstruction = "select id_article from article where wording = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
                preparedStatement.setString(1, wordingArticle);
                ResultSet data = preparedStatement.executeQuery();
                data.next();
                idArticle = data.getInt("id_article");
            } catch (SQLException e) {
                throw new IdArticleException();
            }
        }
        return idArticle;
    }

    // setter
    public void setBill(Bill bill){
        if(connection != null){
            try{
                String sqlInstruction = "insert into bill value(?,?,?,?,?,?,?,?);";
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
                e.printStackTrace(); // à changer ralalala
            }
        }
    }
    public void setListings(ArrayList<Listing> listings) throws SetListingsException {
        if(connection != null){
            try {
                String sqlInstruction = "insert into listing values(?,?,?,?);";
                PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
                for (Listing listing:
                     listings) {
                    preparedStatement.setInt(1,listing.getQuantity());
                    preparedStatement.setDouble(2,listing.getPrice());
                    preparedStatement.setInt(3, listing.getIdBill());
                    preparedStatement.setInt(4, listing.getArticle());
                    preparedStatement.executeUpdate();
                }

            } catch (SQLException throwables) {
                throw new SetListingsException();
            }
        }
    }

}
