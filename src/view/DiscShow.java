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
import javax.swing.JList;
import javax.swing.JLabel;

public class DiscShow extends JFrame {

	private JPanel contentPane;
	private JTable table;

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
	public DiscShow() {
		
		this.setResizable(false);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 205);
		
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
		
		JLabel band = new JLabel("Nome: xxxxx, ano 2019");
		band.setBounds(12, 12, 773, 15);
		contentPane.add(band);
		
		JLabel musics = new JLabel("Musicas");
		musics.setBounds(12, 39, 773, 15);
		contentPane.add(musics);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 66, 773, 75);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		String[] columns = {"Nome", "Banda", "Ano"};
		String[][] rows = {
				{"Teste", "teste", "teste"},
				{"Teste", "teste", "teste"},
				{"Teste", "teste", "teste"},
				{"Teste", "teste", "teste"},
				{"Teste", "teste", "teste"},
				{"Teste", "teste", "teste"},
		};
	}
}
