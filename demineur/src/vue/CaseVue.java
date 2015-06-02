package vue;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import modele.CaseModele;
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
		this.setHorizontalAlignment(CENTER);

		setBackground(Color.CYAN);

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
					CaseVue.this
							.setIcon(new ImageIcon("src/vue/image/flag.png"));
				} else if (caseMod.isClicked()
						&& caseMod.getType() == Type.Mine) {
					CaseVue.this
							.setIcon(new ImageIcon("src/vue/image/bomb.png"));
				} else if (caseMod.isClicked()
						&& caseMod.getType() == Type.Empty) {
					if (caseMod.getValue() == 0) {
						CaseVue.this.setBackground(Color.BLUE);
					} else {
						Font font = new Font("Stencil", Font.BOLD, 18);
						CaseVue.this.setFont(font);
						CaseVue.this.setText(String.valueOf(caseMod.getValue()));
						CaseVue.this.setForeground(Color.DARK_GRAY);
					}
				} else {
					CaseVue.this.setIcon(null);
					CaseVue.this.setText(null);
					CaseVue.this.setBackground(Color.CYAN);
				}

			}

		});

	}
}
