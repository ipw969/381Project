package events;

import controllers.SelectionController.SelectionModifier;
import javafx.event.ActionEvent;

/**
 * A class encapsulating the data associated with a point selection event
 * @author Iain Workman
 */
public class PointSelectionEvent extends ActionEvent{

    // Constructor
    /**
     * Creates an instance of a PointSelectionEvent which encompasses a selection
     * at the provided X and Y coordinate
     * @param pointX::double ~ The X coordinate of the selection
     * @param pointY::double ~ The Y coordinate of the selection
     * @param selectionModifier::SelectionModifier ~ The behavior of the 
     * selection event
     */
    public PointSelectionEvent(double pointX, double pointY, 
            SelectionModifier selectionModifier) {
        pointX_ = pointX;
        pointY_ = pointY;
        selectionModifier_ = selectionModifier;
    }
    
    // Public Methods
    /**
     * @return The X coordinate of the selection
     */
    public double getPointX() {
        return pointX_;
    }
    
    /**
     * @return The Y coordinate of the selection
     */
    public double getPointY() {
        return pointY_;
    }
    
    /**
     * @return The selection behavior of the event
     */
    public SelectionModifier getSelectionModifier() {
        return selectionModifier_;
    }
    
    // Private Member variables
    private final double pointX_;
    private final double pointY_;
    private final SelectionModifier selectionModifier_;
}
