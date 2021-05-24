package view.classe.form;

import controller.ApplicationControler;
import exception.AllEmployeesException;
import exception.EmailException;
import exception.NumPersonException;
import exception.PhoneNumberException;
import model.originalDBClasse.Customer;
import model.originalDBClasse.Employee;
import model.tableModelTool.SearchOne;
import model.tableModelTool.SearchTwo;
import view.classe.tableModel.MySearchTwoTableModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class SearchTwoForm extends JPanel {
    private JLabel label, firstDateLabel, lastDateLabel;
    private JSpinner firstDate;
    private JSpinner lastDate;
    private JButton validateButton;
    private JTable listingSearchTwo;
    private JPanel researchValues;
    private JPanel listingSearchTwoPanel;

    private ApplicationControler controller;


    JScrollPane scrollPane;
    ArrayList<SearchTwo> rowSearchTwos = new ArrayList<>();
    MySearchTwoTableModel mySearchTwoTableModel;

    public SearchTwoForm() throws PhoneNumberException, EmailException, AllEmployeesException, NumPersonException {
        this.setLayout(new BorderLayout());
        researchValues = new JPanel();
        researchValues.setLayout(new GridLayout(4,2));

        label = new JLabel("Recherche du top trois des employés : ");
        firstDateLabel = new JLabel("Date de début de la recherche : ");
        lastDateLabel = new JLabel("date de fin de la recherche : ");
        firstDate = new JSpinner(new SpinnerDateModel());
        lastDate = new JSpinner(new SpinnerDateModel());
        validateButton = new JButton("Valider");

        researchValues.add(label);
        researchValues.add(new JLabel()); // pour mettre un espace blanc dans le gridlayout
        researchValues.add(firstDateLabel);
        researchValues.add(firstDate);
        researchValues.add(lastDateLabel);
        researchValues.add(lastDate);
        researchValues.add(validateButton);

        this.add(researchValues, BorderLayout.NORTH);

        mySearchTwoTableModel = new MySearchTwoTableModel(rowSearchTwos);
        listingSearchTwo = new JTable(mySearchTwoTableModel);
        scrollPane = new JScrollPane(listingSearchTwo);
        scrollPane.setPreferredSize(new Dimension(600,350));
        listingSearchTwo.setFillsViewportHeight(true);

        listingSearchTwoPanel = new JPanel();
        listingSearchTwoPanel.add(scrollPane);
        this.add(listingSearchTwoPanel, BorderLayout.CENTER);

        setController(new ApplicationControler());
        ArrayList<Employee> employees = controller.getAllEmployees();

//        on gère le clic sur valider
        ValidateButtonListener validatButtonListener = new ValidateButtonListener(this);
        validateButton.addActionListener(validatButtonListener);

    }
    // setter
    private void setController(ApplicationControler applicationControler) {
        this.controller = applicationControler;
    }
    private class ValidateButtonListener implements ActionListener{
        private SearchTwoForm searchTwoForm;
        public ValidateButtonListener(SearchTwoForm searchTwoForm){
            this.searchTwoForm = searchTwoForm;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            GregorianCalendar firstDateRead = new GregorianCalendar(controller.getYearJSPinner(searchTwoForm.firstDate),controller.getMonthJSPinner(searchTwoForm.firstDate),
                    controller.getDayOfTheMonthJSPinner(searchTwoForm.firstDate));
            GregorianCalendar lastDateRead = new GregorianCalendar(controller.getYearJSPinner(searchTwoForm.lastDate),controller.getMonthJSPinner(searchTwoForm.lastDate),
                    controller.getDayOfTheMonthJSPinner(searchTwoForm.lastDate));
            ArrayList<SearchTwo> topThreeInformations = new ArrayList<>();
            topThreeInformations = controller.getSearchTwo(firstDateRead, lastDateRead);
            if(topThreeInformations.size() == 0){
                JOptionPane.showMessageDialog(null, "Personne n'as vendu pendant cette période");
            }
            else{
                mySearchTwoTableModel.setRowSearchTwos(topThreeInformations);
            }
            searchTwoForm.repaint();
        }
    }
}
