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
import models.WorkSpaceGraphElement;

/**
 *
 * @author Ryan
 */
public class HotSpotView extends Circle{
    protected HotSpot hotSpotModel_;

    
    public HotSpotView(HotSpot hotSpotModel, WorkSpaceGraphElement parent)
    {
        hotSpotModel_ = hotSpotModel;
        setTypeStyle();
        hotSpotModel.setParent(parent);
        this.setCenterX(hotSpotModel_.getHotSpotx());
        this.setCenterY(hotSpotModel_.getHotSpoty());
        this.setRadius(6);
        this.setStrokeWidth(0);
    }
    
    
    private void setTypeStyle()
    {
         
        if (hotSpotModel_.getHotSpotType() == HotSpotType.INCOMING )
        {
            this.setFill(Color.RED);
        }
        else if (hotSpotModel_.getHotSpotType() == HotSpotType.OUTGOING)
        {
            this.setFill(Color.BLUE);            
        }
    }
    
    public void onHotSpotMoved()
    {
        this.setCenterX(hotSpotModel_.getHotSpotx());
        this.setCenterY(hotSpotModel_.getHotSpoty());
    }
    
    public HotSpot getHotSpot()
    {
        return hotSpotModel_;
    }
}
