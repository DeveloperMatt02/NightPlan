package it.uniroma2.nightplan.graphiccontrollers;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

import it.uniroma2.nightplan.beans.BUserData;
import it.uniroma2.nightplan.exceptions.InvalidValueException;
import it.uniroma2.nightplan.exceptions.TextTooLongException;
import it.uniroma2.nightplan.utils.enums.Alerts;

public class GCClassicRegistration extends GCRegistration{
    @Override
    @FXML
    public void initialize() {
        initRegistration();
        this.dataBean = new BUserData();
    }

    @Override
    @FXML
    public void registerControl(MouseEvent event) {
        try {
            setupDataBean(false);
            register(event);
        } catch (InvalidValueException | TextTooLongException e) {
            this.alert.displayAlertPopup(Alerts.ERROR, e.getMessage());
        }
    }
}
