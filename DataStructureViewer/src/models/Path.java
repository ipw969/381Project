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
     private WorkSpaceGraphElement start_;
     private WorkSpaceGraphElement end_;
    
    public Path(WorkSpaceGraphElement start)
    {
        start_ = start;
    }
    
    public void setEnd(WorkSpaceGraphElement end) {
        end_ = end;
    }
    
    public WorkSpaceGraphElement getStart() {return start_;}
    public WorkSpaceGraphElement getEnd() {return end_;}
}
