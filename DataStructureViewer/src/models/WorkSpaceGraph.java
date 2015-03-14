package models;

import java.util.ArrayList;
import java.util.List;

/**
 * A class which represents the structure of elements which are being
 * represented in a WorkSpace
 *
 * @author Iain Workman
 */
public class WorkSpaceGraph {

    // Constructor
    /**
     * Creates an empty WorkSpaceGraph with no element nodes or edges
     */
    public WorkSpaceGraph() {
        elements_ = new ArrayList<>();
        subscribers_ = new ArrayList<>();
    }

    // Public Methods
    /**
     * Adds the provided WorkSpaceGraphElement to this WorkSpaceGraph as a node
     *
     * @param element::WorkSpaceGraphElement ~ The WorkSpaceGraphElement node to
     * add to the WorkSpaceGraph
     */
    public void addElement(WorkSpaceGraphElement element) {
        elements_.add(element);
        notifySubscribersOfAdd(element);

    }

    /**
     * Removed the provided WorkSpaceGraphElement from this WorkSpaceGraph, and
     * all the incoming and outgoing connections from it
     *
     * @param element::WorkSpaceGraphElement ~ The WorkSpaceGraphElement node to
     * remove from the WorkSpaceGraph
     */
    public void removeElement(WorkSpaceGraphElement element) {
        notifySubscribersOfRemove(element);
        elements_.remove(element);
    }

    /**
     * Adds a WorkSpaceGraphListener to this WorkSpaceGraph which is to be
     * informed of changes.
     *
     * @param subscriber::WorkSpaceGraphListener ~ The subscriber which wished
     * to be notified of changes
     */
    public void addSubscriber(WorkSpaceGraphListener subscriber) {
        subscribers_.add(subscriber);
    }

    /**
     * Removes a WorkSpaceGraphListener from this WorkSpaceGraph which will no
     * longer be informed of changes.
     *
     * @param subscriber::WorkSpaceGraphListener ~ The WorkSpaceGraphListener
     * which no longer wishes to be notified of changes
     */
    public void removeSubscriber(WorkSpaceGraphListener subscriber) {
        subscribers_.remove(subscriber);
    }

    /**
     * The WorkSpaceGraphElement which lies under the provided coordinates, or
     * null if no WorkSpaceGraphElement lies under the provided coordinates.
     *
     * 
     * @param pointX::double ~ The x coordinate of the point to check
     * @param pointY::double ~ The y coordinate of the point to check
     * @return The element under the provided point, or null if no element lies
     * under the provided point.
     */
    public WorkSpaceGraphElement getElementAt(double pointX, double pointY) {
        for (WorkSpaceGraphElement currentElement : elements_) {
            if(currentElement.containsPoint(pointX, pointY)) {
                return currentElement;
            }
        }
        return null;
    }

    /**
     * Returns a list of all the elements which are contained wholly within the
     * provided rectangle
     *
     * @param rectangleX1::double ~ The relative top left X position of the
     * rectangle
     * @param rectangleY1::double ~ The relative top left Y position of the
     * rectangle
     * @param rectangleX2::double ~ The relative bottom right X position of the
     * rectangle
     * @param rectangleY2::double ~ The relative bottom right Y position of the
     * rectangle
     * @return
     */
    public List<WorkSpaceGraphElement> getElementsWithin(double rectangleX1,
            double rectangleY1, double rectangleX2, double rectangleY2) {
        ArrayList<WorkSpaceGraphElement> foundElements = new ArrayList<>();
        for(WorkSpaceGraphElement currentElement : elements_) {
            if(currentElement.isContainedWithin(rectangleX1, rectangleY1, 
                                                rectangleX2, rectangleY2)) {
                foundElements.add(currentElement);
            }
        }
        return foundElements;
    }

    // Private Methods
    /**
     * Notifies all subscribers that a WorkSpaceGraphElement has been added to
     * this WorkSpaceGraph
     *
     * @param element::WorkSpaceGraphElement ~ The WorkSpaceGraphElement which
     * was added to the WorkSpaceGraph
     */
    private void notifySubscribersOfAdd(WorkSpaceGraphElement element) {
        for (WorkSpaceGraphListener subscriber : subscribers_) {
            subscriber.onElementAdded(element);
        }
    }

    /**
     * Notifies all subscribers that a WorkSpaceGraphElement has been removed
     * from the WorkSpaceGraph
     *
     * @param element::WorkSpaceGraphElement ~ The WorkSpaceGraphElement which
     * has been removed from the WorkSpaceGraph
     */
    private void notifySubscribersOfRemove(WorkSpaceGraphElement element) {
        for (WorkSpaceGraphListener subscriber : subscribers_) {
            subscriber.onElementRemoved(element);
        }
    }

    // Package Methods
    /**
     * Notified all subscribers that a WorkSpaceGraphElement of this
     * WorkSpaceGraph has had one of its attributes altered.
     *
     * @param element::WorkSpaceGraphElement ~ The WorkSpaceGraphElement which
     * has had one of its attributes altered
     */
    void notifySubscribersOfAlter(WorkSpaceGraphElement element) {
        for (WorkSpaceGraphListener subscriber : subscribers_) {
            subscriber.onElementAltered(element);
        }
    }

    // Private Member Variables
    private final ArrayList<WorkSpaceGraphElement> elements_;
    private final ArrayList<WorkSpaceGraphListener> subscribers_;
}
