package models;


import Enumerators.Enumerators;
import Enumerators.Enumerators.HotSpotType;
import Enumerators.Enumerators.TransformerLocation;
import javafx.geometry.Point2D;

/**
 * A class which represents a LinkedListElement in the workspace Graph
 *
 * @author Iain Workman
 */
public class LinkedListElement extends WorkSpaceGraphElement {

    // Constructors
    /**
     * Creates an instance of a LinkedListElement which is positioned in the
     * provided position of the workspace graph.
     *
     * @param positionX::double ~ The X position of the LinkedList
     * @param positionY::double ~ The Y position of the LinkedList
     * @param parent::WorkSpaceGraph ~ The WorkSpaceGraph to which this
     * LinkedList belongs
     */
    public LinkedListElement(double positionX, double positionY, int zIndex, WorkSpaceGraph parent) {
        super(positionX, positionY, zIndex, 90, 75, 90, 75, parent);
        head_ = null;
        tail_ = null;
        headHotSpot_ = new HotSpot(positionX+50, positionY+15, HotSpotType.OUTGOING);
        tailHotSpot_ = new HotSpot(positionX+50, positionY+35, HotSpotType.BOTH);
        count_ = 0;

        
        parent.addHotSpot(headHotSpot_);
        parent.addHotSpot(tailHotSpot_);


    }

    // Public Methods

    // Private Member Variables
    // These will eventually become EdgePath objects.
    private WorkSpaceGraphElement head_;
    private WorkSpaceGraphElement tail_;
    private HotSpot headHotSpot_;
    private HotSpot tailHotSpot_;
    private int count_;
}
