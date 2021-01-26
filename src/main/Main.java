package main;
import javax.swing.JFrame;

import control.Control;
import model.Avancer;
import model.Etat;
import model.Voler;
import view.Affichage;

/**Classe principale pour lancer le programme*/
public class Main {

		/**Méthode utilisée pour la création d'une fenêtre*/
		public static void createFenetre(JFrame fenetre, Control c) {
			fenetre.add(c.affichage);
			fenetre.pack();
			fenetre.setVisible(true);
			fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}

		/**Méthode principale*/
		public static void main(String []args) {
			JFrame j = new JFrame("Mini-Jeu: Projet PCII");
			Etat e = new Etat();
			Affichage a = new Affichage(e);
			e.addAffichage(a);
			Control c = new Control(a, e);
			createFenetre(j, c);

			/**Création de nouveaux threads*/
			Voler oval_movement = new Voler(e);
			Avancer line_movement = new Avancer(e);
			/**Lancement des threads*/
			oval_movement.start();
			line_movement.start();
		}
}