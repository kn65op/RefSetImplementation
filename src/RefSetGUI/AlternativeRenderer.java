/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package RefSetGUI;

import RefSetAlgorithm.Alternative;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

/**
 *
 * @author Tomko
 */
public class AlternativeRenderer extends JLabel implements ListCellRenderer<Alternative>   {

  @Override
  public Component getListCellRendererComponent(JList<? extends Alternative> list, Alternative value, int index, boolean isSelected, boolean cellHasFocus) {
    setText(value.toString());
    if (value.isParetoOptimal())
    {
      setForeground(Color.GREEN);
    }
    else
    {
      setForeground(Color.RED);
    }
    return this;
    
  }
  
}
