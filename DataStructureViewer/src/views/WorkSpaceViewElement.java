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

    
    public void refresh()
    {
        this.relocate(this.getElement().getX(), this.getElement().getY());
        this.update();
    }
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
                  TransformSpot tSpot = (TransformSpot) spot;
                  
                if (isVisible == false)
                {
               
                    tSpot.setEnabled(false);
                   
                }
                else
                {
                    
                    tSpot.setEnabled(true);
                }
                    
                    
                      
              } 
         }
    }
    
   public void setSize(double width, double height)
    {
        if (canResizeHeight(height))
        {
            this.setPrefHeight(height);
            this.setMaxHeight(height);
        }

        if (canResizeWidth(width))
        {
            this.setPrefWidth(width);
            this.setMaxWidth(width);
             
        }
    }


   public boolean canResizeHeight(double height)
   {
       double heightDifference = Math.abs(this.getHeight() - height);
       return (this.getHeight() - heightDifference > this.getMinHeight() ) || height > this.getHeight();
   }
   
   public boolean canResizeWidth(double width)
   {
       double widthDifference = Math.abs(this.getWidth() - width);
       return  (this.getWidth() - widthDifference > this.getMinWidth()) || width > this.getWidth();
   }
    public abstract void setupTransformers();
    
    // Private Member Variables
    private final WorkSpaceGraphElement element_;
    private boolean isSelected_;
   
    
}
