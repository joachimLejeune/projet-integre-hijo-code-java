package view.classe.form;

import controller.ApplicationControler;
import exception.*;
import model.originalDBClasse.Article;
import model.originalDBClasse.Customer;
import model.tableModelTool.SearchOne;
import model.tableModelTool.SearchThree;
import view.classe.tableModel.MySearchOneTableModel;
import view.classe.tableModel.MySearchThreeTableModel;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class SearchThreeForm extends JPanel {
    private JLabel indicationLabel;
    private JComboBox articleChoiceComboBox;
    private JButton validateButton;
    private JTable listingSearchOne;
    private JPanel researchValues;
    private JPanel listingSearchOnePanel;

    private ApplicationControler controller;

    JScrollPane scrollPane;
    ArrayList<SearchThree> rowSearchThrees = new ArrayList<>();
    MySearchThreeTableModel mySearchThreeTableModel;

    public SearchThreeForm() throws GetAllArticlesException, VATException, IdArticleException, PriceException, NumAisleException {
        this.setLayout(new BorderLayout());
        researchValues = new JPanel();
        researchValues.setLayout(new BorderLayout());

        indicationLabel = new JLabel("Choisissez l'article :");
        articleChoiceComboBox = new JComboBox();
        validateButton = new JButton("Rechercher");

        researchValues.add(indicationLabel,BorderLayout.WEST);
        researchValues.add(articleChoiceComboBox,BorderLayout.EAST);
        researchValues.add(validateButton, BorderLayout.SOUTH);

        this.add(researchValues,BorderLayout.NORTH);

        mySearchThreeTableModel = new MySearchThreeTableModel(rowSearchThrees);
        listingSearchOne = new JTable(mySearchThreeTableModel);
        scrollPane = new JScrollPane(listingSearchOne);
        scrollPane.setPreferredSize(new Dimension(600,350));
        listingSearchOne.setFillsViewportHeight(true);

        listingSearchOnePanel = new JPanel();
        listingSearchOnePanel.add(scrollPane);
        this.add(listingSearchOnePanel, BorderLayout.CENTER);

        setController(new ApplicationControler());
        ArrayList<Article> articles = controller.getAllArticles();
        for(Article article : articles){
            articleChoiceComboBox.addItem(article.getWording());
        }

//         on gère le clic sur valider
        ValidateButtonListener validatButtonListener = new ValidateButtonListener(this);
        validateButton.addActionListener(validatButtonListener);
    }

    public void setController(ApplicationControler applicationControler) {
        this.controller = applicationControler;
    }

    private class ValidateButtonListener implements ActionListener {
        private SearchThreeForm searchThreeForm;
        public ValidateButtonListener(SearchThreeForm searchThreeForm){
            this.searchThreeForm = searchThreeForm;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                Integer idArticle = controller.getIdArticle(articleChoiceComboBox.getSelectedItem().toString());
                ArrayList<SearchThree> customerSearchThrees = new ArrayList<>();
                customerSearchThrees = controller.getSearchThree(idArticle);

                if(customerSearchThrees.size() == 0){
                    JOptionPane.showMessageDialog(null, "Aucun client n'a acheté cet article.");
                }
                else{
                    mySearchThreeTableModel.setRowSearchThree(customerSearchThrees);
                }
            } catch (IdArticleException | GetSearchThreeException idArticleException) {
                idArticleException.printStackTrace();
            }
            searchThreeForm.repaint();
        }
    }
}
