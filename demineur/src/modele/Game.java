package modele;

import java.util.Random;

public class Game {

	private Grid grid;

	private Player[] players;

	private int nbBombs;

	private boolean _again = false;

	public boolean is_again() {
		return _again;
	}

	public void set_again(boolean _again) {
		this._again = _again;
	}

	public Game(int x, int y, int nb, Player[] players) {
		this.grid = new Grid2D(x, y, this);
		this.players = players;
		this.nbBombs = nb;

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

	public void setGrid() {
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
				} else
					this.getGrid().setCase(
							new CaseModele(Type.Empty, this.getGrid()), i, j);
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
		if (_again == false) {
			_again = true;
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
			System.out.println("YOU LOSE !");
		}
	}

	public void winner() {
		if (_again == false) {
			_again = true;
			System.out.println("YOU WIN !");

		}
	}
}
