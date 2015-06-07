package modele;

public class Game2Players extends Game {
	
	private Player player1;
	
	private Player player2;
	
	private Player currentPlayer;

	public Game2Players(int x, int y, int nb, Player p1, Player p2) {
		super(x, y, nb);
		this.player1 = p1;
		this.player2 = p2;
		currentPlayer = this.player1;
	}
	
	public Game2Players(int y, int nb, Player p1, Player p2) {
		super(y, nb);
		this.player1 = p1;
		this.player2 = p2;
		currentPlayer = this.player1;
	}
	
	public void swapPlayer() {
		if(this.currentPlayer == this.player1) {
			this.currentPlayer = this.player2;
		}
		else { this.currentPlayer = this.player2; }
	}

	public Player getCurrentPlayer() {
		return currentPlayer;
	}

	public Player getPlayer1() {
		return player1;
	}

	public Player getPlayer2() {
		return player2;
	}
	
	
}
