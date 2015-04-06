/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package events;

import javafx.event.ActionEvent;
import models.Path;

/**
 *
 * @author anjagilje
 */
public class PathEvent  extends ActionEvent{
    
    public PathEvent(Path path)
    {
        path_ = path;
    }
    
    public Path getPath()
    {
        return path_;
    }
    
    private Path path_;
    
}
