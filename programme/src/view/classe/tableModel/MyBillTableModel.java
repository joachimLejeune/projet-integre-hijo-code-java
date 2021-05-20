package view.classe.tableModel;

import exception.IdBillException;
import exception.NumPersonException;
import model.originalDBClasse.Bill;
import model.tableModelTool.RowListing;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

public class MyBillTableModel extends AbstractTableModel {
    private ArrayList<String> columnNames;
    private ArrayList<Bill> rowBills;

    // constructeur
    public MyBillTableModel( ArrayList<Bill> rowBills){
        columnNames = new ArrayList<>();
        columnNames.add("Numéro de facture");
        columnNames.add("Date");
        columnNames.add("Escompte possible");
        columnNames.add("Valeur de l'escompte");
        columnNames.add("Coupon de réduction");
        columnNames.add("Remarque éventuelle");
        columnNames.add("Numéro d'employé");
        columnNames.add("Numéro de client");
        setRowBills(rowBills);

    }
    @Override
    public int getColumnCount() {
        if(columnNames != null){
            return columnNames.size();
        }
        return 0;
    }
    @Override
    public int getRowCount() {
        if(rowBills != null){
            return rowBills.size();
        }
        return 0;
    }
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Bill rowBill = rowBills.get(rowIndex);
        switch (columnIndex){
            case 0 : return rowBill.getIdBill();
            case 1 : return rowBill.getDateBillOtherDateFormat();
            case 2 : return rowBill.isDiscountBeforeDeadLine();
            case 3 : return rowBill.getDiscount();
            case 4 : return rowBill.getDiscountCoupon();
            case 5 : return rowBill.getPossibleRemarks();
            case 6 : return rowBill.getEmployee();
            case 7 : return rowBill.getCustomer();
            default : return null;
        }
    }
    public Class getColumnClass(int column){
        Class c;
        switch (column) {
            case 0: return Integer.class;
            case 1: return GregorianCalendar.class;
            case 2: return Boolean.class;
            case 3: return Double.class;
            case 4: return Integer.class;
            case 5: return String.class;
            default: return Integer.class;
        }
    }
    @Override
    public String getColumnName(int column) {
        return columnNames.get(column);
    }

    public void setRowBills(ArrayList<Bill> rowBills) {
        this.rowBills = rowBills;
    }
    public void setRow(Bill rowBill){
        rowBills.add(rowBill);
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Bill rowBill = rowBills.get(rowIndex);
        switch(columnIndex){
            case 0 :
                try {
                    rowBill.setIdBill((Integer) aValue);
                } catch (IdBillException e) {
                    e.printStackTrace();
                }
            case 1 : rowBill.setDateBill((GregorianCalendar)aValue);
            case 2 : rowBill.setDiscount((Boolean)aValue);
            case 3 : rowBill.setDiscount((Double)aValue);
            case 4 : rowBill.setDiscountCoupon((Integer)aValue);
            case 5 : rowBill.setRemarks((String)aValue);
            case 6 :
                try {
                    rowBill.setEmployee((Integer)aValue);
                } catch (NumPersonException e) {
                    e.printStackTrace();
                }
            case 7 :
                try {
                    rowBill.setCustomer((Integer)aValue);
                } catch (NumPersonException e) {
                    e.printStackTrace();
                }
        }
    }
}
