package view;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class DiscList extends JFrame {
	private static DiscList instance = null;
	private JTable table;
	private JScrollPane scroll;
	private JButton add, edit, remove;
	
	private DiscList() {
		GridLayout layout = new GridLayout(2,1);
		this.setLayout(layout);
		
		table = new JTable(30, 4);
		scroll = new JScrollPane(table);
		add = new JButton("Adicionar");
		
		this.add(add);
		this.add(scroll);
		this.pack();
		this.setVisible(true);
	}
	
	public static JFrame getInstance() {
		if(DiscList.instance == null) {
			DiscList.instance = new DiscList();
		}
		return instance;
	}
}
