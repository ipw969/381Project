package models;

/**
 * Interface which all classes will implement if they wish to subscribe to be
 * notified of changes to a WorkSpaceGraph
 *
 * @author Iain Workman
 */
public interface WorkSpaceGraphListener {

    /**
     * Method which defines behavior for a WorkSpaceGraphElement being added
     *
     * @param element::WorkSpaceGraphElement ~ The element added
     */
    public void onElementAdded(WorkSpaceGraphElement element);

    /**
     * Method which defines behavior for a WorkSpaceGraphElement being removed
     *
     * @param element::WorkSpaceGraphElement ~ The element removed
     */
    public void onElementRemoved(WorkSpaceGraphElement element);

    /**
     * Method which defines behavior for a WorkSpaceGraphElement being altered
     *
     * @param element::WorkSpaceGraphElement ~ The element altered
     */
    public void onElementAltered(WorkSpaceGraphElement element);
    
    /**
     * Method which defines behavior for a WorkSpaceGraphElemet's Z Index being
     * altered within the Graph (ie sent to back, or brought to front)
     * 
     * @param element::WorkSpaceGraphElement ~ The element whose Z index was
     * altered
     * @param broughtForward::boolean ~ Whether the element was sendForward or
     * sentBackwards. The semantics of the ordering only allow for sendToBack()
     * and bringToFront() operations
     */
    public void onElementZIndexAltered(WorkSpaceGraphElement element, boolean broughtForward);
    
    /**
     * This method informs the listener that the given element has been moved.
     * Upon reaching this function, the new coordinates are already stored in the element.
     * @param WorkSpaceGraphElement The element that has been moved.
    */
    public void onElementMoved(WorkSpaceGraphElement element);
}
