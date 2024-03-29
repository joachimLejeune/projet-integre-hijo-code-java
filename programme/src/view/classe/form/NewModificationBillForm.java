package view.classe.form;

import controller.ApplicationControler;
import model.originalDBClasse.Listing;
import model.tableModelTool.RowListing;
import view.classe.tableModel.MyTableModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class NewModificationBillForm extends JPanel { // en cours de modification mais sans la feature de modification d'une facture de NewBillRegistrationForm, la fonctionnalité est en attente

    private JPanel informationsFormPanel;
    private JPanel articlesButtonsFormPanel;
    private JPanel articlesFormPanel;
    private JPanel supplementsFormPanel;
    private static String compagnyAddress = "Rue de la Joyeuseté 42, 5000 Namur";
    private static Integer nbArticles = 0;
    private ApplicationControler controller; // servira à la communication avec la couche en dessous
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
    ArrayList<RowListing> rowListings = new ArrayList<>();
    ArrayList<String> columnNames;
    MyTableModel myTableModel;
    Listing listing;
    Integer quantity;
    Double price;
    Integer idBill;
    Integer idArticle;

    public NewModificationBillForm(){
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
    public JPanel InformationsFormPanelBuild(){
        JLabel idLabel, addressLabel, dateLabel, employeeLabel, customerLabel;
        JTextField idTextField, adressTextField;
        JSpinner dateSpinner;
        JComboBox employeeComboBox, customerComboBox;

        idLabel = new JLabel("Numéro de la facture :");
        idTextField = new JTextField();
        idTextField.setEnabled(true);
        addressLabel = new JLabel("adresse de la société :");
        adressTextField = new JTextField(compagnyAddress);
        adressTextField.setEnabled(false);
        dateLabel = new JLabel("Date de facturation :");
        dateSpinner = new JSpinner(new SpinnerDateModel());
        employeeLabel = new JLabel("Employé :");
        employeeComboBox = new JComboBox();
        employeeComboBox.setEnabled(false);
        customerLabel = new JLabel("Client :");
        customerComboBox = new JComboBox();
        customerComboBox.setEnabled(false);

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

        informationsFormPanel.setBorder(BorderFactory.createLineBorder(Color.BLUE));

        return informationsFormPanel;
    }
    public JPanel ArticlesButtonsFormPanelBuild(){
        JButton searchButton;
        searchButton = new JButton("Rechercher");
        articlesButtonsFormPanel.add(searchButton);

        SearchButtonListener searchButtonListener = new SearchButtonListener();
        searchButton.addActionListener(searchButtonListener);

        return articlesButtonsFormPanel;
    }
    public JPanel ArticlesFormPanelBuild(){
        JTable listingArticles;
        String[] columnNames = {"Articles", "quantité","prix unitaire HTVA","prix total HTVA","TVA", "Prix total TVAC"};
        listingArticles = new JTable();
        articlesFormPanel.add(listingArticles);

        articlesFormPanel.setBorder(BorderFactory.createLineBorder(Color.RED));

        return articlesFormPanel;
    }
    public JPanel SupplementsFormPanel(){
        JTextArea notices;
        JLabel discountDeadLineLabel;
        JCheckBox discountDeadLineCheckBox;
        JComboBox discountDeadLineValue;
        JPanel discountDeadLineGroup;
        JButton validateButton;

        supplementsFormPanel.setLayout(new BorderLayout());

        notices = new JTextArea();
        supplementsFormPanel.add(notices, BorderLayout.NORTH);

        discountDeadLineGroup = new JPanel();
        discountDeadLineGroup.setLayout(new FlowLayout());
        discountDeadLineLabel = new JLabel("Escompte :");
        discountDeadLineCheckBox = new JCheckBox();
        discountDeadLineValue = new JComboBox();
        discountDeadLineGroup.add(discountDeadLineLabel);
        discountDeadLineGroup.add(discountDeadLineCheckBox);
        discountDeadLineGroup.add(discountDeadLineValue);

        supplementsFormPanel.add(discountDeadLineGroup,BorderLayout.CENTER);

        validateButton = new JButton("Valider la modification");
        supplementsFormPanel.add(validateButton,BorderLayout.SOUTH);

        supplementsFormPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        return supplementsFormPanel;
    }

    public class SearchButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            // on test si on a rentré un numéro de facture
            if(((JTextField)informationsFormPanel.getComponent(1)).getText().length()<=0){
                JOptionPane.showMessageDialog(null,"Vous avez oublié de mettre le numéro de la facture");
            }
            else{
                articlesButtonsFormPanel.removeAll();
                JButton addArticleButton, modArticleButton, delArticleButton;
                addArticleButton = new JButton("Ajouter un article");
                modArticleButton = new JButton("Modifier un article");
                delArticleButton = new JButton("Supprimer un article");
                articlesButtonsFormPanel.add(addArticleButton,BorderLayout.WEST);
                articlesButtonsFormPanel.add(modArticleButton,BorderLayout.CENTER);
                articlesButtonsFormPanel.add(delArticleButton,BorderLayout.EAST);

                articlesButtonsFormPanel.setBorder(BorderFactory.createLineBorder(Color.GREEN));
            }

        }
    }
}
