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
    public HotSpot(double nx, double ny, HotSpotType nt)
    {
        cx_ = nx;
        cy_ = ny;
        type_ = nt;
        
           
    }
    
    public HotSpot()
    {
        cx_ = 0;
        cy_ = 0;
        type_ = HotSpotType.BOTH;
        
           
    }
    
    //Determine whether a click happened inside this HotSpot
    public boolean contains(double x, double y)
     {
         double dist = Math.sqrt(Math.pow(x-cx_, 2) + Math.pow(y-cy_, 2));
         return (dist < 7);
     }
    
    public double getHotSpotx()
    {
        return cx_;
    }
    
    public double getHotSpoty()
    {
        return cy_;
    }
    
    public HotSpotType getHotSpotType()
    {
        return type_;
    }
    
    private double cx_, cy_;
    private HotSpotType type_;
    
}
