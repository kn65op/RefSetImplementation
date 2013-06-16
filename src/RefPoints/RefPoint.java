/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package RefPoints;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Vector;

/**
 *
 * @author Asia
 */
public class RefPoint {
    private Vector<Double> values = new Vector<Double>();
    RefPoint() {

    }

    public void setSize(int size) throws NegativeValueException {
        if (size <= 0) {
            throw new NegativeValueException();
        }
        else {
            values.setSize(size);
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
                values.setElementAt(value, criterion);
            }
    }

    public Double getCriterionValue(int criterion) {
        return values.elementAt(criterion);
    }

    public void addCriteria(Vector<Double> t) throws NegativeValueException, NullValueException {
        setSize(t.size());
        Double tmp = new Double(0);
        for (int i = 0; i < t.size(); i++) {
            tmp = t.elementAt(i);
            addCriterionValue(i, tmp);
        }
    }

    public Vector<Double> getCriteria() {
        Vector<Double> v = new Vector<Double>();
        v.setSize(values.size());
        Double t = new Double(0);
        for (int i = 0; i < values.size(); i++) {
            t = getCriterionValue(i);
            v.setElementAt(t, i);
        }
        return v;
    }
}
