package modele;

public class Game {
	
	private Grid board;
	
	private Player[] players;
	
	public Game(Grid board, Player[] players){
		this.board = board;
		this.players = players;
	}

	public Grid getBoard() {
		return board;
	}

	public Player[] getPlayers() {
		return players;
	}
	
	

}
