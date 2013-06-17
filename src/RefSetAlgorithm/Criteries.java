/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package RefSetAlgorithm;

import java.util.ArrayList;
import javax.swing.ListModel;
import javax.swing.event.ListDataListener;

/**
 *
 * @author Tomko
 */
public class Criteries implements ListModel<String> {

  ArrayList<String> list = new ArrayList<String>();
  private ArrayList<ListDataListener> listners = new ArrayList<ListDataListener>();
  @Override
  public int getSize() {
    return list.size();
  }

  @Override
  public String getElementAt(int index) {
    return list.get(index);
    
  }
  
  @Override
  public void addListDataListener(ListDataListener l) {
    
    listners.add(l);
  }

  @Override
  public void removeListDataListener(ListDataListener l) {
    listners.remove(l);
  }

  private void notifyListeners() {
    for (ListDataListener l : listners)
    {
      l.contentsChanged(null);
    }
  }
  
  void add(String s)
  {
    list.add(s);
    notifyListeners();
  }
  
  void clear()
  {
    list.clear();
    notifyListeners();
  }
  
  
  
}
