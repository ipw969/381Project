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
        super(positionX, positionY, zIndex, 65, 40, 65, 40, parent);
        value_ = 0;
        
        this.getHotSpots().add(new HotSpot(10, 10, HotSpotType.INCOMING, this));
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
    private int value_;

}
