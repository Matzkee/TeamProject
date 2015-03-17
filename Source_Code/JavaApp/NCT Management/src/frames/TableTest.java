package frames;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextPane;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;

import core.Booking;
import core.Client;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.util.ArrayList;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JSeparator;

public class TableTest extends JFrame {

	private static final long serialVersionUID = 1L;
	private ArrayList<Booking> allBookings = new ArrayList<>();
	private Client testClient;

	/**
	 * Create the panel.
	 * Constructor
	 */
	public TableTest() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		testClient = new Client();
		testClient.viewBookings();
		
		allBookings = testClient.getBookings();
		
		JPanel panel = new JPanel();
		getContentPane().add(panel);
		
		//Booking bookTest = new Booking();
		//bookTest.setCarReg("111111");
		//bookTest.setDate("111111");
		//bookTest.setTime("111111");
		
		//allBookings.add(bookTest);
		
		JTable table = new JTable(new TableModel(allBookings));
		table.setDefaultRenderer(Booking.class, new TableRenderer());
		panel.setLayout(null);
		table.setRowHeight(40);
		table.setTableHeader(null);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(0, 30, 400, 175);
		panel.add(scrollPane);
		
		JPanel headerPane = new JPanel();
		headerPane.setBackground(Color.DARK_GRAY);
		headerPane.setBounds(0, 0, 400, 30);
		panel.add(headerPane);
		headerPane.setLayout(null);
		
		JLabel lblHeader = new JLabel("Bookings");
		lblHeader.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblHeader.setForeground(Color.LIGHT_GRAY);
		lblHeader.setBounds(5, 0, 100, 30);
		headerPane.add(lblHeader);
		
		JSeparator headerSeparator = new JSeparator();
		headerSeparator.setForeground(Color.LIGHT_GRAY);
		headerSeparator.setBounds(100, 25, 275, 1);
		headerPane.add(headerSeparator);
		
	}
	
	// Table Cell Renderer
	// renders each cell
	class TableRenderer implements TableCellRenderer{
		TableRow cell;
		
		public TableRenderer(){
			cell = new TableRow();
		}
		@Override
		public Component getTableCellRendererComponent(JTable table,
				Object value, boolean isSelected, boolean hasFocus, int row,
				int column) {
			Booking book = (Booking) value;
			cell.setRowLabels(book);
			return cell;
		}
		
	}
	
	
	// Table Model
	// contains all info from Booking classes
	class TableModel extends AbstractTableModel{

		private static final long serialVersionUID = 1L;
		private ArrayList<Booking> bookings = new ArrayList<>();
		
		public TableModel(ArrayList<Booking> newBookings){
			bookings = newBookings;
		}
		
		
		@Override
		public String getColumnName(int columnIndex){
			return "Booking";
		}
		@Override
		public Class getColumnClass(int columnIndex){
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
		public boolean isCellEditable(int columnIndex, int rowIndex){
			return false;
		}
		
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TableTest frame = new TableTest();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
