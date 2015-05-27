package vue;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import modele.CaseModele;
import modele.Type;

/**
 *
 * @author Loic
 */
public class CaseVue extends JPanel {
	
	private CaseModele caseMod;
	
    public CaseVue(CaseModele caseM) {
        super();
        this.caseMod = caseM;
        
        setBackground(Color.white);
        
        addMouseListener(new MouseAdapter() {
        
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON3) {
                	caseMod.setFlag();
                }
                if (e.getButton() == MouseEvent.BUTTON1) {
                	caseMod.setClicked();
                	if(caseMod.getType() == Type.Mine) {
                		setBackground(Color.red);
                	}
                	
                }
            }
            
            
            
            
        });
      
        this.caseMod.addObserver(new Observer()
        {

			@Override
			public void update(Observable arg0, Object arg1) {
				if(caseMod.isFlag()) {
					setBackground(Color.black);
				}
				else { setBackground(Color.white);}
			}
        	
        });
    } 
}
