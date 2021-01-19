package model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;
import view.Affichage;

/**Classe utilisée pour la construction de la ligne brisée*/
public class Parcours {
	
	public static final Random rand = new Random();
	
	/**Constantes*/
	
	/**Longueur minimale d'une "sous-ligne" de la ligne brisée le long de l'axe des abscisses*/
	public static final int TAILLE_MIN = 10;
	/**Longueur maximale d'une "sous-ligne" de la ligne brisée le long de l'axe des abscisses*/
	public static final int TAILLE_MAX = 120;
	/**Valeur de l'incrémentation de l'attribut position*/
	public static final int SAUT_POSITION = 2;
	
	/**Attributs*/
	
	/**ArrayList contenant les points constituants la ligne brisée*/
	public ArrayList<Point> pointList;
	/**Entier représentant le score du joueur*/
	public int position = 0;
	/**
	 * Valeur de l'itinéraire en cours représentée par un entier:
	 * 0: la ligne est entrain de descendre
	 * 1: la ligne est entrain de monter
	 * 2: la ligne est entrain de stagner
	 */
	public int oldItineraire = 0;
	/**Valeur de l'abscisse du point initialisée au centre de l'ovale*/
	public int pointX = Affichage.X + Affichage.LARGEUR_OVALE/2;
	/**Valeur de l'ordonnée du point initialisée au centre de l'ovale*/
	public int pointY = Affichage.y + Affichage.HAUTEUR_OVALE/2;;
	/**Taille de la "sous-ligne" dont le traitement est en cours*/
	public int taille = 0;
	/**Compteur permettant de savoir où en est le traitement de la "sous-ligne"*/
	public int cpt = 0;
	
	/**Constructeur*/
	public Parcours() {
		this.pointList = new ArrayList<Point>();
		this.genereLigne(true);
	}
	
	/**Méthode utilisée pour générer la ligne brisée
	 * Le paramètre first vaut true lorsque la méthode est appelée à la création de la ligne
	 * */
	public void genereLigne(boolean first) {
		if(first) {
			/**On choisit si on monte/descend, on choisit de ne pas pouvoir stagner au début*/
			/**On tire pour celà un entier entre 0 et 1*/
			this.oldItineraire = rand.nextInt(2);
			this.taille = rand.nextInt((TAILLE_MAX - TAILLE_MIN) + 1) + TAILLE_MIN;
		} else if(this.cpt == 0) {
			this.nouvelItineraire();
		}
		/**On continue de générer la ligne*/		
		for(int i = 0; i < 120; i++) {
			if(this.cpt == this.taille) { /**La "sous-ligne" est tracée, on en trace une nouvelle avec un itinéraire différent*/
				this.nouvelItineraire();
				this.cpt = 0;
			} else { /**La "sous-ligne" n'est pas fini*/
				/**On regarde l'itinéraire actuel*/
				if(this.oldItineraire == 0) { /**On est entrain de descendre*/
					/**On vérifie les bornes pour le prochain déplacement, ici le bas de la fenêtre
					 * tel que l'ovale puisse passer sur la ligne le plus bas possible dans la fenêtre
					 * sans en sortir	 
					 * */
					if(this.pointY + 1 > Affichage.HAUTEUR_FENETRE - Affichage.HAUTEUR_OVALE) {
						/**On soustrait 1 car cpt est incrémenté à la fin de la boucle, donc cpt == taille à la fin de la boucle si ici cpt == taille - 1*/
						this.cpt = this.taille - 1;
					} else {
						this.pointY++;
					}
				} else if(this.oldItineraire == 1) { /**On est entrain de monter*/
					/**On vérifie les bornes pour le prochain déplacement, ici le haut de la fenêtre
					 * tel que l'ovale puisse passer sur la ligne le plus haut possible dans la fenêtre
					 * sans en sortir					 * 
					 * */
					if(this.pointY - 1 < Affichage.HAUTEUR_OVALE) {
						/**On soustrait 1 car cpt est incrémenté à la fin de la boucle, donc cpt == taille à la fin de la boucle si ici cpt == taille - 1*/
						this.cpt = this.taille - 1;
					} else {
						this.pointY--;
					}
				} else { /**On est entrain de stagner*/
					/**On ne modifie rien*/
				}
			}
			this.pointX++;
			this.cpt++;
			/**Ajout du point à l'ArrayList*/
			this.pointList.add(new Point(this.pointX, this.pointY));
		}
	}
	
	/**Méthode utilisée dans genereLigne pour faire le choix d'un nouvel itinéraire, qui peut être soit: monter, descendre ou stagner
	 * et pour calculer la taille de la prochaine "sous-ligne"*/
	public void nouvelItineraire() {
		/**On tire aléatoirement 0 ou 1*/
		int tirage = rand.nextInt(2);
		if(this.oldItineraire == 1) { /**Si on montait, alors soit on descend soit un stagne*/
			if(tirage == 1) { /**Si on stagne*/
				this.oldItineraire = 2;
			} else { /**Si on descend*/
				this.oldItineraire = 0;
			}	
		} else if(this.oldItineraire == 0) { /**Si on descendait, alors soit on monte soit un stagne*/
			if(tirage == 0) { /**Si on stagne*/
				this.oldItineraire = 2;
			} else { /**Si on monte*/
				this.oldItineraire = 1;
			}				
		} else { /**Si on stagnait, alors soit on monte soit un descend*/
			if(tirage == 0) { /**Si on descend*/
				this.oldItineraire = 0;
			} else if(tirage == 1){ /**Si on monte*/
				this.oldItineraire = 1;
			}
		}
		/**Calcul de la taille de la "sous-ligne"*/
		this.taille = rand.nextInt((TAILLE_MAX - TAILLE_MIN) + 1) + TAILLE_MIN;
	}
	
	/**Méthode utilisée pour obtenir le parcours à suivre*/
	public ArrayList<Point> getParcours() {	
		ArrayList<Point> l = new ArrayList<Point>();
		for(int i = 0; i < this.pointList.size(); i++) {
			Point p = this.pointList.get(i);
			if(p.getX() >= this.position) {
				l.add(new Point(p.x - this.position, p.y));
			}
		}
		this.genereLigne(false);
		return l;
	}
	
	/**Méthode utilisée pour renvoyer la position du joueur*/
	public int getPosition() {
		return this.position;
	}
	
	/**Méthode utilisée pour incrémenter la position du joueur de la valeur sautPosition*/
	public void incrPosition() {
		this.position += SAUT_POSITION;
	}
}
