package view.classe;

import controller.*;
import exception.*;
import model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

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


    private void setController(ApplicationControler applicationControler) {
        this.controller = applicationControler;
    }

    // méthode de construction
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

        setController(new ApplicationControler());
        try{
            ArrayList<Employee> employees = controller.getAllEmployees();
            for(Employee employeeRead : employees){
                employeeComboBox.addItem(employeeRead.getFirstName() + " " +employeeRead.getLastName());
            }
            ArrayList<Customer> customers = controller.getAllCustomers();
            for(Customer customerRead : customers){
                customerComboBox.addItem(customerRead.getFirstName() + " " + customerRead.getLastName());
            }
            Integer lastIdBill = controller.getLastIdBill();
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

        informationsFormPanel.setBorder(BorderFactory.createLineBorder(Color.BLUE));

        return informationsFormPanel;
    }
    public JPanel ArticlesButtonsFormPanelBuild(){
        JButton addArticleButton, modArticleButton, delArticleButton;
        addArticleButton = new JButton("Ajouter un article");
        modArticleButton = new JButton("Modifier un article");
        delArticleButton = new JButton("Supprimer un article");

        // on gère le clic sur ajouter un article
        AddArticleListener addArticleListener = new AddArticleListener();
        addArticleButton.addActionListener(addArticleListener);


        articlesButtonsFormPanel.add(addArticleButton,BorderLayout.WEST);
        articlesButtonsFormPanel.add(modArticleButton,BorderLayout.CENTER);
        articlesButtonsFormPanel.add(delArticleButton,BorderLayout.EAST);

        articlesButtonsFormPanel.setBorder(BorderFactory.createLineBorder(Color.GREEN));

        return articlesButtonsFormPanel;
    }
    public JPanel ArticlesFormPanelBuild(){
        JTable listingArticles;
        String[] columnNames = {"Articles", "Quantité","Prix unitaire HTVA","Prix total HTVA","TVA", "Prix total TVAC"};
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
        JLabel discountCouponLabel;
        JTextField discountCoupon;
        JPanel discountDeadLineGroup;
        JButton validateButton;

        supplementsFormPanel.setLayout(new BorderLayout());

        notices = new JTextArea();
        supplementsFormPanel.add(notices, BorderLayout.NORTH);

        discountDeadLineGroup = new JPanel();
        discountDeadLineGroup.setLayout(new FlowLayout());

        discountDeadLineLabel = new JLabel("Escompte :");

        discountDeadLineCheckBox = new JCheckBox();

        String[] discountDeadLineValues = {"0.02","0.04","0.06","0.08"};
        discountDeadLineValue = new JComboBox(discountDeadLineValues);
        discountDeadLineValue.setEnabled(false);

        // gestion de l'event lié au clic sur la case à cocher pour rendre ou non accessible les % d'escompte
        DiscountDeadLineListener discountDeadLineListener = new DiscountDeadLineListener(discountDeadLineValue);
        discountDeadLineCheckBox.addItemListener(discountDeadLineListener);

        discountCouponLabel = new JLabel("Coupon de réduction :");
        discountCoupon = new JTextField();
        discountCoupon.setColumns(6);

        discountDeadLineGroup.add(discountDeadLineLabel);
        discountDeadLineGroup.add(discountDeadLineCheckBox);
        discountDeadLineGroup.add(discountDeadLineValue);
        discountDeadLineGroup.add(discountCouponLabel);
        discountDeadLineGroup.add(discountCoupon);

        supplementsFormPanel.add(discountDeadLineGroup,BorderLayout.CENTER);

        validateButton = new JButton("Valider la commande");
        supplementsFormPanel.add(validateButton,BorderLayout.SOUTH);

        supplementsFormPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        return supplementsFormPanel;
    }

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
            ResearchArticleWindow researchArticleWindow = new ResearchArticleWindow();
        }
    }
}
