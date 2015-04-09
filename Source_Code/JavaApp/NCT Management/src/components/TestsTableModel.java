package components;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import core.TestResults;

//Table Model
// contains all info from Booking classes
public class TestsTableModel extends AbstractTableModel{

	private static final long serialVersionUID = 1L;
	private ArrayList<TestResults> tests = new ArrayList<TestResults>();
	
	public TestsTableModel(ArrayList<TestResults> newtests){
		tests = newtests;
	}
	
	// Override methods from AbstractTableModel
	@Override
	public String getColumnName(int columnIndex){
		return "Tests";
	}
	@Override
	public Class<TestResults> getColumnClass(int columnIndex){
		return TestResults.class;
	}
	@Override
	public int getRowCount() {
		return (tests == null) ? null : tests.size();
	}
	@Override
	public int getColumnCount() {
		return 1;
	}
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return (tests == null) ? null : tests.get(rowIndex);
	}
	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		tests.set(rowIndex, (TestResults) aValue);
		fireTableCellUpdated(rowIndex, 0);
	}
	@Override
	public boolean isCellEditable(int columnIndex, int rowIndex){
		return false;
	}
}
