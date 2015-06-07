package modele;

import java.util.List;
import java.util.Observable;

public class CaseModele extends Observable {

	private int value;

	private Type type;

	private boolean flag;

	private boolean clicked;

	private Grid grid;

	private static boolean firstCase = true;

	public CaseModele(Type t, Grid grid) {
		this.value = 0;
		this.type = t;
		this.flag = false;
		this.clicked = false;
		this.grid = grid;
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
		if(!(this.getGrid().getGame() instanceof Game2Players)) {
			if (!this.clicked) {
				if(this.flag == true) {
					this.getGrid().removeFlag();
					this.flag = false;
				}
				else {
					this.getGrid().putFlag();
					this.flag = true;
				}
				notifyCase();
			}
		}
	}

	public boolean isClicked() {
		return clicked;
	}

	public void setClicked() {
		if (firstCase) {
			if (this.getType() == Type.Mine) {
				this.moveBomb();
			}
			firstCase = false;
		}
		click();
	}
	
	public Grid getGrid() {
		return this.grid;
	}

	public void click() {

		if (!this.flag) {
			if (!this.isClicked()) {
				this.clicked = true;
				this.playCase();
				grid.checkGame(this);
				}
			}
	}

	private void moveBomb() {
		this.setType(Type.Empty);
		List<CaseModele> voisins = grid.getVoisin(this);
		for (CaseModele voisin : voisins) {
			if (voisin.getType() == Type.Empty) {
				voisin.setType(Type.Mine);
				return;
			}
		}
		this.setType(Type.Mine);
	}

	private void playCase() {
		this.setValue(0);
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
					voisin.click();
				}
			}
		}
	}

	public void setDoubleClick() {
		List<CaseModele> voisins = grid.getVoisin(this);
		for (CaseModele voisin : voisins) {
			if (!voisin.isClicked()
					&& this.grid.getGame().getStatus() == Status.Playing) {
				voisin.click();
			}
		}
		grid.checkGame(this);
	}

	public void notifyCase() {
		setChanged();
		notifyObservers();
	}

	public void resetFlag() {
		this.flag = false;
	}

	public void resetClick() {
		this.clicked = false;
	}

	public static void resetFirstCase() {
		setFirstCase(true);
	}

	public static void setFirstCase(boolean firstCase) {
		CaseModele.firstCase = firstCase;
	}
}
