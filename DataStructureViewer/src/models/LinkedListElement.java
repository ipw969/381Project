package models;

import Enumerators.Enumerators.HotSpotType;


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

        headHotSpot_ = new HotSpot(60, 15, HotSpotType.OUTGOING);
        tailHotSpot_ = new HotSpot(60, 35, HotSpotType.OUTGOING);

        getHotSpots().add(headHotSpot_);
        getHotSpots().add(tailHotSpot_);

    }

    // Public Methods

    // Private Member Variables
    private HotSpot headHotSpot_;
    private HotSpot tailHotSpot_;
}
