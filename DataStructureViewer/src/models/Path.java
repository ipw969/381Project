/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.ArrayList;

/**
 *
 * @author anjagilje
 */
public class Path {
     private ArrayList<Leg> legs;
    
    public Path()
    {
        legs = new ArrayList();
    }
    
    public void addLeg(Double x1, Double y1, Double x2, Double y2)
    {
        Leg temp = new Leg(x1, y1, x2, y2);
        legs.add(temp);
    }
    
    public ArrayList<Leg> getLegs()
    {
        return legs;
    }
    
}
