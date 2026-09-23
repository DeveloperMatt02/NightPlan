package it.uniroma2.nightplan.graphiccontrollers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import it.uniroma2.nightplan.view.EssentialGUI;

import it.uniroma2.nightplan.utils.LoggedUser;
import it.uniroma2.nightplan.utils.enums.UserTypes;

public class GCProfile extends EssentialGUI{

    @FXML
    private Label birthDate;

    @FXML
    private Label city;

    @FXML
    private Label firstName;

    @FXML
    private Label gender;

    @FXML
    private Button goBackBtn;

    @FXML
    private Label lastName;

    @FXML
    private Label province;

    @FXML
    private Label userName;

    @FXML
    private Label userType;

    @FXML
    public void initialize() {
        UserTypes type;
        if (LoggedUser.getUserType().equals(UserTypes.USER)){
            type = UserTypes.USER;
        }else{
            type = UserTypes.ORGANIZER;
        }
        this.userName.setText(LoggedUser.getUserName());
        this.firstName.setText(LoggedUser.getFirstName());
        this.lastName.setText(LoggedUser.getLastName());
        this.birthDate.setText(LoggedUser.getBirthDate());
        this.gender.setText(LoggedUser.getGender());
        this.province.setText(LoggedUser.getProvince());
        this.city.setText(LoggedUser.getCity());
        this.userType.setText(type.toString());
    }

}
