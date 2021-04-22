package userInterface.classe;

import controller.*;
import model.*;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.Calendar;
import java.util.Date;

public class NewBillRegistrationForm extends JPanel {
    private JPanel informationsFormPanel;
    private JPanel articlesButtonsFormPanel;
    private JPanel articlesFormPanel;
    private JPanel supplementsFormPanel;
    private static String compagnyAddress = "Rue de la Joyeuseté 42, 5000 Namur";

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
    public JPanel InformationsFormPanelBuild(){
        JLabel idLabel, addressLabel, dateLabel, employeeLabel, customerLabel;
        JTextField idTextField, adressTextField;
        JSpinner dateSpinner;
        JComboBox employeeComboBox, customerComboBox;

        idLabel = new JLabel("Numéro de la facture :");
        idTextField = new JTextField();
        idTextField.setEnabled(false);
        addressLabel = new JLabel("adresse de la société :");
        adressTextField = new JTextField(compagnyAddress);
        adressTextField.setEnabled(false);
        dateLabel = new JLabel("Date de facturation :");
        dateSpinner = new JSpinner(new SpinnerDateModel());
        employeeLabel = new JLabel("Employé :");
        employeeComboBox = new JComboBox();
        customerLabel = new JLabel("Client :");
        customerComboBox = new JComboBox();


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
        JButton addArticleButton, modArticleButton, delArticleButton;
        addArticleButton = new JButton("Ajouter un article");
        modArticleButton = new JButton("Modifier un article");
        delArticleButton = new JButton("Supprimer un article");
        articlesButtonsFormPanel.add(addArticleButton,BorderLayout.WEST);
        articlesButtonsFormPanel.add(modArticleButton,BorderLayout.CENTER);
        articlesButtonsFormPanel.add(delArticleButton,BorderLayout.EAST);

        articlesButtonsFormPanel.setBorder(BorderFactory.createLineBorder(Color.GREEN));

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

        validateButton = new JButton("Valider la commande");
        supplementsFormPanel.add(validateButton,BorderLayout.SOUTH);

        supplementsFormPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        return supplementsFormPanel;
    }
}
