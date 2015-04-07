package views;

import Enumerators.Enumerators;
import Enumerators.Enumerators.TransformerLocation;
import events.HotSpotEvent;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javafx.animation.ScaleTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import models.HotSpot;
import models.WorkSpaceGraphElement;

/**
 * Abstract class from which all widgets used to represent WorkSpaceGraphElement
 * in the WorkSpaceGraphView will descend.
 *
 * @author Iain Workman
 */
public abstract class WorkSpaceViewElement extends StackPane {

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
        hotSpotViews_ = new ArrayList<>();
        componentsPane_ = new Pane();
        hotSpotPane_ = new Pane();

        this.getChildren().addAll(componentsPane_, hotSpotPane_);

        for (HotSpot hotspot : element.getHotSpots()) {
            HotSpotView hotSpotView = new HotSpotView(hotspot);

            hotSpotView.setOnMouseClicked((MouseEvent e) -> {
                if (e.getButton() == MouseButton.PRIMARY && onHotSpotClicked_ != null) {
                    onHotSpotClicked_.handle(new HotSpotEvent(hotspot, this));
                }
            });

            hotSpotViews_.add(hotSpotView);
            hotSpotPane_.getChildren().add(hotSpotView);
        }

        this.addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                previousTime_ = System.currentTimeMillis();
                previousX_ = element.getX();
                previousY_ = element.getY();
            }
        });

        this.addEventHandler(MouseEvent.MOUSE_RELEASED, new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {

                double timeDifference = System.currentTimeMillis() - previousTime_;
                double dx = Math.abs(element_.getX() - previousX_);
                double dy = Math.abs(element_.getY() - previousY_);
                if (timeDifference < timeDeletionTolerance_ && ((dx > deletionDistanceTolerance_ || dy > deletionDistanceTolerance_) || (dx > (deletionDistanceTolerance_ * 0.75) && dy > (deletionDistanceTolerance_ * 0.75)))) {
                    ((WorkSpaceView) getParent().getParent()).deleteSelectionModel();

                } else if (WorkSpaceViewElement.this.getElement().getX() < 0 && WorkSpaceViewElement.this.getElement().getY() < 0) {
                    ((WorkSpaceView) getParent().getParent()).deleteSelectionModel();
                }

            }
        });
    }

    /**
     * This method should be called whenever this viewElement is to be deleted.
     * It plays a shrink animation before it is deleted. This will result in
     * this element being removed from the GraphView and the GraphModel.
     *
     * @param event The task to perform after the deletion.
     */
    public void onDelete(EventHandler<ActionEvent> event) {

        ScaleTransition deleteAnimation = new ScaleTransition(Duration.millis(1000), this);
        deleteAnimation.setToX(0);
        deleteAnimation.setToY(0);
        deleteAnimation.play();
        if (event == null) {
            event = new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    element_.delete();
                }
            };
        }

        deleteAnimation.setOnFinished(event);

    }

    // Public Methods
    public Pane getComponentsPane() {
        return componentsPane_;
    }

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
        for (Node spot : this.hotSpotPane_.getChildren()) {
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
     * Sets the handler for the onHotSpotClicked event
     *
     * @param onHotSpotClicked::EventHandler<HotSpotEvent> ~ The handler which
     * will be run when the HotSpot is clicked
     */
    public void setOnHotSpotClicked(EventHandler<HotSpotEvent> onHotSpotClicked) {
        onHotSpotClicked_ = onHotSpotClicked;
    }

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
        hotSpotPane_.getChildren().add(topLeft);
        hotSpotPane_.getChildren().add(topRight);
        hotSpotPane_.getChildren().add(bottomLeft);
        hotSpotPane_.getChildren().add(bottomRight);
        hotSpotPane_.getChildren().add(middleLeft);
        hotSpotPane_.getChildren().add(middleRight);
        hotSpotPane_.getChildren().add(middleTop);
        hotSpotPane_.getChildren().add(middleBottom);
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
     * @ param deltaY - the amount the mouse moved in the y coordinate plane. *
     */
    public void translate(double deltaX, double deltaY) {
        this.getElement().translate(deltaX, deltaY);
    }

    public List<HotSpotView> getHotSpotViews() {
        return hotSpotViews_;
    }

    /**
     * Sets up the appropriate listeners for the given label to be editable by
     * the user.
     *
     * @param label ~ The label to set to be editable.
     * @param defaultText ~ The default text of the node. Prevents the text in
     * the label from being a proper substring of the given string. (AKA a count
     * label will not have its count text removed).
     */
    protected void setLabelEditable(Label label, String defaultText) {
        label.textProperty().addListener(new ChangeListener() {

            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                update();
            }

        });
        label.setOnMouseEntered(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                setCursor(Cursor.TEXT);

            }
        });

        label.setOnMouseExited(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                setCursor(Cursor.DEFAULT);
            }
        });
        label.setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                editLabel_ = label;
                editLabel_.setFocusTraversable(true);
                editLabel_.requestFocus();
            }
        });

        setOnKeyPressed(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent event) {
                if (editLabel_ != null) {
                    if (event.getCode().equals(KeyCode.BACK_SPACE) && editLabel_.getText().length() > defaultText.length()) {
                        editLabel_.setText(editLabel_.getText().substring(0, editLabel_.getText().length() - 1));
                    }

                    editLabel_.setText(editLabel_.getText() + event.getText());
                }

            }
        });
    }

    protected final void updateHotSpots() {
        for (HotSpotView hotspotView : getHotSpotViews()) {
            for (HotSpot hotspot : getElement().getHotSpots()) {
                if (hotspotView.getHotSpot() == hotspot) {
                    hotspotView.setCenterX(hotspot.getX());
                    hotspotView.setCenterY(hotspot.getY());
                }

            }
        }
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

    private Label editLabel_;

    private final Pane componentsPane_;
    private final Pane hotSpotPane_;

    private EventHandler<HotSpotEvent> onHotSpotClicked_;
    private final ArrayList<HotSpotView> hotSpotViews_;
}
