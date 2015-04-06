/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import javafx.scene.shape.Circle;

/**
 *
 * @author Ryan
 */
public class HotSpotView extends Circle{
    private Enumerators.Enumerators.HotSpotType type_;
    
    public HotSpotView(Enumerators.Enumerators.HotSpotType type)
    {
       type_ = type;
       setTypeStyle();
    }
    
    public HotSpotView(Enumerators.Enumerators.HotSpotType type, double x, double y)
    {
        type_ = type;
        setTypeStyle();
        this.setCenterX(x);
        this.setCenterY(y);
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
}
