/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package RefSetAlgorithm;

import RefSet.RefSet;
import java.io.File;
import java.util.ArrayList;

/**
 *
 * @author Tomko
 */
public class Problem {
  public enum Metric
  {
    Euclidean,
    Czebyszew
  }
  
  private ArrayList<Alternative> alternatives = new ArrayList<Alternative>();
  private RefSet boundsOfOptimally = new RefSet();
  private RefSet targetPoints = new RefSet();
  private RefSet statusQuo = new RefSet();
  private RefSet antiIdeal = new RefSet();

  
  public Problem() {
    Alternative.setLambda(1);
    //Alternative.distance = new 
  }
  
  boolean setLambda(double lambda)
  {
    return Alternative.setLambda(lambda);
  }
  
  void setMetric(Metric metric)
  {
    switch (metric)
    {
      case Euclidean: 
        break;
      case Czebyszew:
        break;
    }
  }
  
  public boolean readProblem(File file)
  {
    return true;
  }
  
  /**
   * Find compromise solution.
   * @return  Solution if alternative set is not empty or null otherwise.
   */
  public Alternative solve()
  {
    double min_dist = Double.MAX_VALUE;
    Alternative alt = null;
    //solving: calculate distance to A1 and to A3 and find min to A1 and max to A3 min for g(-j) - g(+j)
    for (Alternative a : alternatives)
    {
        //calculate distances
        a.calculateDistances(targetPoints, antiIdeal);
        
        //find max (in G(x) = g(-j) - lambda g(+j) - lambda is argitrary (has to be more then 0)
        a.calculateG();
        
        //find min in G and get alternative
        if (a.G < min_dist)
        {
          min_dist = a.G;
          alt = a;
        }
    }
    
    return alt;
  }
}
