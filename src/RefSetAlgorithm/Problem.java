/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package RefSetAlgorithm;

import RefPoints.NegativeValueException;
import RefPoints.NullValueException;
import RefPoints.RefPoint;
import RefSet.RefSet;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Tomko
 */
public class Problem {
  private int size;

  public Iterable<Alternative> getAlternatives() {
    return alternatives;
  }

  public int criteriaSize() {
    return size;
  }
  
  public enum Metric
  {
    Euclidean,
    Czebyszew
  }
  
  private static final Logger LOG = Logger.getLogger("Algorithm log");
  
  private ArrayList<Alternative> alternatives = new ArrayList<Alternative>();
  private RefSet boundsOfOptimally = new RefSet();
  private RefSet targetPoints = new RefSet();
  private RefSet statusQuo = new RefSet();
  private RefSet antiIdeal = new RefSet();

  
  public Problem() {
    LOG.entering(Problem.class.getName(), "Problem");
    LOG.setLevel(Level.ALL);
    Alternative.setLambda(1);
    Alternative.distance = new EuclideanMetrics();
  }
  
  boolean setLambda(double lambda)
  {
    LOG.entering(Problem.class.getName(), "setLambda");    
    return Alternative.setLambda(lambda);
  }
  
  void setMetric(Metric metric)
  {
    LOG.entering(Problem.class.getName(), "SetMetric");
    switch (metric)
    {
      case Euclidean: 
        break;
      case Czebyszew:
        break;
    }
  }
  
  private RefPoint readRefPoint(int number_of_criteria, Scanner scanner) throws NegativeValueException, NullValueException, InputMismatchException
  {
    LOG.entering(Problem.class.getName(), "readRefPoint");
    RefPoint rp = new RefPoint();
    rp.setSize(number_of_criteria);
    for (int j = 0; j < number_of_criteria; ++j) {
      rp.addCriterionValue(j, scanner.nextDouble());
    }
    return rp;
  }
  
  public boolean readProblem(File file) throws FileNotFoundException, NegativeValueException, NullValueException, InputMismatchException, Exception
  {
    LOG.entering(Problem.class.getName(), "readProblem");
    boolean state = true;
    Scanner scanner = new Scanner(file);
    try {
      int number_of_criteria = scanner.nextInt();
      size = number_of_criteria;
      int number_of_alternatives = scanner.nextInt();
      
      //alternatives
      for (int i=0; i < number_of_alternatives; ++i)
      {
        RefPoint rp;
        rp = readRefPoint(number_of_criteria, scanner);
        alternatives.add(new Alternative(rp));
      }
      LOG.info("Alternatives read");
      
      //refsets bounds of optimally
      scanner.next("boo");
      int number_of_boo = scanner.nextInt();
      for (int i=0; i<number_of_boo; ++i)
      {
        RefPoint rp = readRefPoint(number_of_criteria, scanner);
        boundsOfOptimally.addPoint(rp);
      }
      LOG.info("Bounds of optimally read");
      
      //refsets - target points
      scanner.next("tp");
      int number_of_tp = scanner.nextInt();
      for (int i=0; i<number_of_tp; ++i)  
      {
        RefPoint rp = readRefPoint(number_of_criteria, scanner);
        targetPoints.addPoint(rp);
      }
      LOG.info("Target points read");
      
      //refsets - satus qou
      scanner.next("sq");
      int number_of_sq = scanner.nextInt();
      for (int i=0; i<number_of_sq; ++i)
      {
        RefPoint rp = readRefPoint(number_of_criteria, scanner);
        statusQuo.addPoint(rp);
      }
      LOG.info("Staus quo read");
      
      //refsets - anti ideal
      scanner.next("ai");
      int number_of_ai = scanner.nextInt();
      for (int i=0; i<number_of_ai; ++i)
      {
        RefPoint rp = readRefPoint(number_of_criteria, scanner);
        antiIdeal.addPoint(rp);
      }
      LOG.info("Anti ideal read");
    }
    catch (Exception e)
    {
      scanner.close();
      throw e;
    }
    return state;
  }
  
  /**
   * Find compromise solution.
   * @return  Solution if alternative set is not empty or null otherwise.
   */
  public Alternative solve()
  {
    LOG.entering(Problem.class.getName(), "Solve");
    double min_dist = Double.MAX_VALUE;
    Alternative alt = null;
    //solving: calculate distance to A1 and to A3 and find min to A1 and max to A3 min for g(-j) - g(+j)
    for (Alternative a : alternatives)
    {
        //calculate distances
        a.calculateDistances(targetPoints, antiIdeal);
        LOG.info("Distances calculated");
        
        //find max (in G(x) = g(-j) - lambda g(+j) - lambda is argitrary (has to be more then 0)
        a.calculateG();
        LOG.info("G calculated");
        
        //find min in G and get alternative
        if (a.G < min_dist)
        {
          min_dist = a.G;
          alt = a;
        }
        LOG.info("Found min");
    }
    
    return alt;
  }
}
