package view.classe.form;

import controller.*;
import exception.*;
import model.originalDBClasse.*;
import model.tableModelTool.RowListing;
import view.classe.tableModel.MyTableModel;
import view.classe.window.PointsWindow;
import view.classe.window.ResearchArticleWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class NewBillRegistrationForm extends JPanel {
    private JPanel informationsFormPanel;
    private JPanel articlesButtonsFormPanel;
    private JPanel articlesFormPanel;
    private JPanel supplementsFormPanel;
    private static final String companyAddress = "Rue de la Joyeuseté 42, 5000 Namur";
    private ApplicationControler controller; // servira à la communication avec la couche en dessous
    private double totalBill = 0.0;
    JLabel idLabel, addressLabel, dateLabel, employeeLabel, customerLabel,totalPriceBillLabel,discountDeadLineLabel,discountCouponLabel,noticesLabel;
    JTextField idTextField, adressTextField,totalPriceBill,discountCoupon;
    JSpinner dateSpinner;
    JComboBox employeeComboBox;
    JComboBox customerComboBox;
    JComboBox discountDeadLineValue;
    JButton addArticleButton, modArticleButton, delArticleButton,validateButton, totalButton;
    JTextArea notices;
    JCheckBox discountDeadLineCheckBox;
    JPanel discountDeadLineGroup;
    JScrollPane scrollPane;
    JTable listingArticles;
    ArrayList<RowListing> rowListings = new ArrayList<>();
    ArrayList<String> columnNames;
    MyTableModel myTableModel;
    Listing listing;
    Integer quantity;
    Double price;
    Integer idBill;
    Integer idArticle;


    // constructeur
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
        adressTextField = new JTextField(companyAddress);
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
        modArticleButton.setEnabled(false);
        delArticleButton = new JButton("Supprimer un article");
        delArticleButton.setEnabled(false);

        // on gère le clic sur ajouter un article
        AddArticleListener addArticleListener = new AddArticleListener();
        addArticleButton.addActionListener(addArticleListener);

        // on gère le clic sur modifier un article
        ModArticleListener modArticleListener = new ModArticleListener();
        modArticleButton.addActionListener(modArticleListener);


        articlesButtonsFormPanel.add(addArticleButton,BorderLayout.WEST);
        articlesButtonsFormPanel.add(modArticleButton,BorderLayout.CENTER);
        articlesButtonsFormPanel.add(delArticleButton,BorderLayout.EAST);


        return articlesButtonsFormPanel;
    }
    public JPanel ArticlesFormPanelBuild(){
        myTableModel = new MyTableModel(rowListings);
        listingArticles = new JTable(myTableModel);
        scrollPane = new JScrollPane(listingArticles);
        scrollPane.setPreferredSize(new Dimension(600,350));
        listingArticles.setFillsViewportHeight(true);


        articlesFormPanel.add(scrollPane);


        return articlesFormPanel;
    }
    public JPanel SupplementsFormPanel(){
        supplementsFormPanel.setLayout(new BorderLayout());

//        totalPriceBillLabel = new JLabel("Prix total à payer TVAC");
//        totalPriceBill = new JTextField();
//        totalPriceBill.setColumns(6);
        totalButton = new JButton("Total TVAC");
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
//        discountDeadLineGroup.add(totalPriceBillLabel);
//        discountDeadLineGroup.add(totalPriceBill);
        discountDeadLineGroup.add(totalButton);

        supplementsFormPanel.add(noticesLabel,BorderLayout.WEST);
        supplementsFormPanel.add(notices, BorderLayout.CENTER);
        supplementsFormPanel.add(discountDeadLineGroup,BorderLayout.NORTH);
        supplementsFormPanel.add(validateButton,BorderLayout.SOUTH);

        // gestion de l'event lié au clic sur la case à cocher pour rendre ou non accessible les % d'escompte
        DiscountDeadLineListener discountDeadLineListener = new DiscountDeadLineListener(discountDeadLineValue);
        discountDeadLineCheckBox.addItemListener(discountDeadLineListener);

        // on gère l'event lié au clic sur le bouton valider la commande
        ValidateButtonListener validateButtonListener = new ValidateButtonListener();
        validateButton.addActionListener(validateButtonListener);

        // gestion de l'event lié au clic sur Total TVAC Button
        TotalButtonListener totalButtonListener = new TotalButtonListener();
        totalButton.addActionListener(totalButtonListener);

        return supplementsFormPanel;
    }

    // setter
    private void setController(ApplicationControler applicationControler) {
        this.controller = applicationControler;
    }

    // getter
    public double getTotalBill() {
        return totalBill;
    }

    public JPanel getArticlesFormPanel() {
        return articlesFormPanel;
    }

    // listener
    private static class DiscountDeadLineListener implements ItemListener{
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
            try {
                ResearchArticleWindow researchArticleWindow = new ResearchArticleWindow(NewBillRegistrationForm.this);
            } catch (GetAllArticlesException getAllArticlesException) {
                getAllArticlesException.getMessage();
            } catch (IdArticleException idArticleException) {
                idArticleException.getMessage();
            } catch (VATException vatException) {
                vatException.getMessage();
            } catch (PriceException priceException) {
                priceException.getMessage();
            } catch (NumAisleException numAisleException) {
                numAisleException.getMessage();
            }
        }
    }
    private class ModArticleListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            // à implémenter
        }
    }
    private class ValidateButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if(listingArticles.getRowCount()==0)
            {
                JOptionPane.showMessageDialog(null,"Vous ne pouvez pas envoyer de facture sans inclure d'article.","Champs obligatoires !",JOptionPane.WARNING_MESSAGE);
            }
            else{
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
                } catch (IdBillException idBillException) {
                    idBillException.getMessage();
                } catch (NumPersonException numPersonException) {
                    numPersonException.getMessage();
                }


                // on créer les composants des Listings
                ArrayList<Listing> listings = new ArrayList<>();
                Listing listing;

                for(int i=0;i<listingArticles.getRowCount();i++){
                    try {
                        listing = new Listing(Integer.valueOf(listingArticles.getValueAt(i,1).toString()),
                                Double.parseDouble(listingArticles.getValueAt(i,2).toString()),
                                numBill,
                                controller.getIdArticle(listingArticles.getValueAt(i,0).toString()));
                        listings.add(listing);
                    } catch (QuantityException quantityException) {
                        quantityException.printStackTrace();
                    } catch (PriceException priceException) {
                        priceException.printStackTrace();
                    } catch (IdArticleException idArticleException) {
                        idArticleException.printStackTrace();
                    } catch (IdBillException idBillException) {
                        idBillException.printStackTrace();
                    }
                }
                try {
                    controller.setListings(listings);
                } catch (SetListingsException setListingsException) {
                    setListingsException.printStackTrace();
                }
                JOptionPane.showMessageDialog(null,"La commande est validé et encodée","Etat de la commande",JOptionPane.INFORMATION_MESSAGE);
                NewBillRegistrationForm.this.addArticleButton.setEnabled(false);
                NewBillRegistrationForm.this.validateButton.setEnabled(false);
                NewBillRegistrationForm.this.repaint();
            }
            }

    }
    private class TotalButtonListener implements ActionListener{
        private Boolean inUse = false;
        private PointsWindow pointsWindow;
        @Override
        public void actionPerformed(ActionEvent e) {
            if(!inUse){
                pointsWindow = new PointsWindow(NewBillRegistrationForm.this);
                inUse = true;
            }
            else{
               pointsWindow.dispose();
               inUse = false;
            }

        }
    }

    // methodes
    public void addArticleInListingTable(Article article, Integer quantity){
        Double totalPriceWVAT = article.getPrice() * quantity;
        Double totalPrice = totalPriceWVAT + (totalPriceWVAT * article.getVAT());
        RowListing rowListing = new RowListing(article.getWording(),quantity, article.getPrice(), totalPriceWVAT, article.getVAT(), totalPrice);

        myTableModel.setRow(rowListing);
        listingArticles.repaint();
    }
    public void incTotalBill(double value){
        this.totalBill += value;
    }
}