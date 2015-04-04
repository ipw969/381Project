package models;

import Enumerators.Enumerators;
import javafx.geometry.Point2D;

/**
 * A class which represents the data relating to a single element in a WorkSpace
 * All elements which contain the data modeling a data structure element in a
 * work space (LinkedList Heads, Arrays, Nodes etc) will all extend this class.
 *
 * @author Iain Workman
 */
public abstract class WorkSpaceGraphElement {

    // Constructors
    /**
     * Default Constructor for the abstract WorkSpaceGraphElement class. Sets
     * the position and parent WorkSpaceGraph of this WorkSpaceGraphElement
     *
     * @param positionX::double ~ The X position of the WorkSpaceGraphElement
     * @param positionY::double ~ The Y position of the WorkSpaceGraphElement
     * @param parent::WorkSpaceGraph ~ The WorkSpaceGraph of which this
     * WorkSpaceGraphElement is a node
     */
    public WorkSpaceGraphElement(double positionX, double positionY, int zIndex,
            double width, double height, double minWidth,
            double minHeight, WorkSpaceGraph parent) {
        position_ = new Point2D(positionX, positionY);
        zIndex_ = zIndex;
        width_ = width;
        height_ = height;
        minWidth_ = minWidth;
        minHeight_ = minHeight;
        parent_ = parent;
    }

    /**This method is simply used to pass the delete message to its parent.
     * This will result both in this element being removed from the graph model and
     * its viewElement counterpart being removed from the graphView
     */
    public void delete()
    {
        parent_.removeElement(this);
    }
    
    
    // Public Methods
    /**
     * @return The X position of the WorkSpaceGraphElement
     */
    public double getX() {
        return position_.getX();
    }

    /**
     * @return The Y position of the WorkSpaceGraphElement
     */
    public double getY() {
        return position_.getY();
    }

    /**
     * @return The position of the WorkSpaceGraphElement
     */
    public Point2D getPosition() {
        return position_;
    }

    /**
     * @return The z index (vertical ordering) of the WorkSpaceGraphElement
     */
    public int getZIndex() {
        return zIndex_;
    }

    /**
     * @return The current width of the WorkSpaceGraphElement
     */
    public double getWidth() {
        return width_;
    }

    /**
     * @return The current height of the WorkSpaceGraphElement
     */
    public double getHeight() {
        return height_;
    }

    /**
     * @return The smallest height that the WorkSpaceGraphElement
     */
    public double getMinHeight() {
        return minHeight_;
    }

    /**
     * @return The smallest width of the WorkSpaceGraphElement
     */
    public double getMinWidth() {
        return minWidth_;
    }

    /**
     * Sets the X position of the WorkSpaceGraphElement
     *
     * @param positionX::double ~ The X position of the WorkSpaceGraphElement
     */
    public void setX(double positionX) {
        position_ = new Point2D(positionX, position_.getY());
        parent_.notifySubscribersOfAlter(this);

    }

    /**
     * Sets the Y position of the WorkSpaceGraphElement
     *
     * @param positionY::double ~ The Y position of the WorkSpaceGraphElement
     */
    public void setY(double positionY) {
        position_ = new Point2D(position_.getX(), positionY);
        parent_.notifySubscribersOfAlter(this);
    }

    /**
     * Sets the position of the WorkSpaceGraphElement
     *
     * @param positionX::double ~ The X position of the WorkSpaceGraphElement
     * @param positionY::double ~ The Y position of the WorkSpaceGraphElement
     */
    public void setPosition(double positionX, double positionY) {
        position_ = new Point2D(positionX, positionY);
        parent_.notifySubscribersOfAlter(this);
    }

    /**
     * Sets the position of the WorkSpaceGraphElement
     *
     * @param position::Point2D ~ The position of the WorkSpaceGraphElement
     */
    public void setPosition(Point2D position) {
        position_ = position;
        parent_.notifySubscribersOfAlter(this);
    }

    /**
     * Sets the Z index of the WorkSpaceGraphElement
     *
     * @param zIndex::int ~ The Z Index of the WorkSpaceGraphElement
     */
    public void setZIndex(int zIndex) {
        zIndex_ = zIndex;
        parent_.notifySubscribersOfAlter(this);
    }

    /**
     * Sets the width and height of the WorkSpaceGraphElement
     * @param width::double ~ The new width of the WorkSpaceGraphElement
     * @param height::double ~ The new Height of the WorkSpaceGraphElement
     */
    public void setSize(double width, double height) {
        width_ = width;
        height_ = height;
        parent_.notifySubscribersOfAlter(this);
    }

