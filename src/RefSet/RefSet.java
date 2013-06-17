/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package RefSet;

import RefPoints.RefPoint;
import java.util.ArrayList;
import java.util.LinkedList;
import javax.swing.ListModel;
import javax.swing.event.ListDataListener;

/**
 *
 * @author Tomko
 */
public class RefSet implements ListModel<RefPoint> {
  
  private ArrayList<RefPoint> refPoints = new ArrayList<RefPoint>();
  private LinkedList<ListDataListener> listners = new LinkedList<ListDataListener>();
  
  public void addPoint(RefPoint point)
  {
    refPoints.add(point);
    for (ListDataListener l : listners)
    {
      l.contentsChanged(null);
    }
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
          
  public boolean checkMutualConsistency(RefSet other)
  {
    //check if every point in this set dominate at least on point in other set
    for (RefPoint rp : refPoints)
    {
      if (!findWeakDominatedPoint(rp, other.refPoints))
      {
        return false;
      }
    }
    
    //check if every point in other set is dominated by at least one point in this set
    for (RefPoint rp : other.refPoints)
    {
      if (!findWeakDominatingPoint(rp, refPoints))
      {
        return false;
      }
    }
    return true;
    
    
  }

  /**
   * Check if rp is weakly dominated by at least one point in refPoints.
   * @param rp Point to be weakly dominated.
   * @param refPoints Points, which dominate.
   * @return true if found.
   */
  private boolean findWeakDominatingPoint(RefPoint rp, ArrayList<RefPoint> refPoints) {
    for (RefPoint to_be_dominated : refPoints)
    {
      RefPoint.ComparasionResult res = to_be_dominated.compare(rp);
      if (res == RefPoint.ComparasionResult.LESS || res == RefPoint.ComparasionResult.LESS_EQUAL || res == RefPoint.ComparasionResult.EUQAL)
      {
        return true;
      }
    }
    return false;
  }

  /**
   * Check if at least one point in refPoints is weakly dominated by rp.
   * @param rp Point which domianate.
   * @param refPoints Point to weakly dominating.
   * @return true if is at least one weakly dominated point.
   */
  private boolean findWeakDominatedPoint(RefPoint rp, ArrayList<RefPoint> refPoints) {
    for (RefPoint to_dominate : refPoints)
    {
      RefPoint.ComparasionResult res = to_dominate.compare(rp);
      if (res == RefPoint.ComparasionResult.GREATER || res == RefPoint.ComparasionResult.GREATER_EQUAL || res == RefPoint.ComparasionResult.EUQAL)
      {
        return true;
      }
    }
    return false;
  }

  @Override
  public int getSize() {
    return refPoints.size();
  }

  @Override
  public RefPoint getElementAt(int index) {
    return refPoints.get(index);
  }

  @Override
  public void addListDataListener(ListDataListener l) {
    
    listners.add(l);
  }

  @Override
  public void removeListDataListener(ListDataListener l) {
    listners.remove(l);
  }
}
