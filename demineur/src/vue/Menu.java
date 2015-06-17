package vue;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;

import modele.Game;
import modele.GameMulti;

public class Menu extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JRadioButton rdbtnSolo;

	private JRadioButton rdbtnMulti;

	private JRadioButton rdbtnRectangle;

	private JRadioButton rdbtnTriangle;

	private JSpinner nbJoueur;

	private JSpinner nbLigne;

	private JSpinner nbColonne;

	private JSpinner nbBombe;

	private JComboBox<String> difficulty;

	/**
	 * Create the application.
	 */
	public Menu() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		this.setBounds(100, 100, 271, 272);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(null);

		JLabel lblTitle = new JLabel("Menu");
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setBounds(0, 0, 245, 29);
		this.getContentPane().add(lblTitle);

		ButtonGroup modeSoloMulti = new ButtonGroup();

		rdbtnSolo = new JRadioButton("Solo");
		rdbtnSolo.setBounds(6, 40, 109, 23);
		this.getContentPane().add(rdbtnSolo);
		modeSoloMulti.add(rdbtnSolo);

		rdbtnMulti = new JRadioButton("Multi");
		rdbtnMulti.setBounds(117, 40, 73, 23);
		this.getContentPane().add(rdbtnMulti);
		modeSoloMulti.add(rdbtnMulti);

		rdbtnSolo.setSelected(true);

		rdbtnMulti.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				changeMode();
			}
		});
		rdbtnSolo.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				changeMode();
			}
		});

		nbJoueur = new JSpinner();
		nbJoueur.setModel(new SpinnerNumberModel(1, 1, 100, 1));
		nbJoueur.setBounds(199, 41, 41, 20);
		this.getContentPane().add(nbJoueur);
		nbJoueur.setEnabled(false);

		JSeparator separator = new JSeparator();
		separator.setBounds(0, 70, 261, 3);
		this.getContentPane().add(separator);

		difficulty = new JComboBox<String>();
		difficulty.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				changeDifficulty();
			}
		});
		difficulty.setModel(new DefaultComboBoxModel<String>(new String[] {
				"Facile", "Moyen", "Difficile", "Personnalis\u00E9" }));
		difficulty.setToolTipText("");
		difficulty.setBounds(106, 130, 140, 20);
		this.getContentPane().add(difficulty);

		ButtonGroup form = new ButtonGroup();

		rdbtnRectangle = new JRadioButton("Rectangle");
		rdbtnRectangle.setBounds(6, 80, 109, 23);
		this.getContentPane().add(rdbtnRectangle);
		form.add(rdbtnRectangle);
		rdbtnRectangle.setSelected(true);

		rdbtnTriangle = new JRadioButton("Triangle");
		rdbtnTriangle.setBounds(117, 80, 109, 23);
		this.getContentPane().add(rdbtnTriangle);
		form.add(rdbtnTriangle);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(0, 116, 261, 3);
		this.getContentPane().add(separator_1);

		JLabel lblDifficult = new JLabel("Difficult\u00E9");
		lblDifficult.setBounds(10, 133, 76, 14);
		this.getContentPane().add(lblDifficult);

		nbLigne = new JSpinner();
		nbLigne.setModel(new SpinnerNumberModel(1, 1, 20, 1));
		nbLigne.setBounds(20, 176, 66, 20);
		this.getContentPane().add(nbLigne);

		nbColonne = new JSpinner();
		nbColonne.setModel(new SpinnerNumberModel(1, 1, 40, 1));
		nbColonne.setBounds(96, 176, 60, 20);
		this.getContentPane().add(nbColonne);

		nbBombe = new JSpinner();
		nbBombe.setModel(new SpinnerNumberModel(1, 1, 799, 1));
		nbBombe.setBounds(166, 176, 66, 20);
		this.getContentPane().add(nbBombe);

		JLabel lblColonne = new JLabel("Colonne");
		lblColonne.setBounds(96, 161, 46, 14);
		this.getContentPane().add(lblColonne);

		JLabel lblLigne = new JLabel("Ligne");
		lblLigne.setBounds(20, 161, 41, 14);
		this.getContentPane().add(lblLigne);

		JLabel lblBombe = new JLabel("Bombe");
		lblBombe.setBounds(166, 161, 46, 14);
		this.getContentPane().add(lblBombe);

		JButton btnStart = new JButton("Start");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				start();
			}
		});
		btnStart.setBounds(0, 207, 255, 27);
		this.getContentPane().add(btnStart);

		changeDifficulty();
	}

	protected void changeMode() {
		if (rdbtnSolo.isSelected()) {
			nbJoueur.setModel(new SpinnerNumberModel(1, 1, 1, 1));
			nbJoueur.setValue(1);
			nbJoueur.setEnabled(false);
		} else if (rdbtnMulti.isSelected()) {
			nbJoueur.setModel(new SpinnerNumberModel(2, 2, 100, 1));
			nbJoueur.setEnabled(true);
		}
	}

	protected void changeDifficulty() {
		if (difficulty.getSelectedItem() == "Facile") {
			nbBombe.setEnabled(false);
			nbLigne.setEnabled(false);
			nbColonne.setEnabled(false);

			nbLigne.setValue(9);
			nbColonne.setValue(9);
			nbBombe.setValue(10);

		} else if (difficulty.getSelectedItem() == "Moyen") {
			nbBombe.setEnabled(false);
			nbLigne.setEnabled(false);
			nbColonne.setEnabled(false);

			nbLigne.setValue(16);
			nbColonne.setValue(16);
			nbBombe.setValue(40);

		} else if (difficulty.getSelectedItem() == "Difficile") {
			nbBombe.setEnabled(false);
			nbLigne.setEnabled(false);
			nbColonne.setEnabled(false);

			nbLigne.setValue(16);
			nbColonne.setValue(30);
			nbBombe.setValue(99);

		} else if (difficulty.getSelectedItem() == "Personnalis\u00E9") {
			nbBombe.setEnabled(true);
			nbLigne.setEnabled(true);
			nbColonne.setEnabled(true);
		}
	}

	protected void start() {
		if ((Integer) this.nbJoueur.getValue() == 1) {
			if (this.rdbtnRectangle.isSelected()) {
				Game game = new Game((Integer) nbLigne.getValue(),
						(Integer) nbColonne.getValue(),
						(Integer) nbBombe.getValue());
				ViewRectangle vue = new ViewRectangle(game);
				vue.setVisible(true);

			} else if (this.rdbtnTriangle.isSelected()) {
				Game game = new Game((Integer) nbLigne.getValue(),
						(Integer) nbBombe.getValue());
				ViewTriangle vue = new ViewTriangle(game);
				vue.setVisible(true);

			}
		} else {
			if (this.rdbtnRectangle.isSelected()) {
				GameMulti game = new GameMulti((Integer) nbLigne.getValue(),
						(Integer) nbColonne.getValue(),
						(Integer) nbBombe.getValue(),
						(Integer) nbJoueur.getValue());
				ViewRectangle vue = new ViewRectangle(game);
				vue.setVisible(true);
				;
			} else {
				GameMulti game = new GameMulti((Integer) nbLigne.getValue(),
						(Integer) nbBombe.getValue(),
						(Integer) nbJoueur.getValue());
				ViewTriangle vue = new ViewTriangle(game);
				vue.setVisible(true);
			}
		}
		this.dispose();
	}
}
