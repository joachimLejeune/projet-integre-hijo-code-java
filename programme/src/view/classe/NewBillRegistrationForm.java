package view.classe;

import controller.*;
import exception.*;
import model.*;

import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class NewBillRegistrationForm extends JPanel {
    private JPanel informationsFormPanel;
    private JPanel articlesButtonsFormPanel;
    private JPanel articlesFormPanel;
    private JPanel supplementsFormPanel;
    private static String compagnyAddress = "Rue de la Joyeuseté 42, 5000 Namur";
    private static Integer nbArticles = 0;
    JLabel idLabel, addressLabel, dateLabel, employeeLabel, customerLabel,totalPriceBillLabel,discountDeadLineLabel,discountCouponLabel,noticesLabel;
    JTextField idTextField, adressTextField,totalPriceBill,discountCoupon;
    JSpinner dateSpinner;
    JComboBox employeeComboBox, customerComboBox,discountDeadLineValue;
    JButton addArticleButton, modArticleButton, delArticleButton,validateButton;
    JTextArea notices;
    JCheckBox discountDeadLineCheckBox;
    JPanel discountDeadLineGroup;
    JScrollPane scrollPane;
    JTable listingArticles;
    Object[][] rowData = new Object[50][6];
    String[] columnNames = {"Articles", "Quantité","Prix unitaire HTVA","Prix total HTVA","TVA", "Prix total TVAC"};

    private ApplicationControler controller; // servira à la communication avec la couche en dessous

    public NewBillRegistrationForm(){
        this.setLayout(new BorderLayout());
        informationsFormPanel = new JPanel();
        informationsFormPanel.setLayout(new GridLayout(5,2,5,5));
        informationsFormPanel = InformationsFormPanelBuild();
        this.add(informationsFormPanel, BorderLayout.NORTH);

        // on créer un Panel qui en contiendra deux
        JPanel groupButtonsListing = new JPanel();
        groupButtonsListing.setLayout(new BorderLayout());

        articlesButtonsFormPanel = new JPanel();
        articlesButtonsFormPanel = ArticlesButtonsFormPanelBuild();
        groupButtonsListing.add(articlesButtonsFormPanel,BorderLayout.NORTH);

        articlesFormPanel = new JPanel();
        articlesFormPanel = ArticlesFormPanelBuild();
        groupButtonsListing.add(articlesFormPanel, BorderLayout.CENTER);

        this.add(groupButtonsListing,BorderLayout.CENTER);

        supplementsFormPanel = new JPanel();
        supplementsFormPanel = SupplementsFormPanel();

        this.add(supplementsFormPanel,BorderLayout.SOUTH);

    }

    // méthode de construction
    public JPanel InformationsFormPanelBuild(){

        idLabel = new JLabel("Numéro de la facture :",SwingConstants.CENTER);
        idTextField = new JTextField();
        idTextField.setEnabled(false);
        addressLabel = new JLabel("adresse de la société :",SwingConstants.CENTER);
        adressTextField = new JTextField(compagnyAddress);
        adressTextField.setEnabled(false);
        dateLabel = new JLabel("Date de facturation :",SwingConstants.CENTER);
        dateSpinner = new JSpinner(new SpinnerDateModel());
        employeeLabel = new JLabel("Employé :",SwingConstants.CENTER);
        employeeComboBox = new JComboBox();
        customerLabel = new JLabel("Client :",SwingConstants.CENTER);
        customerComboBox = new JComboBox();

        setController(new ApplicationControler());
        try{
            ArrayList<Employee> employees = controller.getAllEmployees();
            for(Employee employeeRead : employees){
                employeeComboBox.addItem(employeeRead.getNumEmployee() + " " + employeeRead.getFirstName() + " " +employeeRead.getLastName());
            }
            ArrayList<Customer> customers = controller.getAllCustomers();
            for(Customer customerRead : customers){
                customerComboBox.addItem( customerRead.getNumCustomer() + " " + customerRead.getFirstName() + " " + customerRead.getLastName());
            }
            Integer lastIdBill = controller.getNextIdBill();
            idTextField.setText(Integer.toString(lastIdBill));
        }
        catch (AllEmployeesException e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        } catch (EmailException e) {
            e.printStackTrace();
        } catch (PhoneNumberException e) {
            e.printStackTrace();
        } catch (NumPersonException e) {
            e.printStackTrace();
        } catch (AllCustomersException e) {
            e.printStackTrace();
        } catch (GetNextIdBillException e) {
            e.printStackTrace();
        }


        informationsFormPanel.add(idLabel);
        informationsFormPanel.add(idTextField);
        informationsFormPanel.add(addressLabel);
        informationsFormPanel.add(adressTextField);
        informationsFormPanel.add(dateLabel);
        informationsFormPanel.add(dateSpinner);
        informationsFormPanel.add(employeeLabel);
        informationsFormPanel.add(employeeComboBox);
        informationsFormPanel.add(customerLabel);
        informationsFormPanel.add(customerComboBox);

        return informationsFormPanel;
    }
    public JPanel ArticlesButtonsFormPanelBuild(){

        addArticleButton = new JButton("Ajouter un article");
        modArticleButton = new JButton("Modifier un article");
        delArticleButton = new JButton("Supprimer un article");

        // on gère le clic sur ajouter un article
        AddArticleListener addArticleListener = new AddArticleListener();
        addArticleButton.addActionListener(addArticleListener);


        articlesButtonsFormPanel.add(addArticleButton,BorderLayout.WEST);
        articlesButtonsFormPanel.add(modArticleButton,BorderLayout.CENTER);
        articlesButtonsFormPanel.add(delArticleButton,BorderLayout.EAST);


        return articlesButtonsFormPanel;
    }
    public JPanel ArticlesFormPanelBuild(){
        listingArticles = new JTable(rowData,columnNames);
        scrollPane = new JScrollPane(listingArticles);
        scrollPane.setPreferredSize(new Dimension(600,350));

        articlesFormPanel.add(scrollPane);


        return articlesFormPanel;
    }
    public JPanel SupplementsFormPanel(){
        supplementsFormPanel.setLayout(new BorderLayout());

        totalPriceBillLabel = new JLabel("Prix total à payer");
        totalPriceBill = new JTextField();
        totalPriceBill.setColumns(6);
        noticesLabel = new JLabel("Remarques : ");
        notices = new JTextArea();
        discountDeadLineGroup = new JPanel();
        discountDeadLineGroup.setLayout(new FlowLayout());
        discountDeadLineLabel = new JLabel("Escompte :");
        discountDeadLineCheckBox = new JCheckBox();
        validateButton = new JButton("Valider la commande");

        String[] discountDeadLineValues = {"0.02","0.04","0.06","0.08"};
        discountDeadLineValue = new JComboBox(discountDeadLineValues);
        discountDeadLineValue.setEnabled(false);

        discountCouponLabel = new JLabel("Coupon de réduction :");
        discountCoupon = new JTextField();
        discountCoupon.setColumns(6);

        discountDeadLineGroup.add(discountDeadLineLabel);
        discountDeadLineGroup.add(discountDeadLineCheckBox);
        discountDeadLineGroup.add(discountDeadLineValue);
        discountDeadLineGroup.add(discountCouponLabel);
        discountDeadLineGroup.add(discountCoupon);
        discountDeadLineGroup.add(totalPriceBillLabel);
        discountDeadLineGroup.add(totalPriceBill);

        supplementsFormPanel.add(noticesLabel,BorderLayout.WEST);
        supplementsFormPanel.add(notices, BorderLayout.CENTER);
        supplementsFormPanel.add(discountDeadLineGroup,BorderLayout.NORTH);
        supplementsFormPanel.add(validateButton,BorderLayout.SOUTH);

        // gestion de l'event lié au clic sur la case à cocher pour rendre ou non accessible les % d'escompte
        DiscountDeadLineListener discountDeadLineListener = new DiscountDeadLineListener(discountDeadLineValue);
        discountDeadLineCheckBox.addItemListener(discountDeadLineListener);

        // on gère l'event lié au clic sur le bouton vlaider la commande
        ValidateButtonListener validateButtonListener = new ValidateButtonListener();
        validateButton.addActionListener(validateButtonListener);

        return supplementsFormPanel;
    }

    // setter
    private void setController(ApplicationControler applicationControler) {
        this.controller = applicationControler;
    }

    // getter

    // listener
    private class DiscountDeadLineListener implements ItemListener{
        private JComboBox discountValues;
        public DiscountDeadLineListener(JComboBox discountValues){
            this.discountValues = discountValues;
        }

        @Override
        public void itemStateChanged(ItemEvent e) {
            discountValues.setEnabled(e.getStateChange() == ItemEvent.SELECTED);
        }
    }
    private class AddArticleListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            ResearchArticleWindow researchArticleWindow = new ResearchArticleWindow(NewBillRegistrationForm.this);
        }
    }
    private class ValidateButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            // on créer les composants de Bill
            Bill bill;
            Integer numBill;
            GregorianCalendar dateBill;
            Boolean isDiscountBeforeDeadline;
            Double discountBeforeDeadline = 0.00;
            Integer discountCouponRead = 0;
            String possibleNotices = "aucune remarque";
            Integer idEmployee;
            Integer idCustomer;

            numBill = Integer.valueOf(idTextField.getText());
            dateBill = new GregorianCalendar(controller.getYearJSPinner(dateSpinner),controller.getMonthJSPinner(dateSpinner),controller.getDayOfTheMonthJSPinner(dateSpinner));
            isDiscountBeforeDeadline = discountDeadLineCheckBox.isSelected();
            if(isDiscountBeforeDeadline){
                discountBeforeDeadline = Double.parseDouble(discountDeadLineValue.getSelectedItem().toString());
            }
            if(!discountCoupon.getText().equals("")){
                discountCouponRead = Integer.valueOf(discountCoupon.getText());
            }
            if(!notices.getText().equals("")){
                possibleNotices = notices.getText();
            }
            idEmployee = controller.getIdEmployee(employeeComboBox.getSelectedItem().toString());
            idCustomer = controller.getIdCustomer(customerComboBox.getSelectedItem().toString());

            try {
                bill = new Bill(numBill,dateBill,isDiscountBeforeDeadline,discountBeforeDeadline,discountCouponRead,possibleNotices,idEmployee,idCustomer);
                controller.setBill(bill);
                JOptionPane.showMessageDialog(null, bill.getDateBill().getTimeInMillis());
            } catch (IdBillException idBillException) {
                idBillException.printStackTrace();
            } catch (NumPersonException numPersonException) {
                numPersonException.printStackTrace();
            }



            JOptionPane.showMessageDialog(null, "idEmployee : " + idEmployee + " idCustomer : " + idCustomer);

            // on créer les composants du Listing
            Listing listing;


        }
    }

    // methods
    public void addArticleInListingTable(Article article, Integer quantity){
        rowData[nbArticles][0] = article.getWording();
        rowData[nbArticles][1] = quantity;
        rowData[nbArticles][2] = article.getPrice();
        Double totalPriceWVAT = (Double)(article.getPrice()) * quantity;
        rowData[nbArticles][3] = totalPriceWVAT;
        rowData[nbArticles][4] = article.getVAT();
        rowData[nbArticles][5] = totalPriceWVAT + (totalPriceWVAT * article.getVAT());
        nbArticles++;
        listingArticles.repaint();
    }
}


// penser à ajouter un cadre pour afficher le prix total à payer à la fin qui se met à jour dès qu'une nouvelle ligne se rajoute dans le listing