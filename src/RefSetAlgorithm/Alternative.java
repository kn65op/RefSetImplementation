/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package RefSetAlgorithm;

import RefPoints.RefPoint;
import RefSet.RefSet;

/**
 *
 * @author Tomko
 */
public class Alternative {
  RefPoint point;
  double distanceToA1;
  double distanceToA3;
  
  static DistanceFunction distance;
  private static double lambda;
  double G;
  
  void calculateDistances(RefSet A1, RefSet A3)
  {
    distanceToA1 = calculateDistanceToSet(A1);
    distanceToA3 = calculateDistanceToSet(A3);
  }
  
  private double calculateDistanceToSet(RefSet set)
  {
    
    double ret = Double.MAX_VALUE;
    double tmp;
    for (RefPoint rp : set.getPoints())
    {
      tmp = distance.calculateDistance(rp, point);
      if (tmp < ret)
      {
        ret = tmp;
      }
    }
    return ret;
  }

  void calculateG() {
    G = distanceToA1 - lambda * distanceToA3;
  }

  public static boolean setLambda(double lambda) {
    if (lambda > 0)
    {
      Alternative.lambda = lambda;
      return true;
    }
    return false;
  }

}
