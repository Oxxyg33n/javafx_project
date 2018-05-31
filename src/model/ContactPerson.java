package model;
import javafx.beans.property.*;

public class ContactPerson {
    private IntegerProperty contactId;
    private StringProperty name;
    private StringProperty nickName;
    private StringProperty address;
    private StringProperty cellPhone;
    private StringProperty email;
    private StringProperty webSite;
    private StringProperty profession;
    private StringProperty birthDate;

    public ContactPerson() {
        this.contactId = new SimpleIntegerProperty();
        this.name = new SimpleStringProperty();
        this.nickName = new SimpleStringProperty();
        this.address = new SimpleStringProperty();
        this.cellPhone = new SimpleStringProperty();
        this.email = new SimpleStringProperty();
        this.webSite = new SimpleStringProperty();
        this.profession = new SimpleStringProperty();
        this.birthDate = new SimpleStringProperty();
    }

    // Getters
    public int getContactId() { return contactId.get(); }
    public String getName() { return name.get(); }
    public String getNickName() { return nickName.get(); }
    public String getAddress() { return address.get(); }
    public String getCellPhone() { return cellPhone.get(); }
    public String getEmail() { return email.get(); }
    public String getWebSite() { return webSite.get(); }
    public String getProfession() { return profession.get(); }
    public Object getBirthDate() { return birthDate.get(); }

    // Setters
    public void setContactId(int contactID) { this.contactId.set(contactID); }
    public void setName(String name) { this.name.set(name); }
    public void setNickName(String nickName) { this.nickName.set(nickName); }
    public void setAddress(String address) { this.address.set(address); }
    public void setCellPhone(String cellPhone) { this.cellPhone.set(cellPhone); }
    public void setEmail(String email) { this.email.set(email); }
    public void setWebSite(String webSite) { this.webSite.set(webSite); }
    public void setProfession(String profession) { this.profession.set(profession); }
    public void setBirthDate(String birthDate) { this.birthDate.set(birthDate); }

    // Properties
    public IntegerProperty contactIdProperty() { return contactId; }
    public StringProperty nameProperty() { return name; }
    public StringProperty nickNameProperty() { return nickName; }
    public StringProperty addressProperty() { return address; }
    public StringProperty cellPhoneProperty() { return cellPhone; }
    public StringProperty emailProperty() { return email; }
    public StringProperty webSiteProperty() { return webSite; }
    public StringProperty professionProperty() { return profession; }
    public StringProperty birthDateProperty() { return birthDate; }
}