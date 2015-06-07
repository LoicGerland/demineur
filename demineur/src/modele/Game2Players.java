package modele;

public class Game2Players extends Game {
	
	Player player1;
	
	Player player2;
	
	Player currentPlayer;

	public Game2Players(int x, int y, int nb, Player p1, Player p2) {
		super(x, y, nb);
	}
	
	public Game2Players(int y, int nb, Player p1, Player p2) {
		super(y, nb);
	}
	
	public void swapPlayer() {
		if(this.currentPlayer == this.player1) {
			this.currentPlayer = this.player2;
		}
		else { this.currentPlayer = this.player2; }
	}

}
