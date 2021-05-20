package view.classe.form;

import controller.ApplicationControler;
import exception.DeleteBillException;
import exception.GetBillException;
import exception.IdBillException;
import exception.NumPersonException;
import model.originalDBClasse.Bill;
import model.originalDBClasse.Listing;
import view.classe.tableModel.MyBillTableModel;
import view.classe.tableModel.MyListingTableModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class DeleteBillForm extends JPanel {
    private JLabel idBillLabel;
    private JTextField idBillTextField;
    private JButton validateButton;
    private JButton deleteButton;
    private JTable billTable;
    private JTable listingsTable;
    private JPanel searchInfosPanel;
    private JPanel summariesInformationPanel;
    private JPanel summaryBillInformationPanel;
    private JPanel summaryListingInformationPanel;

    ArrayList<Bill> summaryBill = new ArrayList<>();
    ArrayList<Listing> summaryListing = new ArrayList<>();

    JScrollPane scrollPaneBill;
    JScrollPane scrollPaneListing;
    MyBillTableModel myBillTableModel;
    MyListingTableModel myListingTableModel;

    private ApplicationControler controller;

    public DeleteBillForm(){
        this.setLayout(new BorderLayout());
        searchInfosPanel = new JPanel();
        idBillLabel = new JLabel("Numéro de la facture a supprimé");
        idBillTextField = new JTextField();
        validateButton = new JButton("Valider");

        searchInfosPanel.setLayout(new GridLayout(1,3,5,5));
        searchInfosPanel.add(idBillLabel);
        searchInfosPanel.add(idBillTextField);
        searchInfosPanel.add(validateButton);

        this.add(searchInfosPanel,BorderLayout.NORTH);

        summariesInformationPanel = new JPanel();
        summariesInformationPanel.setLayout(new BorderLayout());
        summaryBillInformationPanel = new JPanel();
        myBillTableModel = new MyBillTableModel(summaryBill);
        billTable = new JTable(myBillTableModel);
        billTable.setFillsViewportHeight(true);
        scrollPaneBill = new JScrollPane(billTable);
        scrollPaneBill.setPreferredSize(new Dimension(800,50));
        summaryBillInformationPanel.add(scrollPaneBill);

        summariesInformationPanel.add(summaryBillInformationPanel,BorderLayout.NORTH);

        summaryListingInformationPanel = new JPanel();
        myListingTableModel = new MyListingTableModel(summaryListing);
        listingsTable = new JTable(myListingTableModel);
        listingsTable.setFillsViewportHeight(true);
        scrollPaneListing = new JScrollPane(listingsTable);
        scrollPaneListing.setPreferredSize(new Dimension(800,125));
        summaryListingInformationPanel.add(scrollPaneListing);

        summariesInformationPanel.add(summaryListingInformationPanel,BorderLayout.CENTER);

        this.add(summariesInformationPanel, BorderLayout.CENTER);

        deleteButton = new JButton("Supprimer");
        deleteButton.setEnabled(false);
        this.add(deleteButton,BorderLayout.SOUTH);

        // on gère le clic sur le bouton validé
        ValidateButtonListener validateButtonListener = new ValidateButtonListener();
        validateButton.addActionListener(validateButtonListener);

        setController(new ApplicationControler());
    }

    // setter
    private void setController(ApplicationControler applicationControler) {
        this.controller = applicationControler;
    }

    // listener
    private class ValidateButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            try{
                Integer idBill = Integer.valueOf(idBillTextField.getText());
                summaryBill = controller.getBill(idBill);

                if(summaryBill.size() == 0){
                    JOptionPane.showMessageDialog(null,"Le numéro de facture entré ne correspond à rien");
                }
                else{
                    myBillTableModel.setRowBills(summaryBill);

                    summaryListing = controller.getListings(idBill);
                    myListingTableModel.setRowListings(summaryListing);

                    summariesInformationPanel.repaint();

                    deleteButton.setEnabled(true);

                    // on gère le clic sur le boutton supprimer
                    DeleteButtonListener deleteButtonListener = new DeleteButtonListener(idBill);
                    deleteButton.addActionListener(deleteButtonListener);
                }
            }catch(NumberFormatException numberFormatException){
                JOptionPane.showMessageDialog(null,"Ce que vous avez mis en numéro de facture n'est pas correct");
            } catch (GetBillException getBillException) {
                getBillException.getMessage();
            } catch (IdBillException idBillException) {
                idBillException.getMessage();
            } catch (NumPersonException numPersonException) {
                numPersonException.getMessage();
            }
        }
    }
    // à terminer
    private class DeleteButtonListener implements ActionListener{
        private Integer idBill;
        public DeleteButtonListener(Integer idBill){
            this.idBill = idBill;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            Boolean deleteDone = null;
            try {
                deleteDone = controller.deleteBill(idBill);
            } catch (DeleteBillException deleteBillException) {
                deleteBillException.getMessage();
            }
            if(!deleteDone){
                JOptionPane.showMessageDialog(null,"La suppression de la commande a été effectuée");
            }
        }
    }
}
