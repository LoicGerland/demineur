package modele;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Grid2DTriangle extends Grid {

	public Grid2DTriangle(int y, Game game) {
		super((2 * y) - 1, y, game);
	}

	public List<CaseModele> getVoisin(CaseModele caseMod) {
		Point currentPoint = this.getMap().get(caseMod);
		List<Point> pointsVoisin = new ArrayList<Point>();
		if (this.getHeight() % 2 == 0) {
			if ((currentPoint.getX() + currentPoint.getY()) % 2 == 1) {
				pointsVoisin = getUp(currentPoint);
			} else {
				pointsVoisin = getDown(currentPoint);
			}
		} else if (this.getHeight() % 2 == 1) {
			if ((currentPoint.getX() + currentPoint.getY()) % 2 == 1) {
				pointsVoisin = getDown(currentPoint);
			} else {
				pointsVoisin = getUp(currentPoint);
			}
		}
		List<CaseModele> caseVoisin = new ArrayList<>();
		for (Point point : pointsVoisin) {
			if (this.getCaseMod(point) != null) {
				caseVoisin.add(getCaseMod(point));
			}
		}
		return caseVoisin;
	}

	private CaseModele getCaseMod(Point point) {
		return this.getGrid()[point.getX()][point.getY()];
	}

	private List<Point> getDown(Point p) {
		List<Point> voisin = new ArrayList<>();

		if (p.getX() - 1 >= 0 && p.getY() - 1 >= 0)
			voisin.add(new Point(p.getX() - 1, p.getY() - 1));

		if (p.getY() - 1 >= 0)
			voisin.add(new Point(p.getX(), p.getY() - 1));

		if (p.getX() + 1 < this.getWidth() && p.getY() - 1 >= 0)
			voisin.add(new Point(p.getX() + 1, p.getY() - 1));

		if (p.getX() - 1 >= 0)
			voisin.add(new Point(p.getX() - 1, p.getY()));

		if (p.getX() + 1 < this.getWidth())
			voisin.add(new Point(p.getX() + 1, p.getY()));

		if (p.getY() + 1 < this.getHeight())
			voisin.add(new Point(p.getX(), p.getY() + 1));

		return voisin;
	}

	private List<Point> getUp(Point p) {

		List<Point> voisin = new ArrayList<>();

		if (p.getX() - 1 >= 0 && p.getY() + 1 < this.getHeight())
			voisin.add(new Point(p.getX() - 1, p.getY() + 1));

		if (p.getY() + 1 < this.getHeight())
			voisin.add(new Point(p.getX(), p.getY() + 1));

		if (p.getX() + 1 < this.getWidth() && p.getY() + 1 < this.getHeight())
			voisin.add(new Point(p.getX() + 1, p.getY() + 1));

		if (p.getX() - 1 >= 0)
			voisin.add(new Point(p.getX() - 1, p.getY()));

		if (p.getX() + 1 < this.getWidth())
			voisin.add(new Point(p.getX() + 1, p.getY()));

		if (p.getY() - 1 >= 0)
			voisin.add(new Point(p.getX(), p.getY() - 1));
		return voisin;
	}

	public void checkGame(CaseModele caseMod) {
		// TODO Auto-generated method stub

	}

	public void showBomb() {
		// TODO Auto-generated method stub

	}

	public void setGrid(int nbBombs) {
		int nb = nbBombs;
		Random r = new Random();

		for (int y = 0; y < this.getHeight(); y++) {
			for (int x = 0; x < this.getWidth(); x++) {
				if (x + y >= this.getHeight() - 1
						&& x < this.getWidth() - (this.getHeight() - (y + 1))) {
					if (nb > 0
							&& r.nextInt(this.getHeight() * this.getWidth()) < nbBombs) {
						this.setCase(new CaseModele(Type.Mine, this), x, y);
						nb--;
					} else {
						this.setCase(new CaseModele(Type.Empty, this), x, y);
					}
				} else {
					this.getGrid()[x][y] = null;
				}
			}
		}
		if (nb > 0) {
			while (nb > 0) {
				int y = r.nextInt(this.getHeight());
				int valeurMin = this.getHeight() - (y + 1);
				int valeurMax = this.getWidth() - (this.getHeight() - (y + 1));
				int x = valeurMin + r.nextInt(valeurMax - valeurMin);
				if (this.getGrid()[x][y].getType() != Type.Mine) {
					this.getGrid()[x][y].setType(Type.Mine);
					nb--;
				}
			}
		}
		System.out.println(this.toString());
	}

	public void hideAll() {
		// TODO Auto-generated method stub

	}

	public void showAll() {
		// TODO Auto-generated method stub

	}

	public String toString() {
		String grid = "";
		for (int y = 0; y < this.getHeight(); y++) {
			for (int x = 0; x < this.getWidth(); x++) {
				if (this.getGrid()[x][y] == null) {
					grid += "0";
				} else if (this.getGrid()[x][y].getType() == Type.Empty) {
					grid += "1";
				} else if (this.getGrid()[x][y].getType() == Type.Mine) {
					grid += "2";
				}
			}
			grid += "\n";
		}
		return grid;
	}

	@Override
	public void checkGame1P(CaseModele caseMod) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void checkGame2P(CaseModele caseMod) {
		// TODO Auto-generated method stub
		
	}
}
