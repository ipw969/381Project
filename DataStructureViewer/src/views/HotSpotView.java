/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import Enumerators.Enumerators.HotSpotType;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import models.HotSpot;
/**
 *
 * @author Ryan
 */
public class HotSpotView extends Circle{
    protected HotSpot hotSpotModel_;

    
    public HotSpotView(HotSpot hotSpotModel)
    {
        hotSpotModel_ = hotSpotModel;
        setTypeStyle();
        this.setCenterX(hotSpotModel_.getX());
        this.setCenterY(hotSpotModel_.getY());
        this.setRadius(6);
        this.setStrokeWidth(0);
    }
    
    
    private void setTypeStyle()
    {
         
        if (hotSpotModel_.getHotSpotType() == HotSpotType.INCOMING )
        {
            this.setFill(Color.rgb(255, 0, 0));
            
        }
        else if (hotSpotModel_.getHotSpotType() == HotSpotType.OUTGOING)
        {
            this.setFill(Color.rgb(0, 0, 255));            
        }
    }
    
    public void onHotSpotMoved()
    {
        this.setCenterX(hotSpotModel_.getX());
        this.setCenterY(hotSpotModel_.getY());
    }
    
    public HotSpot getHotSpot()
    {
        return hotSpotModel_;
    }
}
