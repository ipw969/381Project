package models;

import Enumerators.Enumerators;
import Enumerators.Enumerators.HotSpotType;

/**
 *  A class representing a Node of a LinkedList
 * @author Iain Workman
 */
public class LinkedListNodeElement extends WorkSpaceGraphElement  {

    // Constructor
    
    /**
     * Creates an instance of a LinkedListNode at the provided X and Y co ordinates
     * with the provided ZIndex which belongs to the provided parent Graph
     * @param positionX::double ~ The X position of the LinkedListNode
     * @param positionY::double ~ The Y position of the LinkedListNode
     * @param zIndex::int ~ The Z index of the LinkedListNode
     * @param parent::WorkSpaceGraph ~ The graph of work space elements to which
     * this LinkedListNode belongs.
     */
    public LinkedListNodeElement(double positionX, double positionY, int zIndex, WorkSpaceGraph parent) {
        super(positionX, positionY, zIndex, 85, 40, 85, 40, parent);
        value_ = 0;
        
        incomingHotSpot_ = new HotSpot(10, 20, HotSpotType.INCOMING, this);
        outgoingHotSpot_ = new HotSpot(65, 20, HotSpotType.OUTGOING,this);
        
        getHotSpots().add(incomingHotSpot_);
        getHotSpots().add(outgoingHotSpot_);
        resizeImplementation();
    }
    
    // Public Methods
    /**
     * The value which is stored within this LinkedListNode
     */
    public int getValue() {
        return value_;
    }
    
    /**
     * Sets the value stored in this LinkedListNode
     * @param value::int ~ The new value to be stored in the LinkedListNode 
     */
    public void setValue(int value) {
        value_ = value;
        getParent().notifySubscribersOfAlter(this);
    }
    
    // Private Member Variables
    private final HotSpot incomingHotSpot_;
    private final HotSpot outgoingHotSpot_;
    private int value_;

    @Override
    protected final void resizeImplementation() {
        incomingHotSpot_.setX(10);
        incomingHotSpot_.setY(getHeight() / 2);
        
        outgoingHotSpot_.setX(getWidth() - (getWidth() / 4));
        outgoingHotSpot_.setY(getHeight() / 2);
    }

}
