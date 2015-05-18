/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package vue;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;

/**
 *
 * @author Loic
 */
public class Case extends JPanel {

    public Case() {
        super();
        
        setBackground(Color.white);
        
        addMouseListener(new MouseAdapter() {
        
            @Override
            public void mouseClicked(MouseEvent arg0) {
                super.mouseClicked(arg0);
                setBackground(Color.BLACK);
            }
            
            
            
        });
        
    }
    
}
