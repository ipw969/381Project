/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Enumerators.Enumerators.HotSpotType;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import models.HotSpot;
import models.Leg;
import models.Path;
import models.WorkSpaceGraph;
import views.WorkSpaceView;

/**
 *
 * @author anjagilje
 */
public class PathController {
    
    public PathController(WorkSpaceGraph model, WorkSpaceView view)
    {
           pathModel_ = model;
           pathView_ = view;

           ltemp = new Line();
           ltemp.setStartX(0);
           ltemp.setStartY(0);
           ltemp.setEndX(0);
           ltemp.setEndY(0);
           ptemp = new Path();
           
           //Mouse being clicked on view
           view.addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>()
           {
               public void handle(MouseEvent event)
               {
               if(event.isAltDown() && event.isPrimaryButtonDown() && pathModel_.isHotSpot(event.getX(), event.getY()) != null)
                {  
                    HotSpot h = pathModel_.isHotSpot(event.getX(), event.getY());
                   if(h.getHotSpotType() == HotSpotType.OUTGOING)
                   {

                        ltemp.setStartX(h.getHotSpotx());
                        ltemp.setStartY(h.getHotSpoty());
                        ltemp.setEndX(event.getX());
                        ltemp.setEndY(event.getY());
                   }
                   else
                   {
                       ltemp.setEndX(h.getHotSpotx());
                       ltemp.setEndY(h.getHotSpoty());

                       ptemp.addLeg(ltemp.getStartX(), ltemp.getStartY(), ltemp.getEndX(), ltemp.getEndY());

                       pathModel_.addConnector(ptemp);
                       draw();

                       ptemp.getLegs().clear();

                       ltemp.setStartX(0);
                       ltemp.setStartY(0);
                       ltemp.setEndX(0);
                       ltemp.setEndY(0);


                   }

               }
               else if(event.isAltDown() && pathModel_.isHotSpot(event.getX(), event.getY()) == null && ltemp.getStartX() != 0)
               {

                   ltemp.setEndX(event.getX());
                   ltemp.setEndY(event.getY());

                   Circle joint = new Circle(ltemp.getEndX(), ltemp.getEndY(), 10, Color.RED);
                   pathView_.getChildren().add(joint);
                   ptemp.addLeg(ltemp.getStartX(), ltemp.getStartY(), ltemp.getEndX(), ltemp.getEndY());


                   ltemp.setStartX(ltemp.getEndX());
                   ltemp.setStartY(ltemp.getEndY());

                   draw();  
                } 
               }

        });
           
        view.setOnMouseMoved((MouseEvent event) -> {
           if(ltemp.getStartX()!= 0 && ltemp.getStartY() != 0)
            {
                ltemp.setEndX(event.getX());
                ltemp.setEndY(event.getY());
                
            }
        

        });

       
    }
    
    
    public void draw()
    {
           if(ltemp.getStartX() != 0)
           {
                for(Leg l : ptemp.getLegs())
                {
                    Line nLine = new Line(l.getStartX(), l.getStartY(), l.getEndX(), l.getEndY());
                    pathView_.getChildren().add(nLine);
                }
           }
           
           for(Path path : pathModel_.getConnectors())
            {
               for(Leg l : path.getLegs())
                {
                    Line nLine = new Line(l.getStartX(), l.getStartY(), l.getEndX(), l.getEndY());
                    Circle joint = new Circle(l.getEndX(), l.getEndY(), 10, Color.RED);
                    pathView_.getChildren().addAll(nLine, joint);
                    
                }
            }
            
    }
    
    private WorkSpaceGraph pathModel_;
    private WorkSpaceView pathView_;
    private Line ltemp;
    private Path ptemp;
    
    
}
