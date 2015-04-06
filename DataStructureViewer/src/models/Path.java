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
public class Path {
     private final HotSpot start_;
     private final HotSpot end_;
    
    public Path(HotSpot start, HotSpot end)
    {
        start_ = start;
        end_ = end;
    }
    
    public HotSpot getStart() {return start_;}
    public HotSpot getEnd() {return end_;}
}
