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
		if (this.currentPlayer == this.player1) {
			this.currentPlayer = this.player2;
		} else {
			this.currentPlayer = this.player1;
		}
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

	public void playAgain() {
		getGrid().hideAll();
		setStatus(Status.Playing);
		player1.setScore(0);
		player2.setScore(0);
		nbClicked=0;
		nbFlags=0;
		notifyView();
	}

	public Player getWinner() {
		if (player1.getScore() > player2.getScore()) {
			return player1;
		} else
			return player2;
	}
	
	public void winner() {
		if (status == Status.Playing) {
			if(player1.getScore()==player2.getScore()){
				status = Status.Loose;
			} else {
				status = Status.Win;
			}
			notifyView();
		}
	}
	
	public void gotBomb(){
		currentPlayer.addScore();
		nbClicked++;
		notifyView();
		
		if(nbClicked == nbBombs){
			winner();
		}
	}
	
	public void gotEmpty(){
		swapPlayer();
		notifyView();
	}
}
