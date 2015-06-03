package modele;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Grid2DRectangle extends Grid {

	public Grid2DRectangle(int x, int y, Game game) {
		super(x, y, game);
	}

	public List<CaseModele> getVoisin(CaseModele caseMod) {
		Point p = this.getMap().get(caseMod);
		List<CaseModele> cases = new ArrayList<>();

		if (p.getX() - 1 >= 0 && p.getY() - 1 >= 0)
			cases.add(this.getGrid()[p.getX() - 1][p.getY() - 1]);

		if (p.getX() - 1 >= 0)
			cases.add(this.getGrid()[p.getX() - 1][p.getY()]);

		if (p.getX() - 1 >= 0 && p.getY() + 1 < this.getHeight())
			cases.add(this.getGrid()[p.getX() - 1][p.getY() + 1]);

		if (p.getY() - 1 >= 0)
			cases.add(this.getGrid()[p.getX()][p.getY() - 1]);

		if (p.getY() + 1 < this.getHeight())
			cases.add(this.getGrid()[p.getX()][p.getY() + 1]);

		if (p.getX() + 1 < this.getWidth() && p.getY() - 1 >= 0)
			cases.add(this.getGrid()[p.getX() + 1][p.getY() - 1]);

		if (p.getX() + 1 < this.getWidth())
			cases.add(this.getGrid()[p.getX() + 1][p.getY()]);

		if (p.getX() + 1 < this.getWidth() && p.getY() + 1 < this.getHeight())
			cases.add(this.getGrid()[p.getX() + 1][p.getY() + 1]);

		return cases;
	}

	public void checkGame() {
		if (this.getGame().getStatus() == Status.Playing) {
			int nbEmpty = 0;
			int nbFlag = 0;
			for (int i = 0; i < this.getWidth(); i++) {
				for (int j = 0; j < this.getHeight(); j++) {
					CaseModele currentCase = this.getGrid()[i][j];
					if (currentCase.getType() == Type.Mine
							&& currentCase.isClicked()) {
						this.getGame().looser();
						return;
					} else if (currentCase.getType() == Type.Empty
							&& currentCase.isClicked()) {
						nbEmpty++;
					}
					if (currentCase.isFlag()) {
						nbFlag++;
					}
				}
			}
			if (nbEmpty == this.getHeight() * this.getWidth()
					- this.getGame().getNbBombs()) {
				this.getGame().winner();
				return;
			}
			this.getGame().setNbFlags(nbFlag);
			this.getGame().notifyView();
		}
	}

	public void showBomb() {
		for (int i = 0; i < this.getWidth(); i++) {
			for (int j = 0; j < this.getHeight(); j++) {
				if (this.getGrid()[i][j].getType() == Type.Mine) {
					if (this.getGrid()[i][j].isFlag()) {
						this.getGrid()[i][j].setFlag();
					}
					this.getGrid()[i][j].setClicked();
				}
			}
		}
	}

	public void showAll() {
		for (int i = 0; i < this.getWidth(); i++) {
			for (int j = 0; j < this.getHeight(); j++) {
				this.getGrid()[i][j].setClicked();
			}
		}
	}

	public void setGrid(int nbBombs) {
		Random r = new Random();
		int nb = nbBombs;

		for (int i = 0; i < this.getWidth(); i++) {
			for (int j = 0; j < this.getHeight(); j++) {
				if (nb > 0
						&& r.nextInt(this.getHeight() * this.getWidth()) < nbBombs) {
					this.setCase(new CaseModele(Type.Mine, this), i, j);
					nb--;
				} else {
					this.setCase(new CaseModele(Type.Empty, this), i, j);
				}
			}
		}

		if (nb > 0) {
			while (nb > 0) {
				int i = r.nextInt(this.getWidth());
				int j = r.nextInt(this.getHeight());
				if (this.getGrid()[i][j].getType() != Type.Mine) {
					this.getGrid()[i][j].setType(Type.Mine);
					nb--;
				}
			}
		}
	}

	public void hideAll() {
		for (int i = 0; i < this.getWidth(); i++) {
			for (int j = 0; j < this.getHeight(); j++) {
				this.getGrid()[i][j].resetFlag();
				this.getGrid()[i][j].resetClick();
				this.getGrid()[i][j].notifyCase();
			}
		}
	}
}
