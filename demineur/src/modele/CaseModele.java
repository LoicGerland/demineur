package modele;

import java.util.List;
import java.util.Observable;

public class CaseModele extends Observable {

	private int value;

	private Type type;

	private boolean flag;

	private boolean clicked;

	private Grid grid;

	private boolean enabled;

	public CaseModele(Type t, Grid grid) {
		this.value = 0;
		this.type = t;
		this.flag = false;
		this.clicked = false;
		this.grid = grid;
		this.enabled = true;
	}

	public int getValue() {
		return value;
	}

	public Type getType() {
		return type;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag() {
		if (!this.clicked && this.isEnabled()) {
			this.flag = !this.flag;
			grid.checkGame();
			notifyCase();
		}
	}

	public boolean isClicked() {
		return clicked;
	}

	public void setClicked() {
		if (!this.flag && this.isEnabled()) {
			if (!this.isClicked()) {
				this.clicked = true;
				this.playCase();
				grid.checkGame();
			}
		}

	}

	private void playCase() {
		List<CaseModele> voisins = grid.getVoisin(this);
		for (CaseModele voisin : voisins) {
			if (voisin.getType() == Type.Mine) {
				this.setValue(this.getValue() + 1);
			}
		}
		notifyCase();
		if (this.getValue() <= 0 && this.getType() != Type.Mine) {
			for (CaseModele voisin : voisins) {
				if (!voisin.isClicked()) {
					voisin.setClicked();
				}
			}
		}
	}

	public void setDoubleClick() {
		List<CaseModele> voisins = grid.getVoisin(this);
		for (CaseModele voisin : voisins) {
			if (!voisin.isClicked()) {
				voisin.setClicked();
			}
		}
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public boolean isEnabled() {
		return this.enabled;
	}

	public void notifyCase() {
		setChanged();
		notifyObservers();
	}
}
