package modele;

import java.util.Observable;
import java.util.Observer;


public class Game extends Observable {

	private Grid2D grid;

	protected int nbBombs;

	protected int nbFlags;

	protected int nbClicked;

	protected Status status;

	protected Player currentPlayer;

	protected int nbCase;

	protected Chrono chrono;

	// Creer une partie avec une grille rectangulaire
	public Game(int x, int y, int nb) {
		this.grid = new Grid2DRectangle(x, y, this);
		nbCase = x * y;
		if (nbCase < nb) {
			nb = nbCase;
		}
		this.nbBombs = nb;
		nbCase -= nbBombs;
		this.init();
		this.currentPlayer = new Player("P1");
		this.getGrid().setGrid(this.nbBombs);

		notifyView();
	}

	// Creer une partie avec une grille triangulaire
	public Game(int y, int nb) {
		this.grid = new Grid2DTriangle(y, this);
		nbCase = 1;
		for (int i = 1; i < y; i++) {
			nbCase += nbCase + 2;
		}
		if (nbCase < nb) {
			nb = nbCase;
		}
		this.nbBombs = nb;
		nbCase -= nbBombs;

		init();
		this.currentPlayer = new Player("P1");

		this.getGrid().setGrid(this.nbBombs);

		notifyView();
	}

	private void init() {
		this.status = Status.Playing;
		this.nbFlags = 0;
		this.nbClicked = 0;
		chrono = new Chrono();
		chrono.demarrer();
		this.chrono.addObserver(new Observer() {

			@Override
			public void update(Observable arg0, Object arg1) {
					majScore();
			}
		});
	}

	protected void majScore() {
		if (this.status == Status.Playing)
				this.notifyView();
	}

	public Grid2D getGrid() {
		return grid;
	}

	public int getNbBombs() {
		return nbBombs;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public void looser() {
		if (this.status == Status.Playing) {
			this.status = Status.Loose;
			this.getGrid().showBomb();
			notifyView();
		}
	}

	public void winner() {
		if (this.status == Status.Playing) {
			this.status = Status.Win;
		}
		notifyView();
	}

	public Player getCurrentPlayer() {
		return currentPlayer;
	}

	public void notifyView() {
		setChanged();
		notifyObservers();
	}

	public int getNbFlags() {
		return nbFlags;
	}

	public void setNbFlags(int nbFlags) {
		this.nbFlags = nbFlags;
	}

	public int getNbClicked() {
		return nbClicked;
	}

	public void setNbClicked(int nbClicked) {
		this.nbClicked = nbClicked;
	}

	// Cache les cases et remet les compteurs a zéro
	public void playAgain() {
		getGrid().hideAll();
		init();
	}

	// Méthode déclenché lorsqu'une mine est découverte
	public void gotBomb() {
		looser();
	}

	// Méthode déclaenché lorsqu'une case vide est découverte
	public void gotEmpty() {
		nbClicked++;

		// S'il atteinds le nombre de case vide alors victoire !
		if (nbClicked == nbCase) {
			winner();
		}
	}

	public String getText() {
		return (((Integer) (this.getNbBombs() - this.getNbFlags())).toString()
				+ " - " + chrono.toString());
	}

	public Player getWinner() {
		return currentPlayer;
	}
}
