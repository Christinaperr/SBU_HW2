import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.scene.control.TextField;

public class PhoneField {
    TextField phoneField;

    public PhoneField() {
        phoneField = new TextField();
        promptTextPhone();
        checkTextPhone();

    }

    public String getText() {
        return phoneField.getText();
    }

    public TextField getPhoneField() {
        return phoneField;
    }

    public void promptTextPhone() {
        phoneField.setPromptText("(###) ###-####");
    }

    public boolean checkTextPhone() {
            if (!phoneField.getText().matches("[(](\\d{3})[)](\\s)(\\d{3})[-](\\d{4})")) {
                phoneField.setStyle("-fx-text-fill: red;");
                return false;
            }else {
                phoneField.setStyle("-fx-text-fill: black;");
                return true;
            }
    }

    public BooleanBinding bindingPhone() {
        BooleanBinding textFieldValid = Bindings.createBooleanBinding(() -> {
            if (phoneField.getText().isEmpty()) {
                return false;
            } else {
                return true;
            }
        }, phoneField.textProperty());
        return textFieldValid;
    }
}
