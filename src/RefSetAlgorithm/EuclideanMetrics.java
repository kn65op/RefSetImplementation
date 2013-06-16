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
public class EuclideanMetrics implements DistanceFunction{

  @Override
  public double calculateDistance(RefPoint a, RefPoint b) {
    int size = a.getValuesSize();
    double dist = 0;
    for (int i=0; i < size; ++i)
    {
      dist += Math.pow(a.getCriterionValue(i) - b.getCriterionValue(i), 2);
    }
    return Math.sqrt(dist);
  }
  
}
