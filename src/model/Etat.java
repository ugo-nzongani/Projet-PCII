package model;
import java.awt.Point;
import javax.swing.JOptionPane;
import view.Affichage;

/**Classe utilisée pour la représentation du modèle*/
public class Etat {
	/**Constantes*/
	
	/**Valeur de la modification de la hauteur de l'ovale vers le haut*/
	public static final int SAUT_UP = 6;
	/**Valeur de la modification de la hauteur de l'ovale vers le bas*/
	public static final int SAUT_DOWN = 2;
		
	/**Attributs*/
	public boolean running = true;
	public int hauteur;
	public int saut;
	public Affichage affichage;
	public Parcours parcours;
	
	/**Constructeur*/
	public Etat() {
		this.hauteur = Affichage.y;
		this.saut = SAUT_UP;
		this.parcours = new Parcours();
	}
	
	/**Méthode utilisée pour liée l'affichage et l'état*/
	public void addAffichage(Affichage a) {
		this.affichage = a;
	}
	
	/**Méthode utilisée pour renvoyer l'attribut hauteur*/
	public int getHauteur() {
		return this.hauteur;
	}
	
	/**Méthode utilisée pour modifier la hauteur de l'ovale vers le haut en restant dans le cadre de la fenêtre*/
	public void jump() {
		if(this.getHauteur() - this.saut > 0 && this.running){
			this.hauteur -= this.saut;
		} else {
			/**Si la hauteur dépasse les bornes de la fenêtre, ne rien faire*/
		}
		/**On vérifie si la partie est perdue*/
		this.stopGame();
	}
	
	/**Méthode utilisée pour modifier la hauteur de l'ovale vers le bas en restant dans le cadre de la fenêtre*/
	public void moveDown() {
		if(this.getHauteur() + Affichage.LARGEUR_OVALE + this.saut < Affichage.HAUTEUR_FENETRE) {
			this.hauteur += SAUT_DOWN;
		} else {
			/**Si la hauteur dépasse les bornes de la fenêtre, ne rien faire*/
		}
		/**On vérifie si la partie est perdue*/
		this.stopGame();
	}
	
	/**Méthode utilisée pour vérifier si l'ovale est sorti de la ligne**/
	public boolean testPerdu() {
		Point p1 = this.parcours.getParcours().get(0);
		Point p2 = this.parcours.getParcours().get(1);
		/**Calcul de la pente*/
		float pente = (p2.y - p1.y) / (float)(p2.x - p1.x);
		/**Récupération de l'ordonnée du point prochain point invisible sur la fenêtre*/
		float y = (p1.y - pente * (p1.x));
		/**Ordonnée minimale de l'ovale (ordonnée du point le plus haut de l'ovale dans la fenêtre*/
		int yMin_ovale = this.hauteur;
		/**Ordonnée maximale de l'ovale (ordonnée du point le plus bas de l'ovale dans la fenêtre*/
		int yMax_ovale = this.hauteur + Affichage.HAUTEUR_OVALE;
		/**Récupération de l'ordonnée du point de la ligne correspondant à la position de l'ovale*/
		float y_shift = y + (pente * (Affichage.X + Affichage.LARGEUR_OVALE/2));
		return y_shift <= yMin_ovale || y_shift >= yMax_ovale;
	}
	
	/**Méthode utilisée pour stopper le jeu*/
	public void stopGame() {
		if(this.testPerdu()){
			this.running = false;
			JOptionPane.showMessageDialog(null, "Défaite\n"+"Score: "+this.parcours.getPosition(), "Perdu", JOptionPane.PLAIN_MESSAGE);
		}
	}
}
