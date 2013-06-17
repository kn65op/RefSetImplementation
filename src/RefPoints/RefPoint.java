/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package RefPoints;

import java.util.ArrayList;
import java.util.ListIterator;

/**
 *
 * @author Asia
 */
public class RefPoint {
  
    public enum ComparasionResult {
      GREATER,
      GREATER_EQUAL,
      EUQAL,
      LESS,
      LESS_EQUAL,
      UNCOMPARABLE
    }
  
    private ArrayList<Double> values = new ArrayList<Double>();
    public RefPoint() {

    }

    public void setSize(int size) throws NegativeValueException {
        if (size <= 0) {
            throw new NegativeValueException();
        }
        else {
          int i = 0;
          while (i++ < size)
          {
            values.add(0.0);
          }
        }
    }

    public int getValuesSize() {
        return values.size();
    }

    public void addCriterionValue(int criterion, Double value) throws NegativeValueException, NullValueException {
            if (criterion < 0) {
                throw new NegativeValueException();
            }
            else if (value == null) {
                throw new NullValueException();
            }
            else {
                values.set(criterion, value);
            }
    }

    public Double getCriterionValue(int criterion) {
        return values.get(criterion);
    }

    public void addCriteria(ArrayList<Double> t) throws NegativeValueException, NullValueException {
        values.clear();
        setSize(t.size());
        for (int i = 0; i < t.size(); i++) {
            Double tmp = new Double(t.get(i));
            addCriterionValue(i, tmp);
        }
    }

    public ArrayList<Double> getCriteria() {
        ArrayList<Double> v = new ArrayList<Double>();
        for (int i = 0; i < values.size(); i++) {
            Double t = new Double(getCriterionValue(i));
            v.add(t);
        }
        return v;
    }
    
    public ComparasionResult compare(RefPoint other)
    {
      ListIterator it = values.listIterator();
      ListIterator other_it = other.values.listIterator();
      return compareRec(it, other_it);
    }
    
    private ComparasionResult compareRec(ListIterator<Double> it, ListIterator<Double> otherit)
    {
      //boundary condition 
      if (!it.hasNext())
      {
        return null;
      }
      it.next();
      if (!it.hasNext())
      {
        it.previous();
        double this_value = it.next();
        double other_value = otherit.next();
        if (this_value > other_value)
        {
          return ComparasionResult.GREATER;
        }
        else if (this_value < other_value)
        {
          return ComparasionResult.LESS;
        }
        return ComparasionResult.EUQAL;
      }
      it.previous();
      
      //normal conditions
      double this_value = it.next();
      double other_value = otherit.next();
      ComparasionResult previous = compareRec(it, otherit);
      switch (previous)
      {
      case GREATER:
        if (this_value > other_value)
        {
          return ComparasionResult.GREATER;
        }
        else if (this_value < other_value)
        {
          return ComparasionResult.UNCOMPARABLE;
        }
        return ComparasionResult.GREATER_EQUAL;
      case GREATER_EQUAL:
        if (this_value >= other_value)
        {
          return ComparasionResult.GREATER_EQUAL;
        }
        return ComparasionResult.UNCOMPARABLE;
      case EUQAL:
        if (this_value > other_value)
        {
          return ComparasionResult.GREATER_EQUAL;
        }
        else if (this_value < other_value)
        {
          return ComparasionResult.LESS_EQUAL;
        }
        return ComparasionResult.EUQAL;
      case LESS:
        if (this_value > other_value)
        {
          return ComparasionResult.UNCOMPARABLE;
        }
        else if (this_value < other_value)
        {
          return ComparasionResult.LESS;
        }
        return ComparasionResult.LESS_EQUAL;
      case LESS_EQUAL:
        if (this_value <= other_value)
        {
          return ComparasionResult.GREATER_EQUAL;
        }
        return ComparasionResult.UNCOMPARABLE;
      case UNCOMPARABLE:
        return ComparasionResult.UNCOMPARABLE;
      default:
        throw new AssertionError(previous.name());
      }
    }
}
