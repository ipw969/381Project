package views;

import javafx.beans.value.ObservableValue;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import models.WorkSpaceGraphElement;

/**
 *
 * @author Iain Workman
 */
public class BinaryTreeElementView extends WorkSpaceViewElement {

    // Constructor
    public BinaryTreeElementView(WorkSpaceGraphElement element) {
        super(element);

        backgroundRectangle_ = new Rectangle(0, 0, getWidth() - 1, getHeight() - 1);
        backgroundRectangle_.setFill(Color.web("#009688"));
        
        valueLabel_ = new Label("0");
        valueLabel_.setFont(Font.font("Arial", FontWeight.NORMAL, 16));
        valueLabel_.setTextFill(Color.WHITE);
        

        getChildren().addAll(backgroundRectangle_, valueLabel_);

        // Incase we get resized we really should listen for this an update the
        // canvas appropriately.
        widthProperty().addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
            update();
        });

        heightProperty().addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
            update();
        });

        setupTransformers();
        update();

    }

    // Public Methods
    @Override
    public void update() {
        this.setSize(getElement().getWidth(), getElement().getHeight());
        backgroundRectangle_.setWidth(getWidth() - 1);
        backgroundRectangle_.setHeight(getHeight() - 1);

        valueLabel_.relocate((getWidth() - valueLabel_.getWidth()) / 2,
                (getHeight() - valueLabel_.getHeight()) / 2);
    }

    // Private Member Variables
    private final Rectangle backgroundRectangle_;
    private final Label valueLabel_;

}
