package modele;

import java.util.Observable;
import java.util.Random;

public class Game extends Observable{

	private Grid grid;

	private Player[] players;

	private int nbBombs;

	private boolean again = false;
	
	private Status status;

	public boolean isAgain() {
		return again;
	}

	public void setAgain(boolean again) {
		this.again = again;
	}

	public Game(int x, int y, int nb, Player[] players) {
		this.grid = new Grid2D(x, y, this);
		this.players = players;
		this.nbBombs = nb;
		this.status = Status.Playing;

		setGrid();
	}

	public Player[] getPlayers() {
		return players;
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

	public void setGrid() {
		this.setAgain(false);
		Random r = new Random();
		int nb = this.nbBombs;

		for (int i = 0; i < this.getGrid().getWidth(); i++) {
			for (int j = 0; j < this.getGrid().getHeight(); j++) {
				if (nb > 0
						&& r.nextInt(this.getGrid().getHeight()
								* this.getGrid().getWidth()) < this.nbBombs) {
					this.getGrid().setCase(
							new CaseModele(Type.Mine, this.getGrid()), i, j);
					nb--;
				} else {
					this.getGrid().setCase(
							new CaseModele(Type.Empty, this.getGrid()), i, j);
				}
			}
		}

		if (nb > 0) {
			while (nb > 0) {
				int i = r.nextInt(this.getGrid().getWidth());
				int j = r.nextInt(this.getGrid().getHeight());
				if (this.getGrid().getGrid()[i][j].getType() != Type.Mine) {
					this.getGrid().getGrid()[i][j].setType(Type.Mine);
					nb--;
				}
			}
		}
	}

	public void looser() {
		if (again == false) {
			again = true;
			for (int i = 0; i < this.getGrid().getWidth(); i++) {
				for (int j = 0; j < this.getGrid().getHeight(); j++) {
					if (this.getGrid().getGrid()[i][j].getType() == Type.Mine) {
						if (this.getGrid().getGrid()[i][j].isFlag()) {
							this.getGrid().getGrid()[i][j].setFlag();
						}
						this.getGrid().getGrid()[i][j].setClicked();
					}

				}
			}
			this.status = Status.Loose;
			notifyView();
		}
	}

	public void winner() {
		if (again == false) {
			again = true;
			this.status = Status.Win;
			notifyView();
		}
	}
	
	public void notifyView() {
		setChanged();
		notifyObservers();
	}
}
