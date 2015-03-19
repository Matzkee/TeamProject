package frames;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;

import core.Booking;
import core.Client;
import javax.swing.ListSelectionModel;

public class BookingsTable extends JPanel {

	private static final long serialVersionUID = 1L;
	private ArrayList<Booking> allBookings = new ArrayList<>();
	private Client testClient;
	private JTextField txtCarReg;
	private JTextField txtDate;
	private JTextField txtTime;

	/**
	 * Create the panel.
	 * Constructor
	 */
	public BookingsTable() {
		setBackground(Color.DARK_GRAY);
		
		testClient = new Client();
		testClient.viewBookings();
		
		allBookings = testClient.getBookings();
		
		// Panel Size
		setSize(400,380);
		
		// Setting up the table
		JTable table = new JTable(new TableModel(allBookings));
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setBackground(Color.DARK_GRAY);
		table.setDefaultRenderer(Booking.class, new TableRenderer());
		setLayout(null);
		table.setRowHeight(40);
		table.setTableHeader(null);
		// ScrollPane for the table
		JScrollPane tableScrollPane = new JScrollPane(table);
		tableScrollPane.setBackground(Color.DARK_GRAY);
		tableScrollPane.setBounds(0, 30, 400, 170);
		add(tableScrollPane);
		// Header Panel
		JPanel headerPane = new JPanel();
		headerPane.setBackground(Color.DARK_GRAY);
		headerPane.setBounds(0, 0, 400, 30);
		add(headerPane);
		headerPane.setLayout(null);
		// Label for the header panel
		JLabel lblHeader = new JLabel("Bookings");
		lblHeader.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblHeader.setForeground(Color.LIGHT_GRAY);
		lblHeader.setBounds(5, 0, 100, 30);
		headerPane.add(lblHeader);
		// Separator inside header panel
		JSeparator headerSeparator = new JSeparator();
		headerSeparator.setForeground(Color.LIGHT_GRAY);
		headerSeparator.setBounds(100, 25, 275, 1);
		headerPane.add(headerSeparator);
		// CarReg label
		JLabel lblCarRegistration = new JLabel("Car Registration: ");
		lblCarRegistration.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblCarRegistration.setForeground(Color.WHITE);
		lblCarRegistration.setBounds(10, 211, 99, 14);
		add(lblCarRegistration);
		// CarReg text field
		txtCarReg = new JTextField();
		txtCarReg.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		txtCarReg.setForeground(new Color(95, 95, 99));
		txtCarReg.setBounds(119, 208, 86, 20);
		add(txtCarReg);
		txtCarReg.setColumns(10);
		// Date label
		JLabel lblDate = new JLabel("Date: ");
		lblDate.setForeground(Color.WHITE);
		lblDate.setBounds(10, 236, 99, 14);
		add(lblDate);
		// Date text field
		txtDate = new JTextField();
		txtDate.setBounds(119, 233, 86, 20);
		add(txtDate);
		txtDate.setColumns(10);
		// Time label
		JLabel lblTime = new JLabel("Time: ");
		lblTime.setForeground(Color.WHITE);
		lblTime.setBounds(10, 261, 99, 14);
		add(lblTime);
		// Time text field
		txtTime = new JTextField();
		txtTime.setBounds(119, 258, 86, 20);
		add(txtTime);
		txtTime.setColumns(10);
		
		// Add Mouse Listener to the table
		// Set at the end so that components get declared before put into listener
		table.addMouseListener(new TableMouseListener(table,txtCarReg,txtDate,txtTime));
	}
	
	// Table Mouse Listener
	class TableMouseListener implements MouseListener{
		private JTextField mCarReg;
		private JTextField mDate;
		private JTextField mTime;
		private JTable mTable;
		private Booking mBooking;
		
		public TableMouseListener(JTable bTable, JTextField bCarReg, JTextField bDate, JTextField bTime){
			mCarReg = bCarReg;
			mDate = bDate;
			mTime = bTime;
			mTable = bTable;
		}
		
		@Override
		public void mouseClicked(MouseEvent e) {
			int row = mTable.rowAtPoint(e.getPoint());
			mBooking = (Booking) mTable.getValueAt(row, 0);
			
			mCarReg.setText(mBooking.getCarReg());
			mDate.setText(mBooking.getDate());
			mTime.setText(mBooking.getTime());
		}
		@Override
		public void mousePressed(MouseEvent e) {
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
	
	// Table Cell Renderer
	// renders each cell
	class TableRenderer implements TableCellRenderer{
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
	
	
	// Table Model
	// contains all info from Booking classes
	class TableModel extends AbstractTableModel{

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
		public boolean isCellEditable(int columnIndex, int rowIndex){
			return false;
		}
		
	}
}
