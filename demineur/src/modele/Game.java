package modele;

import java.util.Observable;

public class Game extends Observable {

	private Grid grid;

	private int nbBombs;

	private int nbFlags = 0;

	private Status status;

	public Game(int x, int y, int nb) {
		this.grid = new Grid2DRectangle(x, y, this);
		if (x * y < nb) {
			nb = x * y;
		}
		this.nbBombs = nb;
		this.status = Status.Playing;

		this.getGrid().setGrid(this.nbBombs);

		notifyView();
	}
	
	public Game(int y, int nb) {
		this.grid = new Grid2DTriangle(y, this);
		int nbCase = 1;
		for(int i = 0 ; i < y ; i++){
			nbCase += (nbCase + 2);
		}
		if(nbCase < nb){
			nb=nbCase;
		}
		this.nbBombs = nb;
		this.status = Status.Playing;

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
}
