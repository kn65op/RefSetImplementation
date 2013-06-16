/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package RefPoints;

import java.util.ArrayList;

/**
 *
 * @author Asia
 */
public class RefPoint {
    private ArrayList<Double> values = new ArrayList<>();
    public RefPoint() {

    }

    public void setSize(int size) throws NegativeValueException {
        if (size <= 0) {
            throw new NegativeValueException();
        }
        else {
          int i = 0;
          while (i < size)
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
}
