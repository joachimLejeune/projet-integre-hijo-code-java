package view.classe.form;

import controller.ApplicationControler;
import exception.*;
import model.originalDBClasse.Customer;
import model.tableModelTool.SearchOne;
import view.classe.tableModel.MySearchOneTableModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class SearchOneForm extends JPanel {
    private JLabel indicationLabel, firstDateLabel, lastDateLabel;
    private JComboBox customerServedComboBox;
    private JSpinner firstDate;
    private JSpinner lastDate;
    private JButton validateButton;
    private JTable listingSearchOne;
    private JPanel researchValues;
    private JPanel listingSearchOnePanel;


    private ApplicationControler controller;


    JScrollPane scrollPane;
    ArrayList<SearchOne> rowSearchOnes = new ArrayList<>();
    MySearchOneTableModel mySearchOneTableModel;

    public SearchOneForm() throws PhoneNumberException, AllCustomersException, NumPersonException, EmailException {
        this.setLayout(new BorderLayout());
        researchValues = new JPanel();
        researchValues.setLayout(new GridLayout(4,2));

        indicationLabel = new JLabel("Choisissez le client :");
        customerServedComboBox = new JComboBox();
        validateButton = new JButton("Rechercher");
        firstDateLabel = new JLabel("Début de la période recherchée");
        firstDate = new JSpinner(new SpinnerDateModel());
        lastDateLabel = new JLabel("Fin de la période recherchée");
        lastDate = new JSpinner(new SpinnerDateModel());

        researchValues.add(indicationLabel);
        researchValues.add(customerServedComboBox);
        researchValues.add(firstDateLabel);
        researchValues.add(firstDate);
        researchValues.add(lastDateLabel);
        researchValues.add(lastDate);
        researchValues.add(validateButton);

        this.add(researchValues,BorderLayout.NORTH);

        mySearchOneTableModel = new MySearchOneTableModel(rowSearchOnes);
        listingSearchOne = new JTable(mySearchOneTableModel);
        scrollPane = new JScrollPane(listingSearchOne);
        scrollPane.setPreferredSize(new Dimension(600,350));
        listingSearchOne.setFillsViewportHeight(true);

        listingSearchOnePanel = new JPanel();
        listingSearchOnePanel.add(scrollPane);
        this.add(listingSearchOnePanel, BorderLayout.CENTER);

        setController(new ApplicationControler());
        ArrayList<Customer> customers = controller.getAllCustomers();
        for(Customer customer : customers){
            customerServedComboBox.addItem(customer.getNumCustomer() + " " + customer.getFirstName() + " " + customer.getLastName());
        }

//         on gère le clic sur valider
        SearchOneForm.ValidateButtonListener validatButtonListener = new SearchOneForm.ValidateButtonListener(this);
        validateButton.addActionListener(validatButtonListener);

    }
    // setter
    private void setController(ApplicationControler applicationControler) {
        this.controller = applicationControler;
    }
        // listeners
    private class ValidateButtonListener implements ActionListener {
        private SearchOneForm searchOneForm;
        public ValidateButtonListener(SearchOneForm searchOneForm){
            this.searchOneForm = searchOneForm;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            Integer idCustomer = controller.getIdCustomer(customerServedComboBox.getSelectedItem().toString());
            GregorianCalendar firstDateRead = new GregorianCalendar(controller.getYearJSPinner(searchOneForm.firstDate),controller.getMonthJSPinner(searchOneForm.firstDate),
                    controller.getDayOfTheMonthJSPinner(searchOneForm.firstDate));
            GregorianCalendar lastDateRead = new GregorianCalendar(controller.getYearJSPinner(searchOneForm.lastDate),controller.getMonthJSPinner(searchOneForm.lastDate),
                    controller.getDayOfTheMonthJSPinner(searchOneForm.lastDate));
            ArrayList<SearchOne> employeesSearchOne = new ArrayList<>();

            try {
                employeesSearchOne = controller.getSearchOne(firstDateRead,lastDateRead,idCustomer);
            } catch (GetSearchOneException getSearchOneException) {
                getSearchOneException.printStackTrace();
            }
            if(employeesSearchOne.size() == 0){
                JOptionPane.showMessageDialog(null, "Personne n'a servi ce client dans le laps de temps spécifié.");
            }
            else{
                mySearchOneTableModel.setRowSearchOnes(employeesSearchOne);
            }
            searchOneForm.repaint();
        }
    }
}
