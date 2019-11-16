package view;

import java.awt.GridLayout;

import javax.swing.JFrame;

public class DiscEdit extends JFrame {
	private static JFrame instance = null;
	
	private DiscEdit() {
		GridLayout layout = new GridLayout(3,3);
		this.setLayout(layout);
		this.pack();
		this.setVisible(true);
	}	
	
	public JFrame getInstance() {
		if(DiscEdit.instance == null) {
			DiscEdit.instance = new DiscEdit();
		}
		return instance;
	}
}
