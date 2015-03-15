/*
 * This class is used 
 */
package views;

import Enumerators.Enumerators.TransformerLocation;
import Enumerators.Enumerators.TransformerType;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author Ryan
 */
public class TransformSpot extends Rectangle{
    
    public TransformSpot(TransformerType type, TransformerLocation location, WorkSpaceViewElement parent)
    {
        this.type_ = type;
        this.location_ = location;
        this.parent_ = parent;
        
        this.setWidth(10);
        this.setHeight(10);
        this.setFill(Color.BLUE);

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
        
        this.setOnMouseEntered(new EventHandler<MouseEvent>()
        {
            public void handle(MouseEvent event)
            {
                System.out.println("Mouse entered");
                setCursor(Cursor.WAIT);
            }
        });
        
        this.setOnMouseExited(new EventHandler<MouseEvent>()
        {
            public void handle(MouseEvent event)
            {
                System.out.println("Mouse left");
                setCursor(Cursor.DEFAULT);
            }
        });
        
        this.setOnMouseDragged(new EventHandler<MouseEvent>()
        {
            public void handle(MouseEvent event)
            {
                System.out.println("Mouse dragged");
                parent_.relocate(0,0);
            }
        });
        parent_.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            public void handle(MouseEvent event)
            {
                System.out.println("Mouse Clicked");
            }
        });
    }
    
    
    private void updatePosition()
    {
       if (this.location_.equals(TransformerLocation.TOPLEFT))
            this.relocate(0 - this.getWidth()/2, 0 - this.getHeight()/2);
       if (this.location_.equals(TransformerLocation.TOPRIGHT))
            this.relocate(parent_.getWidth() - this.getWidth()/2, 0 - this.getHeight()/2);
       if (this.location_.equals(TransformerLocation.BOTTOMLEFT))
            this.relocate(0 - this.getWidth()/2, parent_.getHeight() - this.getHeight()/2);
       if (this.location_.equals(TransformerLocation.BOTTOMRIGHT))
            this.relocate(parent_.getWidth() - this.getWidth()/2, parent_.getHeight() - this.getHeight()/2);
               
    }
    
    
    private TransformerLocation location_;
    private WorkSpaceViewElement parent_;
    private TransformerType type_;
    
}
