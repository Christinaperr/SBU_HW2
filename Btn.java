import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;

public class Btn {
    Button createProfileBtn;
    NameField nameField1;
    NameField nameField2;
    NameField nameField3;
    PhoneField phoneField1;
    PhoneField phoneField2;
    PhoneField phoneField3;

    public Btn(NameField nameField1, NameField nameField2, NameField nameField3, PhoneField phoneField1, PhoneField phoneField2, PhoneField phoneField3) {
        createProfileBtn = new Button("Create Profiles");
        this.nameField1 = nameField1;
        this.nameField2 = nameField2;
        this.nameField3 = nameField3;
        this.phoneField1 = phoneField1;
        this.phoneField2 = phoneField2;
        this.phoneField3 = phoneField3;
        checkIfEmpty();
    }

    public Btn(NameField nameField1, PhoneField phoneField1){
        createProfileBtn = new Button("Create Profiles");
        this.nameField1 = nameField1;
        this.phoneField1 = phoneField1;
        checkIfEmpty();
    }

    public void checkIfEmpty() {
        createProfileBtn.disableProperty().bind(nameField1.bindingName().not().or(nameField2.bindingName().not()
                .or(nameField3.bindingName().not().or(phoneField1.bindingPhone().not().or(phoneField2.bindingPhone().not()
                        .or(phoneField3.bindingPhone().not()))))));

    }

    public Button getCreateProfileBtn() {
        return createProfileBtn;
    }


    public boolean alert() {

        if (!errorCheck()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid input: you have attempted to provide one or more invalid\n" +
                    "input(s). Please correct the information displayed in red and retry.",
                    ButtonType.CLOSE);
            alert.showAndWait();
            return false;
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "The profiles have been saved and added to the database.", ButtonType.CLOSE);
            alert.showAndWait();
            nameField1.getNameField().setEditable(false);
            nameField2.getNameField().setEditable(false);
            nameField3.getNameField().setEditable(false);
            phoneField1.getPhoneField().setEditable(false);
            phoneField2.getPhoneField().setEditable(false);
            phoneField3.getPhoneField().setEditable(false);
            return true;
        }
    }

    public boolean errorCheck() {
        if (!phoneField1.checkTextPhone()
                || !phoneField2.checkTextPhone()
                || !phoneField3.checkTextPhone()
                || !nameField1.checkTextName() || !nameField2.checkTextName() || !nameField3.checkTextName()) {
            return false;
        } else {
            return true;
    }

    }

}
