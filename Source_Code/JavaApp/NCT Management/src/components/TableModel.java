package components;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import core.Booking;

//Table Model
// contains all info from Booking classes
public class TableModel extends AbstractTableModel{

	private static final long serialVersionUID = 1L;
	private ArrayList<Booking> bookings = new ArrayList<>();
	
	public TableModel(ArrayList<Booking> newBookings){
		bookings = newBookings;
	}
	
	// Override methods from AbstractTableModel
	@Override
	public String getColumnName(int columnIndex){
		return "Booking";
	}
	@Override
	public Class<Booking> getColumnClass(int columnIndex){
		return Booking.class;
	}
	@Override
	public int getRowCount() {
		return (bookings == null) ? null : bookings.size();
	}
	@Override
	public int getColumnCount() {
		return 1;
	}
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return (bookings == null) ? null : bookings.get(rowIndex);
	}
	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		bookings.set(rowIndex, (Booking) aValue);
		fireTableCellUpdated(rowIndex, 0);
	}
	@Override
	public boolean isCellEditable(int columnIndex, int rowIndex){
		return false;
	}
	public void addRow(String carReg, String date, String time, String garageId){
		Booking tempBook = new Booking(carReg, date, time, garageId);
		bookings.add(tempBook);
		fireTableDataChanged();
	}
	public void removeRow(int rowIndex){
		bookings.remove(rowIndex);
		fireTableRowsDeleted(rowIndex, rowIndex);
		System.out.println("Deleted row: " + rowIndex);
	}
	public void updateRow(int rowIndex, String carReg, String date, String time){
		Booking tempBook = bookings.get(rowIndex);
		tempBook.setCarReg(carReg);
		tempBook.setDate(date);
		tempBook.setTime(time);
		setValueAt(tempBook, rowIndex, 0);
	}
}
