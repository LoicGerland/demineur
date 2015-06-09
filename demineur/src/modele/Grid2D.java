package modele;

import java.util.HashMap;
import java.util.List;

public abstract class Grid2D {

	private int height;

	private int width;

	private HashMap<CaseModele, Point> map;

	private CaseModele[][] grid;

	private Game game;

	public Grid2D(int x, int y, Game game) {
		this.height = y;
		this.width = x;
		this.map = new HashMap<CaseModele, Point>();
		this.grid = new CaseModele[x][y];
		this.setGame(game);
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public HashMap<CaseModele, Point> getMap() {
		return map;
	}

	public void setMap(HashMap<CaseModele, Point> map) {
		this.map = map;
	}

	public CaseModele[][] getGrid() {
		return grid;
	}

	public void setCase(CaseModele caseGrid, int x, int y) {
		this.grid[x][y] = caseGrid;
		this.map.put(caseGrid, new Point(x, y));
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public void putFlag() {
		this.getGame().setNbFlags(this.getGame().getNbFlags() + 1);
		this.getGame().notifyView();
	}

	public void removeFlag() {
		this.getGame().setNbFlags(this.getGame().getNbFlags() - 1);
		this.getGame().notifyView();
	}

	public abstract List<CaseModele> getVoisin(CaseModele caseMod);

	public abstract void setGrid(int nbBombs);

	public void showBomb() {
		for (int i = 0; i < this.getWidth(); i++) {
			for (int j = 0; j < this.getHeight(); j++) {
				if (this.getGrid()[i][j] != null
						&& this.getGrid()[i][j].getType() == Type.Mine) {
					if (this.getGrid()[i][j].isFlag()) {
						this.getGrid()[i][j].setFlag(false);
					}
					this.getGrid()[i][j].setClicked(true);
				}
			}
		}
	}

	public void hideAll() {
		for (int i = 0; i < this.getWidth(); i++) {
			for (int j = 0; j < this.getHeight(); j++) {
				if (this.getGrid()[i][j] != null) {
					this.getGrid()[i][j].resetFlag();
					this.getGrid()[i][j].resetClick();
					this.getGrid()[i][j].notifyCase();
				}
			}
		}
	}

	public void showAll() {
		for (int i = 0; i < this.getWidth(); i++) {
			for (int j = 0; j < this.getHeight(); j++) {
				if (this.getGrid()[i][j] != null)
					this.getGrid()[i][j].setClicked(true);
			}
		}
	}

	public void gotBomb() {
		this.game.gotBomb();
	}

	public void gotEmpty() {
		this.game.gotEmpty();
	}
}