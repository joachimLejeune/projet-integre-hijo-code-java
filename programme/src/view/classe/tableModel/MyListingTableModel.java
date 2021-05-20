package view.classe.tableModel;

import exception.*;
import model.originalDBClasse.Listing;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class MyListingTableModel extends AbstractTableModel {
    private ArrayList<String> columnNames;
    private ArrayList<Listing> rowListings;

    // constructeur
    public MyListingTableModel( ArrayList<Listing> rowListings){
        columnNames = new ArrayList<>();
        columnNames.add("Quantité ");
        columnNames.add("Prix unitaire HTVA");
        columnNames.add("Référence de la facture");
        columnNames.add("Référence de l'article");
        setRowListings(rowListings);
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
        if(rowListings != null){
            return rowListings.size();
        }
        return 0;
    }
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Listing rowListing = rowListings.get(rowIndex);
        switch(columnIndex){
            case 0 : return rowListing.getQuantity();
            case 1 : return rowListing.getPrice();
            case 2 : return rowListing.getIdBill();
            case 3 : return rowListing.getArticle();
            default : return null;
        }
    }
    public Class getColumnClass(int column){
        Class c;
        switch (column) {
            case 0: return Integer.class;
            case 1: return Double.class;
            default: return Integer.class;
        }
    }
    @Override
    public String getColumnName(int column) {
        return columnNames.get(column);
    }

    public void setRowListings(ArrayList<Listing> rowListings) {
        this.rowListings = rowListings;
    }
    public void setRow(Listing rowListing){
        rowListings.add(rowListing);
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Listing rowListing = rowListings.get(rowIndex);
        switch(columnIndex){
            case 0 :
                try {
                    rowListing.setQuantity((Integer)aValue);
                } catch (QuantityException e) {
                    e.printStackTrace();
                }
            case 1 :
                try {
                    rowListing.setPrice((Double)aValue);
                } catch (PriceException e) {
                    e.printStackTrace();
                }
            case 2 :
                try {
                    rowListing.setIdBill((Integer)aValue);
                } catch (IdBillException e) {
                    e.printStackTrace();
                }
            case 3 :
                try {
                    rowListing.setArticle((Integer)aValue);
                } catch (IdArticleException e) {
                    e.printStackTrace();
                }
        }
    }
}
