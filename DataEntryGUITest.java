import javafx.embed.swing.JFXPanel;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class DataEntryGUITest {
    private NameField name;
    private NameField name2;
    private NameField name3;
    private PhoneField phone;
    private PhoneField phone2;
    private PhoneField phone3;
    private Btn button;

    @Before
    public void setUp(){
        JFXPanel panel = new JFXPanel();
        this.name = new NameField();
        this.name2 = new NameField();
        this.name3 = new NameField();
        this.phone = new PhoneField();
        this.phone2 = new PhoneField();
        this.phone3 = new PhoneField();
        this.button = new Btn(name, name2, name3, phone, phone2, phone3);
    }

    @After
    public void tearDown(){
        name = null;
        phone = null;
        button = null;
    }

    @Test
    public void promptTextName(){
        assertEquals("Prompt test name matches", name.getNameField().getPromptText(), "Name");

    }

    @Test
    public void promptTextPhone() {
        assertEquals("Prompt test phone matches", phone.getPhoneField().getPromptText(), "(###) ###-####");
    }

    @Test
    public void checkTextNameInvalid1() {
        name.getNameField().setText("JoHn Smith");
        assertEquals("Name is correctly called invalid", name.checkTextName(), false);
    }

    @Test
    public void checkTextNameInvalid2() {
        name.getNameField().setText("Alex");
        assertEquals("Name is correctly called invalid", name.checkTextName(), false);
    }

    @Test
    public void checkTextNameValid1() {
        name.getNameField().setText("Alex Johnson");
        assertEquals("Name is correctly called valid", name.checkTextName(), true);
    }

    @Test
    public void checkTextNameValid2() {
        name.getNameField().setText("Jane D");
        assertEquals("Name is correctly called valid", name.checkTextName(), true);
    }


    @Test
    public void checkTextPhoneInvalid1() {
        phone.getPhoneField().setText("876 876-7667");
        assertEquals("Phone is correctly called invalid", phone.checkTextPhone(), false);
    }

    @Test
    public void checkTextPhoneInvalid2() {
        phone.getPhoneField().setText("(543)75433366789");
        assertEquals("Phone is correctly called invalid", phone.checkTextPhone(), false);
    }

    @Test
    public void checkTextPhoneValid1() {
        phone.getPhoneField().setText("(987) 765-8761");
        assertEquals("Phone is correctly called valid", phone.checkTextPhone(), true);
    }

    @Test
    public void checkTextPhoneValid2() {
        phone.getPhoneField().setText("(102) 532-5436");
        assertEquals("Phone is correctly called valid", phone.checkTextPhone(), true);
    }



    @Test
    public void checkTextNameColor() {
        if(name.checkTextName()) {
            assertEquals("Color name is correctly displayed black", name.getNameField().getStyle(), "-fx-text-fill: black;");
        } else if (!name.checkTextName()) {
            assertEquals("Color name is correctly displayed red", name.getNameField().getStyle(), "-fx-text-fill: red;");
        }
    }


    @Test
    public void checkTextPhoneColor() {
        if(phone.checkTextPhone()) {
            assertEquals("Color phone is correctly displayed black", phone.getPhoneField().getStyle(), "-fx-text-fill: black;");
        } else if (!phone.checkTextPhone()) {
            assertEquals("Color phone is correctly displayed red", phone.getPhoneField().getStyle(), "-fx-text-fill: red;");
        }
    }

    @Test
    public void checkDisabled() {
        if(!button.nameField1.bindingName().not().get() && !button.nameField2.bindingName().not().get() && !button.nameField3.bindingName().not().get() && !button.phoneField1.bindingPhone().not().get() && !button.phoneField2.bindingPhone().not().get() && !button.phoneField3.bindingPhone().not().get()){
            assertEquals("Button is enabled", button.getCreateProfileBtn().disabledProperty().get(), false);
        } else if (button.nameField1.bindingName().not().get() && button.nameField2.bindingName().not().get() && button.nameField3.bindingName().not().get() && button.phoneField1.bindingPhone().not().get() && button.phoneField2.bindingPhone().not().get() && button.phoneField3.bindingPhone().not().get()){
            assertEquals("Button is disabled", button.getCreateProfileBtn().disabledProperty().get(), true);
        }
    }

    @Test
    public void alertTest() {
        assertEquals("Error box is open", button.errorCheck(), false);
    }

    @Test
    public void confirmationTest() {
        phone.getPhoneField().setText("(786) 865-9654");
        phone2.getPhoneField().setText("(786) 865-9654");
        phone3.getPhoneField().setText("(786) 865-9654");
        name.getNameField().setText("Jane Doe");
        name2.getNameField().setText("Jane Doe");
        name3.getNameField().setText("Jane Doe");
        assertEquals("Confirmation box is open and fields are uneditable", button.errorCheck(), true);
    }

}