    /**
     * Sets the height of the WorkSpaceGraphElement
     *
     * @param height::double ~ The new height of the WorkSpaceGraphElement
     */
    public void setHeight(double height) {
        height_ = height;
        parent_.notifySubscribersOfAlter(this);
    }

    /**
     * Sets the width of the WorkSpaceGraphElement
     *
     * @param width::double ~ The new width of the WorkSpaceGraphElement
     */
    public void setWidth(double width) {
        width_ = width;
        parent_.notifySubscribersOfAlter(this);
    }

    /**
     * Whether or not the provided relative point is contained within this
     * WorkSpaceGraphElement
     *
     * @param pointX::double ~ The X coordinate of the point to check for
     * containment within this WorkSpaceGraphElement
     * @param pointY::double ~ The Y coordinate of the point to check for
     * containment within this WorkSpaceGraphElement
     * @return true if the provided point lies within the bound of this
     * WorkSpaceGraphElement
     */
    public boolean containsPoint(double pointX, double pointY) {
        return (getX() < pointX
                && getX() + getWidth() > pointX
                && getY() < pointY
                && getY() + getHeight() > pointY);
    }

    /**
     * Whether or not this WorkSpaceGraphElement is contained within the
     * provided rectangle
     *
     * @param rectangleX1::double ~ The X coordinate of the top left of the
     * rectangle
     * @param rectangleY1::double ~ The Y coordinate of the top left of the
     * rectangle
     * @param rectangleX2::double ~ The X coordinate of the bottom right of the
     * rectangle
     * @param rectangleY2::double ~ The Y coordinate of the bottom right of the
     * rectangle
     * @return True if this WorkSpaceGraphElement is contained within the bounds
     * of the provided rectangle. False otherwise.
     */
    public boolean isContainedWithin(double rectangleX1, double rectangleY1,
            double rectangleX2, double rectangleY2) {
        return (this.getX() >= rectangleX1
                && this.getY() >= rectangleY1
                && this.getX() + this.getWidth() <= rectangleX2
                && this.getY() + this.getHeight() <= rectangleY2);
    }

