package it.uniroma2.nightplan.graphiccontrollers;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

import it.uniroma2.nightplan.view.EssentialGUI;

public class GCHomeOrg extends EssentialGUI {
    @FXML
    void goToAddEvent(MouseEvent event) {
        changeGUI(event, "AddEvent.fxml");
    }
}
