package views;

import javafx.beans.value.ObservableValue;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import models.LinkedListNodeElement;
import models.WorkSpaceGraphElement;

/**
 * A class for the visual representation of a LinkedListNode
 *
 * @author Iain Workman
 */
public class LinkedListNodeViewElement extends WorkSpaceViewElement {

    // Constructor
    public LinkedListNodeViewElement(WorkSpaceGraphElement element) {
        super(element);

        // Initialize UI
        backgroundRectangle_ = new Rectangle(0, 0, getWidth() - 1, getHeight() - 1);
        backgroundRectangle_.setFill(Color.web("#311b92"));        

        dividingLine_ = new Line();
        dividingLine_.setStroke(Color.WHITE);
        dividingLine_.setStrokeWidth(2);
        LinkedListNodeElement nodeElement = (LinkedListNodeElement) getElement();

        valueLabel_ = new Label("0");
        valueLabel_.setTextFill(Color.WHITE);
        valueLabel_.setFont(Font.font("Arial", FontWeight.NORMAL, 16));
        
        // Add components
        getComponentsPane().getChildren().addAll(backgroundRectangle_,
                dividingLine_,
                valueLabel_);

        valueLabel_.setWrapText(true);
        setLabelEditable(valueLabel_, "");
        // Incase we get resized we really should listen for this an update the
        // canvas appropriately.
        widthProperty().addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
            update();
        });

        heightProperty().addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
            update();
        });

        setSnapToPixel(false);
        setupTransformers();
        update();
    }

    // Public Methods
    @Override
    public void update() {
        // Scale This
        WorkSpaceGraphElement element = getElement();
        this.setSize(element.getWidth(), element.getHeight());

        // Scale Rectangle
        backgroundRectangle_.setWidth(getWidth() - 1);
        backgroundRectangle_.setHeight(getHeight() - 1);

        // Position Line
        dividingLine_.setStartX(getWidth() / 2);
        dividingLine_.setEndX(getWidth() / 2);
        dividingLine_.setStartY(0);
        dividingLine_.setEndY(getHeight());

        // Position Label
        //valueLabel_.relocate((getWidth() - valueLabel_.getWidth()) / 4,
               // (getHeight() - valueLabel_.getHeight()) / 2);
        
        
        
        valueLabel_.setMaxWidth(this.getWidth()/2);
        valueLabel_.setMaxHeight(this.getHeight());
        
        Text measureText = new Text(valueLabel_.getText());
        double layoutX = (this.getWidth()/2 - measureText.getLayoutBounds().getWidth())/2;
        double layoutY = (this.getHeight() - measureText.getLayoutBounds().getHeight())/2;
        
        double amountOfRoomHeight = this.getHeight() - valueLabel_.getHeight();
        
        layoutY = amountOfRoomHeight / 2;
       if (layoutX < 0)
       {
           layoutX = 0;
       }
       else if (layoutY < 0)
       {
           layoutY = 0;
       }
       
       if (layoutY + valueLabel_.getHeight() > this.getHeight())
       {
           layoutY = this.getHeight() - valueLabel_.getMaxHeight();
       }

        valueLabel_.relocate(layoutX,layoutY);
        
                
    }

    // Private Member Variables
    private final Rectangle backgroundRectangle_;
    private final Line dividingLine_;
    private final Label valueLabel_;
}
