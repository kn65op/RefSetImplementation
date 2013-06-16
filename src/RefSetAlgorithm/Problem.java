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
  
  private RefPoint readRefPoint(int number_of_criteria, Scanner scanner) throws NegativeValueException, NullValueException, InputMismatchException
  {
    RefPoint rp = new RefPoint();
    rp.setSize(number_of_criteria);
    for (int j = 0; j < number_of_criteria; ++j) {
      rp.addCriterionValue(j, scanner.nextDouble());
    }
    return rp;
  }
  
  public boolean readProblem(File file) throws FileNotFoundException, NegativeValueException, NullValueException, InputMismatchException
  {
    boolean state = true;
    try (Scanner scanner = new Scanner(file)) {
      int number_of_criteria = scanner.nextInt();
      int number_of_alternatives = scanner.nextInt();
      
      //alternatives
      for (int i=0; i < number_of_alternatives; ++i)
      {
        RefPoint rp;
        rp = readRefPoint(number_of_criteria, scanner);
        alternatives.add(new Alternative(rp));
      }
      
      //refsets bounds of optimally
      scanner.next("boo");
      int number_of_boo = scanner.nextInt();
      for (int i=0; i<number_of_boo; ++i)
      {
        RefPoint rp = readRefPoint(number_of_criteria, scanner);
        boundsOfOptimally.addPoint(rp);
      }
      
      //refsets - target points
      scanner.next("tp");
      int number_of_tp = scanner.nextInt();
      for (int i=0; i<number_of_tp; ++i)
      {
        RefPoint rp = readRefPoint(number_of_criteria, scanner);
        targetPoints.addPoint(rp);
      }
      
      //refsets - satus qou
      scanner.next("sq");
      int number_of_sq = scanner.nextInt();
      for (int i=0; i<number_of_sq; ++i)
      {
        RefPoint rp = readRefPoint(number_of_criteria, scanner);
        statusQuo.addPoint(rp);
      }
      
      //refsets - anti ideal
      scanner.next("boo");
      int number_of_ai = scanner.nextInt();
      for (int i=0; i<number_of_ai; ++i)
      {
        RefPoint rp = readRefPoint(number_of_criteria, scanner);
        antiIdeal.addPoint(rp);
      }
    }
    catch (Exception e)
    {
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
