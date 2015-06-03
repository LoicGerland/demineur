package vue;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.ButtonGroup;
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
import modele.Player;

@SuppressWarnings("serial")
public class Menu extends JFrame {

	private JPanel contentPane;

	JSpinner nbLine;

	JSpinner nbColumn;

	JSpinner nbBomb;

	JComboBox<String> difficulty;
	
	Boolean formRectangle = true; //On changera cette variable par un enum si plus de 2 formes possibles

	/**
	 * Create the frame.
	 */
	public Menu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 349, 169);
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
		nbLine.setBounds(152, 68, 50, 20);
		contentPane.add(nbLine);

		nbColumn = new JSpinner();
		nbColumn.setModel(new SpinnerNumberModel(1, 1, 32, 1));
		nbColumn.setBounds(212, 68, 50, 20);
		contentPane.add(nbColumn);

		nbBomb = new JSpinner();
		nbBomb.setModel(new SpinnerNumberModel(1, 1, 576, 1));
		nbBomb.setBounds(272, 68, 50, 20);
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
		difficulty.setBounds(10, 68, 120, 20);
		contentPane.add(difficulty);

		JLabel lblDifficult = new JLabel("Difficult\u00E9");
		lblDifficult.setBounds(10, 55, 66, 14);
		contentPane.add(lblDifficult);

		JLabel lblLigne = new JLabel("Ligne");
		lblLigne.setBounds(152, 55, 46, 14);
		contentPane.add(lblLigne);

		JLabel lblColonne = new JLabel("Colonne");
		lblColonne.setBounds(212, 55, 46, 14);
		contentPane.add(lblColonne);

		JLabel lblBombe = new JLabel("Bombe");
		lblBombe.setBounds(272, 55, 46, 14);
		contentPane.add(lblBombe);

		ButtonGroup formGrille = new ButtonGroup();
		JCheckBox formRectangle = new JCheckBox("Rectangulaire");
		formRectangle.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				changeForm(true);
			}
		});
		JCheckBox formTriangle = new JCheckBox("Triangulaire");
		formTriangle.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				changeForm(false);
			}
		});

		formGrille.add(formRectangle);
		formGrille.add(formTriangle);

		formRectangle.setBounds(10, 40, 120, 14);
		formTriangle.setBounds(140, 40, 120, 14);

		contentPane.add(formTriangle);
		contentPane.add(formRectangle);

		JButton btnNewButton = new JButton("Start");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				start();
			}
		});
		btnNewButton.setBounds(5, 99, 328, 31);
		contentPane.add(btnNewButton);

		changeDifficulty();
	}

	protected void changeForm(boolean rectangle) {
		this.formRectangle = rectangle;
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
		Game game;
		if (!this.formRectangle) {
			game = new Game((Integer) nbLine.getValue(),
					(Integer) nbBomb.getValue());

			ViewTriangle vue = new ViewTriangle(game);
			vue.setVisible(true);

			this.dispose();
		} else {
			game = new Game((Integer) nbLine.getValue(),
					(Integer) nbColumn.getValue(), (Integer) nbBomb.getValue());

			ViewRectangle vue = new ViewRectangle(game);
			vue.setVisible(true);

			this.dispose();
		}
	}
}
