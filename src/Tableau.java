import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;


public class Tableau extends JTable {
	
	String nom_table;
	
	public Tableau(Modele_table a_modele_table) {
		super(a_modele_table);
		nom_table = ((Modele_table) getModel()).nom_table;
		setGridColor(Color.LIGHT_GRAY);
		setRowHeight(30);
		setRowMargin(0);
		setFillsViewportHeight(true);
	}
	

}
