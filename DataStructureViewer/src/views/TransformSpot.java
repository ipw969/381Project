/*
 * This class is used 
 */
package views;

import Enumerators.Enumerators.TransformerLocation;
import Enumerators.Enumerators.TransformerType;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author Ryan
 */
public class TransformSpot extends Rectangle{
    
    public TransformSpot(TransformerType type, TransformerLocation location, WorkSpaceViewElement parent)
    {
        this.type = type;
        this.parent_ = parent;
        
        this.setStyle("-fx-background-color: blue;");
        this.setWidth(10);
        this.setHeight(10);
        this.relocate(parent.getLayoutX(), parent.getLayoutY());
        ChangeListener valueListener = new ChangeListener()
        {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                updatePosition();
            }  
        };

        parent.layoutXProperty().addListener(valueListener);
        parent.layoutYProperty().addListener(valueListener);
        parent.widthProperty().addListener(valueListener);
        parent.heightProperty().addListener(valueListener);
    }
    
    
    private void updatePosition()
    {
       this.relocate(parent_.getLayoutX(), parent_.getLayoutY());
    }
    
    
    
    private WorkSpaceViewElement parent_;
    private TransformerType type;
    
}
