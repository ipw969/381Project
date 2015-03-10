package models;

/**
 * Interface which all classes will implement if they wish to subscribe to be
 * notified of changes to a WorkSpaceGraph
 *
 * @author Iain Workman
 */
public interface WorkSpaceGraphListener {

    /**
     * Method which defines behaviour for a WorkSpaceGraphElement being added
     *
     * @param element::WorkSpaceGraphElement ~ The element added
     */
    public void onElementAdded(WorkSpaceGraphElement element);

    /**
     * Method which defines behaviour for a WorkSpaceGraphElement being removed
     *
     * @param element::WorkSpaceGraphElement ~ The element removed
     */
    public void onElementRemoved(WorkSpaceGraphElement element);

    /**
     * Method which defines behaviour for a WorkSpaceGraphElement being altered
     *
     * @param element::WorkSpaceGraphElement ~ The element altered
     */
    public void onElementAltered(WorkSpaceGraphElement element);
}
