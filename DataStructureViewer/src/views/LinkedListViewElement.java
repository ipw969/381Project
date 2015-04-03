package views;

import javafx.beans.value.ObservableValue;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import models.LinkedListElement;
import models.WorkSpaceGraphElement;

/**
 * A class for the visual representation of a LinkedListElement
 *
 * @author Iain Workman
 */
public class LinkedListViewElement extends WorkSpaceViewElement {

    // Constructor
    /**
     * Creates an instance of a LinkedListViewElement
     *
     * @param element::LinkedListElement ~ The LinkedListElement this view is to
     * represent.
     */
    public LinkedListViewElement(LinkedListElement element) {
        super(element);

        // Initialize UI
        backgroundRectangle_ = new Rectangle(0, 0, getWidth() - 1, getHeight() - 1);
        backgroundRectangle_.setFill(Color.WHITE);
        backgroundRectangle_.setStroke(Color.BLACK);

        headLabel_ = new Label("Head");
        headLabel_.relocate(5, 5);

        tailLabel_ = new Label("Tail");
        tailLabel_.relocate(5, 25);

        countLabel_ = new Label("Count");
        countLabel_.relocate(5, 45);
        getChildren().addAll(backgroundRectangle_,
                headLabel_,
                tailLabel_,
                countLabel_);

        // Incase we get resized we really should listen for this an update the
        // canvas appropriately.
        widthProperty().addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
            update();
        });

        heightProperty().addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
            update();
        });

        this.setSnapToPixel(false);
        this.setupTransformers();
        this.update();
    }

    // Public Methods
    @Override
    public void update() {
        WorkSpaceGraphElement element = getElement();
        
        setSize(element.getWidth(), element.getHeight());
        backgroundRectangle_.setWidth(getWidth() - 1);
        backgroundRectangle_.setHeight(getHeight() - 1);
    }

    //  Private Member Variables
    private final Rectangle backgroundRectangle_;
    private final Label headLabel_;
    private final Label tailLabel_;
    private final Label countLabel_;

}
