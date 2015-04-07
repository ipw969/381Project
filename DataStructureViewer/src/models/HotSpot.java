/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import Enumerators.Enumerators.HotSpotType;

/**
 *
 * @author anjagilje
 */
public class HotSpot {
    
    //Constructors
    public HotSpot(double nx, double ny, HotSpotType nt, WorkSpaceGraphElement parent)
    {
        cx_ = nx;
        cy_ = ny;
        type_ = nt;
        parent_ = parent;
           
    }
    
    //Determine whether a click happened inside this HotSpot
    public boolean contains(double x, double y)
     {
         double dist = Math.sqrt(Math.pow(x-cx_, 2) + Math.pow(y-cy_, 2));
         return (dist < 7);
     }
    
    public double getX()
    {
        return cx_;
    }
    
    public double getY()
    {
        return cy_;
    }
    
    public double getTotalX() {
        return cx_ + parent_.getX();
    }
    
    public double getTotalY() {
        return cy_ + parent_.getY();
    }
    
    public HotSpotType getHotSpotType()
    {
        return type_;
    }
    
    public WorkSpaceGraphElement getParent()
    {
        return parent_;
    }
    
    private double cx_, cy_;
    private final HotSpotType type_;
    private final WorkSpaceGraphElement parent_;
    
}
