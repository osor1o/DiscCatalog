package view;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import dao.BandDAO;
import model.Band;
import view.components.Table;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class BandList extends JFrame {
	private static BandList instance;
	private JPanel contentPane;
	private JTable table;
	private JButton btnAdd;
	private JButton btnEdit;
	private JButton btnRemove;
	private Listener listener = new Listener();
	
	public static BandList getInstance() {
		if(instance == null) {
			instance = new BandList();
		}
		return instance;
	}
	
	private BandList() {
		setTitle("Bandas");
		
		this.setResizable(false);
		
		setBounds(100, 100, 800, 242);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 12, 772, 153);
		contentPane.add(scrollPane);
		
		table = new Table(null);
		table.setRowSelectionAllowed(true);
		refreshData();
		
		scrollPane.setViewportView(table);
		
		btnAdd = new JButton("Adicionar");
		btnAdd.setBounds(418, 177, 114, 25);
		contentPane.add(btnAdd);
		btnAdd.addActionListener(listener);
		
		btnEdit = new JButton("Editar");
		btnEdit.setBounds(544, 177, 114, 25);
		contentPane.add(btnEdit);
		btnEdit.addActionListener(listener);
		
		btnRemove = new JButton("Remover");
		btnRemove.setBounds(670, 177, 114, 25);
		contentPane.add(btnRemove);
		btnRemove.addActionListener(listener);
	}
	
	private class Listener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			int row = table.getSelectedRow();
			
			if(e.getSource() == btnAdd) {
				BandAddEdit addFrame = BandAddEdit.getInstance();
				addFrame.setBand(null);
				addFrame.setVisible(true);
			}
			
			if(e.getSource() == btnEdit) {
				if(row != -1) {
					int id = Integer.parseInt(table.getValueAt(row, 0).toString());
					String name = table.getValueAt(row, 1).toString();
					int year = Integer.parseInt(table.getValueAt(row, 2).toString());
					Band band = new Band(id, name, year);
					BandAddEdit editFrame = BandAddEdit.getInstance();
					editFrame.setBand(band);
					editFrame.setVisible(true);
				}
			}
			
			if(e.getSource() == btnRemove) {
				if(row != -1) {
					int id = Integer.parseInt(table.getValueAt(row, 0).toString());
					BandDAO.remove(id);
					refreshData();
					MusicList.getInstance().refreshComboBoxData();
					DiscList.getInstance().refreshComboBoxData();
				}
			}
		}
	}
	
	public void refreshData() {
		String[] columns = {"id", "Nome", "Ano"};
		DefaultTableModel model = new DefaultTableModel(null, columns);
		
		ArrayList<Band> bands = BandDAO.list();
		for(Band b : bands) {
			String[] row = {
					String.valueOf(b.getId()),
					b.getName(),
					String.valueOf(b.getYear())
			};
			model.addRow(row);
		}
		
		model.fireTableDataChanged();
		table.setModel(model);
	}
}
