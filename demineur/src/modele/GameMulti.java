package modele;

import java.util.ArrayList;
import java.util.List;

public class GameMulti extends Game {

	protected List<Player> players;

	public GameMulti(int x, int y, int nb, int nbJoueur) {
		super(x, y, nb);
		players = setJoueur(nbJoueur);
		// TODO Auto-generated constructor stub
	}

	public GameMulti(int y, int nb, int nbJoueur) {
		super(y, nb);
		players = setJoueur(nbJoueur);
		// TODO Auto-generated constructor stub
	}

	// Creer une liste de joueur
	private List<Player> setJoueur(int nbJoueur) {
		players = new ArrayList<>();
		for (int i = 0; i < nbJoueur; i++) {
			players.add(new Player("P" + ((Integer) (i + 1)).toString()));
		}
		currentPlayer = players.get(0);
		return players;
	}

	// Retourne le joueur avec le plus haut score, null si égalité
	public Player getWinner() {
		boolean egalite=false;
		Player winner = new Player("");
		for (Player player : players) {
			if (player.getScore() > winner.getScore()) {
				winner = player;
				egalite=false;
			} else if (player.getScore() == winner.getScore()
					&& player != winner) {
				egalite = true;
			}
		}
		if(egalite)
			winner = null;
		return winner;
	}

	public void winner() {
		if (this.status == Status.Playing) {
			if (getWinner() == null) {
				status = Status.Loose;
			} else {
				status = Status.Win;
			}
			notifyView();
		}
	}

	// Cache les cases et remet les compteurs a zéro
	public void playAgain() {
		getGrid().hideAll();
		nbClicked = 0;
		nbFlags = 0;
		this.chrono.demarrer();
		setStatus(Status.Playing);
		for (Player player : players) {
			player.setScore(0);
		}
	}

	// Change le joueur courant par le suivant dans la liste
	private void swapPlayer() {
		int i = players.indexOf(currentPlayer) + 1;
		if (i >= players.size()) {
			i = 0;
		}
		currentPlayer = players.get(i);
	}

	public String getText() {
		return (((Integer) (this.getNbBombs() - this.getNbFlags())).toString()
				+ " - " + chrono.toString())+ " - "
				+this.getCurrentPlayer().getName()+" : " +((Integer) this.getCurrentPlayer().getScore()).toString();
	}

	// Méthode déclenché lorsqu'une case vide est découverte
	public void gotEmpty() {
		swapPlayer();
		notifyView();
	}

	// Méthode déclenché lorsqu'une mine est découverte
	public void gotBomb() {
		currentPlayer.addScore();
		nbClicked++;
		nbFlags = nbClicked;
		notifyView();

		// Si le nombre de mine trouvé est égal au nombre de mine dans le jeu,
		// la partie est terminé
		if (nbClicked == nbBombs) {
			winner();
		}
	}

}
