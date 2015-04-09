package models;

/**
 * A class representing a Path which connects two HotSpots
 * @author anjagilje
 */
public class Path {

    // Constructor
    /**
     * Creates a new instance of a Path which connects the provided HotSpots
     * @param start::HotSpot ~ The HotSpot at the beginning of the Path
     * @param end::HotSpot ~ The HotSpot at the end of the Path
     */
    public Path(HotSpot start, HotSpot end) {
        start_ = start;
        end_ = end;
    }

    // Public Methods
    /**
     * The HotSpot at the start of the Path
     */
    public HotSpot getStart() {
        return start_;
    }

    /**
     * The HotSpot at the end of the Path
     * @return 
     */
    public HotSpot getEnd() {
        return end_;
    }

    // Private Member Variables
    private final HotSpot start_;
    private final HotSpot end_;
}
