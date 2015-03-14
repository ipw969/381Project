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
     * provided position of the workspace graph.
     *
     * @param positionX::double ~ The X position of the LinkedList
     * @param positionY::double ~ The Y position of the LinkedList
     * @param parent::WorkSpaceGraph ~ The WorkSpaceGraph to which this
     * LinkedList belongs
     */
    public LinkedListElement(double positionX, double positionY, int zIndex, WorkSpaceGraph parent) {
        super(positionX, positionY, zIndex, parent);
        head_ = null;
        tail_ = null;
        count_ = 0;
        width_ = 65;
        height_ = 85;               
    }

    // Public Methods

    /**
     * @return The width of the LinkedListElement
     */
    public double getWidth() {
        return width_;
    }
    
    /**
     * @return The height of the LinkedListElement
     */
    public double getHeight() {
        return height_;
    }
    
    /**
     * Sets the width of the LinkedListElement
     * @param width::double ~ The new width of the LinkedListElement
     */
    public void setWidth(double width) {
        width_ = width;
    }
    
    /**
     * Sets the height of the LinkedListElement
     * @param height::double ~ The new height of the LinkedListElement
     */
    public void setHeight(double height) {
        height_ = height;
    }
    
    /**
     * Whether or not the provided point is contained within this LinkedListElement
     * @param pointX::double ~ The x coordinate to check
     * @param pointY::double ~ The y coordinate to check
     * @return True if the provided point is contained with this LinkedListElement
     */
    @Override
    public boolean containsPoint(double pointX, double pointY) {
        return (getX() < pointX && 
                getX() + getWidth() > pointX &&
                getY() < pointY &&
                getY() + getHeight() > pointY);
    }

    /**
     * Whether or not this WorkSpaceGraphElement is contained within the provided
     * rectangle
     * 
     * @param rectangleX1::double ~ The X coordinate of the top left of the rectangle
     * @param rectangleY1::double ~ The Y coordinate of the top left of the rectangle
     * @param rectangleX2::double ~ The X coordinate of the bottom right of the rectangle
     * @param rectangleY2::double ~ The Y coordinate of the bottom right of the rectangle
     * @return True if this WorkSpaceGraphElement is contained within the bounds
     * of the provided rectangle. False otherwise.
     */
    @Override
    public boolean isContainedWithin(double rectangleX1, double rectangleY1, double rectangleX2, double rectangleY2) {
        return (this.getX() >= rectangleX1 && 
                this.getY() >= rectangleY1 &&
                this.getX() + this.getWidth() <= rectangleX2 &&
                this.getY() + this.getHeight() <= rectangleY2);
    }
    
    // Private Member Variables
    // These will eventually become EdgePath objects.
    private WorkSpaceGraphElement head_;
    private WorkSpaceGraphElement tail_;
    private int count_;
    private double width_;
    private double height_;

}
