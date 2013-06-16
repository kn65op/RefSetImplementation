/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package RefSet;

import RefPoints.RefPoint;
import java.util.ArrayList;

/**
 *
 * @author Tomko
 */
public class RefSet {
  
  private ArrayList<RefPoint> refPoints = new ArrayList<RefPoint>();
  
  public void addPoint(RefPoint point)
  {
    refPoints.add(point);
  }
  
  public ArrayList<RefPoint> getPoints()
  {
    return refPoints;
    //ArrayList<RefPoint> ret;-
  }
          
}
