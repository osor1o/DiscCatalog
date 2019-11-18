package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JToggleButton;
import javax.swing.JTextField;
import javax.swing.JLabel;

public class DiscAddEdit extends JFrame {
	private JTextField textField;
	private JTextField textField_1;

	public DiscAddEdit() {
		setEnabled(false);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 257);
		getContentPane().setLayout(null);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(12, 39, 372, 24);
		getContentPane().add(comboBox);
		
		textField = new JTextField();
		textField.setBounds(12, 102, 372, 19);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(12, 160, 372, 19);
		getContentPane().add(textField_1);
		
		JLabel lblBand = new JLabel("Banda");
		lblBand.setBounds(12, 12, 66, 15);
		getContentPane().add(lblBand);
		
		JLabel lblName = new JLabel("Nome");
		lblName.setBounds(12, 75, 66, 15);
		getContentPane().add(lblName);
		
		JLabel lblYear = new JLabel("Ano");
		lblYear.setBounds(12, 133, 66, 15);
		getContentPane().add(lblYear);
		
		JButton btnAdicionar = new JButton("Adicionar");
		btnAdicionar.setBounds(270, 191, 114, 25);
		getContentPane().add(btnAdicionar);
	}
}
