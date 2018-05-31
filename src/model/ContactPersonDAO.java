package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;

import util.DBUtil;

public class ContactPersonDAO {

    //*******************************
    //SELECT a Contact
    //*******************************
    public static ContactPerson searchContact (String contId) throws SQLException, ClassNotFoundException {
        //Declare a SELECT statement
        String selectStmt = "SELECT * FROM contact WHERE contact_id="+contId;

        //Execute SELECT statement
        try {
            //Get ResultSet from dbExecuteQuery method
            ResultSet rsCont = DBUtil.dbExecuteQuery(selectStmt);

            //Send ResultSet to the getContactFromResultSet method and get contact object
            ContactPerson contact = getContactFromResultSet(rsCont);

            //Return employee object
            return contact;
        } catch (SQLException e) {
            System.out.println("While searching an employee with " + contId + " id, an error occurred: " + e);
            //Return exception
            throw e;
        }
    }

    //Use ResultSet from DB as parameter and set ContactPerson Object's attributes and return contact object.
    private static ContactPerson getContactFromResultSet(ResultSet rs) throws SQLException
    {
        ContactPerson cont = null;
        if (rs.next()) {
            cont = new ContactPerson();
            cont.setContactId(rs.getInt("CONTACT_ID"));
            cont.setName(rs.getString("NAME"));
            cont.setNickName(rs.getString("NICKNAME"));
            cont.setAddress(rs.getString("ADDRESS"));
            cont.setCellPhone(rs.getString("CELLPHONE_NUMBER"));
            cont.setEmail(rs.getString("EMAIL"));
            cont.setWebSite(rs.getString("WEBSITE"));
            cont.setProfession(rs.getString("PROFESSION"));
            cont.setBirthDate(rs.getString("BIRTH_DATE"));
        }
        return cont;
    }

    //*******************************
    //SELECT Employees
    //*******************************
    public static ObservableList<ContactPerson> searchContacts () throws SQLException, ClassNotFoundException {
        //Declare a SELECT statement
        String selectStmt = "SELECT * FROM contact";

        //Execute SELECT statement
        try {
            //Get ResultSet from dbExecuteQuery method
            ResultSet rsConts = DBUtil.dbExecuteQuery(selectStmt);

            //Send ResultSet to the getEmployeeList method and get employee object
            ObservableList<ContactPerson> contList = getContactList(rsConts);

            //Return employee object
            return contList;
        } catch (SQLException e) {
            System.out.println("SQL select operation has been failed: " + e);
            //Return exception
            throw e;
        }
    }

    //Select * from employees operation
    private static ObservableList<ContactPerson> getContactList(ResultSet rs) throws SQLException, ClassNotFoundException {
        //Declare a observable List which comprises of Employee objects
        ObservableList<ContactPerson> contList = FXCollections.observableArrayList();

        while (rs.next()) {
            ContactPerson cont = new ContactPerson();
            cont.setContactId(rs.getInt("CONTACT_ID"));
            cont.setName(rs.getString("NAME"));
            cont.setNickName(rs.getString("NICKNAME"));
            cont.setAddress(rs.getString("ADDRESS"));
            cont.setCellPhone(rs.getString("CELLPHONE_NUMBER"));
            cont.setEmail(rs.getString("EMAIL"));
            cont.setWebSite(rs.getString("WEBSITE"));
            cont.setProfession(rs.getString("PROFESSION"));
            cont.setBirthDate(rs.getString("BIRTH_DATE"));
            //Add employee to the ObservableList
            contList.add(cont);
        }
        //return contList (ObservableList of Employees)
        return contList;
    }

    //*************************************
    //UPDATE a contact's email address
    //*************************************
    public static void updateContEmail (String contId, String contEmail) throws SQLException, ClassNotFoundException {
        //Declare a UPDATE statement
        String updateStmt = "UPDATE contact SET EMAIL = '" + contEmail + "' WHERE CONTACT_ID = " + contId + ";";

        //Execute UPDATE operation
        try {
            DBUtil.dbExecuteUpdate(updateStmt);
        } catch (SQLException e) {
            System.out.print("Error occurred while UPDATE Operation: " + e);
            throw e;
        }
    }

    //*************************************
    //DELETE a contact
    //*************************************
    public static void deleteContWithId (String contId) throws SQLException, ClassNotFoundException {
        //Declare a DELETE statement
        String updateStmt = "DELETE FROM contact WHERE contact_id ="+ contId +";";

        //Execute UPDATE operation
        try {
            DBUtil.dbExecuteUpdate(updateStmt);
        } catch (SQLException e) {
            System.out.print("Error occurred while DELETE Operation: " + e);
            throw e;
        }
    }

    //*************************************
    //INSERT a contact
    //*************************************
    public static void insertCont (String name, String nickName, String address, String cellPhone, String email, String webSite, String profession, String birthDate) throws SQLException, ClassNotFoundException {
        //Declare a DELETE statement
        String updateStmt =
                        "INSERT INTO contact(NAME, NICKNAME, ADDRESS, CELLPHONE_NUMBER, EMAIL, WEBSITE, PROFESSION, BIRTH_DATE) " +
                        "VALUES " +
                        "('"+name+"', '"+nickName+"', '"+address+"', '"+cellPhone+"','"+email+"', '"+webSite+"', '"+profession+"', '"+birthDate+"');";

        //Execute DELETE operation
        try {
            DBUtil.dbExecuteUpdate(updateStmt);
        } catch (SQLException e) {
            System.out.print("Error occurred while DELETE Operation: " + e);
            throw e;
        }
    }
}