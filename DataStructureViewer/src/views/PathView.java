package views;

import javafx.animation.ScaleTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.util.Duration;
import models.Path;

/**
 * A class for the visual representation of a Path
 * @author anjagilje
 */
public class PathView extends Line {

    // Constructor
    /**
     * Creates an instance of a PathView for the visual representation of the
     * provided Path
     * @param path::Path ~ The Path that this PathView will visualize
     */
    public PathView(Path path) {
        super(path.getStart().getTotalX(), path.getStart().getTotalY(),
                path.getEnd().getTotalX(), path.getEnd().getTotalY());
        path_ = path;
        setStroke(Color.ORANGE);
        setStrokeWidth(2);
    }

    // Public Methods
    /**
     * The Path which this PathView will visualize.
     */
    public Path getPath() {
        return path_;
    }

    /**
     * Method which is called when the underlying Path is deleted. Plays the
     * deletion animation
     */
    public void onDelete(EventHandler<ActionEvent> event) {
        ScaleTransition deleteAnimation = new ScaleTransition(Duration.millis(1000), this);
        deleteAnimation.setToX(0);
        deleteAnimation.setToY(0);
        deleteAnimation.play();
        deleteAnimation.setOnFinished(event);
    }
    
    // Private Member Variables
    private final Path path_;

}
