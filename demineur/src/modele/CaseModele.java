package modele;

import java.util.Observable;

public class CaseModele extends Observable{
	
	private int value;
	
	private Type type;
	
	private boolean flag;
	
	public CaseModele(Type t){
		this.value = 0;
		this.type = t;
		this.flag = false;
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
		if(this.flag) {
			this.flag = false;
		}
		else {this.flag = true; }
		System.out.println("Tu as mis un drapeau sur moi " + this);
		setChanged();
        notifyObservers();
	}

}
