package view.classe.form;

import controller.ApplicationControler;
import exception.GetAllIdBillsException;
import exception.GetBillException;
import exception.IdBillException;
import exception.NumPersonException;
import model.originalDBClasse.Bill;
import model.originalDBClasse.Listing;
import view.classe.tableModel.MyBillTableModel;
import view.classe.tableModel.MyListingTableModel;
import view.classe.window.ConfirmationWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ListingForm extends JPanel {
    private JLabel idBillLabel;
    private JComboBox idBillComboBox;
    private JButton validateButton;
    private JTable billTable;
    private JTable listingsTable;
    private JPanel searchInfosPanel;
    private JPanel summariesInformationPanel;
    private JPanel summaryBillInformationPanel;
    private JPanel summaryListingInformationPanel;
    private Container mainContainer;
    private ApplicationControler controller;

    ArrayList<Bill> summaryBill = new ArrayList<>();
    ArrayList<Listing> summaryListing = new ArrayList<>();

    JScrollPane scrollPaneBill;
    JScrollPane scrollPaneListing;
    MyBillTableModel myBillTableModel;
    MyListingTableModel myListingTableModel;


    public ListingForm() throws GetAllIdBillsException {
        this.setLayout(new BorderLayout());
        searchInfosPanel = new JPanel();
        idBillLabel = new JLabel("Numéro de la facture a lister");
        idBillComboBox = new JComboBox();
        validateButton = new JButton("Valider");

        searchInfosPanel.setLayout(new GridLayout(1,3,5,5));
        searchInfosPanel.add(idBillLabel);
        searchInfosPanel.add(idBillComboBox);
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


        // on gère le clic sur le bouton validé
        ValidateButtonListener validateButtonListener = new ValidateButtonListener();
        validateButton.addActionListener(validateButtonListener);

        setController(new ApplicationControler());

        ArrayList<Integer> allIdBills = controller.getAllIdBill();
        for(Integer idBill : allIdBills){
            idBillComboBox.addItem(idBill);
        }

    }
    private void setController(ApplicationControler applicationControler) {
        this.controller = applicationControler;
    }

    // listener
    private class ValidateButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try{
                Integer idBill = Integer.valueOf(idBillComboBox.getSelectedItem().toString());
                summaryBill = controller.getBill(idBill);

                if(summaryBill.size() == 0){
                    JOptionPane.showMessageDialog(null,"Le numéro de facture entré ne correspond à rien");
                }
                else{
                    myBillTableModel.setRowBills(summaryBill);

                    summaryListing = controller.getListings(idBill);
                    myListingTableModel.setRowListings(summaryListing);

                    summariesInformationPanel.repaint();

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
}
