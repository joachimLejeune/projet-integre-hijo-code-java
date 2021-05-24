package view.classe.form;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class OurCompanyInformation extends JPanel {


    public OurCompanyInformation() {
        this.setLayout(new BorderLayout());

        JLabel informations = new JLabel("<html>" +
                " <p> Hijo Brico SA </p>" +
                " <p> Rue de la Joyeuseté 42 </p>" +
                " <p> 5000 Namur </p>" +
                " <p> 081 81 81 81 </p>" +
                " </html>");
        informations.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(informations,BorderLayout.CENTER);
    }
}
