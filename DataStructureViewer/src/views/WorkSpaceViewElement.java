package views;

import java.util.ArrayList;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import models.WorkSpaceGraphElement;

/**
 * Abstract class from which all widgets used to represent WorkSpaceGraphElement
 * in the WorkSpaceGraphView will descend.
 *
 * @author Iain Workman
 */
public abstract class WorkSpaceViewElement extends Pane {

    // Constructor
    /**
     * The default constructor for all classes which extend
     * WorkSpaceViewElement. Is passed the WorkSpaceGraphElement that this view
     * represents
     *
     * @param element::WorkSpaceGraphElement ~ The WorkSpaceGraphElement which
     * this view is representing
     */
    public WorkSpaceViewElement(WorkSpaceGraphElement element) {
        element_ = element;
    }

    // Public Methods
    /**
     * Abstract method which updates the WorkSpaceViewElement when its data had
     * been changed.
     */
    public abstract void update();

    /**
     * The WorkSpaceGraphElement which this view is visualizing.
     *
     * @return
     */
    public WorkSpaceGraphElement getElement() {
        return element_;
    }

    /**
     * The selection state of the WorkSpaceViewElement
     *
     * @return True if the item is selected, false otherwise
     */
    public boolean isSelected() {
        return isSelected_;
    }

    /**
     * Sets the selection state of the WorkSpaceViewElement
     *
     * @param selectionState::boolean ~ The new selection state of the
     * WorkSpaceViewElement
     */
    public void setIsSelected(boolean selectionState) {
        isSelected_ = selectionState;

        setTransformSpotsVisible(isSelected_);

    }

    public void setTransformSpotsVisible(boolean isVisible)
    {
        
          for (Node spot : this.getChildren())
          {
              if (spot instanceof TransformSpot)
              {
                  //we use opacity instead of visibility becuase setting visibility seems to have an effect on the size of the parent.
                if (isVisible == false)
                    spot.setOpacity(0);
                else
                    spot.setOpacity(100);
                      
              } 
         }
         
        

    }
    public abstract void setupTransformers();
    
    // Private Member Variables
    private final WorkSpaceGraphElement element_;
    private boolean isSelected_;
   
    
}
