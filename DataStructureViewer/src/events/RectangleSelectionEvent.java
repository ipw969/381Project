package events;

import controllers.SelectionController.SelectionModifier;
import javafx.event.ActionEvent;

/**
 * A class encapsulating the data associated with a rectangle selection event
 *
 * @author Iain Workman
 */
public class RectangleSelectionEvent extends ActionEvent {

    // Constructor
    /**
     * Creates a new instance of a RectangleSelectionEvent at the provided
     * coordinates
     *
     * @param startX::double ~ The X coordinate of the start point of the
     * rectangle
     * @param startY::double ~ The Y coordinate of the start point of the
     * rectangle
     * @param endX::double ~ The X coordinate of the end point of the rectangle
     * @param endY::double ~ The Y coordinate of the end point of the rectangle
     * @param selectionModifier::SelectionModifier ~ The behavior of the
     * selection event
     */
    public RectangleSelectionEvent(double startX, double startY,
            double endX, double endY,
            SelectionModifier selectionModifier) {
        startX_ = startX;
        startY_ = startY;
        endX_ = endX;
        endY_ = endY;
        selectionModifier_ = selectionModifier;
    }

    // Public Methods
    /**
     * @return The X position of the start point of the rectangle
     */
    public double startX() {
        return startX_;
    }

    /**
     * @return The Y position of the start point of the rectangle
     */
    public double startY() {
        return startY_;
    }

    /**
     * @return The X position of the end point of the rectangle
     */
    public double endX() {
        return endX_;
    }

    /**
     * @return The Y position of the end point of the rectangle
     */
    public double endY() {
        return endY_;
    }

    /**
     * @return The selection behavior of the event
     */
    public SelectionModifier getSelectionModifier() {
        return selectionModifier_;
    }

    // Private member varaibles
    private final double startX_;
    private final double startY_;
    private final double endX_;
    private final double endY_;
    private final SelectionModifier selectionModifier_;

}
