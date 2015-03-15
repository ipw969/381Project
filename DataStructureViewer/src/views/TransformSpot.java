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
        
        figureOutCursor();
        setEnabled(false);
        this.setOnMouseEntered(new EventHandler<MouseEvent>()
        {
            public void handle(MouseEvent event)
            {
               if (enabled_)
               {
                    setCursor(cursorType_);
               }
               
            }
        });
        
        this.setOnMouseExited(new EventHandler<MouseEvent>()
        {
            public void handle(MouseEvent event)
            {
                if (enabled_)
                {
                    setCursor(Cursor.DEFAULT);
                }
                    
            }
        });
        
        this.setOnMouseDragged(new EventHandler<MouseEvent>()
        {
            public void handle(MouseEvent event)
            {
               
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
    
  public void setEnabled(boolean isEnabled)
  {
      if (isEnabled)
      {
          this.setOpacity(100);
      }
      else
      {
          this.setOpacity(0);
      }
      enabled_ = isEnabled;
  }
  
  public boolean isEnabled()
  {
      return enabled_;
  }
    private void figureOutCursor()
    {

        switch (this.location_)
        {
            case TOPRIGHT : cursorType_ = Cursor.NE_RESIZE;
                            break;
            case TOPLEFT : cursorType_ = Cursor.NW_RESIZE;
                            break;
            case BOTTOMLEFT : cursorType_ = Cursor.SW_RESIZE;
                            break;
            case BOTTOMRIGHT : cursorType_ = Cursor.SE_RESIZE;
                                break;
            case MIDDLELEFT : cursorType_ = Cursor.W_RESIZE;
                                break;
            case MIDDLERIGHT: cursorType_ = Cursor.E_RESIZE;
                                break;      
            case MIDDLETOP :    cursorType_= Cursor.N_RESIZE;
                                break;
            case MIDDLEBOTTOM : cursorType_ = Cursor.S_RESIZE;
                                break;
                    
        }
            
        
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
   private Cursor cursorType_;
   private boolean enabled_;
    
}
