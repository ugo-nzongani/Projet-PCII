package model;
import view.Affichage;

/**Classe utilisée pour la représentation du modèle*/
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
		if(this.getHauteur() - this.saut > 0){
			this.hauteur -= this.saut;
		} else {
			/**Si la hauteur dépasse les bornes de la fenêtre, ne rien faire*/
		}
	}
	
	/**Méthode utilisée pour modifier la hauteur de l'ovale vers le bas en restant dans le cadre de la fenêtre*/
	public void moveDown() {
		if(this.getHauteur() + Affichage.LARGEUR_OVALE + this.saut < Affichage.HAUTEUR_FENETRE) {
			this.hauteur += SAUT_DOWN;
		} else {
			/**Si la hauteur dépasse les bornes de la fenêtre, ne rien faire*/
		}
	}
}
