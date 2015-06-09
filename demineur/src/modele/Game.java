package modele;

import java.util.Observable;

public class Game extends Observable {

	private Grid grid;

	protected int nbBombs;

	protected int nbFlags;

	protected int nbClicked;

	protected Status status;

	public Game(int x, int y, int nb) {
		this.grid = new Grid2DRectangle(x, y, this);
		if (x * y < nb) {
			nb = x * y;
		}
		this.nbBombs = nb;
		this.status = Status.Playing;
		this.nbFlags = 0;
		this.nbClicked = 0;

		this.getGrid().setGrid(this.nbBombs);
		
		notifyView();
	}

	public Game(int y, int nb) {
		this.grid = new Grid2DTriangle(y, this);
		int nbCase = 1;
		for (int i = 0; i < y; i++) {
			nbCase += (nbCase + 2);
		}
		if (nbCase < nb) {
			nb = nbCase;
		}
		this.nbBombs = nb;
		this.status = Status.Playing;
		this.nbFlags = 0;
		this.nbClicked = 0;

		this.getGrid().setGrid(this.nbBombs);

		notifyView();
	}

	public Grid getGrid() {
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
			notifyView();
		}
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

	public void playAgain() {
		getGrid().hideAll();
		nbClicked = 0;
		nbFlags = 0;
		setStatus(Status.Playing);
	}

	public void gotBomb() {
		looser();
	}

	public void gotEmpty() {
		nbClicked++;
		if(nbClicked == (this.getGrid().getHeight() * this.getGrid().getWidth()) - this.nbBombs){
			winner();
		}
	}
}
