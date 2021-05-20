package view.classe.form;

import view.classe.threads.TotalBillCountingThread;

import javax.swing.*;
import java.awt.*;

public class CounterPanelForm extends JPanel {
    private NewBillRegistrationForm newBillRegistrationForm;

    public CounterPanelForm(NewBillRegistrationForm newBillRegistrationForm){
        this.newBillRegistrationForm  = newBillRegistrationForm;

        TotalBillCountingThread totalBillCounting = new TotalBillCountingThread(this);
        Thread pointsCountingThread = new Thread(totalBillCounting);
        pointsCountingThread.start();
    }

    public JPanel getArticlesListingForm() {
        return newBillRegistrationForm;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        g.drawString("Total TVAC :   " + this.newBillRegistrationForm.getTotalBill() + " ",60,30);
    }
}
