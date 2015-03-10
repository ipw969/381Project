package views;

import javafx.scene.layout.Pane;
import models.WorkSpaceGraph;
import models.WorkSpaceGraphElement;
import models.WorkSpaceGraphListener;

/**
 * A view for representing a WorkSpaceGraph
 *
 * @author Iain Workman
 */
public class WorkSpaceView extends Pane implements WorkSpaceGraphListener {

    // Constructor
    /**
     * Creates an empty instance of a WorkSpaceView with no WorkSpaceGraph model
     */
    public WorkSpaceView() {
    }

    // Public Methods
    /**
     * Sets the model WorkSpaceGraph which this WorkSpaceView is visualizing
     *
     * @param workSpaceGraph::WorkSpaceGraph ~ The model which this view is
     * visualizing
     */
    public void setModel(WorkSpaceGraph workSpaceGraph) {
        if (workSpaceGraph_ != null) {
            workSpaceGraph_.removeSubscriber(this);
        }

        workSpaceGraph_ = workSpaceGraph;
        workSpaceGraph_.addSubscriber(this);
    }

    /**
     * Handles a WorkSpaceGraph element being added to the WorkSpaceGraph model
     *
     * @param element::WorkSpaceGraphElement ~ The element added to the
     * WorkSpaceGraph
     */
    @Override
    public void onElementAdded(WorkSpaceGraphElement element) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Handles a WorkSpaceGraph element being removed from the WorkSpaceGraph
     * model
     *
     * @param element::WorkSpaceGraphElement ~ The element removed from the
     * WorkSpaceGraph
     */
    @Override
    public void onElementRemoved(WorkSpaceGraphElement element) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Handles a WorkSpaceGraph element being altered in the WorkSpaceGraph
     * model
     *
     * @param element::WorkSpaceGraphElement ~ The element altered in the
     * WorkSpaceGraph
     */
    @Override
    public void onElementAltered(WorkSpaceGraphElement element) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    // Private Member Variables
    private WorkSpaceGraph workSpaceGraph_;

}
