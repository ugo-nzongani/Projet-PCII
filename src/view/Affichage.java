package view;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

import javax.swing.JPanel;

import control.Control;
import model.Etat;

/**Classe utilisée pour la gestion de l'interface graphique*/
public class Affichage extends JPanel {
	
	/**Constantes liées à l'interface graphique*/
	
	/**Largeur de la fenêtre*/
	public final static int LARGEUR_FENETRE = 600;
	/**Hauteur de la fenêtre*/
	public final static int HAUTEUR_FENETRE = 600;
	/**Abscisse de l'ovale*/
	public static final int X = 1;
	/**Ordonnée de l'ovale*/
	public static int y = HAUTEUR_FENETRE/2;
	/**Largeur de l'ovale*/
	public static final int LARGEUR_OVALE = 40;
	/**Hauteur de l'ovale*/
	public static final int HAUTEUR_OVALE = 35;
	
	/**Attributs*/
	public Etat etat;
	
	/**Constructeur*/
	public Affichage(Etat e) {
		this.etat = e;
		setPreferredSize(new Dimension(LARGEUR_FENETRE, HAUTEUR_FENETRE));
		addMouseListener(new Control(this, this.etat));	
	}
	
	/**Méthode utilisée pour l'affichage*/
	@Override
	public void paint(Graphics g) {
		/**Couleur de l'arrière plan*/
		this.setBackground(Color.cyan);
		super.paint(g);
		/**Couleur de l'ovale*/
		g.setColor(Color.RED);
		g.drawOval(X, this.etat.hauteur, LARGEUR_OVALE, HAUTEUR_OVALE);
		/**Couleur de la ligne brisée*/
		g.setColor(Color.magenta);
		ArrayList<Point> pointList = this.etat.parcours.getParcours();
		/**Boucle dessinant la ligne brisée*/
		for(int i = 0; i < pointList.size(); i++) {
			Point p = pointList.get(i);
			g.drawLine(p.x, p.y, p.x, p.y);
		}
	}
}
