/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package RefPoints;

/**
 *
 * @author Asia
 */
public class NotANumberValueException extends Exception {
    public void showMessage() {
        System.out.println("Value can not be other than a number");
    }
}
