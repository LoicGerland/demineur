package modele;

import java.util.Random;

public class Game {
	
	private CaseModele[][] grid;
	
	private Player[] players;
	
	private int nbBombs;
	
	private int height;
	
	private int width;
		
	public Game(int x, int y,int nb, Player[] players){
		this.width = x;
		this.height = y;
		this.grid = new CaseModele[this.width][this.height];
		this.players = players;
		this.nbBombs = nb;
		
		setGrid();
	}

	public Player[] getPlayers() {
		return players;
	}

	public CaseModele[][] getGrid() {
		return grid;
	}

	public int getNbBombs() {
		return nbBombs;
	}
	
	
	public void check8(int x, int y){
		
	}
	
	public void setGrid()
	{
		Random r = new Random();
		int nb = this.nbBombs;
		
		for(int i = 0; i < this.width; i++){
			for(int j = 0; j < this.height; j++){
				if(nb > 0 & r.nextInt(this.height*this.width) < this.nbBombs){
					this.grid[i][j] = new CaseModele(Type.Mine);
					nb--;
				}
				else this.grid[i][j] = new CaseModele(Type.Empty);
			}
		}
		
		if(nb >0){
			while(nb > 0){
				int x = r.nextInt(this.width);
				int y =	r.nextInt(this.height);
				if(this.grid[x][y].getType() != Type.Mine){
					this.grid[x][y].setType(Type.Mine);
					nb--;						
				}
			}
		}
	}
	
	public void affiche() {
		
		for(int i = 0; i < this.width; i++){
			for(int j = 0; j < this.height; j++){
				if(this.grid[i][j].getType() == Type.Mine){
					System.out.print("1\t");
				}
				else System.out.print("0\t");
			}
			System.out.print("\n");
		}
	}

}
