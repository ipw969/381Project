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
    /**
     * Creates a new instance of a HotSpotEvent with the provided HotSpot and
     * WorkSpaceViewElement
     * @param hotSpot::HotSpot ~ The HotSpot involved in the event
     * @param container::WorkSpaceViewElement ~ The WorkSpaceViewElement which
     * contains the HotSpot involved in the event
     */
    public HotSpotEvent(HotSpot hotSpot, WorkSpaceViewElement container) {
        hotSpot_ = hotSpot;
        container_ = container;
    }
    
    // Public Methods
    /**
     * The HotSpot involved in the event
     */
    public HotSpot getHotSpot() {
        return hotSpot_;
    }
    
    /**
     * The WorkSpaceViewElement which contains the HotSpot involved in the 
     * event
     */
    public WorkSpaceViewElement getContainer() {
        return container_;
    }
    
    // Private Member Varibles
    private final HotSpot hotSpot_;
    private final WorkSpaceViewElement container_;
}
