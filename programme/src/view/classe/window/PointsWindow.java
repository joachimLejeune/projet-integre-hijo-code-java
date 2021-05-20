package view.classe.window;

import view.classe.form.CounterPanelForm;
import view.classe.form.NewBillRegistrationForm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class PointsWindow extends JFrame {
    private CounterPanelForm counterPanelForm;
    private Container container;
    private NewBillRegistrationForm newBillRegistrationForm;

    public PointsWindow(NewBillRegistrationForm newBillRegistrationForm){
        super("Total de la facture");
        setBounds(1400,200,250,100);

        counterPanelForm = new CounterPanelForm(newBillRegistrationForm);
        container = this.getContentPane();
        this.add(counterPanelForm);

        this.setVisible(true);
    }
}
