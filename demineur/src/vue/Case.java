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
	
	private int x;
	
	private int y;

    public Case(int x, int y) {
        super();
        this.x = x;
        this.y = y;
        
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
