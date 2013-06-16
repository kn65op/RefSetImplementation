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
public interface DistanceFunction {
  abstract public double calculateDistance(RefPoint a, RefPoint b);
}
