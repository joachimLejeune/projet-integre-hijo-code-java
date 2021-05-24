package view.classe.window;

import view.classe.form.CounterPanelForm;
import view.classe.form.NewBillRegistrationForm;

import javax.swing.*;
import java.awt.*;

public class TotalBillWindow extends JFrame {
    private CounterPanelForm counterPanelForm;
    private Container container;

    public TotalBillWindow(NewBillRegistrationForm newBillRegistrationForm){
        super("Total de la facture");
        setBounds(1400,200,250,100);

        counterPanelForm = new CounterPanelForm(newBillRegistrationForm);
        container = this.getContentPane();
        this.add(counterPanelForm);

        this.setVisible(true);
    }
}
