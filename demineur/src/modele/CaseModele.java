package modele;

import java.util.List;
import java.util.Observable;

public class CaseModele extends Observable {

	private int value;

	private Type type;

	private boolean flag;

	private boolean clicked;

	private Grid2D grid;

	private static boolean firstCase = true;

	public CaseModele(Type t, Grid2D grid) {
		this.value = 0;
		this.type = t;
		this.flag = false;
		this.clicked = false;
		this.grid = grid;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	// D�pose un drapeau sur la case s�lectionner, ce qu'il l'empeche d'�tre
	// jouer
	public void hasFlag() {
		// Le drapeau ne peut etre poser que lors du mode de jeu en solo
		if (this.getGrid().getGame().getMode() == Mode.Solo) {
			if (!this.clicked) {
				this.setFlag(!this.isFlag());
				if (this.flag == false) {
					this.getGrid().removeFlag();
				} else {
					this.getGrid().putFlag();
				}
				notifyCase();
			}
		}
	}

	public boolean isClicked() {
		return clicked;
	}

	public void setClicked(boolean clicked) {
		this.clicked = clicked;
	}

	// M�thode lanc�e lorsque le joueur clique sur une case
	public void hasClicked() {
		if (firstCase) {
			if (this.getType() == Type.Mine) {
				this.moveBomb();
			}
			firstCase = false;
		}
		if (!this.clicked && !this.flag) {
			playCase();

			// Notifie � Grid qu'un joueur a cliqu� sur une case vide dans le
			// cas d'une partie en multijoueur
			if (this.getGrid().getGame().getMode() == Mode.Multi) {
				notifyGrid();
			}
		}
	}

	// Remonte a Grid la case d�couverte
	private void notifyGrid() {
		if (this.type == Type.Mine) {
			grid.gotBomb();
		} else {
			grid.gotEmpty();
		}
	}

	public Grid2D getGrid() {
		return this.grid;
	}

	// M�thode qui g�re les differents cas de case
	public void playCase() {
		if (!this.flag && !this.clicked) {
			this.setClicked(true);

			// Remonte a la grille une bombe d�couverte
			if (this.type == Type.Mine) {
				notifyCase();

				// Sinon calcule la valeur de la case en fonction du nombre de
				// bombes autour de celle-ci
			} else {
				this.setValue(0);
				for (CaseModele voisin : grid.getVoisin(this)) {
					if (voisin.getType() == Type.Mine) {
						this.setValue(this.getValue() + 1);
					}
				}
				notifyCase();

				// Propage l'appel � ces voisin si la valeur est nulle
				if (this.getValue() <= 0) {
					for (CaseModele voisin : grid.getVoisin(this)) {
						voisin.playCase();
					}
				}
			}
			// Si la partie est en mode solo, on remonte chaque case vide trouv�
			if (this.getGrid().getGame().getMode() == Mode.Solo) {
				notifyGrid();
			}
		}
	}

	// Echange la bombe de position avec un de ces voisins
	private void moveBomb() {
		if (this.type == Type.Mine) {
			this.setType(Type.Empty);
			List<CaseModele> voisins = grid.getVoisin(this);
			for (CaseModele voisin : voisins) {
				if (voisin.getType() == Type.Empty) {
					voisin.setType(Type.Mine);
					return;
				}
			}

			// Si aucune case vide autour, remet la mine a sa place
			this.setType(Type.Mine);
		}
	}

	//M�thode lanc� lors d'un double clique sur une case
	public void hasDoubleClick() {
		//M�thode disponible seulement lors d'une partie en solo
		if (this.getGrid().getGame().getMode() == Mode.Solo) {
			//Joue toutes les cases voisines sans flag
			for (CaseModele voisin : grid.getVoisin(this)) {
				if (!voisin.isClicked()
						&& this.grid.getGame().getStatus() == Status.Playing) {
					voisin.playCase();
					
					//Si une mine est jou�, on stoppe la m�thode, le joueur a perdu
					if (voisin.getType() == Type.Mine && !voisin.isFlag())
						return;
				}
			}
		}
	}

	public void notifyCase() {
		setChanged();
		notifyObservers();
	}

	public void resetFlag() {
		this.flag = false;
	}

	public void resetClick() {
		this.clicked = false;
	}

	public static void resetFirstCase() {
		CaseModele.firstCase = true;
	}

	public static void setFirstCase(boolean firstCase) {
		CaseModele.firstCase = firstCase;
	}
}
