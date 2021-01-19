package model;

/**Classe utilisée pour modifier la position de l'ovale vers le bas en l'abscence de clic*/
public class Voler extends Thread {
	
	/**Attributs*/
	public Etat etat;
	
	/**Constructeur*/
	public Voler(Etat e) {
		this.etat = e;
	}
	
	/**Méthode utilisée pour modifier progressivement la valeur de la hauteur de l'ovale vers le bas*/
	public void run() {
		/**Boucle infinie*/
		while(true) {
			/**Traitement du thread*/
			this.etat.moveDown();
			this.etat.affichage.repaint();
			try {
				this.etat.affichage.revalidate();
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}	
		}
	}
}
