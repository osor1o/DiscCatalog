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
import dao.MusicDAO;
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
		
		JMenu mnNewMenu = new JMenu("Menu");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Discos");
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenuItem menuItem = new JMenuItem("Bandas");
		mnNewMenu.add(menuItem);
		
		JMenuItem mntmMusicas = new JMenuItem("Musicas");
		mnNewMenu.add(mntmMusicas);
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
		
		refreshTableData();
		refreshComboBoxData();
	}
	
	public void refreshTableData() {
		String[] columns = {"id", "Nome", "Ano"};
		DefaultTableModel tableModel = new DefaultTableModel(null, columns);
		
		ArrayList<Disc> discs = DiscDAO.list(band);
		for(Disc d : discs) {
			String[] row = {
					String.valueOf(d.getId()),
					d.getName(),
					String.valueOf(d.getBand().getName()),
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
			if(e.getSource() == comboBand) {
				System.out.println("combo band");
			}
			
			if(e.getSource() == btnShowMusics) {
				System.out.println("show musics");
			}
			
			if(e.getSource() == btnAdd) {
				System.out.println("add");
			}
			
			if(e.getSource() == btnEdit) {
				System.out.println("edit");
			}
			
			if(e.getSource() == btnRemove) {
				System.out.println("remove");
			}
		}
	}
}
