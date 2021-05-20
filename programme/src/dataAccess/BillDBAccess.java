package dataAccess;

import dataAccess.dBInterface.BillDataAccess;
import exception.*;
import model.originalDBClasse.*;
import model.tableModelTool.SearchOne;
import model.tableModelTool.SearchThree;
import model.tableModelTool.SearchTwo;

import java.sql.*;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class BillDBAccess  implements BillDataAccess {
    private final Connection connection;
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
    public ArrayList<Article> getAllArticles() throws GetAllArticlesException, IdArticleException, VATException, PriceException, NumAisleException {
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
    public ArrayList<SearchOne> getSearchOne(GregorianCalendar firstDate, GregorianCalendar lastDate, Integer idCustomer) throws GetSearchOneException {
        ArrayList<SearchOne> employeesSearchOne = new ArrayList<>();
        SearchOne employeeSearchOne;
        if(connection != null){
            try{
                String sqlInstruction = "select e.first_name, e.last_name, b.date_bill from employee e " +
                        " join bill b on e.num_employee = b.employee" +
                        " join customer c on b.customer = c.num_customer" +
                        " where date_bill between ? and ? and c.num_customer = ?" +
                        " group by e.last_name, b.date_bill" +
                        " order by e.first_name;";
                PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
                preparedStatement.setDate(1, new java.sql.Date(firstDate.getTimeInMillis()));
                preparedStatement.setDate(2, new java.sql.Date(lastDate.getTimeInMillis()));
                preparedStatement.setInt(3,idCustomer);
                ResultSet data = preparedStatement.executeQuery();
                java.sql.Date dateBill;
                while(data.next()){
                    dateBill = data.getDate("date_bill");
                    employeeSearchOne = new SearchOne(data.getString("first_name"),data.getString("last_name"),dateBill);
                    employeesSearchOne.add(employeeSearchOne);
                }
            } catch (SQLException e) {
                throw new GetSearchOneException();
            }
        }
        return employeesSearchOne;
    }
    public ArrayList<SearchTwo> getSearchTwo(GregorianCalendar firstDateRead, GregorianCalendar lastDateRead) {
        ArrayList<SearchTwo> topThreeInformations = new ArrayList<>();
//        SearchTwo searchTwo;
//        if(connection != null){
//            try{
//                String sqlInstruction = "";
//                PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
//                PreparedStatement.setDate(1,new java.sql.Date(firstDateRead.getTimeInMillis()));
//                PreparedStatement.setDate(2,new java.sql.Date(lastDateRead.getTimeInMillis()));
//                ResultSet data = preparedStatement.executeQuery();
//                while(data.next()){
//                    searchTwo = new SearchTwo(data.getString("first_name"),data.getString("last_name"),data.getString("wording"),data.getInt("quantity"));
//                    topThreeInformations.add(searchTwo);
//                }
//            }catch(SQLException e){
//                e.printStackTrace(); // à modifier à la fin
//            }
//        }
        return topThreeInformations;
    }
    public ArrayList<SearchThree> getSearchThree(Integer idArticle) throws GetSearchThreeException {
        ArrayList<SearchThree> customerSearchThrees = new ArrayList<>();
        SearchThree customerSearchThree;
        if(connection != null){
            try{
                String sqlInstruction = "select c.first_name, c.last_name, sum(quantity) from article a" +
                        " join listing l on a.id_article = l.article" +
                        " join bill b on l.bill = b.id_bill" +
                        " join customer c on b.customer = c.num_customer" +
                        " where id_article = ?" +
                        " group by c.last_name" +
                        " order by sum(quantity) desc;";
                PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
                preparedStatement.setInt(1, idArticle);
                ResultSet data = preparedStatement.executeQuery();
                while(data.next()){
                    customerSearchThree = new SearchThree(data.getString("first_name"),data.getString("last_name"),data.getInt("sum(quantity)"));
                    customerSearchThrees.add(customerSearchThree);
                }
            } catch (SQLException throwables) {
                throw new GetSearchThreeException();
            }
        }
        return customerSearchThrees;
    }
    public ArrayList<Bill> getBill(Integer idBill) throws NumPersonException, GetBillException, IdBillException {
        ArrayList<Bill> bills = new ArrayList<>();
        Bill bill;
        if(connection != null){
            try{
                String sqlinstruction = "select * from bill where id_bill = ?;";
                PreparedStatement preparedStatement = connection.prepareStatement(sqlinstruction);
                preparedStatement.setInt(1,idBill);
                ResultSet data = preparedStatement.executeQuery();
                GregorianCalendar calendar = new GregorianCalendar();
                while(data.next()){
                    java.sql.Date dateBill = data.getDate("date_bill");
                    calendar.setTime(dateBill);
                    bill = new Bill(data.getInt("id_bill"), calendar, data.getBoolean("is_discount_before_deadline"),
                            data.getDouble("discount_before_deadline"),
                            data.getInt("discount_coupon"),data.getString("possible_remarks"),data.getInt("employee"),
                            data.getInt("customer"));
//                    bill = new Bill(data.getInt("id_bill"),data.getInt("employee"), data.getInt("customer"));

                    bills.add(bill);
                }
            }
            catch(SQLException e){
                throw new GetBillException();
            }
        }
        return bills;
    }
    public ArrayList<Listing> getListings(Integer idBill) {
        ArrayList<Listing> listings = new ArrayList<>();
        Listing listing;
        if(connection != null){
            try{
                String sqlInstruction = "select * from listing where bill = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
                preparedStatement.setInt(1,idBill);
                ResultSet data = preparedStatement.executeQuery();
                while(data.next()){
                    listing = new Listing(data.getInt("quantity"),data.getDouble("price"), data.getInt("bill"), data.getInt("article"));
                    listings.add(listing);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (IdArticleException e) {
                e.printStackTrace();
            } catch (QuantityException e) {
                e.printStackTrace();
            } catch (IdBillException e) {
                e.printStackTrace();
            } catch (PriceException e) {
                e.printStackTrace();
            }
        }
        return listings;
    }


    // setter
    public void setBill(Bill bill) throws IdBillException {
        if(connection != null){
            try{
                String sqlInstruction = "insert into bill value(?,?,?,?,?,?,?,?);";
                PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
                preparedStatement.setInt(1,bill.getIdBill());
                preparedStatement.setDate(2, new java.sql.Date(bill.getDateBill().getTimeInMillis()));
                preparedStatement.setBoolean(3,bill.isDiscountBeforeDeadLine());
                preparedStatement.setDouble(4,bill.getDiscount());
                preparedStatement.setInt(5,bill.getDiscountCoupon());
                preparedStatement.setString(6,bill.getRemarks());
                preparedStatement.setInt(7,bill.getEmployee());
                preparedStatement.setInt(8,bill.getCustomer());
                preparedStatement.executeUpdate();
            }
            catch(SQLException e){
                throw new IdBillException(bill.getIdBill());
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


    // méthode
    public Boolean deleteBill(Integer idBill) throws DeleteBillException {
        int rowCount = 0;
        if(connection != null){
            try{
                String sqlInstruction1 = "delete from listing where bill = ?;";
                String sqlInstruction2 = " delete from bill where id_bill = ?;";
                PreparedStatement preparedStatement1 = connection.prepareStatement(sqlInstruction1);
                preparedStatement1.setInt(1,idBill);
                rowCount += preparedStatement1.executeUpdate();
                PreparedStatement preparedStatement2 = connection.prepareStatement(sqlInstruction2);
                preparedStatement2.setInt(1,idBill);
                rowCount += preparedStatement2.executeUpdate();
            } catch (SQLException throwables) {
                throw new DeleteBillException();
            }
        }
        return rowCount == 0;
    }
}
