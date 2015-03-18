
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

/*
 * This class is used to attach TransformSpots to GraphViewElements. These nodes will be responsible for rescaling the nodes.
 * This class needs to have a location and a type associated with it.
 * This class ensures that it stays attached to its parent.
 * The class currently only supports the diagonal transformer spots.
 * @ author Ryan La Forge
 */
public class TransformSpot extends Rectangle{
    
    /**
     * 
     * @param type The type of the Transformer. This is to indicate in what direction it can move in.
     * @param location The location of the Transformer relative to its parent.
     * @param parent The parent of this TransformerSpot.
     */
    public TransformSpot(TransformerType type, TransformerLocation location, WorkSpaceViewElement parent)
    {
        this.type_ = type;
        this.location_ = location;
        this.parent_ = parent;
        
        this.setWidth(10);
        this.setHeight(10);
        this.setFill(Color.BLUE);

        //This change listener is attached to every value of the property of the parent. Anytime the parent 
        //moves, or resizes, the transfomrer spot will follow it.
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
        
        //Used to determine what cursor the transformerspot will change the mousecursor to. This is dependent on location.
        figureOutCursor();
        
        //The node will start invisible, and will only become visible when selected.
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
        
       
        //This is where hte rescaling happens.
        this.setOnMouseDragged(new EventHandler<MouseEvent>()
        {
          
            public void handle(MouseEvent event)
            {
                if (location_.equals(TransformerLocation.TOPLEFT))
                {
                    /*
                    //negative if moved right, positive if moved left.
                    double amountMouseMovedX = event.getSceneX() - previousMousePositionX_;
                    
                    //negative if moved up, positive if moved down.
                    double amountMouseMovedY = event.getSceneY() - previousMousePositionY_;
                    
                    double newX = parent_.getElement().getX() + amountMouseMovedX;
                    double newY = parent_.getElement().getY() + amountMouseMovedY;
                    
                    double tempX = ( -(parent_.getWidth()/2));
                    double tempY = ( - (parent_.getHeight()/2));
                    
                    double parentScaleWidth = parent_.getWidth() * parent_.getScaleX();
                    double parentScaleHeight = parent_.getHeight() * parent_.getScaleY();
                    
                    double newWidth = parentScaleWidth - amountMouseMovedX;
                    double newHeight = parentScaleHeight - amountMouseMovedY;
                    
                    parent_.relocate(tempX, tempY);
                    parent_.setSize(parent_.getWidth(), parent_.getHeight());
                    parent_.setScaleX((newWidth/parentScaleWidth));
                    parent_.setScaleY((newHeight/parentScaleHeight));
                    parent_.setSize(parent_.getWidth() * parent_.getScaleX(), parent_.getHeight() * parent_.getScaleY());
                    
                    parent_.getElement().setPosition(newX,newY);
                    previousMousePositionX_ = event.getSceneX();
                    previousMousePositionY_ = event.getSceneY();
                    */
                }
                
                
                dragInProgress_ = true;
                //Positive if mouse moved to the right , negative if it moved to the left.
                double amountMouseMovedX =  event.getSceneX() - previousMousePositionX_;
                
                //Positive if the mouse moved up, negative if it moved down.
                double amountMouseMovedY =   previousMousePositionY_ - event.getSceneY();

                
                //px -> The right most x coordinate of the parent.
                double px = parent_.getElement().getX() + parent_.getWidth();
                
                
                //py -> The left most y coordinate of the parent.
                double py = parent_.getElement().getY() + parent_.getHeight();
                
                
               if (location_.equals(TransformerLocation.TOPLEFT))
               {
                  // double mouseMovementX = amountMouseMovedX / parent_.getElement().getX()* 100;
                  
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
               
                  /*
               //debugging information.
               for (int i = 0; py - parent_.getHeight() - parent_.getElement().getY() != 0 || px - parent_.getWidth() - parent_.getElement().getX() != 0; i++)
               {
                   if (i % 1000 == 0)
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
               }*/
            }
        });
        
    }
    
    /**Readjusts the parent to have the given properties. 
     * This function will only resize and move the parent to the minimum size of the parent.
     * @param newX the new x coordinate of the parent.
     * @param newY The new y coordinate of the parent.
     * @param newWidth The new width of the parent.
     * @param newHeight The new height of the parent.
     * 
     * 
     */
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
                   
      
  /**SEt whether this node is visible or not.
   * 
   * @param isEnabled Whether the node is visible and usable or not.
   */
  public void setEnabled(boolean isEnabled)
  {
      if (isEnabled)
      {
          this.setOpacity(0.5);
          this.setPickOnBounds(true);
      }
      else
      {
          this.setPickOnBounds(false);
          this.setOpacity(0);
      }
      enabled_ = isEnabled;
  }
  
  /**
   * 
   * @return Whether this node is currently visible and usable.
   */
  public boolean isEnabled()
  {
      return enabled_;
  }
  
  /**Determine the mouse cursor to be used when the mouse enters this transformer spot.
   * 
   */
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
    
    /**Update the position of the transformer spot according to its parent.
     * 
     */
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
