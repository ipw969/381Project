/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import javafx.scene.shape.Line;
import models.Path;

/**
 *
 * @author anjagilje
 */
public class PathView extends Line{
    
    public PathView(Path path)
    {
        super(path.getStart().getTotalX(), path.getStart().getTotalY(),
              path.getEnd().getTotalX(), path.getEnd().getTotalY());
        path_ = path;
    }
    
    public Path getPath()
    {
        return path_;
    }
    
    private final Path path_;
    
}
