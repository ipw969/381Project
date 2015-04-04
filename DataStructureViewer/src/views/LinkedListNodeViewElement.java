package views;

import javafx.beans.value.ObservableValue;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
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
        backgroundRectangle_.setFill(Color.WHITE);
        backgroundRectangle_.setStroke(Color.BLACK);

        dividingLine_ = new Line();

        LinkedListNodeElement nodeElement = (LinkedListNodeElement) getElement();

        valueLabel_ = new Label("0");

        // Add components
        this.getChildren().addAll(backgroundRectangle_,
                dividingLine_,
                valueLabel_);

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
        double thisHeight = this.getHeight();
        double labelHeight = valueLabel_.getHeight();

        valueLabel_.relocate((getWidth() - valueLabel_.getWidth()) / 4,
                (getHeight() - valueLabel_.getHeight()) / 2);
    }

    // Private Member Variables
    private final Rectangle backgroundRectangle_;
    private final Line dividingLine_;
    private final Label valueLabel_;
}
