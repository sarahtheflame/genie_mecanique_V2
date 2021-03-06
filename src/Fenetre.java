import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;

public class Fenetre extends JFrame{

	Panneau_haut current_p_haut;
	
	Panneau_centre_recherche current_p_centre_recherche;
	Panneau_bas current_p_bas_recherche;
	Panneau_droite_recherche current_p_droit_recherche;
	
	Panneau_centre_etudiant current_p_centre_etudiant;
	Panneau_bas current_p_bas_etudiant;
	Panneau_droite_etudiant current_p_droit_etudiant;
	
	JPanel p_haut;
	JPanel p_gauche;
	JPanel p_centre;
	JPanel p_droite;
	JPanel p_bas;
	
	Bouton b_lancer_commande;
	JTextField t_commande;
	char focus_char = '.';
	
	int screen_height;
	int screen_width;
	
	Membre administrateur = new Membre();
	Membre etudiant = new Membre();
	Controlleur controlleur;
	
	public Fenetre() {
		
		new JFrame();
		UIManager.put("TitledBorder.border", new LineBorder(new Color(125,125,125), 1));
		
		controlleur = new Controlleur(this);
		
		b_lancer_commande = new Bouton("Exécuter");
		
		t_commande = new JTextField();
		t_commande.setPreferredSize(
				new Dimension(150, 30));
		t_commande.setMaximumSize(
				new Dimension(150, 30));
		
		t_commande.addActionListener( 
				new AbstractAction()
				{
				    @Override
				    public void actionPerformed(ActionEvent e)
				    {
				    	b_lancer_commande.doClick();
				    }
				});

		setTitle("Système de gestion - 2014");
		setExtendedState(JFrame.MAXIMIZED_BOTH); 
		setMinimumSize(new Dimension(800, 600));
		
		setLayout(new BorderLayout());
		setVisible(false);
		
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
		
		current_p_haut = new Panneau_haut(this);
		
		current_p_centre_recherche = new Panneau_centre_recherche(this);
		current_p_droit_recherche = new Panneau_droite_recherche(this);
		current_p_bas_recherche = new Panneau_bas(this);
		
		current_p_centre_etudiant = new Panneau_centre_etudiant(this);
		current_p_droit_etudiant = new Panneau_droite_etudiant(this);
		current_p_bas_etudiant = new Panneau_bas(this);
		
		p_bas.add(current_p_bas_recherche);
		p_droite.add(current_p_droit_recherche);
		p_centre.add(current_p_centre_recherche);
		
		add(current_p_haut, BorderLayout.NORTH);
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
		
		b_lancer_commande.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String commande = t_commande.getText();
				
				if (commande.equals("selection")|| commande.equals(".selection"))
				{
					charger_panneau_etudiant();
				}
				else if (commande.equals("recherche")|| commande.equals(".recherche"))
				{
					set_panneau_recherche();
				}
				else if (commande.equals("location")|| commande.equals(".location"))
				{
					if (etudiant.id != "") {
						controlleur.faire_location(etudiant);
					} else {
						etudiant = controlleur.scanner_etudiant();
						if (etudiant.id != ""){
							controlleur.faire_location(etudiant);
						}
					}
				}
				else if (commande.equals("don")|| commande.equals(".don"))
				{
					if (etudiant.id != "") {
						controlleur.faire_don(etudiant);
					} else {
						etudiant = controlleur.scanner_etudiant();
						if (etudiant.id != ""){
							controlleur.faire_don(etudiant);
						}
					}
				}
				else if (commande.equals("retour")|| commande.equals(".retour"))
				{
					if (etudiant.id != "") {
						controlleur.faire_retour(etudiant, 0);
					} else {
						etudiant = controlleur.scanner_etudiant();
						if (etudiant.id != ""){
							controlleur.faire_retour(etudiant, 0);
						}
					}
				}
				else if (commande.equals("bris") || commande.equals(".bris"))
				{
					if (etudiant.id != "") {
						controlleur.faire_retour(etudiant, 1);
					} else {
						etudiant = controlleur.scanner_etudiant();
						if (etudiant.id != ""){
							controlleur.faire_retour(etudiant, 1);
						}
					}
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Erreur : La commande entrée est invalide");
				}
				
				t_commande.setText("");
			}
		});
		
		KeyboardFocusManager.getCurrentKeyboardFocusManager()
		  .addKeyEventDispatcher(new KeyEventDispatcher() {
		      public boolean dispatchKeyEvent(KeyEvent e) {
		    	  if (e.getKeyChar() == focus_char)
		    	  {
		    		  t_commande.requestFocus();
		    	  }
		        return false;
		      }
		});
	}
	
	public void set_panneau_recherche()
	{	
		p_bas.removeAll();
		p_droite.removeAll();
		p_centre.removeAll();
		
		p_bas.revalidate();
		p_droite.revalidate();
		p_centre.revalidate();
		
		p_bas.add(current_p_bas_recherche);
		p_droite.add(current_p_droit_recherche);
		p_centre.add(current_p_centre_recherche);
		current_p_haut.set_panneau_gauche_recherche();
		etudiant = new Membre();
	}
	
	public void set_panneau_etudiant()
	{		
		p_bas.removeAll();
		p_droite.removeAll();
		p_centre.removeAll();
		
		p_bas.revalidate();
		p_droite.revalidate();
		p_centre.revalidate();
		
		p_bas.add(current_p_bas_etudiant);
		p_droite.add(current_p_droit_etudiant);
		p_centre.add(current_p_centre_etudiant);
		current_p_haut.set_panneau_gauche_etudiant();
	}
	
	public void set_administrateur(Membre a_membre)
	{
		administrateur = a_membre;
	}
	public void charger_panneau_etudiant()
	{
		etudiant = controlleur.scanner_etudiant();
		if (etudiant.id != null) 
		{	
			if (etudiant.id != "")
			{
				set_panneau_etudiant();	
				current_p_centre_etudiant.rafraichir_tableaux();
			}	
		}
	}

	
}
