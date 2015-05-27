package vue;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import modele.CaseModele;
import modele.Point;
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
		setLayout(new FlowLayout());

		setBackground(Color.white);

		addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON3) {
					caseMod.setFlag();
				}
				if (e.getButton() == MouseEvent.BUTTON1) {
					caseMod.setClicked();
				}
			}

		});

		this.caseMod.addObserver(new Observer() {

			@Override
			public void update(Observable arg0, Object arg1) {
				if (caseMod.isFlag()) {
					setBackground(Color.black);
				} else if (caseMod.isClicked()
						&& caseMod.getType() == Type.Mine) {
					setBackground(Color.red);
				} else if (caseMod.isClicked()
						&& caseMod.getType() == Type.Empty) {
					add(new JLabel(String.valueOf(caseMod.getValue())));
				} else {
					setBackground(Color.white);
				}
			}

		});
	}
}
