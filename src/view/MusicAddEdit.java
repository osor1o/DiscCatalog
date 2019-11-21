package view;

import javax.swing.JFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JTextField;

import dao.MusicDAO;
import model.Band;
import model.Music;

import javax.swing.JLabel;

public class MusicAddEdit extends JFrame {
	private static MusicAddEdit instance;
	private Music music;
	private Band band;
	private JLabel lblBand;
	private JButton btnAddEdit;
	private JTextField name;
	private JTextField year;
	private Listener listener = new Listener();

	public static MusicAddEdit getInstance() {
		if(instance == null) {
			instance = new MusicAddEdit();
		}
		return instance;
	}
	
	private MusicAddEdit() {
		setResizable(false);
		setBounds(100, 100, 400, 216);
		getContentPane().setLayout(null);
		
		name = new JTextField();
		name.setBounds(12, 66, 372, 19);
		getContentPane().add(name);
		name.setColumns(10);
		
		year = new JTextField();
		year.setColumns(10);
		year.setBounds(12, 124, 372, 19);
		getContentPane().add(year);
		
		lblBand = new JLabel("Banda");
		lblBand.setBounds(12, 12, 66, 15);
		getContentPane().add(lblBand);
		
		JLabel lblName = new JLabel("Nome");
		lblName.setBounds(12, 39, 66, 15);
		getContentPane().add(lblName);
		
		JLabel lblYear = new JLabel("Ano");
		lblYear.setBounds(12, 97, 66, 15);
		getContentPane().add(lblYear);
		
		btnAddEdit = new JButton("Adicionar");
		btnAddEdit.setBounds(270, 155, 114, 25);
		getContentPane().add(btnAddEdit);
		btnAddEdit.addActionListener(listener);
	}
	
	public void setMusic(Music m) {
		music = m;
		if(m != null) {
			setTitle("Editar Musica");
			btnAddEdit.setText("Editar");
			name.setText(m.getName());
			year.setText(String.valueOf(m.getYear()));
		} else {
			setTitle("Adicionar Musica");
			btnAddEdit.setText("Adicionar");
			name.setText("");
			year.setText("");
		}
	}
	
	public void setBand(Band b) {
		band = b;
		lblBand.setText("Banda: " + b.getName());
	}
	
	private class Listener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if(music == null) {
				MusicDAO.add(name.getText(), Integer.parseInt(year.getText()), band);
			} else {
				MusicDAO.edit(music.getId(), name.getText(), Integer.parseInt(year.getText()), music.getBand());
			}
			setVisible(false);
			MusicList.getInstance().refreshTableData();
		}
		
	}
}
