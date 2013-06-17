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
  
  public boolean checkInternalConsistency()
  {
    int size = refPoints.size();
    for (int i=0; i < size; ++i)
    {
      for (int j = i + 1; j < size; ++j)
      {
        if (refPoints.get(i).compare(refPoints.get(j)) != RefPoint.ComparasionResult.UNCOMPARABLE)
        {
          return false;
        }
      }
    }
    return true;
  }
          
}
