package view.classe.tableModel;

import model.tableModelTool.RowListing;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class MyTableModel extends AbstractTableModel {
    private ArrayList<String> columnNames;
    private ArrayList<RowListing> rowListings;

    // constructeur
    public MyTableModel( ArrayList<RowListing> rowListings){
        columnNames = new ArrayList<>();
        columnNames.add("Articles");
        columnNames.add("Quantité");
        columnNames.add("Prix unitaire HTVA");
        columnNames.add("Prix total HTVA");
        columnNames.add("TVA");
        columnNames.add("Prix total TVAC");
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
        RowListing rowListing = rowListings.get(rowIndex);
        switch (columnIndex){
            case 0 : return rowListing.getWording();
            case 1 : return rowListing.getQuantity();
            case 2 : return rowListing.getUnitPriceWVAT();
            case 3 : return rowListing.getTotalPriceWVAT();
            case 4 : return rowListing.getVAT();
            case 5 : return rowListing.getTotalPrice();
            default : return null;
        }
    }
    public Class getColumnClass(int column){
        Class c;
        switch (column) {
            case 0: return String.class;
            case 1: return Integer.class;
            default: return Double.class;
        }
    }
    @Override
    public String getColumnName(int column) {
        return columnNames.get(column);
    }

    private void setRowListings(ArrayList<RowListing> rowListings) {
        this.rowListings = rowListings;
    }
    public void setRow(RowListing rowListing){
        rowListings.add(rowListing);
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        RowListing row = rowListings.get(rowIndex);
        switch(columnIndex){
            case 1 : row.setQuantity((Integer) aValue);
//            case 2 : row.setUnitPriceWVAT((Double) aValue);
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex == 1;
    }
}
