package model;

/**Classe utilis�e pour faire avancer la ligne bris�e*/
public class Avancer extends Thread {
	
	/**Attributs*/
	public Etat etat;
	
	/**Constructeur*/
	public Avancer(Etat e) {
		this.etat = e;
	}
	
	/**M�thode utilis�e pour faire avancer progressivement la ligne bris�e*/
	public void run() {
		/**Boucle infinie*/
		while(this.etat.running) {
			/**Traitement du thread*/
			this.etat.parcours.incrPosition();
			this.etat.affichage.repaint();
			/**On test s'il y a eu collision entre l'ovale et la ligne*/
			this.etat.stopGame();
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}	
		}
	}
}