package models;

import java.util.ArrayList;

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
    ArrayList<WorkSpaceGraphElement> elements_;
    ArrayList<WorkSpaceGraphListener> subscribers_;
}
