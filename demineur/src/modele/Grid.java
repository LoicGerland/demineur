package modele;

import java.util.HashMap;
import java.util.List;

public abstract class Grid {

	private int height;

	private int width;

	private HashMap<CaseModele, Point> map;

	private CaseModele[][] grid;

	private Game game;

	public Grid(int x, int y, Game game) {
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

	public abstract List<CaseModele> getVoisin(CaseModele caseMod);

	public abstract void checkGame();

	public abstract void showBomb();

	public abstract void setGrid(int nbBombs);

	public abstract void hideAll();
	
	public abstract void showAll();
}