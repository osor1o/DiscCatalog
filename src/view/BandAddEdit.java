package view;

import javax.swing.JFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JTextField;

import dao.BandDAO;
import model.Band;

import javax.swing.JLabel;

public class BandAddEdit extends JFrame {
	private static BandAddEdit instance;
	private JTextField name;
	private JTextField year;
	private JButton btnAddEdit;
	private Band band;
	private ActionListener listener = new Listener();
	
	public static BandAddEdit getInstence() {
		if(instance == null) {
			instance = new BandAddEdit();
		}
		return instance;
	}
	
	private BandAddEdit() {
		setResizable(false);
		
		setTitle("Adicionar Banda");
		
		setBounds(100, 100, 400, 191);
		getContentPane().setLayout(null);
		
		name = new JTextField();
		name.setBounds(12, 39, 372, 19);
		getContentPane().add(name);
		name.setColumns(10);
		
		year = new JTextField();
		year.setColumns(10);
		year.setBounds(12, 97, 372, 19);
		getContentPane().add(year);
		
		JLabel lblName = new JLabel("Nome");
		lblName.setBounds(12, 12, 66, 15);
		getContentPane().add(lblName);
		
		JLabel lblYear = new JLabel("Ano");
		lblYear.setBounds(12, 70, 66, 15);
		getContentPane().add(lblYear);
		
		btnAddEdit = new JButton("Adicionar");
		btnAddEdit.setBounds(270, 128, 114, 25);
		getContentPane().add(btnAddEdit);
		btnAddEdit.addActionListener(listener);
		
		this.setVisible(true);
		
	}
	
	public void setBand(Band b) {
		band = b;
		if(b != null) {
			setTitle("Editar Banda");
			btnAddEdit.setText("Editar");
			name.setText(b.getName());
			year.setText(String.valueOf(b.getYear()));
		} else {
			setTitle("Adicionar Banda");
			btnAddEdit.setText("Adicionar");
			name.setText("");
			year.setText("");
		}
		
	}
	
	private class Listener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(band == null) {
				BandDAO.add(name.getText(), Integer.parseInt(year.getText()));
			} else {
				BandDAO.edit(band.getId(), name.getText(), Integer.parseInt(year.getText()));
			}
			setVisible(false);
			BandList.getInstence().refreshData();
		}
	}
}
