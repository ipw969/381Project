/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import javafx.animation.ScaleTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.util.Duration;
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
        setStroke(Color.ORANGE);
        setStrokeWidth(2);
    }
    
    public Path getPath()
    {
        return path_;
    }
    
    public void onDelete(EventHandler<ActionEvent> event)
    {
        ScaleTransition deleteAnimation = new ScaleTransition(Duration.millis(1000), this);
        deleteAnimation.setToX(0);
        deleteAnimation.setToY(0);
        deleteAnimation.play();
        deleteAnimation.setOnFinished(event);
    }
    private final Path path_;
    
}
