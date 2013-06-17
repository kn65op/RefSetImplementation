/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package RefSetAlgorithm;

import RefPoints.RefPoint;
import RefSet.RefSet;
import com.sun.xml.internal.fastinfoset.tools.StAX2SAXReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Tomko
 */
public class Alternative {
  private final static Logger LOG = Logger.getLogger("Alternatives");
  
  private RefPoint point;
  double distanceToA1;
  double distanceToA3;
  
  static DistanceFunction distance;
  private static double lambda;
  double G;

  public Alternative(RefPoint point) {
    this.point = point;
  }
  
  
  
  void calculateDistances(RefSet A1, RefSet A3)
  {
    LOG.entering(Alternative.class.getName(), "calculateDistances");
    distanceToA1 = calculateDistanceToSet(A1);
    distanceToA3 = calculateDistanceToSet(A3);
    LOG.log(Level.INFO, "distanceToA1 = {0}\ndsitanceToA3 = {1}", new Object[]{Double.toString(distanceToA1), Double.toString(distanceToA3)});
  }
  
  private double calculateDistanceToSet(RefSet set)
  {
    LOG.entering(Alternative.class.getName(), "calculateDistancesToSet");
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
    LOG.entering(Alternative.class.getName(), "calculateG");
    G = distanceToA1 - lambda * distanceToA3;
    LOG.log(Level.INFO, "G = {0}", Double.toString(G));
  }

  public static boolean setLambda(double lambda) {
    LOG.entering(Alternative.class.getName(), "setLambda", lambda);
    if (lambda > 0)
    {
      Alternative.lambda = lambda;
      return true;
    }
    return false;
  }

  public void showConsole() {
    System.out.println("Alternative:");
    for (double d : point.getCriteria())
    {
      System.out.println(d);
    }
    System.out.println(distanceToA1);
    System.out.println(distanceToA3);
    System.out.println(G);
  }



}
