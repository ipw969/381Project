package models;

/**
 *
 * @author Iain Workman
 */
public interface WorkSpaceGraphListener {
    public void onElementAdded(WorkSpaceGraphElement element);
    public void onElementRemoved(WorkSpaceGraphElement element);
    public void onElementAltered(WorkSpaceGraphElement element);
}
