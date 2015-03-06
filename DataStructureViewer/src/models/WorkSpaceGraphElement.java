package models;

import javafx.geometry.Point2D;

/**
 * A class which represents the data relating to a single element in a WorkSpace
 * That is all elements which are to be drawn in a work space (LinkedList Heads,
 * Arrays, Nodes etc) will all extend this class.
 * @author Iain Workman
 */
public abstract class WorkSpaceGraphElement {
    
    // Constructor
    public WorkSpaceGraphElement(int positionX, int positionY, WorkSpaceGraph parent) {
        position_ = new Point2D(positionX, positionY);
        parent_ = parent;
    }
    
    public WorkSpaceGraphElement(Point2D position, WorkSpaceGraph parent) {
        position_ = position;
        parent_ = parent;
    }
    
    // Public Methods
    public double getX() {
        return position_.getX();
    }
    
    public double getY() {
        return position_.getY();
    }
    
    public Point2D getPostion() {
        return position_;
    }
    
    public void setX(double positionX) {
        position_ = new Point2D(positionX, position_.getY());
        parent_.notifySubscribersOfAlter(this);
    }
    
    public void setY(double positionY) {
        position_ = new Point2D(position_.getX(), positionY);
        parent_.notifySubscribersOfAlter(this);
    }
    
    public void setPosition(double positionX, double positionY) {
        position_ = new Point2D(positionX, positionY);
        parent_.notifySubscribersOfAlter(this);
    }
    
    public void setPosition(Point2D position) {
        position_ = position;
        parent_.notifySubscribersOfAlter(this);
    }
    
    // Protected Methods
    protected WorkSpaceGraph getParent() {
        return parent_;
    }
    
    // Private Member Variables
    private Point2D position_;
    private final WorkSpaceGraph parent_;
}
