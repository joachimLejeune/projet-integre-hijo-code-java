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
        setBounds(800,150,435,600);
        // on ajoute le label de texte
        JLabel helpText = new JLabel("<html>" +
                " <h1>Comment utiliser le programme :</h1>" +
                " <h2>Menu Application</h2>" +
                " <p> Le premier sous menu correspond à récupérer l'ensemble des numéros de facture et d'afficher leurs contenus.</p>" +
                " <p> Le deuxième sous menu permet de quitter le programme</p>" +
                " <h2>Menu Facture</h2>" +
                " <p> Le premier sous menu correspond à la création et la sauvegarde d'une nouvelle facture.</p>" +
                " <p> Le deuxième sous menu permettra, une fois implémenté, de modifier une facture existante.</p>" +
                " <p> Le troisième sous menu permet de supprimer une facture existante sur base de son numéro unique.</p>" +
                " <h2>Menu Recherches</h2>" +
                " <p> Le premier sous menu correspond à la recherche des employés qui ont fait une ou des facture(s) à un client précis pendant une période précise.</p>" +
                " <p> Le deuxième sous menu correspond à la recherche du top 3 des vendeurs à féliciter.</p>" +
                " <p> Le troisième sous menu correspond  à la liste des clients qui ont acheté un article précis.</p>" +
                " <h2>Menu Infos</h2>" +
                " <p> Le premier sous menu correspond aux informations de l'entreprise.</p>" +
                " <p> Le deuxième sous menu correspond à ce panneau d'aide.</p>" +
                " </html>");
        helpText.setHorizontalAlignment(SwingConstants.CENTER);

        // on créer le bouton de retour à la fenêtre principale
        JButton returnButton = new JButton("Retour à la fenêtre principale");

        this.setLayout(new BorderLayout());
        helpContainer = this.getContentPane();
        helpContainer.add(helpText, BorderLayout.NORTH);
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
