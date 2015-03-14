package controllers;

import events.PointSelectionEvent;
import events.RectangleSelectionEvent;
import javafx.event.EventHandler;

/**
 * A class which encapsulates a selection interaction, and defines a consistent
 * selection semantics for a view
 *
 * @author Iain Workman
 */
public class SelectionController {

    // Public Methods
    /**
     * Begins a selection at the provided coordinates
     *
     * @param startX::double ~ The X coordinate of the start of the selection
     * event
     * @param startY::double ~ The Y coordinate of the start of the selection
     * event
     */
    public void startSelectionAt(double startX, double startY) {
        startX_ = startX;
        startY_ = startY;
        currentX_ = startX;
        currentY_ = startY;
        isPointSelection_ = true;
    }

    /**
     * Ends the selection at the provided coordinates. A rectangle or point
     * selection event will be passed to the relevant event handlers depending
     * on whether the current selection is a point or rectangle.
     *
     * @param selectionModifier::The behavior specified for the current
     * selection
     */
    public void endSelection(SelectionModifier selectionModifier) {
        if (isPointSelection_ && pointSelectionEventHandler_ != null) {
            pointSelectionEventHandler_.handle(new PointSelectionEvent(currentX_, currentY_, selectionModifier));
        } else if (!isPointSelection_ && rectangleSelectionEventHandler_ != null) {

            // Get a normalized version of our selection rectangle 
            double x1 = Math.min(startX_, currentX_);
            double y1 = Math.min(startY_, currentY_);

            double x2 = Math.max(startX_, currentX_);
            double y2 = Math.max(startY_, currentY_);

            RectangleSelectionEvent selectionEvent
                    = new RectangleSelectionEvent(x1, y1, x2, y2, selectionModifier);

            rectangleSelectionEventHandler_.handle(selectionEvent);
        }
    }

    /**
     * @return The X coordinate of the start of the current selection
     */
    public double getStartX() {
        return startX_;
    }

    /**
     * @return The Y coordinate of the start of the current selection
     */
    public double getStartY() {
        return startY_;
    }

    /**
     * @return The X coordinate of the last input event position of the current
     * selection
     */
    public double getCurrentX() {
        return currentX_;
    }

    /**
     * Sets the latest X position of the current selection.
     *
     * @param currentX::double ~ The latest X position of the current selection
     */
    public void setCurrentX(double currentX) {
        currentX_ = currentX;
        if (Math.abs(currentX_ - startX_) > TRAVEL_TOLERENCE) {
            isPointSelection_ = false;
        }
    }

    /**
     * @return The Y coordinate of the last input event position of the current
     * selection
     */
    public double getCurrentY() {
        return currentY_;
    }

    /**
     * Sets the latest Y position of the current selection.
     *
     * @param currentY::double ~ The latest Y position of the current selection
     */
    public void setCurrentY(double currentY) {
        currentY_ = currentY;
        if (Math.abs(currentY_ - startY_) > TRAVEL_TOLERENCE) {
            isPointSelection_ = false;
        }
    }

    /**
     * Sets the handler for a PointSelectionEvent
     *
     * @param pointSelectionEventHandler::EventHandler<PointSelectionEvent> ~
     * The handler for a PointSelectionEvent
     */
    public void setOnPointSelection(EventHandler<PointSelectionEvent> pointSelectionEventHandler) {
        pointSelectionEventHandler_ = pointSelectionEventHandler;
    }

    /**
     * Sets the handler for a RectangleSelectionEvent
     *
     * @param
     * rectangleSelectionEventHandler::EventHandler<RectangleSelectionEvent> ~
     * The handler for a RectangleSelectionEvent
     */
    public void setOnRectangleSelection(EventHandler<RectangleSelectionEvent> rectangleSelectionEventHandler) {
        rectangleSelectionEventHandler_ = rectangleSelectionEventHandler;
    }

    /**
     * @return Whether the current selection is considered a point or rectangle
     * selection
     */
    public boolean isPointSelection() {
        return isPointSelection_;
    }

    // Public Enums
    /**
     * Modifiers for a Selection.
     */
    public enum SelectionModifier {

        ClearAndSelect,     // Remove all selected items, and select those passed instead
        Append              // Append the passed items to the current selection
    }

    // Private member variables
    private double startX_;
    private double startY_;
    private double currentX_;
    private double currentY_;
    private boolean isPointSelection_;
    private EventHandler<RectangleSelectionEvent> rectangleSelectionEventHandler_;
    private EventHandler<PointSelectionEvent> pointSelectionEventHandler_;

    // Private static constants
    /// Defines how far the mouse must have travelled with a button pressed
    /// before a selection is considered a rectangle selection not a point 
    /// selection.
    private static final double TRAVEL_TOLERENCE = 10;
}
