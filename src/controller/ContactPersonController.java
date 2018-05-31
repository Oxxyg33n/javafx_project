package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.sql.SQLException;

import model.ContactPersonDAO;
import model.ContactPerson;

public class ContactPersonController {

    @FXML
    private TextField contIdText;
    @FXML
    private TextArea resultArea;
    @FXML
    private TextField newEmailText;
    @FXML
    private TextField nameText;
    @FXML
    private TextField nickNameText;
    @FXML
    private TextField emailText;
    @FXML
    private TextField addressText;
    @FXML
    private TextField cellPhoneText;
    @FXML
    private TextField webSiteText;
    @FXML
    private TextField professionText;
    @FXML
    private TextField birthDateText;
    @FXML
    private TableView contactTable;
    @FXML
    private TableColumn<ContactPerson, Integer>  contIdColumn;
    @FXML
    private TableColumn<ContactPerson, String>  contNameColumn;
    @FXML
    private TableColumn<ContactPerson, String> contNickNameColumn;
    @FXML
    private TableColumn<ContactPerson, String> contAddressColumn;
    @FXML
    private TableColumn<ContactPerson, String> contCellPhoneColumn;
    @FXML
    private TableColumn<ContactPerson, String> contEmailColumn;
    @FXML
    private TableColumn<ContactPerson, String> contWebSiteColumn;
    @FXML
    private TableColumn<ContactPerson, String> contProfessionColumn;
    @FXML
    private TableColumn<ContactPerson, String> contBirthDateColumn;

    //Search a contact
    @FXML
    private void searchContact (ActionEvent actionEvent) throws ClassNotFoundException, SQLException {
        try {
            //Get Contact information
            ContactPerson cont = ContactPersonDAO.searchContact(contIdText.getText());
            //Populate Contact on TableView and Display on TextArea
            populateAndShowContact(cont);
        } catch (SQLException e) {
            e.printStackTrace();
            resultArea.setText("Error occurred while getting contact information from DB.\n" + e);
            throw e;
        }
    }

    //Search all contacts
    @FXML
    private void searchContacts(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        try {
            //Get all Contacts information
            ObservableList<ContactPerson> contData = ContactPersonDAO.searchContacts();
            //Populate Contacts on TableView
            populateContacts(contData);
        } catch (SQLException e){
            System.out.println("Error occurred while getting contacts information from DB.\n" + e);
            throw e;
        }
    }

    //Initializing the controller class.
    //This method is automatically called after the fxml file has been loaded.
    @FXML
    private void initialize () {
        contIdColumn.setCellValueFactory(cellData -> cellData.getValue().contactIdProperty().asObject());
        contNameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        contNickNameColumn.setCellValueFactory(cellData -> cellData.getValue().nickNameProperty());
        contAddressColumn.setCellValueFactory(cellData -> cellData.getValue().addressProperty());
        contCellPhoneColumn.setCellValueFactory(cellData -> cellData.getValue().cellPhoneProperty());
        contEmailColumn.setCellValueFactory(cellData -> cellData.getValue().emailProperty());
        contWebSiteColumn.setCellValueFactory(cellData -> cellData.getValue().webSiteProperty());
        contProfessionColumn.setCellValueFactory(cellData -> cellData.getValue().professionProperty());
        contBirthDateColumn.setCellValueFactory(cellData -> cellData.getValue().birthDateProperty());
    }

    //Populate Contact
    @FXML
    private void populateContact (ContactPerson cont) throws ClassNotFoundException {
        ObservableList<ContactPerson> contData = FXCollections.observableArrayList();
        contData.add(cont);
        contactTable.setItems(contData);
    }

    //Set Contact information to Text Area
    @FXML
    private void setContInfoToTextArea (ContactPerson cont) {
        resultArea.setText("Name: " + cont.getName() + "\n" +
                "Nickname: " + cont.getNickName());
    }

    //Populate Contact for TableView and Display Contact on TextArea
    @FXML
    private void populateAndShowContact(ContactPerson cont) throws ClassNotFoundException {
        if (cont != null) {
            populateContact(cont);
            setContInfoToTextArea(cont);
        } else {
            resultArea.setText("This contact does not exist!\n");
        }
    }

    //Populate Contacts for TableView
    @FXML
    private void populateContacts (ObservableList<ContactPerson> contData) throws ClassNotFoundException {
        //Set items to the employeeTable
        contactTable.setItems(contData);
    }

    //Update contact's email with given email
    @FXML
    private void updateContactEmail (ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        try {
            ContactPersonDAO.updateContEmail(contIdText.getText(),newEmailText.getText());
            resultArea.setText("Email has been updated for contact id: " + contIdText.getText() + "\n");
        } catch (SQLException e) {
            resultArea.setText("Problem occurred while updating email: " + e);
        }
    }

    //Insert new contact to the DB
    @FXML
    private void insertContact (ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        try {
            ContactPersonDAO.insertCont(nameText.getText(),nickNameText.getText(),addressText.getText(),cellPhoneText.getText(),emailText.getText(),webSiteText.getText(), professionText.getText(), birthDateText.getText());
            resultArea.setText("New contact added\n");
        } catch (SQLException e) {
            resultArea.setText("Problem occurred while adding new contact " + e);
            throw e;
        }
    }

    //Delete contact with given ID from DB
    @FXML
    private void deleteContact (ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        try {
            ContactPersonDAO.deleteContWithId(contIdText.getText());
            resultArea.setText("Contact deleted! Contact id: " + contIdText.getText() + "\n");
        } catch (SQLException e) {
            resultArea.setText("Problem occurred while deleting contact " + e);
            throw e;
        }
    }
}