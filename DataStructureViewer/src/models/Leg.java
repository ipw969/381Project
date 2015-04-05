/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author anjagilje
 */

/**A path will be made up of one or more of these legs**/
public class Leg {
    public Leg (Double x1, Double y1, Double x2, Double y2)
    {
        startX_ = x1;
        startY_ = y1;
        endX_ = x2;
        endY_ = y2;
    }
    
    public Double getStartX()
    {
        return startX_;
    }
    
    public Double getStartY()
    {
        return startY_;
    }
    
    public Double getEndX()
    {
        return endX_;
    }
    
    public Double getEndY()
    {
        return endY_;
    }
    
    Double startX_, 
           startY_, 
           endX_, 
           endY_;
}
