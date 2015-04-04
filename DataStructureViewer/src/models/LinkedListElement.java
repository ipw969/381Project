package models;

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
        count_ = 0;
    }

    // Public Methods

    // Private Member Variables
    // These will eventually become EdgePath objects.
    private WorkSpaceGraphElement head_;
    private WorkSpaceGraphElement tail_;
    private int count_;
}
