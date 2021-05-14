package view.classe.tableModel;

import model.tableModelTool.SearchOne;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

public class MySearchOneTableModel extends AbstractTableModel {
    private ArrayList<String> columnNames;
    private ArrayList<SearchOne> rowSearchOnes;

    public MySearchOneTableModel(ArrayList<SearchOne> rowSearchOnes){
        columnNames = new ArrayList<>();
        columnNames.add("Prénom");
        columnNames.add("Nom");
        columnNames.add("Date");
        setRowSearchOnes(rowSearchOnes);
    }

    public void setRowSearchOnes(ArrayList<SearchOne> rowSearchOne) {
        this.rowSearchOnes = rowSearchOne;
    }

    public void setRow(SearchOne searchOne){
        rowSearchOnes.add(searchOne);
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
        if(rowSearchOnes != null){
            return rowSearchOnes.size();
        }
        return 0;
    }
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        SearchOne rowSearchOne = rowSearchOnes.get(rowIndex);
        switch (columnIndex){
            case 0 : return rowSearchOne.getFirstNameCustomer();
            case 1 : return rowSearchOne.getLastNameCustomer();
            case 2 : return rowSearchOne.getDateBill();
            default : return null;
        }
    }
    public Class getColumnClass(int column){
        Class c;
        switch (column) {
            case 2: return GregorianCalendar.class;
            default: return String.class;
        }
    }
    @Override
    public String getColumnName(int column) {
        return columnNames.get(column);
    }
}
