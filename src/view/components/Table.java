package view.components;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Table extends JTable {

	public Table(DefaultTableModel model) {
		super(model);
	}
	
	public boolean isCellEditable(int row, int column) {
        return false;
    }
}
