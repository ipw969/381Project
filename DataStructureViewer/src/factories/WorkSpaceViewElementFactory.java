package factories;

import models.LinkedListElement;
import models.WorkSpaceGraphElement;
import views.LinkedListViewElement;
import views.WorkSpaceViewElement;

/**
 * Abstract factory class which creates WorkSpaceViewElements
 * @author Iain Workman
 */
public class WorkSpaceViewElementFactory {
    /**
     * Creates an instance of the WorkSpaceViewElement which corresponds to the
     * passed WorkSpaceGraphElement
     * @param element::WorkSpaceGraphElement ~ The element which we are to create
     * a WorkSpaceViewElement for
     * @return A WorkSpaceViewElement derived class for the visual representation
     * of the passed WorkSpaceGraphElement
     */
    public static WorkSpaceViewElement viewElement(WorkSpaceGraphElement element) {
        if(element instanceof LinkedListElement) {
            return new LinkedListViewElement();                    
        }
        return null;
    }
}
