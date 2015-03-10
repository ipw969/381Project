package models;

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
     * provided relative position of the workspace graph.
     *
     * @param positionX::double ~ The relative X position of the LinkedList
     * @param positionY::double ~ The relative Y position of the LinkedList
     * @param parent::WorkSpaceGraph ~ The WorkSpaceGraph to which this
     * LinkedList belongs
     */
    public LinkedListElement(double positionX, double positionY, WorkSpaceGraph parent) {
        super(positionX, positionY, parent);
        head_ = null;
        tail_ = null;
        count_ = 0;
    }

    /**
     * Creates an instance of a LinkedListElement which is positioned in the
     * provided relative position of the workspace graph.
     *
     * @param position::Point2D ~ The relative position of the LinkedList
     * @param parent::WorkSpaceGraph ~ The WorkSpaceGraph to which this
     * LinkedList belongs
     */
    public LinkedListElement(Point2D position, WorkSpaceGraph parent) {
        super(position, parent);
        head_ = null;
        tail_ = null;
        count_ = 0;
    }

    // Private Member Variables
    // These will eventually become EdgePath objects.
    private WorkSpaceGraphElement head_;
    private WorkSpaceGraphElement tail_;
    private int count_;

}
