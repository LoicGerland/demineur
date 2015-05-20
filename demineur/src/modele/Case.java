package modele;

public class Case {
	
	private int value;
	
	private Type type;
	
	private boolean flag;
	
	public Case(Type t){
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

}
