/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package RefPoints;

import RefPoints.RefPoint.ComparasionResult;
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
public class RefPointTest {
  
  public RefPointTest() {
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
   * Test of setSize method, of class RefPoint.
   */
  @Test
  public void testSetSize() throws Exception {
    System.out.println("setSize");
    int size = 2;
    RefPoint instance = new RefPoint();
    instance.setSize(size);
    // TODO review the generated test code and remove the default call to fail.
    assertEquals(size, instance.getValuesSize());
  }

    /**
   * Test of addCriterionValue method, of class RefPoint.
   */
  @Test
  public void testAddCriterionValue() throws Exception {
    System.out.println("addCriterionValue");
    int criterion = 0;
    Double value = 8.7;
    RefPoint instance = new RefPoint();
    instance.setSize(1);
    instance.addCriterionValue(criterion, value);
    // TODO review the generated test code and remove the default call to fail.
  //  fail("The test case is a prototype.");
  }

  /**
   * Test of addCriteria method, of class RefPoint.
   */
  @Test
  public void testAddCriteria() throws Exception {
    System.out.println("addCriteria");
    ArrayList<Double> t = new ArrayList<Double>();
    t.add(2.3);
    t.add(2.3);
    t.add(2.3);
    RefPoint instance = new RefPoint();
    instance.addCriteria(t);
    // TODO review the generated test code and remove the default call to fail.
    //fa/il("The test case is a prototype.");
  }

  
  /**
   * Test of compare method, of class RefPoint.
   */
  @Test
  public void testCompare() throws NegativeValueException, NullValueException {
    System.out.println("compare");
    RefPoint instance = new RefPoint();
    RefPoint other = new RefPoint();
    ComparasionResult expResult = null;
    ComparasionResult result = null;   
    ArrayList<Double> list = new ArrayList<Double>();
    
    list.add(1.1);
    list.add(1.2);
    list.add(1.3);
    list.add(1.4);
    list.add(1.5);
    instance.addCriteria(list);
    list.set(0, 1.2);
    other.addCriteria(list);
    expResult = ComparasionResult.LESS_EQUAL;
    result = instance.compare(other);
    assertEquals(expResult, result);
    
    list.set(0, 1.1);
    list.add(3, 1.6);
    instance.addCriteria(list);
    list.set(3, 1.1);
    other.addCriteria(list);
    expResult = ComparasionResult.GREATER_EQUAL;
    result = instance.compare(other);
    assertEquals(expResult, result);
    
    list.clear();
    list.add(1.1);
    list.add(1.2);
    list.add(1.3);
    instance.addCriteria(list);
    list.set(0, 1.2);
    list.set(1, 1.6);
    list.set(2, 1.5);
    other.addCriteria(list);
    expResult = ComparasionResult.LESS;
    result = instance.compare(other);
    assertEquals(expResult, result);
    
    list.clear();
    list.add(1.1);
    list.add(1.2);
    list.add(1.3);
    instance.addCriteria(list);
    list.set(0, 0.5);
    list.set(1, 0.1);
    list.set(2, 1.2);
    other.addCriteria(list);
    expResult = ComparasionResult.GREATER;
    result = instance.compare(other);
    assertEquals(expResult, result);
    
    list.clear();
    list.add(1.1);
    list.add(1.2);
    instance.addCriteria(list);
    list.set(0, 1.2);
    list.set(1, 1.1);
    other.addCriteria(list);
    expResult = ComparasionResult.UNCOMPARABLE;
    result = instance.compare(other);
    assertEquals(expResult, result);
  }

  /**
   * Test of getValuesSize method, of class RefPoint.
   */
  @Test
  public void testGetValuesSize() {
    System.out.println("getValuesSize");
    RefPoint instance = new RefPoint();
    int expResult = 0;
    int result = instance.getValuesSize();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getCriterionValue method, of class RefPoint.
   */
  @Test
  public void testGetCriterionValue() {
    System.out.println("getCriterionValue");
    int criterion = 0;
    RefPoint instance = new RefPoint();
    Double expResult = null;
    Double result = instance.getCriterionValue(criterion);
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getCriteria method, of class RefPoint.
   */
  @Test
  public void testGetCriteria() {
    System.out.println("getCriteria");
    RefPoint instance = new RefPoint();
    ArrayList expResult = null;
    ArrayList result = instance.getCriteria();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }
}
