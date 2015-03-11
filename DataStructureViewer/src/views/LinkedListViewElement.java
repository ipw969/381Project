package views;


/**
 * A class for the visual representation of a LinkedListElement
 * 
 * NOTE: Currently this does almost nothing to actually visualize the data, I 
 * just needed it on there to get the drag and drop working ~ Iain
 * @author Iain Workman
 */
public class LinkedListViewElement extends WorkSpaceViewElement {
    
    // Constructor
    /**
     * Creates an instance of a LinkedListViewElement
     */
    public LinkedListViewElement () {
        this.setMinSize(120, 80);
        setStyle("-fx-background-color: red;");        
    }
    
    // Private Member Variables

}
