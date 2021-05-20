package view.classe.window;

import exception.*;
import model.tableModelTool.SearchThree;
import model.tableModelTool.SearchTwo;
import view.classe.form.*;

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
    private JMenu searchMenu;
    private JMenu infosMenu;
    private JMenuItem exitMenu;
    private JMenuItem newBillMenu;
    private JMenuItem modificationBillMenu;
    private JMenuItem deleteBillMenu;
    private JMenuItem search1;
    private JMenuItem search2;
    private JMenuItem search3;
    private JMenuItem creditsMenu;
    private JMenuItem helpMenu;
    private Container mainContainer;
    private JLabel welcomeMessage;

    // constructeur
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
        newBillMenu = new JMenuItem("Nouvelle");
        billMenu.add(newBillMenu);
        modificationBillMenu = new JMenuItem("Modification");
        modificationBillMenu.setEnabled(false);
        billMenu.add(modificationBillMenu);
        deleteBillMenu = new JMenuItem("Supprimer");
        deleteBillMenu.setEnabled(true);
        billMenu.add(deleteBillMenu);
        billMenu.setMnemonic('G');
        menuBar.add(billMenu);

        // Menu Recherche
        searchMenu = new JMenu("Recherches");
        search1 = new JMenuItem("Recherche d'employés");
        search2 = new JMenuItem("Top 3 des employés");
        search2.setEnabled(false);
        search3 = new JMenuItem("Recherche client cible");
        searchMenu.add(search1);
        searchMenu.add(search2);
        searchMenu.add(search3);
        menuBar.add(searchMenu);

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

        // on gère le clic sur nouvelle facture
        RegistrationListener registrationListener = new RegistrationListener();
        newBillMenu.addActionListener(registrationListener);

        // on gère le clic sur modification
        ModificationListener modificationListener = new ModificationListener();
        modificationBillMenu.addActionListener(modificationListener);

        // on gère le clic sur supprimer
        DeleteBillListener deleteBillListener = new DeleteBillListener();
        deleteBillMenu.addActionListener(deleteBillListener);

        // on gère le clic sur la recherche 1
        SearchOneListener searchOneListener = new SearchOneListener();
        search1.addActionListener(searchOneListener);

        // on gère le clic sur la recherche 2
        SearchTwoListener searchTwoListener = new SearchTwoListener();
        search2.addActionListener(searchTwoListener);

        // on gère le clic sur la recherche 3
        SearchThreeListener searchThreeListener = new SearchThreeListener();
        search3.addActionListener(searchThreeListener);

        // on associe au MainMenu un FlowLayout pour le positionnement des labels
        this.setLayout(new FlowLayout());
        mainContainer = this.getContentPane();
        mainContainer.setLayout(new BorderLayout());
        welcomeMessage.setHorizontalAlignment(SwingConstants.CENTER);
        mainContainer.add(welcomeMessage,BorderLayout.NORTH);

        // rend la fenêtre visible
        setVisible(true);
    }

    // getter
    public Container getMainContainer() {
        return mainContainer;
    }
    // setters

    // listeners
    private class ExitListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }
    private class CreditsListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
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

            setBounds(625,125,735,735);
            NewBillRegistrationForm newBillRegistrationForm = new NewBillRegistrationForm();
            mainContainer.removeAll();
            mainContainer.add(newBillRegistrationForm);
            mainContainer.repaint();
            setVisible(true);
        }
    }
    private class ModificationListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            setBounds(650,150,635,635);
            NewModificationBillForm newModificationBillForm = new NewModificationBillForm();
            mainContainer.removeAll();
            mainContainer.add(newModificationBillForm);
            mainContainer.repaint();
            setVisible(true);

        }
    }
    private class DeleteBillListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            DeleteBillForm deleteBillForm = new DeleteBillForm();
            setBounds(750,200,900,300);
            mainContainer.removeAll();
            mainContainer.add(deleteBillForm);
            mainContainer.repaint();
            setVisible(true);
        }
    }
    private class SearchOneListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                setBounds(750,200,635,400);
                SearchOneForm searchOneForm = new SearchOneForm();
                mainContainer.removeAll();
                mainContainer.add(searchOneForm);
                mainContainer.repaint();
                setVisible(true);
            } catch (PhoneNumberException phoneNumberException) {
                phoneNumberException.printStackTrace();
            } catch (AllCustomersException allCustomersException) {
                allCustomersException.printStackTrace();
            } catch (NumPersonException numPersonException) {
                numPersonException.printStackTrace();
            } catch (EmailException emailException) {
                emailException.printStackTrace();
            }

        }
    }
    private class SearchTwoListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            setBounds(750,200,635,400);
            try {
                SearchTwoForm searchTwoForm = new SearchTwoForm();
                mainContainer.removeAll();
                mainContainer.add(searchTwoForm);
                mainContainer.repaint();
                setVisible(true);
            } catch (PhoneNumberException phoneNumberException) {
                phoneNumberException.printStackTrace();
            } catch (EmailException emailException) {
                emailException.printStackTrace();
            } catch (AllEmployeesException allEmployeesException) {
                allEmployeesException.printStackTrace();
            } catch (NumPersonException numPersonException) {
                numPersonException.printStackTrace();
            }

        }
    }
    private class SearchThreeListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                setBounds(750,200,635,400);
                SearchThreeForm searchThreeForm = new SearchThreeForm();
                mainContainer.removeAll();
                mainContainer.add(searchThreeForm);
                mainContainer.repaint();
                setVisible(true);
            } catch (GetAllArticlesException getAllArticlesException) {
                getAllArticlesException.printStackTrace();
            } catch (IdArticleException idArticleException) {
                idArticleException.getMessage();
            } catch (VATException vatException) {
                vatException.getMessage();
            } catch (PriceException priceException) {
                priceException.getMessage();
            } catch (NumAisleException numAisleException) {
                numAisleException.getMessage();
            }

        }
    }
}
