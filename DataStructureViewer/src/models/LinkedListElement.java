package models;

import Enumerators.Enumerators;
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
        parent_.notifySubscribersOfAlter(this);
    }
    
    /**
     * Sets the height of the LinkedListElement
     * @param height::double ~ The new height of the LinkedListElement
     */
    public void setHeight(double height) {
        height_ = height;
        parent_.notifySubscribersOfAlter(this);
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
    
    /**This function is responsible for collecting information from a view about a transformation, and figuring out what might have to be resized, then
     * notifying the Graph of the change.
     * *******NOTE::: This calculation is done using a coordinate plane that increases in the right, down directions.
     * @param deltaX : The amount that the mouse moved in the x-coordinate plane.
     *                  negative if the mouse moved to the left, positive if it moved to the right.
     * @param deltaY : The amount that the mouse moved in the y-coordinate plane.
     *                  negative if the mouse moved to the left, positive if it moved to the right.
     * @param location : The TransformSpot that generated this event. This corresponds to the location that the transformation occurred.
     */
    @Override
    public void transform(double deltaX, double deltaY, Enumerators.TransformerLocation location)
    {
        if (location.equals(TransformerLocation.TOPLEFT))
        {
            //If the mouse moved to the left, we need to increase the width to keep the element in place.
            double deltaWidth = (-1) * deltaX;
            
            //If the mouse moved up, we need to increase the height to keep the element in place.
            double deltaHeight = (-1) * deltaY;
            
            position_ = new Point2D(position_.getX() + deltaX, position_.getY() + deltaY);
            width_ = width_ + deltaWidth;
            height_ = height_ + deltaHeight;
            parent_.notifySubscribersOfAlter(this);
        }
        else if (location.equals(TransformerLocation.TOPRIGHT))
        {
            //The width should increase if the mouse moved to the right, and vice-versa
            double deltaWidth = deltaX;
            
            //The height should increase if the mouse moved up.
            double deltaHeight = (-1) * deltaY;
            
            width_ = width_ + deltaWidth;
            height_ = height_ + deltaHeight;
            
            //We do not want to update the x-coordinate because it is located at the top left part of the node.
            position_ = new Point2D(position_.getX(), position_.getY() + deltaY);
            
            
        }
        
        else if (location.equals(TransformerLocation.BOTTOMLEFT))
        {
            //The width should increase if the mouse moves to the left.
            double deltaWidth = (-1) * deltaX;
            
            //The height should increase if the mouse moved down.
            double deltaHeight = deltaY;
            
            width_ = width_ + deltaWidth;
            height_ = height_ + deltaHeight;
            
            //We do not need to update the y-coordinate, only the x-coordinate.
            position_ = new Point2D(position_.getX() + deltaX, position_.getY());
            
            
        }
        
        else if (location.equals(TransformerLocation.BOTTOMRIGHT))
        {
            //The width should increase if the mouse moves to the right.
            double deltaWidth = deltaX;
            
            //The height should increase if the mouse moved down.
            double deltaHeight = deltaY;
            
            width_ = width_ + deltaWidth;
            height_ = height_ + deltaHeight;
            
            //We do not need to update any of the coordinates because the node is resized towards the bottom right already.
            //No change to position.
            
            
        }
        else if (location.equals(TransformerLocation.MIDDLELEFT))
        {
            //The width should increase when the mouse moves left.
            double deltaWidth = (-1) * deltaX;
            
            //The height never needs to change.
            
            //Only the x coordinate needs to change.
            
            width_ = width_ + deltaWidth;
            
            position_ = new Point2D(position_.getX() + deltaX, position_.getY());   
        }
        else if (location.equals(TransformerLocation.MIDDLEBOTTOM))
        {
            //The height should increase when the mouse moves down.
            double deltaHeight = deltaY;
            
            
            //The width or x coordinates never need to change.
            
            height_ = height_ + deltaHeight;
            
            position_ = new Point2D(position_.getX(), position_.getY());
        }
        else if (location.equals(TransformerLocation.MIDDLERIGHT))
        {
            //The width should increase when the mouse moved to the right.
            double deltaWidth = deltaX;
            
            //The position or height never needs to change.
            
            width_ = width_ + deltaWidth;
            
        }
        
        else if (location.equals(TransformerLocation.MIDDLETOP))
        {
            //Only the height and y coordinate ever changes.
            
            double deltaHeight = (-1) * deltaY;
            
            height_ = height_ + deltaHeight;
            
            position_ = new Point2D(position_.getX(), position_.getY() + deltaY);
        }
        parent_.informListenersOfElementMoved(this);
        parent_.informListenersOfElementResized(this);
    }
    
    
    // Private Member Variables
    // These will eventually become EdgePath objects.
    private WorkSpaceGraphElement head_;
    private WorkSpaceGraphElement tail_;
    private int count_;
    private double width_;
    private double height_;

    @Override
    public void translate(double deltaX, double deltaY) 
    {
        position_ = new Point2D(position_.getX() + deltaX, position_.getY() + deltaY);
        parent_.informListenersOfElementMoved(this);
    }





}
