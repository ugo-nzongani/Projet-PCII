package model;
import view.Affichage;

/**Classe utilis�e pour la repr�sentation du mod�le*/
public class Etat {

	/**Constantes*/
	
	/**Valeur de la modification de la hauteur de l'ovale vers le haut*/
	public static final int SAUT_UP = 6;
	/**Valeur de la modification de la hauteur de l'ovale vers le bas*/
	public static final int SAUT_DOWN = 2;
		
	/**Attributs*/
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
		if(this.getHauteur() - this.saut > 0){
			this.hauteur -= this.saut;
		} else {
			/**Si la hauteur d�passe les bornes de la fen�tre, ne rien faire*/
		}
	}
	
	/**M�thode utilis�e pour modifier la hauteur de l'ovale vers le bas en restant dans le cadre de la fen�tre*/
	public void moveDown() {
		if(this.getHauteur() + Affichage.LARGEUR_OVALE + this.saut < Affichage.HAUTEUR_FENETRE) {
			this.hauteur += SAUT_DOWN;
		} else {
			/**Si la hauteur d�passe les bornes de la fen�tre, ne rien faire*/
		}
	}
}
