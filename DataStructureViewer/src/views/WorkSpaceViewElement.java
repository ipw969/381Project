package views;

import Enumerators.Enumerators;
import Enumerators.Enumerators.TransformerLocation;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.ScaleTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
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
        this.addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>(){
            public void handle(MouseEvent event)
            {
                previousTime_ = System.currentTimeMillis();
                previousX_ = element.getX();
                previousY_ = element.getY();
            }
        });
        
        this.addEventHandler(MouseEvent.MOUSE_RELEASED, new EventHandler<MouseEvent>(){
            public void handle(MouseEvent event)
            {

                double timeDifference = System.currentTimeMillis() - previousTime_;
                double dx = Math.abs(element_.getX() - previousX_);
                double dy = Math.abs(element_.getY() - previousY_);
                if (timeDifference < timeDeletionTolerance_ && ((dx > deletionDistanceTolerance_ || dy > deletionDistanceTolerance_) || (dx > (deletionDistanceTolerance_ * 0.75) && dy > (deletionDistanceTolerance_ * 0.75))))
                {
                          ((WorkSpaceView)  getParent().getParent()).deleteSelectionModel();

                }
                else if(WorkSpaceViewElement.this.getElement().getX() < 0 && WorkSpaceViewElement.this.getElement().getY() < 0)
                {
                     ((WorkSpaceView)  getParent().getParent()).deleteSelectionModel();
                }
 
            }
        });
    }

    /**This method should be called whenever this viewElement is to be deleted. It plays a shrink animation before it is deleted.
     * This will result in this element being removed from the GraphView and the GraphModel.
     * @param event The task to perform after the deletion.
     */
    public void onDelete(EventHandler<ActionEvent> event)
    {
        
        ScaleTransition deleteAnimation = new ScaleTransition(Duration.millis(1000), this);
        deleteAnimation.setToX(0);
        deleteAnimation.setToY(0);
        deleteAnimation.play();
        if (event == null)
        {
            event = new EventHandler<ActionEvent>(){
                @Override
                public void handle(ActionEvent event) {
                     element_.delete();
                }
            };
        }       
                    
        deleteAnimation.setOnFinished(event);


        
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

    /**
     * Control whether the transformers are visible and usable or not.
     *
     * @param isVisible True if the transformers should be visible and usable,
     * false if not.
     */
    public void setTransformSpotsVisible(boolean isVisible) {
        for (Node spot : this.getChildren()) {
            if (spot instanceof TransformSpot) {
                TransformSpot tSpot = (TransformSpot) spot;

                if (isVisible == false) {

                    tSpot.setEnabled(false);

                } else {

                    tSpot.setEnabled(true);
                }

            }
        }
    }

    /**
     * Set the size of this element , if possible
     *
     * @param width the new width value
     * @param height the new height value.
     */
    public void setSize(double width, double height) {

        this.setMaxHeight(height);
        this.setPrefHeight(height);

        this.setMaxWidth(width);
        this.setPrefWidth(width);

    }

    /**
     * Every element needs to implement this because it may need to do
     * additional work when it is resized. This function should take care of any
     * additional resize work the element has to take care of.
     */
    public abstract void onResize();

    /**
     * SEts up the transformers for this element. NOTE: The transformers are
     * added to this as children. Currently supports TOPLEFT: DIAGONAL TOPRIGHT:
     * DIAGONAL BOTTOMLEFT: DIAGONAL BOTTOMRIGHT: DIAGONAL
     */
    public void setupTransformers() {
        TransformSpot topLeft = new TransformSpot(Enumerators.TransformerType.DIAGONAL, Enumerators.TransformerLocation.TOPLEFT, this);
        TransformSpot topRight = new TransformSpot(Enumerators.TransformerType.DIAGONAL, Enumerators.TransformerLocation.TOPRIGHT, this);
        TransformSpot bottomLeft = new TransformSpot(Enumerators.TransformerType.DIAGONAL, Enumerators.TransformerLocation.BOTTOMLEFT, this);
        TransformSpot bottomRight = new TransformSpot(Enumerators.TransformerType.DIAGONAL, Enumerators.TransformerLocation.BOTTOMRIGHT, this);
        TransformSpot middleLeft = new TransformSpot(Enumerators.TransformerType.HORIZONTAL, Enumerators.TransformerLocation.MIDDLELEFT, this);
        TransformSpot middleRight = new TransformSpot(Enumerators.TransformerType.HORIZONTAL, Enumerators.TransformerLocation.MIDDLERIGHT, this);
        TransformSpot middleTop = new TransformSpot(Enumerators.TransformerType.VERTICAL, Enumerators.TransformerLocation.MIDDLETOP, this);
        TransformSpot middleBottom = new TransformSpot(Enumerators.TransformerType.VERTICAL, Enumerators.TransformerLocation.MIDDLEBOTTOM, this);
        this.getChildren().add(topLeft);
        this.getChildren().add(topRight);
        this.getChildren().add(bottomLeft);
        this.getChildren().add(bottomRight);
        this.getChildren().add(middleLeft);
        this.getChildren().add(middleRight);
        this.getChildren().add(middleTop);
        this.getChildren().add(middleBottom);
    }

    /**
     * This function is responsible for collecting information from a
     * TransformSpot, and figuring out what might have to be resized, then
     * sending the message to its element. This function should intercept the
     * signal if this node cannot be resized! *******NOTE::: This calculation is
     * done using a coordinate plane that increases in the right, down
     * directions.
     *
     * @param deltaX : The amount that the mouse moved in the x-coordinate
     * plane. negative if the mouse moved to the left, positive if it moved to
     * the right.
     * @param deltaY : The amount that the mouse moved in the y-coordinate
     * plane. negative if the mouse moved to the left, positive if it moved to
     * the right.
     * @param location : The TransformSpot that generated this event.
     */
    public void transform(double deltaX, double deltaY, TransformerLocation location) {
        //intercept the signal if unable to resize the node to prevent movement.
        this.getElement().transform(deltaX, deltaY, location);

    }

    /**
     * This functions should be called whenever this view element needs to
     * undergo a transformation. It informs the model that it needs to change
     *
     * @param deltaX - The amount the mouse moved in the x coordinate plane.
     * @ param deltaY - the amount the mouse moved in the y coordinate plane.
     * *
     */
    public void translate(double deltaX, double deltaY) {
        this.getElement().translate(deltaX, deltaY);
    }
    // Private Member Variables
    private final WorkSpaceGraphElement element_;
    private boolean isSelected_;

    //This variable are used to keep track of the time in order to moniter whether deletion
    //should occur or not.
    private double previousTime_;

    
    
    //the previous coordinates that this was located at. Used to track movement for deletion.
    private double previousX_;
    private double previousY_;
    
    private double deletionDistanceTolerance_ = 750;
    private double timeDeletionTolerance_ = 500;
}
