package factories;

import models.LinkedListElement;
import models.WorkSpaceGraphElement;
import views.LinkedListViewElement;
import views.WorkSpaceViewElement;

/**
 *
 * @author Iain Workman
 */
public class WorkSpaceViewElementFactory {
    public static WorkSpaceViewElement viewElement(WorkSpaceGraphElement element) {
        if(element instanceof LinkedListElement) {
            return new LinkedListViewElement();                    
        }
        return null;
    }
}
