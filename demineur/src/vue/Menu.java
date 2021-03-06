package vue;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import modele.Game;
import modele.Game2Players;
import modele.Player;

import javax.swing.JRadioButton;

@SuppressWarnings("serial")
public class Menu extends JFrame {

	private JPanel contentPane;

	JSpinner nbLine;

	JSpinner nbColumn;

	JSpinner nbBomb;

	JComboBox<String> difficulty;
	
	Boolean formRectangle = true; //On changera cette variable par un enum si plus de 2 formes possibles
	
	Boolean twoPlayers = false;

	/**
	 * Create the frame.
	 */
	public Menu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 349, 174);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblTitre = new JLabel("MENU");
		lblTitre.setBounds(5, 5, 317, 19);
		lblTitre.setFont(new Font("Stencil", Font.PLAIN, 18));
		lblTitre.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblTitre);

		nbLine = new JSpinner();
		nbLine.setModel(new SpinnerNumberModel(1, 1, 18, 1));
		nbLine.setBounds(157, 67, 50, 20);
		contentPane.add(nbLine);

		nbColumn = new JSpinner();
		nbColumn.setModel(new SpinnerNumberModel(1, 1, 32, 1));
		nbColumn.setBounds(217, 67, 50, 20);
		contentPane.add(nbColumn);

		nbBomb = new JSpinner();
		nbBomb.setModel(new SpinnerNumberModel(1, 1, 576, 1));
		nbBomb.setBounds(277, 67, 50, 20);
		contentPane.add(nbBomb);

		difficulty = new JComboBox<String>();
		difficulty.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				changeDifficulty();
			}
		});
		difficulty.setModel(new DefaultComboBoxModel<String>(new String[] {
				"Facile", "Moyen", "Difficile", "Personnalis\u00E9" }));
		difficulty.setToolTipText("");
		difficulty.setBounds(15, 67, 120, 20);
		contentPane.add(difficulty);

		JLabel lblDifficult = new JLabel("Difficult\u00E9");
		lblDifficult.setBounds(19, 52, 66, 14);
		contentPane.add(lblDifficult);

		JLabel lblLigne = new JLabel("Ligne");
		lblLigne.setBounds(161, 52, 46, 14);
		contentPane.add(lblLigne);

		JLabel lblColonne = new JLabel("Colonne");
		lblColonne.setBounds(221, 52, 46, 14);
		contentPane.add(lblColonne);

		JLabel lblBombe = new JLabel("Bombe");
		lblBombe.setBounds(281, 52, 46, 14);
		contentPane.add(lblBombe);

		ButtonGroup formGrille = new ButtonGroup();
		JRadioButton formRectangle = new JRadioButton("Rectangulaire");
		formRectangle.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				changeForm(true);
			}
		});
		JRadioButton formTriangle = new JRadioButton("Triangulaire");
		formTriangle.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				changeForm(false);
			}
		});

		formGrille.add(formRectangle);
		formGrille.add(formTriangle);
		formRectangle.setSelected(true);

		formRectangle.setBounds(15, 31, 120, 14);
		formTriangle.setBounds(135, 31, 87, 14);

		contentPane.add(formTriangle);
		contentPane.add(formRectangle);

		JButton btnNewButton = new JButton("Start");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				start();
			}
		});
		btnNewButton.setBounds(5, 98, 328, 31);
		contentPane.add(btnNewButton);
		
		JCheckBox rdb2Joueurs = new JCheckBox("2 Joueurs");
		rdb2Joueurs.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				SettwoPlayers();
			}
		});
		rdb2Joueurs.setBounds(224, 29, 109, 19);
		contentPane.add(rdb2Joueurs);

		changeDifficulty();
	}

	protected void changeForm(boolean rectangle) {
		this.formRectangle = rectangle;
	}
	
	protected void SettwoPlayers() {
		this.twoPlayers = (!this.twoPlayers);
	}

	protected void changeDifficulty() {
		if (difficulty.getSelectedItem() == "Facile") {
			nbBomb.setEnabled(false);
			nbLine.setEnabled(false);
			nbColumn.setEnabled(false);

			nbLine.setValue(9);
			nbColumn.setValue(9);
			nbBomb.setValue(10);

		} else if (difficulty.getSelectedItem() == "Moyen") {
			nbBomb.setEnabled(false);
			nbLine.setEnabled(false);
			nbColumn.setEnabled(false);

			nbLine.setValue(16);
			nbColumn.setValue(16);
			nbBomb.setValue(40);

		} else if (difficulty.getSelectedItem() == "Difficile") {
			nbBomb.setEnabled(false);
			nbLine.setEnabled(false);
			nbColumn.setEnabled(false);

			nbLine.setValue(16);
			nbColumn.setValue(30);
			nbBomb.setValue(99);

		} else if (difficulty.getSelectedItem() == "Personnalis\u00E9") {
			nbBomb.setEnabled(true);
			nbLine.setEnabled(true);
			nbColumn.setEnabled(true);
		}
	}

	protected void start() {
		if (!this.formRectangle) {
			Game game = new Game((Integer) nbLine.getValue(),
					(Integer) nbBomb.getValue());

			ViewTriangle vue = new ViewTriangle(game);
			vue.setVisible(true);

			this.dispose();
		} 
		else {
			if(!this.twoPlayers){
				Game game = new Game((Integer) nbLine.getValue(),
					(Integer) nbColumn.getValue(), (Integer) nbBomb.getValue());
				
				ViewRectangle vue = new ViewRectangle(game);
				vue.setVisible(true);
			}
			else {
				Player p1 = new Player("P1");
				Player p2 = new Player("P2");
				Game2Players game = new Game2Players((Integer) nbLine.getValue(), (Integer) nbColumn.getValue(), (Integer) nbBomb.getValue(),p1,p2);
				
				ViewRectangle2Players vue = new ViewRectangle2Players(game);
				vue.setVisible(true);
			}

			this.dispose();
		}
	}
}
