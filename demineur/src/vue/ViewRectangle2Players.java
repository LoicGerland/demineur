package vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;

import modele.Game2Players;
import modele.Status;

/**
 * @author Loic
 *
 */
public class ViewRectangle2Players extends ViewRectangle {

	private static final long serialVersionUID = 6831936801716949149L;
	
	private Game2Players game2P;

	public ViewRectangle2Players(Game2Players g) {
		super(g);
		this.game2P = g;
		
		this.game2P.addObserver(new Observer() {

			@Override
			public void update(Observable arg0, Object arg1) {
				if (game2P.getStatus() == Status.Win) {
					int option = JOptionPane.showConfirmDialog(null,
							"Voulez-vous continuer à jouer ?",((Game2Players)arg0).getCurrentPlayer().getName() + " à gagné !!",
							JOptionPane.YES_NO_OPTION,
							JOptionPane.QUESTION_MESSAGE);

					if (option == JOptionPane.OK_OPTION) {
						continuer();
					} else {
						quit();
					}
				}
				if (game2P.getStatus() == Status.Loose) {
					int option = JOptionPane.showConfirmDialog(null,
							"Voulez-vous continuer à jouer ?", "Egalité !!",
							JOptionPane.YES_NO_OPTION,
							JOptionPane.QUESTION_MESSAGE);

					if (option == JOptionPane.OK_OPTION) {
						continuer();
					} else {
						quit();
					}
				}
				if (game2P.getStatus() == Status.Playing) {
					lblNbBomb.setText(((Integer) (game2P.getNbBombs() - game2P
							.getNbFlags())).toString());
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
		
		
		
		JMenu colorMenu = new JMenu("Changer de couleur");
		
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
		
		menu.add(colorMenu);
		

		JMenuItem quit = new JMenuItem("Quitter");
		quit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				quit();
			}
		});
		menu.add(quit);
		
		menuBar.add(menu);

		setJMenuBar(menuBar);

		JComponent window = new JPanel(new BorderLayout());
		JPanel status = new JPanel(new FlowLayout());
		JLabel lblScore1 = new JLabel(((Integer) game2P.getPlayer1().getScore()).toString());
		JLabel lblScore2 = new JLabel(((Integer) game2P.getPlayer2().getScore()).toString());
		lblNbBomb = new JLabel(((Integer) game2P.getNbBombs()).toString());
		status.add(lblScore1);
		status.add(lblNbBomb);
		status.add(lblScore2);

		setTitle("Démineur");
		
		pan = new JPanel(new GridLayout(game2P.getGrid().getWidth(),
				game2P.getGrid().getHeight()));
		

		Border blackline = BorderFactory.createLineBorder(Color.black, 1);

		for (int i = 0; i < game2P.getGrid().getWidth(); i++) {
			for (int j = 0; j < game2P.getGrid().getHeight(); j++) {
				JComponent ptest = new CaseVue(
						this.game2P.getGrid().getGrid()[i][j]);
				ptest.setBorder(blackline);
				pan.add(ptest);
			}
		}
		pan.setBorder(blackline);

		window.add(status, BorderLayout.NORTH);
		window.add(pan, BorderLayout.CENTER);
		add(window);
		setSize(36 * game2P.getGrid().getHeight(), (int) (status.getSize()
				.getWidth() + 36 * game2P.getGrid().getWidth() + 36));
	}

}
