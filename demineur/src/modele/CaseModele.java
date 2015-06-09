package modele;

import java.util.List;
import java.util.Observable;

public class CaseModele extends Observable {

	private int value;

	private Type type;

	private boolean flag;

	private boolean clicked;

	private Grid2D grid;

	private static boolean firstCase = true;

	public CaseModele(Type t, Grid2D grid) {
		this.value = 0;
		this.type = t;
		this.flag = false;
		this.clicked = false;
		this.grid = grid;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public void hasFlag() {
		if (!(this.getGrid().getGame() instanceof Game2Players)) {
			if (!this.clicked) {
				this.setFlag(!this.isFlag());
				if (this.flag == false) {
					this.getGrid().removeFlag();
				} else {
					this.getGrid().putFlag();
				}
				notifyCase();
			}
		}
	}

	public boolean isClicked() {
		return clicked;
	}

	public void setClicked(boolean clicked) {
		this.clicked = clicked;
	}

	public void hasClicked() {
		if (firstCase) {
			if (this.getType() == Type.Mine) {
				this.moveBomb();
			}
			firstCase = false;
		}
		if (!this.clicked && !this.flag) {
			playCase();
			if (this.getGrid().getGame() instanceof Game2Players) {
				notifyGrid();
			}
		}
	}

	private void notifyGrid() {
		if (this.type == Type.Mine) {
			grid.gotBomb();
		} else {
			grid.gotEmpty();
		}
	}

	public Grid2D getGrid() {
		return this.grid;
	}

	public void playCase() {
		if (!this.flag && !this.clicked) {
			this.setClicked(true);
			if (this.type == Type.Mine) {
				notifyCase();
			} else {
				this.setValue(0);
				for (CaseModele voisin : grid.getVoisin(this)) {
					if (voisin.getType() == Type.Mine) {
						this.setValue(this.getValue() + 1);
					}
				}
				notifyCase();
				if (this.getValue() <= 0) {
					for (CaseModele voisin : grid.getVoisin(this)) {
						voisin.playCase();
					}
				}
			}
			if (!(this.getGrid().getGame() instanceof Game2Players)) {
				notifyGrid();
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

	public void hasDoubleClick() {
		if (!(this.getGrid().getGame() instanceof Game2Players)) {
			for (CaseModele voisin : grid.getVoisin(this)) {
				if (!voisin.isClicked()
						&& this.grid.getGame().getStatus() == Status.Playing) {
					voisin.playCase();
					if (voisin.getType() == Type.Mine && !voisin.isFlag())
						return;
				}
			}
		}
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
		CaseModele.firstCase = true;
	}

	public static void setFirstCase(boolean firstCase) {
		CaseModele.firstCase = firstCase;
	}
}
