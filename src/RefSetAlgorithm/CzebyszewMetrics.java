/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package RefSetAlgorithm;

import RefPoints.RefPoint;

/**
 *
 * @author Tomko
 */
public class CzebyszewMetrics implements DistanceFunction{

  @Override
  public double calculateDistance(RefPoint a, RefPoint b) {
    int size = a.getValuesSize();
    double dist = 0;
    double tmp;
    for (int i=0; i < size; ++i)
    {
      tmp = Math.abs(a.getCriterionValue(i) - b.getCriterionValue(i));
      if (tmp > dist)
      {
        dist = tmp;
      }
    }
    return dist;
  }
  
}
