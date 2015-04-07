package events;

import javafx.event.ActionEvent;
import models.HotSpot;
import views.WorkSpaceViewElement;

/**
 * Class which encapsulates all the data relating to a HotSpot event
 * @author Iain Workman
 */
public class HotSpotEvent extends ActionEvent {
    // Constructor
    public HotSpotEvent(HotSpot hotSpot, WorkSpaceViewElement container) {
        hotSpot_ = hotSpot;
        container_ = container;
    }
    
    // Public Methods
    public HotSpot getHotSpot() {
        return hotSpot_;
    }
    
    public WorkSpaceViewElement getContainer() {
        return container_;
    }
    
    // Private Member Varibles
    private final HotSpot hotSpot_;
    private final WorkSpaceViewElement container_;
}
