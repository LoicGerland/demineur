package modele;

public class Case {
	
	private int value;
	
	private Type type;
	
	public Case(Type t){
		this.value = 0;
		this.type = t;
	}

	public int getValue() {
		return value;
	}

	public Type getType() {
		return type;
	}
	
	

}
