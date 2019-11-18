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
	private Band band;
	private JComboBox<String> comboBand;
	
	public static MusicList getInstance() {
		if(instance == null) {
			instance = new MusicList();
		}
		return instance;
	}
	
	private MusicList() {
		setTitle("Músicas");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
		
		JButton btnAdicionar = new JButton("Adicionar");
		btnAdicionar.setBounds(418, 267, 114, 25);
		contentPane.add(btnAdicionar);
		
		JButton btnRemover = new JButton("Remover");
		btnRemover.setBounds(670, 267, 114, 25);
		contentPane.add(btnRemover);
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.setBounds(544, 267, 114, 25);
		contentPane.add(btnEditar);
		
		comboBand = new JComboBox<String>();
		comboBand.setBounds(12, 39, 772, 24);
		contentPane.add(comboBand);
		
		refreshData();
		
		JLabel lblBanda = new JLabel("Banda");
		lblBanda.setBounds(12, 12, 66, 15);
		contentPane.add(lblBanda);
		
		JLabel lblMusicas = new JLabel("Músicas");
		lblMusicas.setBounds(12, 75, 66, 15);
		contentPane.add(lblMusicas);
	}
	
	public void refreshData() {
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
		
		tableModel.fireTableDataChanged();
		table.setModel(tableModel);
		
		ArrayList<Band> bands = BandDAO.list();
		ArrayList<String> bandsName = new ArrayList<String>();
		
		for(Band b : bands) {
			bandsName.add(b.getId() +" - " + b.getName());
		}
		
		DefaultComboBoxModel<String> comboModel = new DefaultComboBoxModel<String>(bandsName.toArray(new String[bandsName.size()]));
		comboBand.setModel(comboModel);
	}
}
