package modele;

import java.util.ArrayList;
import java.util.List;

public class Grid2D extends Grid {

	public Grid2D(int x, int y, Game game) {
		super(x, y, game);
	}

	public List<CaseModele> getVoisin(CaseModele caseMod) {
		Point p = this.getMap().get(caseMod);
		List<CaseModele> cases = new ArrayList<>();
		if (p.getX() - 1 >= 0)
			cases.add(this.getGrid()[p.getX() - 1][p.getY()]);

		if (p.getX() + 1 < this.getWidth())
			cases.add(this.getGrid()[p.getX() + 1][p.getY()]);

		if (p.getY() - 1 >= 0)
			cases.add(this.getGrid()[p.getX()][p.getY() - 1]);

		if (p.getY() + 1 < this.getHeight())
			cases.add(this.getGrid()[p.getX()][p.getY() + 1]);

		if (p.getX() + 1 < this.getWidth() && p.getY() + 1 < this.getHeight())
			cases.add(this.getGrid()[p.getX() + 1][p.getY() + 1]);

		if (p.getX() + 1 < this.getWidth() && p.getY() - 1 >= 0)
			cases.add(this.getGrid()[p.getX() + 1][p.getY() - 1]);

		if (p.getX() - 1 >= 0 && p.getY() + 1 < this.getHeight())
			cases.add(this.getGrid()[p.getX() - 1][p.getY() + 1]);

		if (p.getX() - 1 >= 0 && p.getY() - 1 >= 0)
			cases.add(this.getGrid()[p.getX() - 1][p.getY() - 1]);

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
					if(currentCase.isFlag()){
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
}
