package it.uniroma2.nightplan.graphiccontrollers;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

import it.uniroma2.nightplan.beans.BUserData;
import it.uniroma2.nightplan.exceptions.InvalidValueException;
import it.uniroma2.nightplan.exceptions.TextTooLongException;
import it.uniroma2.nightplan.utils.enums.Alerts;
import it.uniroma2.nightplan.utils.LoggedUser;

public class GCGoogleRegistration extends GCRegistration {
    @FXML
    @Override
    public void initialize() {
        initRegistration();
        this.dataBean = new BUserData(LoggedUser.getUserName()); //inizializzo il data bean con username preso da GoogleAuth
    }

    @FXML
    @Override
    public void registerControl(MouseEvent event) {
        try {
            setupDataBean(true);
            register(event);
        } catch (InvalidValueException | TextTooLongException e) {
            this.alert.displayAlertPopup(Alerts.ERROR, e.getMessage());
        }
    }
}
