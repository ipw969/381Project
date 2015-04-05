package views;

import factories.WorkSpaceViewElementFactory;
import java.util.ArrayList;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
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
        viewElements_ = new ArrayList<>();
        selectionSet_ = new ArrayList<>();

        selectionRectangle_ = new Rectangle(0, 0, 0, 0);
        selectionRectangle_.setStroke(Color.WHITE);
        selectionRectangle_.setStrokeWidth(2);
        selectionRectangle_.setFill(Color.rgb(100, 149, 237, 0.6));
        elementPane_ = new Pane();
        Pane selectionOverlayPane = new Pane();
        selectionOverlayPane.getChildren().add(selectionRectangle_);

        this.getChildren().addAll(elementPane_, selectionOverlayPane);

        elementPane_.setPickOnBounds(false);
        selectionOverlayPane.setPickOnBounds(false);


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
     * Selects an WorkSpaceViewElement at the provided position of the
     * WorkSpaceView.
     *
     * @param positionX::double ~ The X position of the coordinate to select an
     * item at
     * @param positionY::double ~ The Y position of the coordinate to select an
     * item at
     * @param appendToSelection::boolean ~ Whether the item under to coordinate
     * should be appended to the selection set.
     */
    public void selectAt(double positionX, double positionY, boolean appendToSelection) {
        WorkSpaceGraphElement elementUnderPoint
                = workSpaceGraph_.getElementAt(positionX, positionY);

        if (elementUnderPoint != null) {
            selectViewForElement(elementUnderPoint, appendToSelection);
        }
    }

    /**
     * Selects all the WorkSpaceGraphViewElements which are contained within the
     * provided rectangle
     *
     * @param rectangleX1::double ~ The X coordinate of the rectangle top left
     * @param rectangleY1::double ~ The Y coordinate of the rectangle top left
     * @param rectangleX2::double ~ The X coordinate of the rectangle bottom
     * right
     * @param rectangleY2::double ~ The Y coordinate of the rectangle bottom
     * right
     * @param appendToSelection::boolean ~ Whether to append the contents of the
     * rectangle to the current selection or to form a new one.
     */
    public void selectWithRectangle(double rectangleX1,
            double rectangleY1,
            double rectangleX2,
            double rectangleY2, boolean appendToSelection) {

        List<WorkSpaceGraphElement> containedElements
                = workSpaceGraph_.getElementsWithin(rectangleX1, rectangleY1,
                        rectangleX2, rectangleY2);

        if (!appendToSelection) {
            clearSelection();
        }

        for (WorkSpaceGraphElement currentElement : containedElements) {
            for (WorkSpaceViewElement viewElement : viewElements_) {
                if (viewElement.getElement() == currentElement) {
                    if (!selectionSet_.contains(viewElement)) {
                        selectionSet_.add(viewElement);
                        viewElement.setIsSelected(true);
                    }
                }
            }
        }
    }

    /**
     * Clears the current selection of items in the WorkSpaceView
     */
    public void clearSelection() {
        for (WorkSpaceViewElement currentViewElement : viewElements_) {
            currentViewElement.setIsSelected(false);
        }
        selectionSet_.clear();
    }

    public void updateSelectionRectangle(double rectangleX1, double rectangleY1,
            double rectangleX2, double rectangleY2) {
        selectionRectangle_.setX(rectangleX1);
        selectionRectangle_.setY(rectangleY1);
        selectionRectangle_.setWidth(rectangleX2 - rectangleX1);
        selectionRectangle_.setHeight(rectangleY2 - rectangleY1);

        selectionRectangle_.setVisible(true);
    }

    public void resetSelectionRectangle() {
        selectionRectangle_.setVisible(false);

        selectionRectangle_.setX(0);
        selectionRectangle_.setY(0);
        selectionRectangle_.setWidth(0);
        selectionRectangle_.setHeight(0);

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

        if (elementToAdd != null) {
            elementToAdd.relocate(element.getX(), element.getY());

            elementPane_.getChildren().add(elementToAdd);
            viewElements_.add(elementToAdd);
            elementToAdd.update();
        }
    }

    /**Deletes every element that is currently selected.
     * 
     */
    public void deleteSelectionModel()
    {
        for(WorkSpaceViewElement viewElement : selectionSet_)
        {
            viewElement.onDelete(null);
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
                
        for (WorkSpaceViewElement viewElement : viewElements_) {
            if (viewElement.getElement() == element) {

                viewElement.onDelete(new EventHandler<ActionEvent>(){

                    @Override
                    public void handle(ActionEvent event) {
                        elementPane_.getChildren().remove(viewElement);
                        viewElements_.remove(viewElement);
                    }
                    
                });

            }
        }
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
        for (WorkSpaceViewElement viewElement : viewElements_) {
            if (viewElement.getElement() == element) {
                viewElement.relocate(element.getX(), element.getY());
                viewElement.update();
                return;
            }            
        }
    }

    /**
     * Handles a WorkSpaceGraphElement having its Z Index altered in the
     * underlying Graph
     *
     * @param element::WorkSpaceGraphElement ~ The element whose Z index was
     * altered
     * @param broughtForward::boolean ~ Whether the element was sendForward or
     * sentBackwards. The semantics of the ordering only allow for sendToBack()
     * and bringToFront() operations
     */
    @Override
    public void onElementZIndexAltered(WorkSpaceGraphElement element, boolean broughtForward) {
        for (WorkSpaceViewElement viewElement : viewElements_) {
            if (viewElement.getElement() == element) {
                if (broughtForward) {
                    viewElement.toFront();
                } else {
                    viewElement.toBack();
                }
            }
        }
    }

    /**
     * Get the GraphSpaceViewElements that are currently selected in this graph.
     *
     * @return the selection set of this graph.
     */
    public List<WorkSpaceViewElement> getSelectionSet() {
        return selectionSet_;
    }

    /**
     * Readjust all of the elements by the given amounts.
     *
     * @param deltaX : The amount that the mouse moved in the x coordinate
     * plane. Should be positive if the mouse moved to the right, and negative
     * if it moved to the left.
     * @parem deltaY : The amount that the mouse moved in the y coordinate
     * plane. Should be positive if the mouse moves down, and negative if it
     * moves to the left.
     */
    public void moveSelection(double deltaX, double deltaY) {
        for (WorkSpaceViewElement element : selectionSet_) {
            element.translate(deltaX, deltaY);
        }
    }

    // Private Methods

    /**
     * Selects the WorkSpaceViewElement which is being used to visualize the
     * provided WorkSpaceGraphElement
     *
     * @param element::WorkSpaceGraphElement ~ The model element which the
     * WorkSpaceViewElement to be selected is visualizing
     * @param appendToSelection::boolean ~ Whether to append to the current
     * selection, or start a new selection
     */
    private void selectViewForElement(WorkSpaceGraphElement element,
            boolean appendToSelection) {
        for (WorkSpaceViewElement viewElement : viewElements_) {
            if (viewElement.getElement() == element) {
                if (appendToSelection && !selectionSet_.contains(viewElement)) {
                    selectionSet_.add(viewElement);
                    viewElement.setIsSelected(true);
                } else {
                    clearSelection();
                    selectionSet_.add(viewElement);
                    viewElement.setIsSelected(true);
                }
                return;
            }
        }
    }

    // Private Member Variables
    private final Pane elementPane_;
    private final Rectangle selectionRectangle_;
    private WorkSpaceGraph workSpaceGraph_;
    private final ArrayList<WorkSpaceViewElement> viewElements_;
    private final List<WorkSpaceViewElement> selectionSet_;
}
