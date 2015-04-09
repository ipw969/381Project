package views;

import enumerators.HotSpotType;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import models.HotSpot;

/**
 * A class which represents a visual representation of a HotSpot
 *
 * @author Ryan
 */
public class HotSpotView extends Circle {

    /**
     * Creates a new instance of a HotSpotView which represents the provided
     * HotSpot
     *
     * @param hotSpotModel::HotSpot ~ The HotSpot which this HotSpotView is
     * visualizing
     */
    public HotSpotView(HotSpot hotSpotModel) {
        hotSpotModel_ = hotSpotModel;
        setTypeStyle();
        this.setCenterX(hotSpotModel_.getX());
        this.setCenterY(hotSpotModel_.getY());
        this.setRadius(6);
        this.setStrokeWidth(0);
    }

    // Public Methods
    /**
     * Method which is called when the underlying HotSpot is moved
     */
    public void onHotSpotMoved() {
        this.setCenterX(hotSpotModel_.getX());
        this.setCenterY(hotSpotModel_.getY());
    }

    /**
     * The HotSpot which this HotSpotView is visualizing
     */
    public HotSpot getHotSpot() {
        return hotSpotModel_;
    }

    // Private Methods
    /**
     * Helper method which updates the visual style of the HotSpotView depending
     * on the HotSpotType of the underlying HotSpot
     */
    private void setTypeStyle() {

        if (hotSpotModel_.getHotSpotType() == HotSpotType.INCOMING) {
            this.setFill(Color.rgb(255, 0, 0));

        } else if (hotSpotModel_.getHotSpotType() == HotSpotType.OUTGOING) {
            this.setFill(Color.rgb(0, 0, 255));
        }
    }

    // Private Member Variables
    private final HotSpot hotSpotModel_;
}
