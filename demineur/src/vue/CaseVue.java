package vue;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import modele.CaseModele;
import modele.Point;
import modele.Type;

/**
 *
 * @author Loic
 */
public class CaseVue extends JButton {

	private CaseModele caseMod;

	public CaseVue(CaseModele caseM) {
		super();
		this.caseMod = caseM;
		this.setIconTextGap(-CaseVue.this.getWidth());
		this.setHorizontalTextPosition(SwingConstants.CENTER);

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
				if (e.getClickCount() == 2 && !e.isConsumed()
						&& e.getButton() == MouseEvent.BUTTON1) {
					e.consume();
					caseMod.setDoubleClick();
				}
			}

		});

		this.caseMod.addObserver(new Observer() {

			@Override
			public void update(Observable arg0, Object arg1) {
				if (caseMod.isFlag()) {
					CaseVue.this.setBackground(Color.black);
				} else if (caseMod.isClicked()
						&& caseMod.getType() == Type.Mine) {
					CaseVue.this.setBackground(Color.red);
				} else if (caseMod.isClicked()
						&& caseMod.getType() == Type.Empty) {
					CaseVue.this.setText(String.valueOf(caseMod.getValue()));
				} else {
					CaseVue.this.setBackground(Color.white);
				}
				if (!caseMod.isEnabled()) {
					CaseVue.this.getParent().getParent().getParent()
							.getParent().getParent().setVisible(false);
				}

			}

		});

	}
}
