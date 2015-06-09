package vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;

import modele.CaseModele;
import modele.Game;
import modele.Mode;
import modele.Status;

/**
 *
 * @author Loic
 */
@SuppressWarnings("serial")
public class ViewRectangle extends JFrame {

	protected Game game;

	protected Couleur color;

	protected JLabel lblNbBomb;

	protected JComponent pan;

	private JLabel lblCurrentPlayer;

	public ViewRectangle(Game g) {
		super();
		this.game = g;
		color = Couleur.Bleu;
		build();

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				super.windowClosing(arg0);
				System.exit(0);
			}
		});

		this.game.addObserver(new Observer() {

			@Override
			public void update(Observable arg0, Object arg1) {
				if (game.getStatus() == Status.Win) {
					int option = JOptionPane.showConfirmDialog(null, "Bravo "
							+ ((Game) arg0).getWinner().getName()
							+ " ! Voulez-vous continuer à jouer ?", "Gagné !!",
							JOptionPane.YES_NO_OPTION,
							JOptionPane.QUESTION_MESSAGE);

					if (option == JOptionPane.OK_OPTION) {
						continuer();
					} else {
						quit();
					}
				}
				if (game.getStatus() == Status.Loose) {
					int option = JOptionPane.showConfirmDialog(null,
							"Voulez-vous continuer à jouer ?", "Perdu !!",
							JOptionPane.YES_NO_OPTION,
							JOptionPane.QUESTION_MESSAGE);

					if (option == JOptionPane.OK_OPTION) {
						continuer();
					} else {
						quit();
					}
				}
				if (game.getStatus() == Status.Playing) {
					if (((Game) arg0).getMode() == Mode.Solo) {
						lblNbBomb.setText(((Integer) (game.getNbBombs() - game
								.getNbFlags())).toString());
					} else {
						lblCurrentPlayer
								.setText(game.getCurrentPlayer().getName()
										+ " : "
										+ ((Integer) game.getCurrentPlayer()
												.getScore()).toString());
					}
				}
			}
		});
	}

	public void build() {
		JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu("Option");

		JMenu again = new JMenu("Recommencer");

		JMenuItem againThis = new JMenuItem("cette partie");
		againThis.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				againThis();
			}
		});
		again.add(againThis);

		JMenuItem againNew = new JMenuItem("nouvelle partie");
		againNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				againNew();
			}
		});
		again.add(againNew);

		menu.add(again);

		JMenu colorMenu = new JMenu("Couleur");

		JMenuItem colorWhite = new JMenuItem("Blanc");
		colorWhite.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setColor(Couleur.Blanc);
			}
		});
		colorMenu.add(colorWhite);

		JMenuItem colorBlue = new JMenuItem("Bleu");
		colorBlue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setColor(Couleur.Bleu);
			}
		});
		colorMenu.add(colorBlue);

		JMenuItem colorGreen = new JMenuItem("Vert");
		colorGreen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setColor(Couleur.Vert);
			}
		});
		colorMenu.add(colorGreen);

		JMenuItem colorRed = new JMenuItem("Rouge");
		colorRed.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setColor(Couleur.Rouge);
			}
		});
		colorMenu.add(colorRed);

		JMenuItem colorPurple = new JMenuItem("Violet");
		colorPurple.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setColor(Couleur.Violet);
			}
		});
		colorMenu.add(colorPurple);

		JMenuItem quit = new JMenuItem("Quitter");
		quit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				quit();
			}
		});
		menu.add(quit);

		menuBar.add(menu);
		menuBar.add(colorMenu);

		setJMenuBar(menuBar);

		JComponent window = new JPanel(new BorderLayout());
		JPanel status = new JPanel(new FlowLayout());
		lblNbBomb = new JLabel(((Integer) game.getNbBombs()).toString());
		status.add(lblNbBomb);
		if (this.game.getMode() == Mode.Multi) {
			lblCurrentPlayer = new JLabel(game.getCurrentPlayer().getName()
					+ " : "
					+ ((Integer) game.getCurrentPlayer().getScore()).toString());
			status.add(lblCurrentPlayer);
		}

		setTitle("Démineur");

		pan = new JPanel(new GridLayout(game.getGrid().getWidth(), game
				.getGrid().getHeight()));

		Border blackline = BorderFactory.createLineBorder(Color.black, 1);

		for (int i = 0; i < game.getGrid().getWidth(); i++) {
			for (int j = 0; j < game.getGrid().getHeight(); j++) {
				JComponent ptest = new CaseVue(
						this.game.getGrid().getGrid()[i][j]);
				ptest.setBorder(blackline);
				pan.add(ptest);
			}
		}
		pan.setBorder(blackline);

		window.add(status, BorderLayout.NORTH);
		window.add(pan, BorderLayout.CENTER);
		add(window);
		setSize(36 * game.getGrid().getHeight(), (int) (status.getSize()
				.getWidth() + 36 * game.getGrid().getWidth() + 36));
	}

	protected void majCase() {
		for (int i = 0; i < this.pan.getComponentCount(); i++) {
			((CaseVue) this.pan.getComponent(i)).setCouleur();
		}
	}

	protected void continuer() {
		int option = JOptionPane.showConfirmDialog(null,
				"Recommencer cette partie ?", "Partie",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

		if (option == JOptionPane.OK_OPTION) {
			againThis();
		} else {
			againNew();
		}
	}

	protected void againThis() {
		game.playAgain();
	}

	protected void againNew() {
		CaseModele.resetFirstCase();
		this.dispose();
		new Menu().setVisible(true);
	}

	protected void quit() {
		System.exit(0);
	}

	protected void setColor(Couleur color) {
		this.color = color;
		if (this.getColor() == Couleur.Bleu) {
			CaseVue.CaseUnplay = Color.BLUE;
			CaseVue.CasePlay = Color.CYAN;
			CaseVue.colorFont = Color.WHITE;
		} else if (this.getColor() == Couleur.Rouge) {
			CaseVue.CaseUnplay = Color.RED;
			CaseVue.CasePlay = Color.ORANGE;
			CaseVue.colorFont = Color.WHITE;
		} else if (this.getColor() == Couleur.Vert) {
			CaseVue.CaseUnplay = Color.GREEN;
			CaseVue.CasePlay = Color.YELLOW;
			CaseVue.colorFont = Color.BLACK;
		} else if (this.getColor() == Couleur.Violet) {
			CaseVue.CaseUnplay = new Color(255, 0, 255);
			CaseVue.CasePlay = Color.BLUE;
			CaseVue.colorFont = Color.WHITE;
		} else {
			CaseVue.CaseUnplay = Color.GRAY;
			CaseVue.CasePlay = Color.WHITE;
			CaseVue.colorFont = Color.DARK_GRAY;
		}
		majCase();
	}

	public Couleur getColor() {
		return this.color;
	}

}
