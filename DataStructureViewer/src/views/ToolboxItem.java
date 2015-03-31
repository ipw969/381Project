package views;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

/**
 * A class representing one of the listed items in a Toolbox
 *
 * @author Iain Workman
 */
public class ToolboxItem extends HBox {

    // Constructor
    /**
     * Creates a ToolboxItem with the provided itemText
     *
     * @param itemText::String ~ The text which represents the item in the
     * toolbox
     */
    public ToolboxItem(String itemText) {
        setupUi(itemText);
        wireEvents();
    }

    // Public Methods
    /**
     * Sets the item's state to selected. Alters its appearance.
     *
     * @param isSelected::boolean ~ Whether the item is selected or not
     */
    public void setIsSelected(boolean isSelected) {
        isSelected_ = isSelected;
        if (isSelected_) {
            itemText_.setTextFill(SELECTED_TEXT_COLOR);
            setStyle("-fx-background-color: " + SELECTED_BACKGROUND_COLOR);
        } else {
            itemText_.setTextFill(DEFAULT_TEXT_COLOR);
            setStyle("-fx-background-color: " + DEFAULT_BACKGROUND_COLOR);
        }
    }

    // Private Methods
    /**
     * Sets up the widgets for the ToolboxItem
     *
     * @param itemText::String ~ The text to display for the item
     */
    private void setupUi(String itemText) {
        itemText_ = new Label(itemText);
        itemText_.setTextFill(DEFAULT_TEXT_COLOR);

        setSpacing(5);
        setPadding(new Insets(5, 5, 5, 5));

        getChildren().addAll(itemText_);
    }

    /**
     * Wires the general events for the ToolboxItem
     */
    private void wireEvents() {
        // handles mouse hover
        this.setOnMouseEntered((MouseEvent) -> {
            if (!isSelected_) {
                itemText_.setTextFill(HOVER_TEXT_COLOR);
            }
        });

        // handles ending the mouse hover
        this.setOnMouseExited((MouseEvent) -> {
            if (!isSelected_) {
                itemText_.setTextFill(DEFAULT_TEXT_COLOR);
            }
        });

    }

    // Private Member Variables
    private Label itemText_;
    private boolean isSelected_;

    // Static Class Constants
    private final static Color DEFAULT_TEXT_COLOR = Color.BLACK;
    private final static Color HOVER_TEXT_COLOR = Color.web("#009688");
    private final static Color SELECTED_TEXT_COLOR = Color.web("#ffffff");
    private final static String DEFAULT_BACKGROUND_COLOR = "#ffffff";
    private final static String SELECTED_BACKGROUND_COLOR = "#009688";

}
