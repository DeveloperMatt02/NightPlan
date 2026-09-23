package it.uniroma2.nightplan.graphiccontrollers;

import javafx.scene.input.MouseEvent;
import it.uniroma2.nightplan.beans.BEvent;

public interface DoubleClickListener {
    void setupEventClickListener();
    void onItemDoubleClick(MouseEvent event, BEvent selectedEventBean, String fxmlpage);
}
