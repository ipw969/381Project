package views;

import Enumerators.Enumerators;
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

    /**Control whether the transformers are visible and usable or not.
     * 
     * @param isVisible True if the transformers should be visible and usable, false if not.
     */
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
    
    /**Set the size of this element , if possible
     * 
     * @param width the new width value
     * @param height the new height value.
     */
   public void setSize(double width, double height)
    {
        if (canResizeHeight(height))
        {
            this.setPrefHeight(height);
            this.setMaxHeight(height);
            this.onResize();
        }

        if (canResizeWidth(width))
        {
            this.setPrefWidth(width);
            this.setMaxWidth(width);
            this.onResize();
        }
    }
/**Every element needs to implement this because it may need to do additional work when it is resized.
 * This function should take care of any additional resize work the element has to take care of.
 */
   public abstract void onResize();
   
      /**Check to see if the new height value is under the minimum size for this element
    * 
    * @param height The proposed new width of the pane.
    * @return True if the element can be resized to that height, false if not.
    */
   public boolean canResizeHeight(double height)
   {
       double heightDifference = Math.abs(this.getHeight() - height);
       return (this.getHeight() - heightDifference > this.getMinHeight() ) || height > this.getHeight();
   }
   
   /**Check to see if the new width value is under the minimum size for this element
    * 
    * @param width The proposed new width of the pane.
    * @return True if the element can be resized to that width, false if not.
    */
   public boolean canResizeWidth(double width)
   {
       double widthDifference = Math.abs(this.getWidth() - width);
       return  (this.getWidth() - widthDifference > this.getMinWidth()) || width > this.getWidth();
   }
   
   /**SEts up the transformers for this element.
     * NOTE: The transformers are added to this as children.
     * Currently supports
     * TOPLEFT: DIAGONAL
     * TOPRIGHT: DIAGONAL
     * BOTTOMLEFT: DIAGONAL
     * BOTTOMRIGHT: DIAGONAL
    */
    public  void setupTransformers()
    {
        TransformSpot topLeft = new TransformSpot(Enumerators.TransformerType.DIAGONAL, Enumerators.TransformerLocation.TOPLEFT, this);
        TransformSpot topRight = new TransformSpot(Enumerators.TransformerType.DIAGONAL, Enumerators.TransformerLocation.TOPRIGHT, this);
        TransformSpot bottomLeft = new TransformSpot(Enumerators.TransformerType.DIAGONAL, Enumerators.TransformerLocation.BOTTOMLEFT, this);
        TransformSpot bottomRight = new TransformSpot(Enumerators.TransformerType.DIAGONAL, Enumerators.TransformerLocation.BOTTOMRIGHT, this);
        this.getChildren().add(topLeft);
        this.getChildren().add(topRight);
        this.getChildren().add(bottomLeft);
        this.getChildren().add(bottomRight); 
    }
    
    // Private Member Variables
    private final WorkSpaceGraphElement element_;
    private boolean isSelected_;
   
    
}
