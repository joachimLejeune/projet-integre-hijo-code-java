package view.classe.window;

import controller.ApplicationControler;
import exception.DeleteBillException;
import view.classe.form.DeleteBillForm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConfirmationWindow extends JFrame {
    private JLabel question;
    private JButton yesButton, noButton;
    private JPanel buttons;
    private Container container;
    private Integer idBill;
    private ApplicationControler controller;
    private Container mainContainer;

    public ConfirmationWindow(Integer idBill, Container mainContainer){
        super("Demande de confirmation");

        this.idBill = idBill;
        this.mainContainer = mainContainer;
        setBounds(760,450,350,100);

        container = this.getContentPane();

        this.setLayout(new BorderLayout());
        buttons = new JPanel();

        question = new JLabel("Êtes-vous sûr de vouloir supprimer cette facture ?");
        question.setHorizontalAlignment(SwingConstants.CENTER);
        yesButton = new JButton("Oui");
        noButton = new JButton("Non");

        container.add(question, BorderLayout.NORTH);
        buttons.add(yesButton);
        buttons.add(noButton);
        container.add(buttons, BorderLayout.CENTER);

        controller = new ApplicationControler();

        // on gère le clic sur oui
        YesButtonListener yesButtonListener = new YesButtonListener();
        yesButton.addActionListener(yesButtonListener);


        // on gère le clic sur non
        NoButtonListener noButtonListener = new NoButtonListener();
        noButton.addActionListener(noButtonListener);

        this.setVisible(true);
    }
    public class YesButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            Boolean deleteDone = null;
            try {
                deleteDone = controller.deleteBill(idBill);
                mainContainer.removeAll();
                DeleteBillForm deleteBillForm2 = new DeleteBillForm(mainContainer);
                mainContainer.add(deleteBillForm2);
                mainContainer.revalidate();
                mainContainer.setVisible(true);
                dispose();
            } catch (DeleteBillException deleteBillException) {
                deleteBillException.getMessage();
            }
            if(!deleteDone){
                JOptionPane.showMessageDialog(null,"La suppression de la commande a été effectuée");
            }
        }
    }
    public class NoButtonListener implements  ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            mainContainer.removeAll();
            DeleteBillForm deleteBillForm2 = new DeleteBillForm(mainContainer);
            mainContainer.add(deleteBillForm2);
            mainContainer.revalidate();
            mainContainer.setVisible(true);
            dispose();

        }
    }


}
