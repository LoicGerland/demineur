/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package vue;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.Border;

/**
 *
 * @author Loic
 */
public class View extends JFrame {

    public View() {
        super();
        
        build();
        
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent arg0) {
                super.windowClosing(arg0);
                System.exit(0);
            }
        });
        
        
    }
    
    public void build() {
    	
        JMenuBar jm = new JMenuBar();
        
        JMenu m = new JMenu("Jeu");
        
        JMenuItem mi = new JMenuItem("Partie");
        
        m.add(mi);
        
        jm.add(m);
        
        setJMenuBar(jm);
        
        setTitle("D�mineur");
        setSize(400, 400);
        JComponent pan = new JPanel (new GridLayout(10,10));
        Border blackline = BorderFactory.createLineBorder(Color.black,1);

        for(int i = 0; i<100;i++){
            JComponent ptest = new Case(i/10,i%10);
            ptest.setBorder(blackline);
            pan.add(ptest);
        }
        pan.setBorder(blackline);
        add(pan);
        //setContentPane(pan);

        
        
        
    }

    
}
