package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;

public class DiscList extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DiscList frame = new DiscList();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public DiscList() {
		
		this.setResizable(false);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 801, 303);
		
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
		
		JButton btnNewButton = new JButton("Buscar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnNewButton.setBounds(670, 12, 114, 25);
		contentPane.add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 49, 772, 153);
		contentPane.add(scrollPane);
		
		String[] columns = {"Nome", "Banda", "Ano"};
		String[][] rows = {
				{"Teste", "teste", "teste"},
				{"Teste", "teste", "teste"},
				{"Teste", "teste", "teste"},
				{"Teste", "teste", "teste"},
				{"Teste", "teste", "teste"},
				{"Teste", "teste", "teste"},
		};
			
		table = new JTable(rows, columns);		
		table.setEnabled(false);
		
		scrollPane.setViewportView(table);
		
		textField = new JTextField();
		textField.setBounds(12, 12, 646, 25);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnAdicionar = new JButton("Adicionar");
		btnAdicionar.setBounds(418, 214, 114, 25);
		contentPane.add(btnAdicionar);
		
		JButton btnRemover = new JButton("Remover");
		btnRemover.setBounds(670, 214, 114, 25);
		contentPane.add(btnRemover);
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.setBounds(544, 214, 114, 25);
		contentPane.add(btnEditar);
		
		JButton btnVerMsicas = new JButton("Ver Músicas");
		btnVerMsicas.setBounds(264, 214, 142, 25);
		contentPane.add(btnVerMsicas);
	}
}
