import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Fenetre extends JFrame {

	JPanel p_haut;
	JPanel p_gauche;
	JPanel p_centre;
	JPanel p_droite;
	JPanel p_bas;
	
	int screen_height;
	int screen_width;
	
	public Fenetre() {
		/* Constructeur de l'objet Fenetre */
		
		new JFrame();
		setTitle("Système de gestion - 2014");
		setExtendedState(JFrame.MAXIMIZED_BOTH); // Met la fenêtre "fullscreen" si elle est "resizable"
		
		setLayout(new BorderLayout());
		setVisible(false);
		/*  ----- Affecte le "fullscreen" -----
		setResizable(false);
		 */
		
		screen_height = Toolkit.getDefaultToolkit().getScreenSize().height;
		screen_width = Toolkit.getDefaultToolkit().getScreenSize().width;
		
		p_haut = new JPanel();
		p_gauche = new JPanel();
		p_centre = new JPanel();
		p_droite = new JPanel();
		p_bas = new JPanel();
		
		p_haut.setLayout(new GridLayout(1, 1));
		p_gauche.setLayout(new GridLayout(1, 1));
		p_centre.setLayout(new GridLayout(1, 1));
		p_droite.setLayout(new GridLayout(1, 1));
		p_bas.setLayout(new GridLayout(1, 1));
		
		add(p_haut, BorderLayout.NORTH);
		add(p_gauche, BorderLayout.WEST);
		add(p_centre, BorderLayout.CENTER);
		add(p_droite, BorderLayout.EAST);
		add(p_bas, BorderLayout.SOUTH);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent) {
				setVisible(false);
			}
		});
	}
}
