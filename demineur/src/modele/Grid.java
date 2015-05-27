package modele;

import java.util.HashMap;
import java.util.List;

public abstract class Grid {

	private int height;

	private int width;

	private HashMap<CaseModele, Point> map;

	private CaseModele[][] grid;

	public Grid(int x, int y) {
		this.height = y;
		this.width = x;
		this.map = new HashMap<CaseModele, Point>();
		this.grid = new CaseModele[x][y];
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

	public abstract List<CaseModele> getVoisin(CaseModele caseMod);

	public CaseModele[][] getGrid() {
		return grid;
	}

	public void setGrid(CaseModele[][] grid) {
		this.grid = grid;
	}

	public void setCase(CaseModele caseGrid, int x, int y) {
		this.grid[x][y] = caseGrid;
		this.map.put(caseGrid, new Point(x, y));
	}
}