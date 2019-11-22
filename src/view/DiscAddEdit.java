package view;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JTextField;

import dao.DiscDAO;
import dao.MusicDAO;
import model.Band;
import model.Disc;
import model.Music;

import javax.swing.JLabel;

public class DiscAddEdit extends JFrame {
	private static DiscAddEdit instance;
	private Disc disc;
	private Band band;
	private JTextField name;
	private JTextField year;
	private JButton btnAddEdit;
	private JLabel lblBand;
	private JList<Music> listMusics;
	private JList<Music> listSelectedMusics;
	private JButton btnAddMusic;
	JButton btnRemoveMusic;
	private Listener listener = new Listener();

	public static DiscAddEdit getInstance() {
		if(instance == null) {
			instance = new DiscAddEdit();
		}
		return instance;
	}
	
	public DiscAddEdit() {
		setTitle("Adicionar Disco");
		setResizable(false);
		setBounds(100, 100, 600, 309);
		getContentPane().setLayout(null);
		
		lblBand = new JLabel("Banda");
		lblBand.setBounds(12, 12, 572, 15);
		getContentPane().add(lblBand);
		
		name = new JTextField();
		name.setBounds(12, 66, 348, 19);
		getContentPane().add(name);
		name.setColumns(10);
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setBounds(12, 39, 66, 15);
		getContentPane().add(lblNome);
		
		year = new JTextField();
		year.setColumns(10);
		year.setBounds(377, 66, 207, 19);
		getContentPane().add(year);
		
		JLabel lblAno = new JLabel("Ano");
		lblAno.setBounds(377, 39, 66, 15);
		getContentPane().add(lblAno);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 124, 216, 104);
		getContentPane().add(scrollPane);
		
		listMusics = new JList<Music>();
		scrollPane.setViewportView(listMusics);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(377, 124, 207, 104);
		getContentPane().add(scrollPane_1);
		
		listSelectedMusics = new JList<Music>();
		scrollPane_1.setViewportView(listSelectedMusics);
		
		JLabel lblMusicas = new JLabel("Musicas");
		lblMusicas.setBounds(12, 97, 66, 15);
		getContentPane().add(lblMusicas);
		
		btnAddMusic = new JButton(">>");
		btnAddMusic.setBounds(240, 141, 120, 25);
		getContentPane().add(btnAddMusic);
		btnAddMusic.addActionListener(listener);
		
		btnRemoveMusic = new JButton("<<");
		btnRemoveMusic.setBounds(240, 178, 120, 25);
		getContentPane().add(btnRemoveMusic);
		btnRemoveMusic.addActionListener(listener);
		
		btnAddEdit = new JButton("Adicionar");
		btnAddEdit.setBounds(464, 245, 120, 25);
		getContentPane().add(btnAddEdit);
		btnAddEdit.addActionListener(listener);
	}
	
	public void setDisc(Disc d) {
		disc = d;
		if(d != null) {
			setTitle("Editar Disco");
			btnAddEdit.setText("Editar");
			name.setText(d.getName());
			year.setText(String.valueOf(d.getYear()));
		} else {
			setTitle("Adicionar Disco");
			btnAddEdit.setText("Adicionar");
			name.setText("");
			year.setText("");
		}
		refreshListSelectedMusics();
	}
	
	public void setBand(Band b) {
		band = b;
		lblBand.setText("Banda: " + b.getName());
	}
	
	public void refreshListSelectedMusics() {
		DefaultListModel<Music> musicsModel = new DefaultListModel<Music>();
		ArrayList<Music> musics = MusicDAO.list(disc);
		for(Music m : musics) {
			musicsModel.addElement(m);
		}
		listSelectedMusics.setModel(musicsModel);
		refreshListMusics(musics);
	}
	
	public void refreshListMusics(ArrayList<Music> selectedMusics) {
		DefaultListModel<Music> musicsModel = new DefaultListModel<Music>();
		ArrayList<Music> musics = MusicDAO.list(band);
		HashMap<Integer, Music> mmap = new HashMap<Integer, Music>();
		for(Music m : musics) {
			mmap.put(m.getId(), m);
		}
		for(Music m : selectedMusics) {
			mmap.remove(m.getId());
		}
		musics.clear();
		musics.addAll(mmap.values());
		for(Music m : musics) {
			musicsModel.addElement(m);
		}
		listMusics.setModel(musicsModel);
	}
	
	
	private class Listener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			DefaultListModel<Music> modelListSelectedMusics = (DefaultListModel<Music>) listSelectedMusics.getModel();
			DefaultListModel<Music> modelListMusics = (DefaultListModel<Music>) listMusics.getModel();
			
			if(e.getSource() == btnAddMusic && listMusics.getSelectedIndex() != -1) {
				modelListSelectedMusics.addElement(listMusics.getSelectedValue());
				modelListMusics.remove(listMusics.getSelectedIndex());
			}
			
			if(e.getSource() == btnRemoveMusic && listSelectedMusics.getSelectedIndex() != -1) {
				modelListMusics.addElement(listSelectedMusics.getSelectedValue());
				modelListSelectedMusics.remove(listSelectedMusics.getSelectedIndex());
			}
			
			if(e.getSource() == btnAddEdit) {
				ArrayList<Music> musics = new ArrayList<Music>();
				for(int i = 0; i < modelListSelectedMusics.getSize(); i++) {
					musics.add(modelListSelectedMusics.get(i));
				}
				if(disc == null) {
					DiscDAO.add(name.getText(), Integer.parseInt(year.getText()), band, musics);
				} else {
					DiscDAO.edit(disc.getId(), name.getText(), Integer.parseInt(year.getText()), band, musics);
				}
				setVisible(false);
				DiscList.getInstance().refreshTableData();
			}
			
			listSelectedMusics.setModel(modelListSelectedMusics);
			listMusics.setModel(modelListMusics);
		}
	}
}
