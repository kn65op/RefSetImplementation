/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package RefSet;

import RefPoints.NegativeValueException;
import RefPoints.NullValueException;
import RefPoints.RefPoint;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Tomko
 */
public class RefSetTest {
  
  public RefSetTest() {
  }
  
  @BeforeClass
  public static void setUpClass() {
  }
  
  @AfterClass
  public static void tearDownClass() {
  }
  
  @Before
  public void setUp() {
  }
  
  @After
  public void tearDown() {
  }

  /**
   * Test of checkInternalConsistency method, of class RefSet.
   */
  @Test
  public void testCheckInternalConsistency() throws NullValueException, NegativeValueException {
    System.out.println("checkInternalConsistency");
    RefSet instance = new RefSet();
    ArrayList<Double> list = new ArrayList<Double>();
    
    RefPoint rp = new RefPoint();
    list.add(1.1);
    list.add(1.2);
    list.add(1.3);
    rp.addCriteria(list);
    instance.addPoint(rp);
    
    rp = new RefPoint();
    list.set(0, 1.2);
    list.set(1, 1.1);
    list.set(2, 1.1);
    rp.addCriteria(list);
    instance.addPoint(rp);
    
    rp = new RefPoint();
    list.set(0, 0.1);
    list.set(1, 1.1);
    list.set(2, 3.1);
    rp.addCriteria(list);
    instance.addPoint(rp);
    
    rp = new RefPoint();
    list.set(0, 3.1);
    list.set(1, 1.1);
    list.set(2, 0.1);
    rp.addCriteria(list);
    instance.addPoint(rp);
    
    boolean expResult = true;
    boolean result = instance.checkInternalConsistency();
    assertEquals(expResult, result);
    
    instance = new RefSet();
        
    rp = new RefPoint();
    list.set(0, 1.2);
    list.set(1, 1.1);
    list.set(2, 1.1);
    rp.addCriteria(list);
    instance.addPoint(rp);
    
    rp = new RefPoint();
    list.set(0, 1.1);
    list.set(1, 1.1);
    list.set(2, 1.1);
    rp.addCriteria(list);
    instance.addPoint(rp);
    
    rp = new RefPoint();
    list.set(0, 3.1);
    list.set(1, 1.1);
    list.set(2, 0.1);
    rp.addCriteria(list);
    instance.addPoint(rp);
    
    expResult = false;
    result = instance.checkInternalConsistency();
    assertEquals(expResult, result);
    
    instance = new RefSet();
        
    rp = new RefPoint();
    list.set(0, 1.2);
    list.set(1, 1.2);
    list.set(2, 1.2);
    rp.addCriteria(list);
    instance.addPoint(rp);
    
    rp = new RefPoint();
    list.set(0, 1.1);
    list.set(1, 1.1);
    list.set(2, 1.1);
    rp.addCriteria(list);
    instance.addPoint(rp);
    
    rp = new RefPoint();
    list.set(0, 3.1);
    list.set(1, 1.1);
    list.set(2, 0.1);
    rp.addCriteria(list);
    instance.addPoint(rp);
    
    expResult = false;
    result = instance.checkInternalConsistency();
    assertEquals(expResult, result);
    
    instance = new RefSet();
        
    rp = new RefPoint();
    list.set(0, 0.1);
    list.set(1, 1.1);
    list.set(2, 1.1);
    rp.addCriteria(list);
    instance.addPoint(rp);
    
    rp = new RefPoint();
    list.set(0, 1.1);
    list.set(1, 0.1);
    list.set(2, 1.1);
    rp.addCriteria(list);
    instance.addPoint(rp);
    
    rp = new RefPoint();
    list.set(0, 1.1);
    list.set(1, 1.1);
    list.set(2, 0.1);
    rp.addCriteria(list);
    instance.addPoint(rp);
    
    expResult = true;
    result = instance.checkInternalConsistency();
    assertEquals(expResult, result);
  }
}
