package userInterface.classe;

import javax.swing.*;
import java.awt.*;

public class NewBillRegistrationForm extends JPanel {
    private JPanel informationsFormPanel;
    private JPanel articlesFormPanel;
    private JPanel supplementsFormPanel;
    private static String compagnyAddress = "Rue de la Joyeuseté 42";

    public NewBillRegistrationForm(){
        this.setLayout(new BorderLayout());
        informationsFormPanel = new JPanel();
        informationsFormPanel.setLayout(new GridLayout(4,2,5,5));
        informationsFormPanel = InformationsFormPanelBuild();
        this.add(informationsFormPanel,BorderLayout.NORTH);
    }
    public JPanel InformationsFormPanelBuild(){
        JLabel idLabel, addressLabel, dateLabel, employeeLabel, customerLabel;
        JTextField idTextField, adressTextField;
        JSpinner dateSpinner;
        JComboBox employeeComboBox, customerComboBox;

        idLabel = new JLabel("Numéro de la facture");
        idTextField = new JTextField();
        addressLabel = new JLabel("adresse de la société");

        informationsFormPanel.add(idLabel);
        informationsFormPanel.add(idTextField);
        informationsFormPanel.add(addressLabel);

        informationsFormPanel.setBorder(BorderFactory.createLineBorder(Color.BLUE));

        return informationsFormPanel;
    }
}
