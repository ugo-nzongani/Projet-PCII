package control;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import model.Etat;
import view.Affichage;

/**Classe utilisée pour l'implémentation du Listener*/
public class Control implements MouseListener {

    /**Attributs*/
    public Affichage affichage;
    public Etat etat;
    
    /**Constructeur*/
    public Control(Affichage a, Etat e) {
        this.affichage = a;
        this.etat = e;
    }

    /**Méthode utilisée pour agir lors d'un clic de souris*/
    @Override
    public void mouseClicked(MouseEvent e) {
        this.etat.jump();
        this.affichage.repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
    }
}