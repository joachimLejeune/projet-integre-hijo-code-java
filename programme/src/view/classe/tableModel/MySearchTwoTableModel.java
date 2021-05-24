package view.classe.tableModel;

import model.tableModelTool.SearchOne;
import model.tableModelTool.SearchTwo;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class MySearchTwoTableModel extends AbstractTableModel {

    private ArrayList<String> columnNames;
    private ArrayList<SearchTwo> rowSearchTwos;

    public MySearchTwoTableModel(ArrayList<SearchTwo> rowSearchTwos){
        columnNames = new ArrayList<>();
        columnNames.add("Prénom");
        columnNames.add("Nom");
//        demander dans l'enoncé de base, mais impossible à afficher pour l'instant
//        columnNames.add("Libellé");
//        columnNames.add("Quantité");
        setRowSearchTwos(rowSearchTwos);
    }
    public void setRowSearchTwos(ArrayList<SearchTwo> rowSearchTwos) {
        this.rowSearchTwos = rowSearchTwos;
    }

    public void setRow(SearchTwo searchTwo){
        rowSearchTwos.add(searchTwo);
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
        if(rowSearchTwos != null){
            return rowSearchTwos.size();
        }
        return 0;
    }
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        SearchTwo rowSearchTwo = rowSearchTwos.get(rowIndex);
        switch (columnIndex){
            case 0 : return rowSearchTwo.getFirstName();
            case 1 : return rowSearchTwo.getLastName();
//            case 2 : return rowSearchTwo.getWording();
//            case 3 : return rowSearchTwo.getQuantity();
            default : return null;
        }
    }
    public Class getColumnClass(int column){
        Class c;
        switch (column) {
//            case 3: return Integer.class;
            default: return String.class;
        }
    }
    @Override
    public String getColumnName(int column) {
        return columnNames.get(column);
    }
}
