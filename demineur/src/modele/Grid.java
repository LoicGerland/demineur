package modele;

import java.util.HashMap;
import java.util.List;

public abstract class Grid {

	private int height;
	
	private int width;
	
	private HashMap<CaseModele,Point> map;
	
	public Grid(int x, int y) {
		this.height = y;
		this.width = x;
		this.map = new HashMap<CaseModele,Point>();
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
		
	public HashMap<CaseModele,Point> getMap() {
		return map;
	}

	public void setMap(HashMap<CaseModele,Point> map) {
		this.map = map;
	}

	public abstract List<CaseModele> getVoisin(CaseModele caseMod);
}