package models;

/**
 * A class which represents a binary tree node
 *
 * @author Iain Workman
 */
public class BinaryTreeElement extends WorkSpaceGraphElement {

    // Constructor
    /**
     * Create a BinaryTreeElement at the provided position, with the provided
     * size
     *
     * @param positionX::double ~ The X position of the BinaryTreeElement
     * @param positionY::double ~ The Y position of the BinaryTreeElement
     * @param zIndex::int ~ The Z index of the BinaryTreeElement
     * @param parent::WorkSpaceGraph ~ The WorkSpaceGraph to which this element
     * belongs
     */
    public BinaryTreeElement(double positionX, double positionY, int zIndex, WorkSpaceGraph parent) {
        super(positionX, positionY, zIndex, 60, 60, 60, 60, parent);
    }

    // Public Methods
    /**
     * The value which is stored within this BinaryTreeElement
     */
    public int getValue() {
        return value_;
    }

    /**
     * Sets the value stored in this BinaryTreeElement
     *
     * @param value::int ~ The new value to be stored in the BinaryTreeElement
     */
    public void setValue(int value) {
        value_ = value;
        getParent().notifySubscribersOfAlter(this);
    }

    // Private Member Variables
    private int value_;

}
