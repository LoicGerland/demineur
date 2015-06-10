package modele;

import java.util.Observable;

public class Chrono extends Observable implements Runnable {
	private Thread deroulement;
	private long tempsEcoule = 0; // exprime en millisecondes
	private long momentDebut = 0;
	private long momentSuspension;
	private boolean continuer;
	private boolean finir;

	public Chrono() {
	}

	/* Demarre le chronometre */
	public void demarrer() {
		if (enFonctionnement()) {
			arreter();
			try {
				deroulement.join();
			} catch (InterruptedException exc) {
				exc.printStackTrace();
			}
		}
		deroulement = new Thread(this);
		deroulement.start();
	}

	/*
	 * Suspend le deroulement du temps ; ce deroulement pourra etre repris dans
	 * l'etat ou il se trouvait par la methode reprendre
	 */
	public void suspendre() {
		if (enFonctionnement() && continuer) {
			momentSuspension = System.currentTimeMillis();
			continuer = false;
		}
	}

	/*
	 * Si le chronometre est en fonctionnment mais a ete suspendu, il recommence
	 * a tourne r
	 */
	public synchronized void reprendre() {
		if (enFonctionnement() && !continuer) {
			momentDebut += System.currentTimeMillis() - momentSuspension;
			continuer = true;
			notifyAll();

		}
	}

	/*
	 * Arrete le chronometre. Une fois arrete, le chronometre ne peut repartir
	 * qu'avec la methode demarrer, au debut du décompte du temps
	 */
	public synchronized void arreter() {
		if (enFonctionnement()) {
			finir = true;
			notifyAll();
		}
	}

	/* Fait tourner le chronometre */
	public void run() {
		Thread.currentThread().setPriority(Thread.MIN_PRIORITY);
		continuer = true;
		finir = false;
		momentDebut = System.currentTimeMillis();
		while (!finir) {
			setTempsEcoule(System.currentTimeMillis() - momentDebut);
			try {
				Thread.sleep(1000);
				synchronized (this) {
					while (!continuer && !finir)
						wait();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				notifyGame();
			}
		}
	}

	private void notifyGame() {
		setChanged();
		notifyObservers();
	}

	/*
	 * Retourne true si le chronometre est en fonctionnement, eventuellement
	 * suspendu et false si le chronometre n'est pas demarre, ou bien a ete
	 * arrete, ou bien a fini de tourner
	 */
	public boolean enFonctionnement() {
		return (deroulement != null) && (deroulement.isAlive());
	}

	public long getTempsEcoule() {
		return tempsEcoule;
	}

	public void setTempsEcoule(long tempsEcoule) {
		this.tempsEcoule = tempsEcoule;
	}

	public String toString(){
		int seconde = (int) (tempsEcoule/1000);
		int minute = seconde/60;
		seconde = seconde % 60;
		
		String min;
		String sec;
		
		if (minute < 10){
			min = "0"+((Integer)minute).toString();
		} else {
			min = ((Integer)minute).toString();
			
		}
		
		if (seconde < 10){
			sec = "0"+((Integer)seconde).toString();
		} else {
			sec = ((Integer)seconde).toString();
			
		}
		return min+":"+sec;
	}
}