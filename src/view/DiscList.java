package view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import dao.BandDAO;
import dao.DiscDAO;
import model.Band;
import model.Disc;
import model.Music;
import view.components.Table;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import java.util.ArrayList;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.JComboBox;
import javax.swing.JLabel;

public class DiscList extends JFrame {
	private static DiscList instance;
	private JPanel contentPane;
	private Table table;
	private Band band;
	private JComboBox<Band> comboBand;
	private JButton btnShowMusics;
	private JButton btnAdd;
	private JButton btnEdit;
	private JButton btnRemove;
	private JMenuItem menuBands;
	private JMenuItem menuMusics;
	private Listener listener = new Listener();

	public static DiscList getInstance() {
		if(instance == null) {
			instance = new DiscList();
		}
		return instance;
	}
	
	public DiscList() {
		setTitle("Discos");
		
		this.setResizable(false);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 801, 324);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu menu = new JMenu("Menu");
		menuBar.add(menu);
		
		menuMusics = new JMenuItem("Musicas");
		menu.add(menuMusics);
		menuMusics.addActionListener(listener);
		
		menuBands = new JMenuItem("Bandas");
		menu.add(menuBands);
		menuBands.addActionListener(listener);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 75, 772, 153);
		contentPane.add(scrollPane);
			
		table = new Table(null);
		table.setRowSelectionAllowed(true);
		
		scrollPane.setViewportView(table);
		
		btnShowMusics = new JButton("Ver Músicas");
		btnShowMusics.setBounds(265, 240, 142, 25);
		contentPane.add(btnShowMusics);
		btnShowMusics.addActionListener(listener);
		
		btnAdd = new JButton("Adicionar");
		btnAdd.setBounds(419, 240, 114, 25);
		contentPane.add(btnAdd);
		btnAdd.addActionListener(listener);
		
		btnEdit = new JButton("Editar");
		btnEdit.setBounds(545, 240, 114, 25);
		contentPane.add(btnEdit);
		btnEdit.addActionListener(listener);
		
		btnRemove = new JButton("Remover");
		btnRemove.setBounds(671, 240, 114, 25);
		contentPane.add(btnRemove);
		btnRemove.addActionListener(listener);
		
		comboBand = new JComboBox<Band>();
		comboBand.setBounds(12, 39, 772, 24);
		contentPane.add(comboBand);
		comboBand.addActionListener(listener);
		
		JLabel lblBanda = new JLabel("Banda");
		lblBanda.setBounds(12, 12, 66, 15);
		contentPane.add(lblBanda);
		
		refreshComboBoxData();
		refreshTableData();
	}
	
	public void refreshTableData() {
		String[] columns = {"id", "Nome", "Ano"};
		DefaultTableModel tableModel = new DefaultTableModel(null, columns);
		
		ArrayList<Disc> discs = DiscDAO.list(band);
		for(Disc d : discs) {
			String[] row = {
					String.valueOf(d.getId()),
					d.getName(),
					String.valueOf(d.getYear())
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
			
			if(e.getSource() == btnShowMusics && row != -1) {
				int id = Integer.parseInt(table.getValueAt(row, 0).toString());
				String name = table.getValueAt(row, 1).toString();
				int year = Integer.parseInt(table.getValueAt(row, 2).toString());
				Disc disc = new Disc(id, name, year, band, null);
				new DiscShow(disc);
			}
			
			if(e.getSource() == btnAdd) {
				DiscAddEdit frame = DiscAddEdit.getInstance();
				frame.setBand(band);
				frame.setDisc(null);
				frame.setVisible(true);
			}
			
			if(e.getSource() == btnEdit && row != -1) {
				int id = Integer.parseInt(table.getValueAt(row, 0).toString());
				String name = table.getValueAt(row, 1).toString();
				int year = Integer.parseInt(table.getValueAt(row, 2).toString());
				Disc disc = new Disc(id, name, year, band, null);
				DiscAddEdit frame = DiscAddEdit.getInstance();
				frame.setBand(band);
				frame.setDisc(disc);
				frame.setVisible(true);
			}
			
			if(e.getSource() == btnRemove) {
				int id = Integer.parseInt(table.getValueAt(row, 0).toString());
				DiscDAO.remove(id);
				refreshTableData();
			}
			
			if(e.getSource() == menuMusics) {
				MusicList frame = MusicList.getInstance();
				frame.setVisible(true);
			}
			
			if(e.getSource() == menuBands) {
				BandList frame = BandList.getInstance();
				frame.setVisible(true);
			}
		}
	}
}
