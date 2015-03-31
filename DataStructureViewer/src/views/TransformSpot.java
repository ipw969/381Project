package views;

import Enumerators.Enumerators.TransformerLocation;
import Enumerators.Enumerators.TransformerType;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/*
 * This class is used to attach TransformSpots to GraphViewElements. These nodes will be responsible for rescaling the nodes.
 * This class needs to have a location and a type associated with it.
 * This class ensures that it stays attached to its parent.
 * The class currently only supports the diagonal transformer spots.
 * @ author Ryan La Forge
 */
public class TransformSpot extends Rectangle {

    /**
     *
     * @param type The type of the Transformer. This is to indicate in what
     * direction it can move in.
     * @param location The location of the Transformer relative to its parent.
     * @param parent The parent of this TransformerSpot.
     */
    public TransformSpot(TransformerType type, TransformerLocation location, WorkSpaceViewElement parent) {
        this.type_ = type;
        this.location_ = location;
        this.parent_ = parent;

        this.setWidth(10);
        this.setHeight(10);
        this.setFill(Color.BLUE);

        //This change listener is attached to every value of the property of the parent. Anytime the parent 
        //moves, or resizes, the transfomrer spot will follow it.
        ChangeListener valueListener = new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                updatePosition();
            }
        };

        parent_.layoutXProperty().addListener(valueListener);
        parent_.layoutYProperty().addListener(valueListener);
        parent_.widthProperty().addListener(valueListener);
        parent_.heightProperty().addListener(valueListener);

        //Used to determine what cursor the transformerspot will change the mousecursor to. This is dependent on location.
        figureOutCursor();

        //The node will start invisible, and will only become visible when selected.
        setEnabled(false);

        this.setOnMouseEntered(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                if (enabled_) {
                    setCursor(cursorType_);
                }

            }
        });

        this.setOnMouseExited(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                if (enabled_) {
                    setCursor(Cursor.DEFAULT);
                }

            }
        });

        this.setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                previousMousePositionX_ = event.getSceneX();
                previousMousePositionY_ = event.getSceneY();
            }
        });

        //This is where hte rescaling happens.
        this.setOnMouseDragged(new EventHandler<MouseEvent>() {

            public void handle(MouseEvent event) {
                //deltaX will be positive if the mouse oved to the right, and negative if it moved to the left.
                double deltaX = event.getSceneX() - previousMousePositionX_;

                //deltaY will be positive if the mouse moved down, and negative if it moved up. 
                double deltaY = event.getSceneY() - previousMousePositionY_;

                parent_.transform(deltaX, deltaY, location_);

                previousMousePositionX_ = event.getSceneX();
                previousMousePositionY_ = event.getSceneY();

            }
        });

    }

    /**
     * SEt whether this node is visible or not.
     *
     * @param isEnabled Whether the node is visible and usable or not.
     */
    public void setEnabled(boolean isEnabled) {
        if (isEnabled) {
            this.setOpacity(0.5);
            this.setPickOnBounds(true);
        } else {
            this.setPickOnBounds(false);
            this.setOpacity(0);
        }
        enabled_ = isEnabled;
    }

    /**
     *
     * @return Whether this node is currently visible and usable.
     */
    public boolean isEnabled() {
        return enabled_;
    }

    /**
     * Determine the mouse cursor to be used when the mouse enters this
     * transformer spot.
     *
     */
    private void figureOutCursor() {

        switch (this.location_) {
            case TOPRIGHT:
                cursorType_ = Cursor.NE_RESIZE;
                break;
            case TOPLEFT:
                cursorType_ = Cursor.NW_RESIZE;
                break;
            case BOTTOMLEFT:
                cursorType_ = Cursor.SW_RESIZE;
                break;
            case BOTTOMRIGHT:
                cursorType_ = Cursor.SE_RESIZE;
                break;
            case MIDDLELEFT:
                cursorType_ = Cursor.W_RESIZE;
                break;
            case MIDDLERIGHT:
                cursorType_ = Cursor.E_RESIZE;
                break;
            case MIDDLETOP:
                cursorType_ = Cursor.N_RESIZE;
                break;
            case MIDDLEBOTTOM:
                cursorType_ = Cursor.S_RESIZE;
                break;

        }

    }

    /**
     * Update the position of the transformer spot according to its parent.
     *
     */
    private void updatePosition() {
        if (this.location_.equals(TransformerLocation.TOPLEFT)) {
            this.relocate(0 - this.getWidth() / 2, 0 - this.getHeight() / 2);
        }
        if (this.location_.equals(TransformerLocation.TOPRIGHT)) {
            this.relocate(parent_.getWidth() - this.getWidth() / 2, 0 - this.getHeight() / 2);
        }
        if (this.location_.equals(TransformerLocation.BOTTOMLEFT)) {
            this.relocate(0 - this.getWidth() / 2, parent_.getHeight() - this.getHeight() / 2);
        }
        if (this.location_.equals(TransformerLocation.BOTTOMRIGHT)) {
            this.relocate(parent_.getWidth() - this.getWidth() / 2, parent_.getHeight() - this.getHeight() / 2);
        }
        if (this.location_.equals(TransformerLocation.MIDDLETOP)) {
            this.relocate((parent_.getWidth() / 2), 0 - (this.getHeight() / 2));
        }
        if (this.location_.equals(TransformerLocation.MIDDLELEFT)) {
            this.relocate(0 - (this.getWidth() / 2), (parent_.getHeight() / 2));
        }
        if (this.location_.equals(TransformerLocation.MIDDLEBOTTOM)) {
            this.relocate(parent_.getWidth() / 2, parent_.getHeight() - (this.getHeight() / 2));
        }
        if (this.location_.equals(TransformerLocation.MIDDLERIGHT)) {
            this.relocate(parent_.getWidth() - (this.getWidth() / 2), parent_.getHeight() / 2);
        }
    }

    //private variables.
    //Used to track the mouse's previous location. These should be updated in every mouse event this is interested in.
    private double previousMousePositionX_;
    private double previousMousePositionY_;

    //Whether or not the parent is currently being resized.
    private boolean dragInProgress_ = false;

    //The location of this transformer relative to its parent.
    private TransformerLocation location_;

    private WorkSpaceViewElement parent_;

    //The type of the transformer, used to determine what direction it can move.
    private TransformerType type_;

    private Cursor cursorType_;
    private boolean enabled_;

}
