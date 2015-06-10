package modele;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class Game extends Observable {

	private Grid2D grid;

	protected int nbBombs;

	protected int nbFlags;

	protected int nbClicked;

	protected Status status;

	protected Mode mode;

	protected List<Player> players;

	protected Player currentPlayer;
	
	protected int nbCase;

	
	//Creer une partie avec une grille rectangulaire
	public Game(int x, int y, int nb, int nbJoueur) {
		this.grid = new Grid2DRectangle(x, y, this);
		nbCase = this.grid.getHeight()*this.grid.getWidth();
		if (nbCase < nb) {
			nb = nbCase;
		}
		this.nbBombs = nb;
		nbCase -= nbBombs;
		this.status = Status.Playing;
		this.nbFlags = 0;
		this.nbClicked = 0;
		players = setJoueur(nbJoueur);

		this.getGrid().setGrid(this.nbBombs);

		notifyView();
	}
	
	//Creer une partie avec une grille triangulaire
	public Game(int y, int nb, int nbJoueur) {
		this.grid = new Grid2DTriangle(y, this);
		nbCase = 1;
		for(int i = 1; i < y;i++){
			nbCase += nbCase + 2;
		}
		if (nbCase < nb) {
			nb = nbCase;
		}
		this.nbBombs = nb;
		nbCase -= nbBombs;
		
		this.status = Status.Playing;
		this.nbFlags = 0;
		this.nbClicked = 0;
		players = setJoueur(nbJoueur);
		

		this.getGrid().setGrid(this.nbBombs);

		notifyView();
	}
	

	//Creer une liste de joueur
	private List<Player> setJoueur(int nbJoueur) {
		players = new ArrayList<>();
		for (int i = 0; i < nbJoueur; i++) {
			players.add(new Player("P"+((Integer)(i+1)).toString()));
		}
		//Si un seul joueur => mode solo
		if(players.size()==1){
			this.mode = Mode.Solo;
		} else {
			this.mode = Mode.Multi;
		}
		currentPlayer = players.get(0);
		return players;
	}

	public Grid2D getGrid() {
		return grid;
	}

	public int getNbBombs() {
		return nbBombs;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public void looser() {
		if (this.status == Status.Playing) {
			this.status = Status.Loose;
			this.getGrid().showBomb();
			notifyView();
		}
	}

	public void winner() {
		if (this.status == Status.Playing) {
			if (this.mode == Mode.Solo) {
				this.status = Status.Win;

			} else {
				//Si les joueurs sont égalité, la partie est "perdu" pour chacun d'eux
				if (getWinner() == null) {
					status = Status.Loose;
					
				//Sinon un joueur a plus de point, la partie est donc gagner pour lui
				} else {
					status = Status.Win;
				}
			}
			notifyView();
		}
	}

	//Retourne le joueur avec le plus haut score, null si égalité
	public Player getWinner() {
		int score = 0;
		Player winner = currentPlayer;
		for (Player player : players) {
			if (player.getScore() > score) {
				winner = player;
			} else if (player.getScore() == score && player != winner) {
				winner = null;
			}
		}
		return winner;
	}

	public Player getCurrentPlayer() {
		return currentPlayer;
	}

	public Mode getMode() {
		return mode;
	}

	public void notifyView() {
		setChanged();
		notifyObservers();
	}

	public int getNbFlags() {
		return nbFlags;
	}

	public void setNbFlags(int nbFlags) {
		this.nbFlags = nbFlags;
	}

	public int getNbClicked() {
		return nbClicked;
	}

	public void setNbClicked(int nbClicked) {
		this.nbClicked = nbClicked;
	}

	//Cache les cases et remet les compteurs a zéro
	public void playAgain() {
		getGrid().hideAll();
		nbClicked = 0;
		nbFlags = 0;
		setStatus(Status.Playing);
		if (this.mode == Mode.Multi) {
			for (Player player : players) {
				player.setScore(0);
			}
		}
	}

	//Méthode déclenché lorsqu'une mine est découverte
	public void gotBomb() {
		
		//Si la partie est en mode solo, le joueur perd
		if (this.mode == Mode.Solo) {
			looser();
			
		//Si la partie est en mode multi, le joueur courant gagne un point
		} else {
			currentPlayer.addScore();
			nbClicked++;
			nbFlags = nbClicked;
			notifyView();

			//Si le nombre de mine trouvé est égal au nombre de mine dans le jeu, la partie est terminé
			if (nbClicked == nbBombs) {
				winner();
			}
		}
	}

	//Méthode déclaenché lorsqu'une case vide est découverte
	public void gotEmpty() {
		
		//En mode solo, on compte le nombre de case découverte
		if (this.mode == Mode.Solo) {
			nbClicked++;
			
			//S'il atteinds le nombre de case vide alors victoire !
			if (nbClicked == nbCase) {
				winner();
			}
			
		//en mode multi, le joueur courant a terminé, on passe au suivant
		} else {
			swapPlayer();
			notifyView();
		}
	}

	//Change le joueur courant par le suivant dans la liste
	private void swapPlayer() {
		int i = players.indexOf(currentPlayer) + 1;
		if (i >= players.size()) {
			i = 0;
		}
		currentPlayer = players.get(i);
	}

	public String getText() {
		if( this.mode == Mode.Solo){
			return ((Integer) (this.getNbBombs() - this.getNbFlags())).toString();
		} else {
			return ((Integer) (this.getNbBombs() - this.getNbFlags())).toString() +"  "+this.getCurrentPlayer().getName()
					+ " : "
					+ ((Integer) this.getCurrentPlayer()
							.getScore()).toString();
		}
	}
}
