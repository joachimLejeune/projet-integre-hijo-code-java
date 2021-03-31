package userInterface.classe;

import javax.swing.*;
import java.awt.*;

public class OurCompanyInformation extends JPanel {
    private JLabel adress;
    private JLabel townPostalCode;
    private JLabel phone;

    public OurCompanyInformation() {
        this.setLayout(new FlowLayout());
        adress = new JLabel("Rue de la Joyeuseté 42");
        townPostalCode = new JLabel("5000 Namur");
        phone = new JLabel("081 81 81 81");
        adress.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        townPostalCode.setBorder(BorderFactory.createLineBorder(Color.ORANGE));
        phone.setBorder(BorderFactory.createLineBorder(Color.GREEN));
        this.add(adress);
        this.add(townPostalCode);
        this.add(phone);
    }
}
