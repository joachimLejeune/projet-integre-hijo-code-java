package view.classe;

import controller.ApplicationControler;
import exception.GetAllArticlesException;
import model.Article;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class SearchOneWindow extends JFrame {
    private JLabel indicationLabel;
    private JComboBox articlesChoiceComboBox;
    private JButton validateButton;
    private Container container;
    private NewBillRegistrationForm newBillRegistrationForm;
    private ApplicationControler controller;

    public ResearchArticleWindow(NewBillRegistrationForm newBillRegistrationForm) throws GetAllArticlesException {
        super("Recherche d'un article");
        setBounds(400,200,400,150);

        this.newBillRegistrationForm = newBillRegistrationForm;
        indicationLabel = new JLabel("Choississez un article à ajouter à votre facture :");
        articlesChoiceComboBox = new JComboBox();
        validateButton = new JButton("Valider");

        this.setLayout(new FlowLayout());
        container = this.getContentPane();
        container.add(indicationLabel);
        container.add(articlesChoiceComboBox);
        container.add(validateButton);

        setController(new ApplicationControler());
        ArrayList<Article> articles = controller.getAllArticles();
        for(Article articleRead : articles){
            articlesChoiceComboBox.addItem(articleRead.getWording());
        }

        // on gère le clic sur valider
        SearchOneWindow.ValidateButtonListener validatButtonListener = new SearchOneWindow.ValidateButtonListener(articles,this);
        validateButton.addActionListener(validatButtonListener);

        setVisible(true);
    }

    private void setController(ApplicationControler applicationControler) {
        this.controller = applicationControler;
    }

    // listeners
    private class ValidateButtonListener implements ActionListener {
        private ArrayList<Article> articles;
        private ResearchArticleWindow researchArticleWindow;
        public ValidateButtonListener(ArrayList<Article> articles,ResearchArticleWindow researchArticleWindow){
            this.articles = articles;
            this.researchArticleWindow = researchArticleWindow;
        }
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }
}
