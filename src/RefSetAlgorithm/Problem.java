/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package RefSetAlgorithm;

import RefPoints.NegativeValueException;
import RefPoints.NullValueException;
import RefPoints.RefPoint;
import RefPoints.RefPoint.ComparasionResult;
import RefSet.RefSet;
import RefSetAlgorithm.Alternative.State;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ListModel;
import javax.swing.event.ListDataListener;

/**
 *
 * @author Tomko
 */
public class Problem implements ListModel<Alternative> {
  private int size;
  private Object consistency_message;

  public Iterable<Alternative> getAlternatives() {
    return alternatives;
  }

  public int criteriaSize() {
    return size;
  }

  public double getLambda() {
    return Alternative.getLambda();
  }

  @Override
  public int getSize() {
    return alternatives.size();
  }

  @Override
  public Alternative getElementAt(int index) {
    return alternatives.get(index);
  }
  
  @Override
  public void addListDataListener(ListDataListener l) {
  }

  @Override
  public void removeListDataListener(ListDataListener l) {
  }

  public RefSet getBOO() {
    return boundsOfOptimally;
  }

  public RefSet getAI() {
    return antiIdeal;
  }

  public RefSet getSQ() {
    return statusQuo;
  }

  public RefSet getTP() {
    return targetPoints;
  }

  public Object getConsistencyProblem() {
    return consistency_message;
  }

  public boolean isConsistent() {
    boolean consistent = true;
    consistency_message = "";
    
    if (!boundsOfOptimally.checkInternalConsistency())
    {
      consistency_message += "Bounds of Optimality points are not internal consistent\n";
      consistent = false;
    }
    if (!targetPoints.checkInternalConsistency())
    {
      consistency_message += "Target points are not internal consistent\n";
      consistent = false;
    }
    if (!statusQuo.checkInternalConsistency())
    {
      consistency_message += "Status Quo points are not internal consistent\n";
      consistent = false;
    }
    if (!antiIdeal.checkInternalConsistency())
    {
      consistency_message += "Anti-ideal points are not internal consistent\n";
      consistent = false;
    }
    
    if (!boundsOfOptimally.checkMutualConsistency(targetPoints))
    {
      consistency_message += "Bounds of optimality and target points are not mutual consistent";
      consistent = false;
    }
    if (!boundsOfOptimally.checkMutualConsistency(statusQuo))
    {
      consistency_message += "Bounds of optimality and status quo points are not mutual consistent";
      consistent = false;
    }
    if (!boundsOfOptimally.checkMutualConsistency(antiIdeal))
    {
      consistency_message += "Bounds of optimality and anti ideal are not mutual consistent";
      consistent = false;
    }
    if (!targetPoints.checkMutualConsistency(statusQuo))
    {
      consistency_message += "Target points and status quo are not mutual consistent";
      consistent = false;
    }
    if (!targetPoints.checkMutualConsistency(antiIdeal))
    {
      consistency_message += "Target points and anti ideal are not mutual consistent";
      consistent = false;
    }
    if (!statusQuo.checkMutualConsistency(antiIdeal))
    {
      consistency_message += "Status quo points and anti ideal are not mutual consistent";
      consistent = false;
    }
        
    if (consistent)
    {
      consistency_message = "Everything is good";
    }
    return consistent;
  }

  public Iterable<Alternative> getParetoOptimalAlternatives() {
    return findAlternatives(Alternative.State.DOMINATING);
    
  }

  private Iterable<Alternative> findAlternatives(State state) {
    ArrayList<Alternative> ret = new ArrayList<Alternative>();
    for (Alternative a : alternatives)
    {
      if (a.state == state)
      {
        ret.add(a);
      }
    }
    return ret;
  }

  public Iterable<Alternative> getRest() {
    return findAlternatives(State.DOMINATED);
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
  
  public void setMetric(Metric metric)
  {
    LOG.entering(Problem.class.getName(), "SetMetric", metric);
    switch (metric)
    {
      case Euclidean: 
        Alternative.distance = new EuclideanMetrics();
        break;
      case Czebyszew:
        Alternative.distance = new CzebyszewMetrics();
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
    calculateParetoPoints();
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
  
  void calculateParetoPoints()
  {
    int s = alternatives.size();
    for (int i = 0; i < s; ++i)
    {
      Alternative a = alternatives.get(i);
      if (a.state == Alternative.State.DOMINATED)
      {
        continue; //not need to check 
      }
      for (int j = i + 1; j < s; ++j)
      {
        Alternative b = alternatives.get(j);
        ComparasionResult compare = a.getPoint().compare(b.getPoint());
        if (compare == RefPoint.ComparasionResult.GREATER || compare == RefPoint.ComparasionResult.GREATER_EQUAL)
        {
          a.state = Alternative.State.DOMINATED;
          break;
        }
        else if (compare == RefPoint.ComparasionResult.LESS || compare == RefPoint.ComparasionResult.LESS_EQUAL)
        {
          b.state = Alternative.State.DOMINATED;
        }
      }
      if (a.state == Alternative.State.NOT_TESTED)
      {
        a.state = Alternative.State.DOMINATING;
      }
    }
  }
}
