package vue;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import modele.CaseModele;
import modele.Type;

/**
 *
 * @author Loic
 */
@SuppressWarnings("serial")
public class CaseVue extends JButton {

	private CaseModele caseMod;

	public static Color CasePlay = Color.BLUE;
	public static Color CaseUnplay = Color.YELLOW;
	public static Color colorFont = Color.GREEN;

	public CaseModele getCaseMod() {
		return caseMod;
	}

	public CaseVue(CaseModele caseM) {
		super();
		this.caseMod = caseM;
		this.setIconTextGap(-CaseVue.this.getWidth());
		this.setHorizontalTextPosition(SwingConstants.CENTER);
		this.setHorizontalAlignment(CENTER);
		this.setBorder(BorderFactory.createRaisedBevelBorder());

		setCouleur();

		addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON3) {
					caseMod.hasFlag();
				}
				if (e.getButton() == MouseEvent.BUTTON1) {
					caseMod.hasClicked();
				}
				if (e.getClickCount() == 2 && !e.isConsumed()
						&& e.getButton() == MouseEvent.BUTTON1) {
					e.consume();
					caseMod.hasDoubleClick();
				}
			}

		});

		this.caseMod.addObserver(new Observer() {

			@Override
			public void update(Observable arg0, Object arg1) {
				setImage();
				setCouleur();
			}

		});

	}

	protected void setImage() {
		if (caseMod.isFlag()) {
			this.setIcon(new ImageIcon("sprite/flag.png"));
		} else if (caseMod.isClicked() && caseMod.getType() == Type.Mine) {
			this.setIcon(new ImageIcon("sprite/bomb.png"));
		} else if (caseMod.isClicked() && caseMod.getType() == Type.Empty
				&& caseMod.getValue() > 0) {
			Font font = new Font("Stencil", Font.BOLD, 18);
			this.setFont(font);
			this.setText(String.valueOf(caseMod.getValue()));
		} else {
			this.setIcon(null);
			this.setText(null);
		}
	}

	public void setCouleur() {
		if (caseMod.isClicked() && caseMod.getType() == Type.Empty) {
			this.setBackground(CasePlay);
			if (caseMod.getValue() > 0) {
				this.setForeground(colorFont);
			}
		} else {
			this.setBackground(CaseUnplay);
		}

		this.repaint();
	}
}
