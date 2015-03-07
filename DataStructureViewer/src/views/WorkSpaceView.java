package views;

import javafx.scene.layout.Pane;
import models.WorkSpaceGraph;
import models.WorkSpaceGraphElement;
import models.WorkSpaceGraphListener;

/**
 * A view for representing a WorkSpaceGraph
 * @author Iain Workman
 */
public class WorkSpaceView extends Pane implements WorkSpaceGraphListener {

    // Constructor
    public WorkSpaceView() {
        
    }
    
    // Public Methods
    public void setModel(WorkSpaceGraph workSpaceGraph) {
        workSpaceGraph_ = workSpaceGraph;
    }
    
    @Override
    public void onElementAdded(WorkSpaceGraphElement element) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void onElementRemoved(WorkSpaceGraphElement element) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void onElementAltered(WorkSpaceGraphElement element) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    // Private Member Variables
    private WorkSpaceGraph workSpaceGraph_;

    
}
