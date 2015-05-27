package modele;

import java.util.ArrayList;
import java.util.List;

public class Grid2D extends Grid {

	
	public Grid2D(int x, int y) {
		super(x,y);
	}
	
	public List<CaseModele> getVoisin(CaseModele caseMod) {
		Point p = this.getMap().get(caseMod);
		List<CaseModele> cases = new ArrayList<>();
		if (p.getX()-1>=0) cases.add(this.getGrid()[p.getX()-1][p.getY()]);
		
		if (p.getX()+1<this.getWidth()) cases.add(this.getGrid()[p.getX()+1][p.getY()]);
		
		if (p.getY()-1>=0) cases.add(this.getGrid()[p.getX()][p.getY()-1]);
		
		if (p.getY()+1<this.getHeight()) cases.add(this.getGrid()[p.getX()][p.getY()+1]);
		
		if (p.getX()+1<this.getWidth() && p.getY()+1<this.getHeight()) cases.add(this.getGrid()[p.getX()+1][p.getY()+1]);
		
		if (p.getX()+1<this.getWidth() && p.getY()-1>=0) cases.add(this.getGrid()[p.getX()+1][p.getY()-1]);
		
		if (p.getX()-1>=0 && p.getY()+1<this.getHeight()) cases.add(this.getGrid()[p.getX()-1][p.getY()+1]);
		
		if (p.getX()-1>=0 && p.getY()-1>=0) cases.add(this.getGrid()[p.getX()-1][p.getY()-1]);
		
		return cases;
	}

}
