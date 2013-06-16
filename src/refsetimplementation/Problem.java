/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package refsetimplementation;

import RefPoints.RefPoint;
import RefSet.RefSet;
import java.util.ArrayList;

/**
 *
 * @author Tomko
 */
public class Problem {
  private ArrayList<RefPoint> alternatives = new ArrayList<RefPoint>();
  private RefSet boundsOfOptimally = new RefSet();
  private RefSet TargetPoints = new RefSet();
  private RefSet StatusQuo = new RefSet();
  private RefSet AntiIdeal = new RefSet();
}
