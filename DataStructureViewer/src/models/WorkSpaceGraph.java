package models;

import java.util.ArrayList;

/**
 * A class which represents the structure of elements which are being represented
 * in a WorkSpace
 * @author Iain Workman
 */
public class WorkSpaceGraph {
    
    // Constructor
    public WorkSpaceGraph() {
        elements_ = new ArrayList<>();
        subscribers_ = new ArrayList<>();
    }
    
    // Public Methods
    public void addElement(WorkSpaceGraphElement element) {
        elements_.add(element);
        notifySubscribersOfAdd(element);
        
    }
    
    private void removeElement(WorkSpaceGraphElement element) {
        notifySubscribersOfRemove(element);
        elements_.remove(element);
    }
    
    // Private Methods
    private void notifySubscribersOfAdd(WorkSpaceGraphElement element) {
        for (WorkSpaceGraphListener subscriber : subscribers_) {
            subscriber.onElementAdded(element);
        }
    }
    
    private void notifySubscribersOfRemove(WorkSpaceGraphElement element) {
        for (WorkSpaceGraphListener subscriber : subscribers_) {
            subscriber.onElementRemoved(element);
        }
    }
    
    // Package Methods
    
    void notifySubscribersOfAlter(WorkSpaceGraphElement element) {
        for (WorkSpaceGraphListener subscriber : subscribers_) {
            subscriber.onElementAltered(element);
        }
    }
    
    // Private Member Variables
    ArrayList<WorkSpaceGraphElement> elements_;
    ArrayList<WorkSpaceGraphListener> subscribers_;
}
