package models;

import enumerators.HotSpotType;

/**
 * A class which represents a point within a WorkSpaceGraphElement which can form
 * the beginning or ending of a Path.
 * @author anjagilje
 */
public class HotSpot {

    //Constructors
    /**
     * Creates an instance of a HotSpot of the provided HotSpotType, at the provided
     * co-ordinates and belonging to the provided WorkSpaceGraphElement
     * @param positionX::double ~ The X position within the WorkSpaceGraphElement
     * of the HotSpot
     * @param positionY::double ~ The Y position within the WorkSpaceGraphElement
     * of the HotSpot
     * @param type::HotSpotType ~ Whether the HotSpot is incoming or outgoing
     * @param parent::WorkSpaceGraphElement ~ The WorkSpaceGraphElement to which
     * this HotSpot belongs
     */
    public HotSpot(double positionX, double positionY, HotSpotType type, WorkSpaceGraphElement parent) {
        positionX_ = positionX;
        positionY_ = positionY;
        type_ = type;
        parent_ = parent;

    }

    /**
     * The X position of the HotSpot within its WorkSpaceGraphElement
     */
    public double getX() {
        return positionX_;
    }

    /**
     * The Y position of the HotSpot within its WorkSpaceGraphElement
     * @return 
     */
    public double getY() {
        return positionY_;
    }

    /**
     * Sets the X position of this HotSpot within its WorkSpaceGraphElement
     * @param positionX::double ~ The new X position of the HotSpot within its
     * WorkSpaceGraphElement
     */
    public void setX(double positionX) {
        positionX_ = positionX;
    }

    /**
     * Sets the Y position of this HotSpot within its WorkSpaceGraphElement
     * @param positionY::double ~ The new Y position of the HotSpot within its
     * WorkSpaceGraphElement
     */
    public void setY(double positionY) {
        positionY_ = positionY;
    }

    /**
     * The X position of the HotSpot within the WorkSpaceGraph
     */
    public double getTotalX() {
        return positionX_ + parent_.getX();
    }

    /**
     * The Y position of the HotSpot within the WorkSpaceGraph
     */
    public double getTotalY() {
        return positionY_ + parent_.getY();
    }

    /**
     * The Type of the HotSpot
     */
    public HotSpotType getHotSpotType() {
        return type_;
    }

    /**
     * The WorkSpaceGraphElement which contains this HotSpot
     */
    public WorkSpaceGraphElement getParent() {
        return parent_;
    }

    // Private Member Variables
    
    private double positionX_, positionY_;
    private final HotSpotType type_;
    private final WorkSpaceGraphElement parent_;

}
