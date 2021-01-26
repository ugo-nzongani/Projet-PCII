package model;

/**Classe utilisée pour faire avancer la ligne brisée*/
public class Avancer extends Thread {
	
	/**Attributs*/
	public Etat etat;
	
	/**Constructeur*/
	public Avancer(Etat e) {
		this.etat = e;
	}
	
	/**Méthode utilisée pour faire avancer progressivement la ligne brisée*/
	public void run() {
		/**Boucle infinie*/
		while(this.etat.running) {
			/**Traitement du thread*/
			this.etat.parcours.incrPosition();
			this.etat.affichage.repaint();
			try {
				Thread.sleep(80);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}	
		}
	}
}