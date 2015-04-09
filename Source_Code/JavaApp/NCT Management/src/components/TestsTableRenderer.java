package components;

import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

import core.TestResults;
import frames.TestsTableRow;

//Table Cell Renderer
// renders each cell
public class TestsTableRenderer implements TableCellRenderer{
	TestsTableRow cell;
	
	// Constructor
	public TestsTableRenderer(){
		cell = new TestsTableRow();
	}
	@Override
	public Component getTableCellRendererComponent(JTable table,
			Object value, boolean isSelected, boolean hasFocus, int row,
			int column) {
		TestResults test = (TestResults) value;
		cell.setRowLabels(test, isSelected, table);
		
		return cell;
	}
}