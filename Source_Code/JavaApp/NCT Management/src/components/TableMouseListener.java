package components;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;

import core.Booking;

//Table Mouse Listener
public class TableMouseListener implements MouseListener{
	private JTextField mCarReg, mDate, mTime;
	private JTable mTable;
	private Booking mBooking;
	private JLabel systemInfo;
	
	public TableMouseListener(JTable bTable, JTextField bCarReg, JTextField bDate, JTextField bTime, JLabel sysLabel){
		mCarReg = bCarReg;
		mDate = bDate;
		mTime = bTime;
		mTable = bTable;
		systemInfo = sysLabel;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
	}
	@Override
	public void mousePressed(MouseEvent e) {
		systemInfo.setText("Booking Table");
		int row = mTable.rowAtPoint(e.getPoint());
		mBooking = (Booking) mTable.getValueAt(row, 0);
		mCarReg.setText(mBooking.getCarReg());
		mDate.setText(mBooking.getDate());
		mTime.setText(mBooking.getTime());
	}
	@Override
	public void mouseReleased(MouseEvent e) {
	}
	@Override
	public void mouseEntered(MouseEvent e) {
	}
	@Override
	public void mouseExited(MouseEvent e) {
	}
}