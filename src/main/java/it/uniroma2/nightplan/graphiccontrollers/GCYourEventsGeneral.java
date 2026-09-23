package it.uniroma2.nightplan.graphiccontrollers;

import javafx.scene.control.ListView;
import it.uniroma2.nightplan.beans.BEvent;
import it.uniroma2.nightplan.view.EssentialGUI;

import java.time.format.DateTimeFormatter;
import java.util.List;

public abstract class GCYourEventsGeneral extends EssentialGUI implements DoubleClickListener {

    protected DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    protected String formatTimeAndDate(String date, String time){
        return (date + " - "+ time);
    }

    public static BEvent getBeanFromListView(ListView<String> lv, List<BEvent> beansArray){
        int selectedEventIndex = lv.getSelectionModel().getSelectedIndex();
        if(selectedEventIndex == -1){
            return null;
        }
        return beansArray.get(selectedEventIndex);
    }
}
