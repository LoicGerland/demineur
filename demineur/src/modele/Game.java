package modele;

import java.util.Random;

public class Game {
	
	private Grid2D grid;
	
	private Player[] players;
	
	private int nbBombs;
		
	public Game(int x, int y,int nb, Player[] players){
		this.grid = new Grid2D(x,y);
		this.players = players;
		this.nbBombs = nb;
		
		setGrid();
	}

	public Player[] getPlayers() {
		return players;
	}

	public Grid2D getGrid() {
		return grid;
	}

	public int getNbBombs() {
		return nbBombs;
	}
	
	public void setGrid()
	{
		Random r = new Random();
		int nb = this.nbBombs;
		
		for(int i = 0; i < this.getGrid().getWidth(); i++){
			for(int j = 0; j < this.getGrid().getHeight(); j++){
				if(nb > 0 && r.nextInt(this.getGrid().getHeight()*this.getGrid().getWidth()) < this.nbBombs){
					this.getGrid().getMap().put(new Point(i,j), new CaseModele(Type.Mine));
					nb--;
				}
				else this.getGrid().getMap().put(new Point(i,j), new CaseModele(Type.Empty));
			}
		}
		
		if(nb >0){
			while(nb > 0){
				int x = r.nextInt(this.getGrid().getWidth());
				int y =	r.nextInt(this.getGrid().getHeight());
				if(this.getGrid().getMap().get(new Point(x,y)).getType() != Type.Mine){
					this.getGrid().getMap().get(new Point(x,y)).setType(Type.Mine);
					nb--;						
				}
			}
		}
	}
}
