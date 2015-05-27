package modele;

import java.util.List;
import java.util.Observable;

public class CaseModele extends Observable {

	private int value;

	private Type type;

	private boolean flag;

	private boolean clicked;

	private boolean checked;

	public CaseModele(Type t) {
		this.value = 0;
		this.type = t;
		this.flag = false;
		this.clicked = false;
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
	
	public void setUncheck(){
		this.checked = false;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag() {
		if (!this.clicked) {
			this.flag = !this.flag;
			System.out.println("Tu as mis un drapeau sur moi, Moi, de type "
					+ this.getType() + " " + this);
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
			this.flag = false;
			this.setChecked();
			this.playCase();
			System.out.println("Tu m'as dévoilée ! Moi, de type "
					+ this.getType() + " " + this);
		}
	}

	private void playCase() {
		List<CaseModele> voisins = Grid2D.getVoisin(this);
		for (CaseModele voisin : voisins) {
			this.setValue(this.getValue() + voisin.getValue());
		}
		setChanged();
		notifyObservers();
		if (this.getValue() <= 0) {
			for (CaseModele voisin : voisins) {
				if (!voisin.isChecked()) {
					voisin.setClicked();
				}
			}
		}
	}
}
