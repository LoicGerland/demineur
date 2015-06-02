package vue;

import java.awt.Color;
import java.awt.Font;
import java.awt.color.ColorSpace;
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
@SuppressWarnings("serial")
public class CaseVue extends JButton {

	private CaseModele caseMod;

	private View view;

	public CaseModele getCaseMod() {
		return caseMod;
	}

	public CaseVue(CaseModele caseM, View view) {
		super();
		this.caseMod = caseM;
		this.view = view;
		this.setIconTextGap(-CaseVue.this.getWidth());
		this.setHorizontalTextPosition(SwingConstants.CENTER);
		this.setHorizontalAlignment(CENTER);

		setCouleur();

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
				}
				setCouleur();
			}

		});

	}

	public void setCouleur() {
		Color caseNull;
		Color caseValue;
		Color colorFont;

		if (this.view.getColor() == Couleur.Bleu) {
			caseNull = Color.BLUE;
			caseValue = Color.CYAN;
			colorFont = Color.DARK_GRAY;
		} else if (this.view.getColor() == Couleur.Rouge) {
			caseNull = Color.ORANGE;
			caseValue = Color.RED;
			colorFont = Color.WHITE;
		} else if (this.view.getColor() == Couleur.Vert) {
			caseNull = Color.YELLOW;
			caseValue = Color.GREEN;
			colorFont = Color.BLACK;
		} else if (this.view.getColor() == Couleur.Violet) {
			caseNull = Color.PINK;
			caseValue = new Color(255, 0, 255);
			colorFont = Color.BLACK;
		} else {
			caseNull = Color.GRAY;
			caseValue = Color.WHITE;
			colorFont = Color.DARK_GRAY;
		}

		if (caseMod.isClicked() && caseMod.getType() == Type.Empty) {
			if (caseMod.getValue() == 0) {
				CaseVue.this.setBackground(caseNull);
			} else {
				Font font = new Font("Stencil", Font.BOLD, 18);
				CaseVue.this.setFont(font);
				CaseVue.this.setText(String.valueOf(caseMod.getValue()));
				CaseVue.this.setForeground(colorFont);
				CaseVue.this.setBackground(caseValue);
			}
		} else if (caseMod.isFlag()
				|| (caseMod.isClicked() && caseMod.getType() == Type.Mine)) {
			CaseVue.this.setBackground(caseNull);
		} else {
			CaseVue.this.setIcon(null);
			CaseVue.this.setText(null);
			CaseVue.this.setBackground(caseValue);
		}

	}
}
