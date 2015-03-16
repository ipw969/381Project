package models;

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
    public WorkSpaceGraphElement(double positionX, double positionY, int zIndex, WorkSpaceGraph parent) {
        position_ = new Point2D(positionX, positionY);
        zIndex_ = zIndex;
        parent_ = parent;
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
    public Point2D getPostion() {
        return position_;
    }

    /**
     * @return The z index (vertical ordering) of the WorkSpaceGraphElement
     */
    public int getZIndex() {
        return zIndex_;
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
    public abstract boolean containsPoint(double pointX, double pointY);

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
    public abstract boolean isContainedWithin(double rectangleX1, double rectangleY1, double rectangleX2, double rectangleY2);

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
}
