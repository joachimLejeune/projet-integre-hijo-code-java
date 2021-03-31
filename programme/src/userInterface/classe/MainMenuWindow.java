package userInterface.classe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainMenuWindow extends JFrame {
    private JMenuBar menuBar;
    private JMenu applicationMenu;
    private JMenu billMenu;
    private JMenu infosMenu;
    private JMenuItem exitMenu;
    private JMenuItem newBillMenu;
    private JMenuItem modificationBillMenu;
    private JMenuItem deleteBillMenu;
    private JMenuItem creditsMenu;
    private JMenuItem helpMenu;
    private Container mainContainer;
    private JLabel welcomeMessage;

    public MainMenuWindow(){
        super("Création de commandes");
        welcomeMessage = new JLabel("Bonjour et bienvenue");
        // détermine la taille de la fenêtre principale
        setBounds(750,200,435,400);
        // ici on indique qu'en cliquant sur la croix on ferme le programme
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        //la barre de menu
        menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        // Menu application
        applicationMenu = new JMenu("Application");
        exitMenu = new JMenuItem("Quitter");
        applicationMenu.add(exitMenu);
        applicationMenu.setMnemonic('F');
        menuBar.add(applicationMenu);

        // Menu facture
        billMenu = new JMenu("Facture");
        newBillMenu = new JMenuItem("Nouvelle facture");
        billMenu.add(newBillMenu);
        modificationBillMenu = new JMenuItem("Modification facture");
        billMenu.add(modificationBillMenu);
        deleteBillMenu = new JMenuItem("Supprimer facture");
        billMenu.add(deleteBillMenu);
        billMenu.setMnemonic('G');
        menuBar.add(billMenu);

        // Menu infos
        infosMenu = new JMenu("Infos");
        creditsMenu = new JMenuItem("Notre entreprise");
        infosMenu.add(creditsMenu);
        helpMenu = new JMenuItem("Aide");
        infosMenu.add(helpMenu);
        infosMenu.setMnemonic('H');
        menuBar.add(infosMenu);


        // on gère le clic sur quitter
        ExitListener exitListener = new ExitListener();
        exitMenu.addActionListener(exitListener);

        // on gère le clic sur notre entreprise
        CreditsListener iesnListener = new CreditsListener();
        creditsMenu.addActionListener(iesnListener);

        // on gère le clic sur Aide
        HelpListener helpListener = new HelpListener();
        helpMenu.addActionListener(helpListener);

        // on gère le clic sur inscription
        RegistrationListener registrationListener = new RegistrationListener();
        newBillMenu.addActionListener(registrationListener);

        // on associe au MainMenu un FlowLayout pour le positionnement des labels
        this.setLayout(new FlowLayout());
        mainContainer = this.getContentPane();
        mainContainer.add(welcomeMessage);



        // rend la fenêtre visible
        setVisible(true);
    }

    public Container getMainContainer() {
        return mainContainer;
    }

    private class ExitListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }
    private class CreditsListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
//            IesnInformation iesnInformation;
//            iesnInformation = new IesnInformation();
//            mainContainer.removeAll();
//            mainContainer.add(iesnInformation);
//            mainContainer.repaint();
//            setVisible(true);
            OurCompanyInformation ourCompanyInformation = new OurCompanyInformation();
            mainContainer.removeAll();
            mainContainer.add(ourCompanyInformation);
            mainContainer.repaint();
            setVisible(true);
        }
    }
    private class HelpListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            HelpWindow helpWindow = new HelpWindow();
        }
    }
    private class RegistrationListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
//            RegistrationForm registrationForm;
//            registrationForm = new RegistrationForm();
//            mainContainer.removeAll();
//            mainContainer.add(registrationForm);
//            mainContainer.repaint();
//            setVisible(true);
            setBounds(800,50,635,1000);
            NewBillRegistrationForm newBillRegistrationForm = new NewBillRegistrationForm();
            mainContainer.removeAll();
            mainContainer.add(newBillRegistrationForm);
            mainContainer.repaint();
            setVisible(true);
        }
    }
}
