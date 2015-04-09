package events;

import javafx.event.ActionEvent;
import models.Path;

/**
 * A Class which encapsulates all the data related to a PathEvent
 *
 * @author anjagilje
 */
public class PathEvent extends ActionEvent {

    // Constructor
    /**
     * Creates an instance of a PathEvent with the provided Path
     * @param path::Path ~ The Path related to the event
     */
    public PathEvent(Path path) {
        path_ = path;
    }

    // Public Methods
    /**
     * The Path related to the event
     */
    public Path getPath() {
        return path_;
    }

    // Private Methods
    private final Path path_;

}
