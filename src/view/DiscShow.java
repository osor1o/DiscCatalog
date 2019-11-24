package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import dao.MusicDAO;
import model.Disc;
import model.Music;
import view.components.Table;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.util.ArrayList;
import javax.swing.JLabel;

public class DiscShow extends JFrame {
	private JPanel contentPane;
	private JTable table;
	private Disc disc;
	
	public DiscShow(Disc d) {
		disc = d;
		setResizable(false);
		setTitle(d.getName() + " - " + d.getYear());
		setVisible(true);
		
		setBounds(100, 100, 699, 230);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel musics = new JLabel("Musicas");
		musics.setBounds(12, 12, 671, 15);
		contentPane.add(musics);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 39, 671, 152);
		contentPane.add(scrollPane);
		
		table = new Table(null);
		scrollPane.setViewportView(table);
		refreshTableData();
	}
	
	public void refreshTableData() {
		String[] columns = {"id", "Nome", "Ano"};
		DefaultTableModel tableModel = new DefaultTableModel(null, columns);
		
		ArrayList<Music> musics = MusicDAO.list(disc);
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
}
