/**
 * @author Kushal Paudyal
 * www.sanjaal.com/java
 * Last Modified On: 05-21-2009
 */
package eg.edu.guc.entanglement.gui;
 
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
 
/**
 * Demonstrating  How To Get the
 * Available Font Names Using Java
 *
 */
public class AvailableFontLister {
 
    public static void main(String args [])
    {
        System.out.println("***************************");
        System.out.println("**Listing Fonts Using Deprecated Method From ToolKit Class**");
      //  listUsingToolKit();
        System.out.println();
 
        System.out.println("****************************");
        System.out.println("**Listing Fonts Using Method From GraphicsEnvironment Class**");
        listUsingGraphicsEnvironment ();
 
    }
 
    /**
     * Prints a list of all available fonts from
     * the local graphics environment.
     *
     * The output list varies from manchine to machine
     */
    public static void listUsingGraphicsEnvironment ()
    {
        GraphicsEnvironment ge= null;
 
        ge=GraphicsEnvironment.getLocalGraphicsEnvironment();
 
        String [] fontNames = ge.getAvailableFontFamilyNames();
 
        for (int i = 0; i < fontNames.length; i++) {
            System.out.println(fontNames[i]);
        }
    }
 
}