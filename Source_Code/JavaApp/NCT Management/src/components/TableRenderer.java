package components;

import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

import core.Booking;
import frames.TableRow;

//Table Cell Renderer
// renders each cell
public class TableRenderer implements TableCellRenderer{
	TableRow cell;
	
	// Constructor
	public TableRenderer(){
		cell = new TableRow();
	}
	@Override
	public Component getTableCellRendererComponent(JTable table,
			Object value, boolean isSelected, boolean hasFocus, int row,
			int column) {
		Booking book = (Booking) value;
		cell.setRowLabels(book, isSelected, table);
		
		return cell;
	}
}