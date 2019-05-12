import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.scene.control.TextField;


public class NameField extends DataEntryGUI {
    TextField nameField;

    public NameField() {
        nameField = new TextField();
        promptTextName();
        checkTextName();


    }

    public String getText() {
        return nameField.getText();
    }

    public TextField getNameField() {
        return nameField;
    }

    public void promptTextName() {
        nameField.setPromptText("Name");
    }

    public boolean checkTextName() {
            if(nameField.getText().length() > 20 || !nameField.getText().matches("[(A-Z)][(a-z)]*(\\s)[A-Z][(a-z)]*")) {
                nameField.setStyle("-fx-text-fill: red;");
                return false;
            } else {
                nameField.setStyle("-fx-text-fill: black;");
                return true;
            }
    }

    public BooleanBinding bindingName() {
        BooleanBinding textFieldValid = Bindings.createBooleanBinding(() -> {
            if (nameField.getText().isEmpty()) {
                return false;
            } else {
                return true;
            }
        }, nameField.textProperty());
        return textFieldValid;
    }

}
