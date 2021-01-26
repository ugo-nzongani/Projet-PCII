package model;
import java.awt.Point;
import javax.swing.JOptionPane;
import view.Affichage;

/**Classe utilis�e pour la repr�sentation du mod�le*/
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
	
	/**M�thode utilis�e pour li�e l'affichage et l'�tat*/
	public void addAffichage(Affichage a) {
		this.affichage = a;
	}
	
	/**M�thode utilis�e pour renvoyer l'attribut hauteur*/
	public int getHauteur() {
		return this.hauteur;
	}
	
	/**M�thode utilis�e pour modifier la hauteur de l'ovale vers le haut en restant dans le cadre de la fen�tre*/
	public void jump() {
		if(this.getHauteur() - this.saut > 0 && this.running){
			this.hauteur -= this.saut;
		} else {
			/**Si la hauteur d�passe les bornes de la fen�tre, ne rien faire*/
		}
		/**On v�rifie si la partie est perdue*/
		this.stopGame();
	}
	
	/**M�thode utilis�e pour modifier la hauteur de l'ovale vers le bas en restant dans le cadre de la fen�tre*/
	public void moveDown() {
		if(this.getHauteur() + Affichage.LARGEUR_OVALE + this.saut < Affichage.HAUTEUR_FENETRE) {
			this.hauteur += SAUT_DOWN;
		} else {
			/**Si la hauteur d�passe les bornes de la fen�tre, ne rien faire*/
		}
		/**On v�rifie si la partie est perdue*/
		this.stopGame();
	}
	
	/**M�thode utilis�e pour v�rifier si l'ovale est sorti de la ligne**/
	public boolean testPerdu() {
		Point p1 = this.parcours.getParcours().get(0);
		Point p2 = this.parcours.getParcours().get(1);
		/**Calcul de la pente*/
		float pente = (p2.y - p1.y) / (float)(p2.x - p1.x);
		/**R�cup�ration de l'ordonn�e du point prochain point invisible sur la fen�tre*/
		float y = (p1.y - pente * (p1.x));
		/**Ordonn�e minimale de l'ovale (ordonn�e du point le plus haut de l'ovale dans la fen�tre*/
		int yMin_ovale = this.hauteur;
		/**Ordonn�e maximale de l'ovale (ordonn�e du point le plus bas de l'ovale dans la fen�tre*/
		int yMax_ovale = this.hauteur + Affichage.HAUTEUR_OVALE;
		/**R�cup�ration de l'ordonn�e du point de la ligne correspondant � la position de l'ovale*/
		float y_shift = y + (pente * (Affichage.X + Affichage.LARGEUR_OVALE/2));
		return y_shift <= yMin_ovale || y_shift >= yMax_ovale;
	}
	
	/**M�thode utilis�e pour stopper le jeu*/
	public void stopGame() {
		if(this.testPerdu()){
			this.running = false;
			JOptionPane.showMessageDialog(null, "D�faite\n"+"Score: "+this.parcours.getPosition(), "Perdu", JOptionPane.PLAIN_MESSAGE);
		}
	}
}
