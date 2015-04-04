package views;

import java.util.ArrayList;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Label;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * A widget which represents a Toolbox from which elements for the WorkSpace can
 * be dragged.
 *
 * @author Iain Workman
 */
public class Toolbox extends VBox {

    // Constructor
    /**
     * Creates an instance of a Toolbox which lists WorkSpace elements which can
     * be added to a WorkSpace
     */
    public Toolbox() {
        toolboxItems_ = new ArrayList<>();
        setMinWidth(80);

        setupUi();
        addItems();
        wireEvents();
    }

    // Private Methods
    /**
     * Performs initial setup of the toolbox
     */
    private void setupUi() {
        setStyle("-fx-background-color: white");
        StackPane header = new StackPane();
        headerRectangle_ = new Rectangle(0, 0, 0, 22);
        headerRectangle_.setFill(HEADER_COLOR);
        headerTitle_ = new Label("Toolbox");
        headerTitle_.setTextFill(Color.WHITE);

        header.getChildren().addAll(headerRectangle_, headerTitle_);

        getChildren().add(header);
    }

    /**
     * Adds the items to the toolbox
     */
    private void addItems() {
        addItem("Linked List");
        addItem("Linked List Node");
        addItem("Binary Tree");
    }

    /**
     * Adds an item to the toolbox with the provided text and wires its events
     *
     * @param text::String ~ The text to display for the toolbox item
     */
    private void addItem(String text) {
        ToolboxItem newItem = new ToolboxItem(text);

        // handles selecting an item in the toolbox
        newItem.setOnMousePressed((MouseEvent event) -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                if (selectedItem_ != null) {
                    selectedItem_.setIsSelected(false);
                }
                selectedItem_ = newItem;
                newItem.setIsSelected(true);
            }
        });

        // handles a drag operating beginning
        newItem.setOnDragDetected((MouseEvent event) -> {
            Dragboard dragBoard = startDragAndDrop(TransferMode.COPY);

            ClipboardContent content = new ClipboardContent();
            content.putString(text);
            dragBoard.setContent(content);

            event.consume();
        });

        getChildren().add(newItem);
    }

    /**
     * Wires general events for the toolbox
     */
    private void wireEvents() {

        // Listening to resizes of the toolbox. Allows us to size our children
        // accoringly
        widthProperty().addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
            headerRectangle_.setWidth(getWidth());
        });

        heightProperty().addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
            headerRectangle_.setWidth(getWidth());
        });

    }

    // Private Member Variables
    private Rectangle headerRectangle_;
    private Label headerTitle_;
    private final ArrayList<ToolboxItem> toolboxItems_;
    private ToolboxItem selectedItem_;
    // Static Class Constants
    private final static Color HEADER_COLOR = Color.web("#00695C");
}
