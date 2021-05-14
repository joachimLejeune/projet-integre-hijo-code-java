package view.classe.window;

import view.classe.window.MainMenuWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HelpWindow extends JFrame {
    private MainMenuWindow mainMenuWindow;
    private Container helpContainer;

    public HelpWindow(){
        super("Aide");
        setBounds(800,150,435,400);
        // on ajoute le label de texte
        JLabel helpText = new JLabel("<html>Ceci est le texte d'aide et c'est une aide précieuse !</html>");
        helpText.setHorizontalAlignment(SwingConstants.CENTER);

        // on créer le bouton de retour à la fenêtre principale
        JButton returnButton = new JButton("Retour à la fenêtre principale");

        this.setLayout(new BorderLayout());
        helpContainer = this.getContentPane();
        helpContainer.add(helpText, BorderLayout.CENTER);
        helpContainer.add(returnButton, BorderLayout.SOUTH);

        ReturnButtonListener returnButtonListener = new ReturnButtonListener();
        returnButton.addActionListener(returnButtonListener);

        setVisible(true);
    }
    private class ReturnButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            dispose();
        }
    }
}
