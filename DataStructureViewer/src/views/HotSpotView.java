/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import javafx.scene.shape.Circle;
import models.HotSpot;

/**
 *
 * @author Ryan
 */
public class HotSpotView extends Circle{
    private Enumerators.Enumerators.HotSpotType type_;
    protected HotSpot hotSpotModel_;

    
    public HotSpotView(Enumerators.Enumerators.HotSpotType type, HotSpot hotSpotModel)
    {
        type_ = type;
        hotSpotModel_ = hotSpotModel;
        setTypeStyle();
        hotSpotModel.setParent(this);
        this.setCenterX(hotSpotModel_.getHotSpotx());
        this.setCenterY(hotSpotModel_.getHotSpoty());
        
    }
    
    
    private void setTypeStyle()
    {
         
        if (type_.equals(Enumerators.Enumerators.HotSpotType.INCOMING))
        {
            this.setStyle("-fx-background-color: RED");
        }
        else if (type_.equals(Enumerators.Enumerators.HotSpotType.OUTGOING))
        {
            this.setStyle("-fx-background-color: BLUE");
        }
    }
    
    public void onHotSpotMoved()
    {
        this.setCenterX(hotSpotModel_.getHotSpotx());
        this.setCenterY(hotSpotModel_.getHotSpoty());
    }
}