    /**
     * This function is responsible for collecting information from a view about
     * a transformation, and figuring out what might have to be resized, then
     * notifying the Graph of the change. *******NOTE::: This calculation is
     * done using a coordinate plane that increases in the right, down
     * directions.
     *
     * @param deltaX : The amount that the mouse moved in the x-coordinate
     * plane. negative if the mouse moved to the left, positive if it moved to
     * the right.
     * @param deltaY : The amount that the mouse moved in the y-coordinate
     * plane. negative if the mouse moved to the left, positive if it moved to
     * the right.
     * @param location : The TransformSpot that generated this event. This
     * corresponds to the location that the transformation occurred.
     */
    public void transform(double deltaX, double deltaY,
            Enumerators.TransformerLocation location) {
        double newX = this.getX();
        double newY = this.getY();
        double minWidth = this.getMinWidth();
        double minHeight = this.getMinHeight();
        double currentWidth = this.getWidth();
        double currentHeight = this.getHeight();
        double newWidth = currentWidth;
        double newHeight = currentHeight;
        if (location.equals(Enumerators.TransformerLocation.TOPLEFT)) {
            //If the mouse moved to the left, we need to increase the width to keep the element in place.
            double deltaWidth = (-1) * deltaX;

            //If the mouse moved up, we need to increase the height to keep the element in place.
            double deltaHeight = (-1) * deltaY;

            newX = getPosition().getX() + deltaX;
            newY = getPosition().getY() + deltaY;
            newWidth = currentWidth + deltaWidth;
            newHeight = currentHeight + deltaHeight;

            if (newWidth < minWidth) {
                double widthOffset = newWidth - minWidth;
                newWidth = minWidth;
                newX = newX + widthOffset;
            }
            if (newHeight < minHeight) {
                double heightOffset = newHeight - currentHeight;
                newHeight = minHeight;
                newY = newY + heightOffset;
            }
        } else if (location.equals(Enumerators.TransformerLocation.TOPRIGHT)) {
            //The width should increase if the mouse moved to the right, and vice-versa
            double deltaWidth = deltaX;

            //The height should increase if the mouse moved up.
            double deltaHeight = (-1) * deltaY;

            newWidth = currentWidth + deltaWidth;
            newHeight = currentHeight + deltaHeight;

            //We do not want to update the x-coordinate because it is located at the top left part of the node.
            newX = getPosition().getX();
            newY = getPosition().getY() + deltaY;

            if (newWidth < minWidth) {
                newWidth = minWidth;
            }
            if (newHeight < minHeight) {
                double heightOffset = newHeight - currentHeight;
                newHeight = minHeight;
                newY = newY + heightOffset;
            }
        } else if (location.equals(Enumerators.TransformerLocation.BOTTOMLEFT)) {
            //The width should increase if the mouse moves to the left.
            double deltaWidth = (-1) * deltaX;

            //The height should increase if the mouse moved down.
            double deltaHeight = deltaY;

            newWidth = currentWidth + deltaWidth;
            newHeight = currentHeight + deltaHeight;

            //We do not need to update the y-coordinate, only the x-coordinate.
            newX = getPosition().getX() + deltaX;
            newY = getPosition().getY();

            if (newWidth < minWidth) {
                double widthOffset = newWidth - minWidth;
                newWidth = minWidth;
                newX = newX + widthOffset;
            }
            if (newHeight < minHeight) {
                newHeight = minHeight;
            }

        } else if (location.equals(Enumerators.TransformerLocation.BOTTOMRIGHT)) {
            //The width should increase if the mouse moves to the right.
            double deltaWidth = deltaX;

            //The height should increase if the mouse moved down.
            double deltaHeight = deltaY;

            newWidth = currentWidth + deltaWidth;
            newHeight = currentHeight + deltaHeight;

            //We do not need to update any of the coordinates because the node is resized towards the bottom right already.
            //No change to position.
            newX = this.getX();
            newY = this.getY();

            if (newWidth < minWidth) {
                newWidth = minWidth;
            }
            if (newHeight < minHeight) {
                newHeight = minHeight;
            }

        } else if (location.equals(Enumerators.TransformerLocation.MIDDLELEFT)) {
            //The width should increase when the mouse moves left.
            double deltaWidth = (-1) * deltaX;

            //The height never needs to change.
            //Only the x coordinate needs to change.
            newWidth = currentWidth + deltaWidth;
            newHeight = currentHeight;
            newX = getPosition().getX() + deltaX;
            newY = getPosition().getY();

            if (newWidth < minWidth) {
                double widthOffset = newWidth - minWidth;
                newWidth = minWidth;
                newX = newX + widthOffset;
            }
        } else if (location.equals(Enumerators.TransformerLocation.MIDDLEBOTTOM)) {
            //The height should increase when the mouse moves down.
            double deltaHeight = deltaY;

            //The width or x coordinates never need to change.
            newHeight = currentHeight + deltaHeight;
            newWidth = currentWidth;
            newX = getPosition().getX();
            newY = getPosition().getY();

            if (newHeight < minHeight) {
                newHeight = minHeight;
            }
        } else if (location.equals(Enumerators.TransformerLocation.MIDDLERIGHT)) {
            //The width should increase when the mouse moved to the right.
            double deltaWidth = deltaX;

            //The position or height never needs to change.
            newWidth = currentWidth + deltaWidth;
            newHeight = currentHeight;

            newX = getPosition().getX();
            newY = getPosition().getY();

            if (newWidth < minWidth) {
                newWidth = minWidth;
            }
        } else if (location.equals(Enumerators.TransformerLocation.MIDDLETOP)) {
            //Only the height and y coordinate ever changes.

            double deltaHeight = (-1) * deltaY;

            newWidth = currentWidth;
            newHeight = currentHeight + deltaHeight;

            newX = getPosition().getX();
            newY = getPosition().getY() + deltaY;

            if (newHeight < minHeight) {
                double heightOffset = newHeight - currentHeight;
                newHeight = minHeight;
                newY = newY + heightOffset;
            }
        }

        if (!((newX == this.getX()) && (newY == this.getY()) && (newWidth == currentWidth) && (newHeight == currentHeight))) {
            position_ = new Point2D(newX, newY);
            
            width_ = newWidth;
            height_ = newHeight;
            
            parent_.notifySubscribersOfAlter(this);

        }
    }

    /**
     * This functions should be called whenever this element needs to undergo a
     * translation. It informs the model that it needs to change
     *
     * @param deltaX - The amount the mouse moved in the x coordinate plane.
     * @param deltaY - the amount the mouse moved in the y coordinate plane. *
     */
    public void translate(double deltaX, double deltaY) {
        setPosition(new Point2D(getPosition().getX() + deltaX, getPosition().getY() + deltaY));
        parent_.notifySubscribersOfAlter(this);
    }

    // Protected Methods
    /**
     * @return The parent WorkSpaceGraph of which this WorkSpaceGraphElement is
     * a node
     */
    protected WorkSpaceGraph getParent() {
        return parent_;
    }

    // Private Member Variables
    private Point2D position_;
    private final WorkSpaceGraph parent_;
    private int zIndex_;
    private double width_;
    private double height_;
    private final double minWidth_;
    private final double minHeight_;
}
