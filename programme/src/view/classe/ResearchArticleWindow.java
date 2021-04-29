package view.classe;

import controller.ApplicationControler;
import model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

public class ResearchArticleWindow extends JFrame {
    private JLabel indicationLabel, quantityArticleLabel;
    private JComboBox articlesChoiceComboBox;
    private JComboBox quantityArticle;
    private JButton validateButton;
    private Container container;
    private ApplicationControler controller;

    public ResearchArticleWindow(){
        super("Recherche d'un article");
        setBounds(400,200,400,100);

        indicationLabel = new JLabel("Choississez un article à ajouter à votre facture :");
        quantityArticleLabel = new JLabel("Quantité : ");
        articlesChoiceComboBox = new JComboBox();
        quantityArticle = new JComboBox(new String[]{"1","2","3","4","5","6","7","8","9","10"});
        validateButton = new JButton("Valider");

        this.setLayout(new FlowLayout());
        container = this.getContentPane();
        container.add(indicationLabel);
        container.add(articlesChoiceComboBox);
        container.add(quantityArticleLabel);
        container.add(quantityArticle);
        container.add(validateButton);

        setController(new ApplicationControler());
        ArrayList<Article> articles = controller.getAllArticles();
        for(Article articleRead : articles){
            articlesChoiceComboBox.addItem(articleRead.getWording());
        }

        // on gère le clic sur valider
        ValidatButtonListener validatButtonListener = new ValidatButtonListener(articles,this);
        validateButton.addActionListener(validatButtonListener);

        setVisible(true);
    }

    private void setController(ApplicationControler applicationControler) {
        this.controller = applicationControler;
    }

    // listeners
    private class ValidatButtonListener implements ActionListener {
        private ArrayList<Article> articles;
        private ResearchArticleWindow researchArticleWindow;
        public ValidatButtonListener(ArrayList<Article> articles,ResearchArticleWindow researchArticleWindow){
            this.articles = articles;
            this.researchArticleWindow = researchArticleWindow;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            //Article articleRead = articles.get(articles.indexOf(researchArticleWindow.articlesChoiceComboBox.getSelectedItem()));
            JOptionPane.showMessageDialog(null,researchArticleWindow.articlesChoiceComboBox.getSelectedItem());

        }
    }
}
