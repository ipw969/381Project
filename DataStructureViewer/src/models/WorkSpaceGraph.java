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
        paths_ = new ArrayList<>();
        hotSpots_ = new ArrayList<>();
    }

    // Public Methods
    public void addPath(HotSpot start, HotSpot end) {
        Path newPath = new Path(start, end);
        
        this.paths_.add(newPath);
        notifySubscribersOfPathAdd(newPath);
    }
    
    /**
     * Adds the provided WorkSpaceGraphElement to this WorkSpaceGraph as a node
     *
     * @param element::WorkSpaceGraphElement ~ The WorkSpaceGraphElement node to
     * add to the WorkSpaceGraph
     */
    public void addElement(WorkSpaceGraphElement element) {
        element.setZIndex(getHighestZIndex() + 1);
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
    
    /**Add the provided Path to the WorkSpaceGraph as a connector between two elements
     
     @param p :: Path - the path to add as a connector to the graph**/
    public void addConnector(Path p)
    {
        paths_.add(p);
    }
    
    /**Passes the lists of connectors**/
    public ArrayList<Path> getConnectors()
    {
        return paths_;
    }
    
    /**Add the provided HotSpot to the Array
     * @param h     
     **/
    public void addHotSpot(HotSpot h)
    {
        hotSpots_.add(h);
    }
    
    public HotSpot isHotSpot(double x, double y)
     {
       for(HotSpot h : hotSpots_)
         {
             if(h.contains(x, y))
             {
                 return h;
             } 
         }
         return null;
         
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
        WorkSpaceGraphElement highestZOrderedElement = null;
        for (WorkSpaceGraphElement currentElement : elements_) {
            if (currentElement.containsPoint(pointX, pointY)) {
                if (highestZOrderedElement == null) {
                    highestZOrderedElement = currentElement;
                } else if (highestZOrderedElement.getZIndex() < currentElement.getZIndex()) {
                    highestZOrderedElement = currentElement;
                }
            }
        }
        return highestZOrderedElement;
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
        for (WorkSpaceGraphElement currentElement : elements_) {
            if (currentElement.isContainedWithin(rectangleX1, rectangleY1,
                    rectangleX2, rectangleY2)) {
                foundElements.add(currentElement);
            }
        }
        return foundElements;
    }

    /**
     * Brings the provided element to the front in terms of ZIndex
     *
     * @param element::WorkSpaceGraphElement ~ The element to be brought to the
     * front
     */
    public void bringToFront(WorkSpaceGraphElement element) {

        int currentHighestZIndex = getHighestZIndex();
        element.setZIndex(currentHighestZIndex + 1);
        notifySubscribersOfZIndexAltered(element, true);
    }

    /**
     * Sends the provided element to the back in terms of ZIndex
     *
     * @param element::WorkSpaceGraphElement ~ The element to be sent to the
     * back
     */
    public void sendToBack(WorkSpaceGraphElement element) {
        int currentLowestZIndex = getLowestZIndex();
        element.setZIndex(currentLowestZIndex - 1);
        notifySubscribersOfZIndexAltered(element, false);
    }

    /**
     * Moves the element to the specified position and informs the listeners of
     * this graph.
     *
     * @param element: The element to move.
     * @param newXCoord: The new X coordinate of the element.
     * @param newYCoord: The new Y coordinate of the element.
     */
    public void moveElement(WorkSpaceGraphElement element, double newXCoord, double newYCoord) {
        if (element != null) {
            element.setX(newXCoord);
            element.setY(newYCoord);

            notifySubscribersOfAlter(element);
        }

    }
    // Private Methods

    public void notifySubscribersOfPathAdd(Path path) {
        for (WorkSpaceGraphListener subscriber: subscribers_) {
            subscriber.onPathAdded(path);
        }
    }
    
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

    /**
     * Notifies all subscribers that a WorkSpaceGraphElement has has its ZIndex
     * altered
     *
     * @param element::WorkSpaceGraphElement ~ The WorkSpaceGraphElement which
     * has had it's ZIndex Altered
     * @param broughtToFront::boolean ~ Whether the element was brought to
     * front, or sent to back
     */
    private void notifySubscribersOfZIndexAltered(WorkSpaceGraphElement element,
            boolean broughtToFront) {

        for (WorkSpaceGraphListener subscriber : subscribers_) {
            subscriber.onElementZIndexAltered(element, broughtToFront);
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
    protected void notifySubscribersOfAlter(WorkSpaceGraphElement element) {
        for (WorkSpaceGraphListener subscriber : subscribers_) {
            subscriber.onElementAltered(element);
        }
    }

    // Private Methods
    
    /**
     * @return The highest Z index of any item within the WorkSpaceGraph
     */
    private int getHighestZIndex() {
        int currentHighestZIndex = 0;
        for (WorkSpaceGraphElement currentElement : elements_) {
            if (currentElement.getZIndex() > currentHighestZIndex) {
                currentHighestZIndex = currentElement.getZIndex();
            }
        }

        return currentHighestZIndex;
    }

    /**
     * @return The lowest Z index of any item within the WorkSpaceGraph
     */
    private int getLowestZIndex() {
        int currentLowestZIndex = 0;
        for (WorkSpaceGraphElement currentElement : elements_) {
            if (currentElement.getZIndex() < currentLowestZIndex) {
                currentLowestZIndex = currentElement.getZIndex();
            }
        }

        return currentLowestZIndex;
    }

    // Private Member Variables
    private final ArrayList<WorkSpaceGraphElement> elements_;
    private final ArrayList<WorkSpaceGraphListener> subscribers_;
    private final ArrayList<Path> paths_;
    private final ArrayList<HotSpot> hotSpots_;
}
