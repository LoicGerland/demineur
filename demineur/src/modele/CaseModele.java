package modele;

import java.util.List;
import java.util.Observable;

public class CaseModele extends Observable {

	private int value;

	private Type type;

	private boolean flag;

	private boolean clicked;

	private boolean checked;

	private Game game;

	public CaseModele(Type t, Game game) {
		this.value = 0;
		this.type = t;
		this.flag = false;
		this.clicked = false;
		this.game = game;
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

	public boolean isChecked() {
		return checked;
	}

	public void setChecked() {
		this.checked = true;
	}

	public void setUncheck() {
		this.checked = false;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag() {
		if (!this.clicked) {
			this.flag = !this.flag;
			setChanged();
			notifyObservers();
		}
	}

	public boolean isClicked() {
		return clicked;
	}

	public void setClicked() {
		if (!this.flag) {
			this.clicked = true;
			if (!this.isChecked()) {
				this.setChecked();
				this.playCase();
				if (this.getType() == Type.Mine) {
					game.looser();
				}
			}
		}

	}

	private void playCase() {
		List<CaseModele> voisins = game.getGrid().getVoisin(this);
		for (CaseModele voisin : voisins) {
			if (voisin.getType() == Type.Mine) {
				this.setValue(this.getValue() + 1);
			}
		}
		setChanged();
		notifyObservers();
		if (this.getValue() <= 0 && this.getType()!= Type.Mine) {
			for (CaseModele voisin : voisins) {
				if (!voisin.isChecked()) {
					voisin.setClicked();
				}
			}
		}
	}

	public Game getGame() {
		return this.game;
	}
}
