package views;

import javafx.scene.layout.Pane;
import models.WorkSpaceGraphElement;

/**
 * Abstract class from which all widgets used to represent WorkSpaceGraphElement
 * in the WorkSpaceGraphView will descend.
 * @author Iain Workman
 */
public abstract class WorkSpaceViewElement extends Pane {

    // Constructor
    /**
     * The default constructor for all classes which extend WorkSpaceViewElement.
     * Is passed the WorkSpaceGraphElement that this view represents
     * @param element::WorkSpaceGraphElement ~ The WorkSpaceGraphElement which 
     * this view is representing
     */
    public WorkSpaceViewElement(WorkSpaceGraphElement element) {
        element_ = element;
    }
       
    // Public Methods
    /**
     * Abstract method which each WorkSpaceViewElement must implement to define
     * how it should change its visualization of the element which it represents.
     */
    public abstract void update();
    
    /**
     * The WorkSpaceGraphElement which this view is visualizing.
     * @return 
     */
    public WorkSpaceGraphElement getElement() {
        return element_;
    }
    
    // Private Member Variables
    private final WorkSpaceGraphElement element_;
}
