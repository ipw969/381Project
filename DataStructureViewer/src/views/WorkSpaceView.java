package views;

import factories.WorkSpaceViewElementFactory;
import javafx.geometry.Point2D;
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
        setStyle("-fx-background-color: cornflowerblue;");
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
     * Converts the provided co-ordinates within the view to relative co-ordinates
     * ranging from 0 (top/left) to 1 (bottom/right)
     * @param positionX::double ~ The X position of the point to convert
     * @param positionY::double ~ The Y position of the point to convert
     * @return 
     */
    public Point2D getRelativePosition(double positionX, double positionY) {
        double relativeX;
        double relativeY;
        
        if(getWidth() == 0)
            relativeX = 0;
        else
            relativeX = positionX / getWidth();
        
        if(getHeight() == 0)
            relativeY = 0;
        else
            relativeY = positionY / getHeight();
        
        return new Point2D(relativeX, relativeY);
    }

    /**
     * Handles a WorkSpaceGraph element being added to the WorkSpaceGraph model
     *
     * @param element::WorkSpaceGraphElement ~ The element added to the
     * WorkSpaceGraph
     */
    @Override
    public void onElementAdded(WorkSpaceGraphElement element) {
        WorkSpaceViewElement elementToAdd = WorkSpaceViewElementFactory.viewElement(element);
        
        if(elementToAdd != null) {
            double absolutePositionX = element.getX() * getWidth();
            double absolutePositionY = element.getY() * getHeight();
            elementToAdd.relocate(absolutePositionX, absolutePositionY);
            getChildren().add(elementToAdd);
        }
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
