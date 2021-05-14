package view.classe.tableModel;

import model.tableModelTool.SearchOne;
import model.tableModelTool.SearchThree;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class MySearchThreeTableModel extends AbstractTableModel {
    private ArrayList<String> columnNames;
    private ArrayList<SearchThree> rowSearchThrees;

    public MySearchThreeTableModel(ArrayList<SearchThree> rowSearchThrees){
        columnNames = new ArrayList<>();
        columnNames.add("Prénom");
        columnNames.add("Nom");
        columnNames.add("Somme totale");
        setRowSearchOnes(rowSearchThrees);
    }
    public void setRowSearchOnes(ArrayList<SearchThree> rowSearchThrees) {
        this.rowSearchThrees = rowSearchThrees;
    }

    public void setRow(SearchThree searchThree){
        rowSearchThrees.add(searchThree);
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
        if(rowSearchThrees != null){
            return rowSearchThrees.size();
        }
        return 0;
    }
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        SearchThree rowSearchThree = rowSearchThrees.get(rowIndex);
        switch (columnIndex){
            case 0 : return rowSearchThree.getFirstName();
            case 1 : return rowSearchThree.getLastName();
            case 2 : return rowSearchThree.getSumQuantity();
            default : return null;
        }
    }
    public Class getColumnClass(int column){
        Class c;
        switch (column) {
            case 2: return Integer.class;
            default: return String.class;
        }
    }
    @Override
    public String getColumnName(int column) {
        return columnNames.get(column);
    }

    public void setRowSearchThree(ArrayList<SearchThree> rowSearchThrees) {
        this.rowSearchThrees = rowSearchThrees;
    }
}
