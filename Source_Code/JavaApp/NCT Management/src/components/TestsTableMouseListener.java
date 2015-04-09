package components;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JTable;

import core.TestResults;

//Table Mouse Listener
public class TestsTableMouseListener implements MouseListener{
	private JLabel test1, test2, test3, test4, test5;
	private JTable mTable;
	private TestResults test;
	private JLabel systemInfo;
	
	public TestsTableMouseListener(JTable bTable, JLabel testt1, JLabel testt2, JLabel testt3, JLabel testt4, JLabel testt5, JLabel sysLabel){
		test1 = testt1;
		test2 = testt2;
		test3 = testt3;
		test4 = testt4;
		test5 = testt5;
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
		test = (TestResults) mTable.getValueAt(row, 0);
		test1.setText("Alignment: "+test.getTestAlignment());
		test2.setText("Suspension: "+test.getTestSuspension());
		test3.setText("Brakes: "+test.getTestBrakes());
		test4.setText("Exhaust Emission: "+test.getTestEEmission());
		test5.setText("Head Lights: "+test.getTestHeadLights());
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