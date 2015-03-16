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

        this.relocate(parent_.getLayoutX(), parent_.getLayoutY());
        ChangeListener valueListener = new ChangeListener()
        {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                updatePosition();
            }  
        };

        parent_.layoutXProperty().addListener(valueListener);
        parent_.layoutYProperty().addListener(valueListener);
        parent_.widthProperty().addListener(valueListener);
        parent_.heightProperty().addListener(valueListener);
        
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
        
        this.setOnMousePressed(new EventHandler<MouseEvent>()
        {
            public void handle(MouseEvent event)
            {
                previousMousePositionX_ = event.getSceneX();
                previousMousePositionY_ = event.getSceneY();
            }
        });
        this.setOnMouseDragged(new EventHandler<MouseEvent>()
        {
          
            public void handle(MouseEvent event)
            {
                if (dragInProgress_)
                    return;
                dragInProgress_ = true;
                //Positive if mouse moved to the right , negative if it moved to the left.
                double amountMouseMovedX =  event.getSceneX() - previousMousePositionX_;
                
                //Positive if the mouse moved up, negative if it moved down.
                double amountMouseMovedY =   previousMousePositionY_ - event.getSceneY();

                double px = parent_.getElement().getX() + parent_.getWidth();
                double py = parent_.getElement().getY() + parent_.getHeight();
                
               if (location_.equals(TransformerLocation.TOPLEFT))
               {
                   double newWidth = parent_.getWidth() - amountMouseMovedX;
                   double newHeight = parent_.getHeight() + amountMouseMovedY;
                   readjustParent(parent_.getElement().getX() + amountMouseMovedX, parent_.getElement().getY() - amountMouseMovedY, newWidth, newHeight);
                }
               
               else if (location_.equals(TransformerLocation.TOPRIGHT))
               {
                   double newWidth = parent_.getWidth() + amountMouseMovedX;
                   double newHeight = parent_.getHeight() + amountMouseMovedY;
                   readjustParent(parent_.getElement().getX(), parent_.getElement().getY() - amountMouseMovedY, newWidth, newHeight);
               }
               else if (location_.equals(TransformerLocation.BOTTOMLEFT))
               {
                   double newWidth = parent_.getWidth() - amountMouseMovedX;
                   double newHeight = parent_.getHeight() + amountMouseMovedY;
                   
                   readjustParent(parent_.getElement().getX() + amountMouseMovedX, parent_.getElement().getY(), newWidth, newHeight);
               }
               else if (location_.equals(TransformerLocation.BOTTOMRIGHT))
               {
                  double newWidth = parent_.getWidth() + amountMouseMovedX;
                  double newHeight = parent_.getHeight() + amountMouseMovedX;
                  
                  readjustParent(parent_.getElement().getX(), parent_.getElement().getY(), newWidth, newHeight);
               }
               
               previousMousePositionX_ = event.getSceneX();
               previousMousePositionY_ = event.getSceneY();
               dragInProgress_ = false;
                  
               /**
               for (int i = 0; py - parent_.getHeight() - parent_.getElement().getY() != 0 || px - parent_.getWidth() - parent_.getElement().getX() != 0; i++)
               {
                   if (i % 3 == 0)
                   {
                       System.out.println("Unaligned " + i + "times.");
                   }
                   
                   double adjustWidthAmount = px - parent_.getWidth() - parent_.getElement().getX();
                   double adjustHeightAmount = py - parent_.getWidth() - parent_.getElement().getX();
                    readjustParent(parent_.getElement().getX(),parent_.getElement().getY(), parent_.getWidth() + adjustWidthAmount, parent_.getHeight() + adjustHeightAmount);
               }
              
              
               if (px - parent_.getElement().getX() != parent_.getWidth())
               {
                   throw new RuntimeException("The width got unaligned. by " + ( px - parent_.getWidth() - parent_.getElement().getX()));
               }
               else if (py - parent_.getElement().getY() != parent_.getHeight())
               {
                   throw new RuntimeException("The height is unaligned. by " + ( py - parent_.getHeight() - parent_.getElement().getY()));
               }**/
            }
        });
        
    }
    
  public void readjustParent(double newX, double newY, double newWidth, double newHeight)
  {
       if (parent_.canResizeWidth(newWidth) && parent_.canResizeHeight(newHeight))
       {
             parent_.getElement().setPosition(newX, newY);
             parent_.setSize(newWidth,newHeight);
        }
       
        else  if (parent_.canResizeWidth(newWidth))
        {
             parent_.getElement().setPosition(newX, parent_.getElement().getY());
             parent_.setSize(newWidth, parent_.getHeight());
        }
                   
        else if (parent_.canResizeHeight(newHeight))
        {
            parent_.getElement().setPosition(parent_.getElement().getX(), newY );
            parent_.setSize(parent_.getWidth(), newHeight);
        }
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
    
    double previousMousePositionX_;
    double previousMousePositionY_;
    boolean dragInProgress_ = false;
    private TransformerLocation location_;
    private WorkSpaceViewElement parent_;
    private TransformerType type_;
   private Cursor cursorType_;
   private boolean enabled_;
    
}
