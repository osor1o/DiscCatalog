package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import dao.BandDAO;
import dao.MusicDAO;
import model.Band;
import model.Music;
import view.components.Table;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JLabel;

public class MusicList extends JFrame {
	private static MusicList instance;
	private JPanel contentPane;
	private JTable table;
	private Music music;
	private Band band;
	private JComboBox<Band> comboBand;
	private JButton btnAdd;
	private JButton btnEdit;
	private JButton btnRemove;
	private Listener listener = new Listener();
	
	public static MusicList getInstance() {
		if(instance == null) {
			instance = new MusicList();
		}
		return instance;
	}
	
	private MusicList() {
		setResizable(false);
		setTitle("Músicas");
		
		setBounds(100, 100, 801, 331);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 102, 772, 153);
		contentPane.add(scrollPane);
			
		table = new Table(null);
		table.setRowSelectionAllowed(true);
		
		scrollPane.setViewportView(table);
		
		btnAdd = new JButton("Adicionar");
		btnAdd.setBounds(418, 267, 114, 25);
		contentPane.add(btnAdd);
		btnAdd.addActionListener(listener);
		
		btnEdit = new JButton("Editar");
		btnEdit.setBounds(544, 267, 114, 25);
		contentPane.add(btnEdit);
		btnEdit.addActionListener(listener);
		
		btnRemove = new JButton("Remover");
		btnRemove.setBounds(670, 267, 114, 25);
		contentPane.add(btnRemove);
		btnRemove.addActionListener(listener);
		
		comboBand = new JComboBox<Band>();
		comboBand.setBounds(12, 39, 772, 24);
		contentPane.add(comboBand);
		comboBand.addActionListener(listener);
		
		refreshTableData();
		refreshComboBoxData();
		
		JLabel lblBanda = new JLabel("Banda");
		lblBanda.setBounds(12, 12, 66, 15);
		contentPane.add(lblBanda);
		
		JLabel lblMusicas = new JLabel("Músicas");
		lblMusicas.setBounds(12, 75, 66, 15);
		contentPane.add(lblMusicas);
	}
	
	public void refreshTableData() {
		String[] columns = {"id", "Nome", "Ano"};
		DefaultTableModel tableModel = new DefaultTableModel(null, columns);
		
		ArrayList<Music> musics = MusicDAO.list(band);
		for(Music m : musics) {
			String[] row = {
					String.valueOf(m.getId()),
					m.getName(),
					String.valueOf(m.getYear())
			};
			tableModel.addRow(row);
		}
		table.setModel(tableModel);
	}
	
	public void refreshComboBoxData() {
		ArrayList<Band> bands = BandDAO.list();
		comboBand.removeAllItems();
		for(Band b : bands) {
			comboBand.addItem(b);
		}
	}
	
	private class Listener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			int row = table.getSelectedRow();
			
			if(e.getSource() == comboBand) {
				band = (Band) comboBand.getSelectedItem();
				refreshTableData();
			}
			
			if(e.getSource() == btnAdd) {
				MusicAddEdit addFrame = MusicAddEdit.getInstance();
				addFrame.setMusic(null);
				addFrame.setBand(band);
				addFrame.setVisible(true);
			}
			
			if(e.getSource() == btnEdit) {
				if(row != -1) {
					int id = Integer.parseInt(table.getValueAt(row, 0).toString());
					String name = table.getValueAt(row, 1).toString();
					int year = Integer.parseInt(table.getValueAt(row, 2).toString());
					Music music = new Music(id, name, year, band);
					MusicAddEdit editFrame = MusicAddEdit.getInstance();
					editFrame.setMusic(music);
					editFrame.setBand(null);
					editFrame.setVisible(true);
				}
			}
			
			if(e.getSource() == btnRemove) {
				if(row != -1) {
					int id = Integer.parseInt(table.getValueAt(row, 0).toString());
					MusicDAO.remove(id);
					refreshTableData();
				}
			}
		}
		
	}
}
