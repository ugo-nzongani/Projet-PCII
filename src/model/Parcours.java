package model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;
import view.Affichage;

/**Classe utilis�e pour la construction de la ligne bris�e*/
public class Parcours {
	
	public static final Random rand = new Random();
	
	/**Constantes*/

	/**Valeur de l'incr�mentation de l'attribut position*/
	public static final int SAUT_POSITION = 2;
	/**Longueur maximale d'une ligne sur l'axe des abscisses*/
	public static final int X_MAX = 200;
	/**Longueur minimale d'une ligne sur l'axe des abscisses*/
	public static final int X_MIN = 30;
	/**Ordonn�e maximale d'une ligne*/
	public static final int Y_MAX = Affichage.HAUTEUR_FENETRE - Affichage.HAUTEUR_OVALE;
	/**Ordonn�e minimale d'une ligne*/
	public static final int Y_MIN = Affichage.HAUTEUR_OVALE;
	
	/**Attributs*/
	
	/**ArrayList contenant les points constituants la ligne bris�e*/
	public ArrayList<Point> pointList;
	/**Entier repr�sentant le score du joueur*/
	public int position = 0;
	/**Valeur de l'itin�raire en cours repr�sent�e par un entier, 0 pour descendre et 1 pour monter*/
	public int oldItineraire = rand.nextInt(2);
	/**Valeur de l'abscisse du point initialis�e au centre de l'ovale*/
	public int pointX = Affichage.X + Affichage.LARGEUR_OVALE/2;
	/**Valeur de l'ordonn�e du point initialis�e au centre de l'ovale*/
	public int pointY = Affichage.y + Affichage.HAUTEUR_OVALE/2;;
	
	/**Constructeur*/
	public Parcours() {
		this.pointList = new ArrayList<Point>();
		this.pointList.add(new Point(0, this.pointY));
		this.pointList.add(new Point(Affichage.LARGEUR_FENETRE/8, this.pointY));
		this.pointX = Affichage.LARGEUR_FENETRE/8;
		this.init_pointList();
	}
	
	/**M�thode utilis�e pour renvoyer la position du joueur*/
	public int getPosition() {
		return this.position;
	}
	
	/**M�thode utilis�e pour incr�menter la position du joueur de la valeur sautPosition*/
	public void incrPosition() {
		this.position += SAUT_POSITION;
	}
	
	/**M�thode utilis�e pour g�n�rer une ligne*/
	public void createLigne() {
		int x, y;
		x = rand.nextInt((this.pointX + X_MAX - this.pointX + X_MIN) + 1) + X_MIN + this.pointX;
		float pente = 0;
		do {
			if(this.oldItineraire == 0) { /**Si on descendait*/
				y = rand.nextInt((this.pointY - Y_MIN) + 1) + Y_MIN;
			} else { /**Si on montait*/
				y = rand.nextInt((Y_MAX - this.pointY) + 1) + this.pointY;
			}
			pente = (y - this.pointY)/((float)x - (float)this.pointX);
		} while(pente > 0.7 || pente < -0.7);
		/**Modification de l'attribut oldItineraire*/
		if(this.oldItineraire == 0) {
			this.oldItineraire = 1;
		} else {
			this.oldItineraire = 0;
		}
		/**Ajout du point � l'ArrayList de Point*/
		this.pointList.add(new Point(x, y));
		/**Les attributs pointX et pointY prennent la valeur des coordonn�es du dernier point de la ligne */
		this.pointX = x;
		this.pointY = y;
	}
	
	/**M�thode utilis�e pour initialiser l'attribut pointList lors de la cr�ation d'un object parcours*/
	public void init_pointList() {
		int moyenne = (X_MAX - X_MIN)/2;
		for(int i = 0; i < Affichage.LARGEUR_FENETRE/moyenne; i++) {
			this.createLigne();
		}
	}
	
	/**M�thode utilis�e pour obtenir le parcours � suivre*/
	public ArrayList<Point> getParcours(){
		ArrayList<Point> onlyVisible = new ArrayList<Point>();
		for(int i = 0; i < this.pointList.size(); i++) {
			if(i < this.pointList.size() - 1) {
				Point p1 = this.pointList.get(i);
				Point p2 = this.pointList.get(i+1);
				if(p2.getX() >= this.getPosition()) { /**Si le point est visible**/
					onlyVisible.add(new Point(p1.x - this.getPosition(), p1.y));
				} else { /**Si le point n'est plus visible**/
					this.pointList.remove(0);
					this.createLigne();
				}
				onlyVisible.add(new Point(p2.x - this.getPosition(), p2.y));
			}
		}
		return onlyVisible;
	}
}
