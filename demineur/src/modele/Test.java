package modele;

public class Test {

	public static void main(String[] args) {
		
		Player[] p = new Player[1];
		Player p1 = new Player("P1");
		p[0] = p1;
		Game game = new Game(10,10,20,p);
		
		game.affiche();

	}

}
