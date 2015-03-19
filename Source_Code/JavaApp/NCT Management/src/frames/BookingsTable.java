package frames;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;

import core.Booking;
import core.Client;

public class BookingsTable extends JPanel {

	private static final long serialVersionUID = 1L;
	private ArrayList<Booking> allBookings = new ArrayList<>();
	private Client testClient;
	private JLabel CarReg;
	private JLabel Date;
	private JLabel Time;

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
		TableModel tableModel = new TableModel(allBookings);
		JTable table = new JTable(tableModel);
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
		// Date label
		JLabel lblDate = new JLabel("Date: ");
		lblDate.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblDate.setForeground(Color.WHITE);
		lblDate.setBounds(10, 236, 99, 14);
		add(lblDate);
		// Time label
		JLabel lblTime = new JLabel("Time: ");
		lblTime.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblTime.setForeground(Color.WHITE);
		lblTime.setBounds(10, 261, 99, 14);
		add(lblTime);
		
		CarReg = new JLabel("");
		CarReg.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		CarReg.setForeground(Color.WHITE);
		CarReg.setBounds(119, 212, 86, 14);
		add(CarReg);
		
		Date = new JLabel("");
		Date.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		Date.setForeground(Color.WHITE);
		Date.setBounds(119, 236, 86, 14);
		add(Date);
		
		Time = new JLabel("");
		Time.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		Time.setForeground(Color.WHITE);
		Time.setBounds(119, 261, 86, 14);
		add(Time);
		
		// Apply table mouse listener
		// Set at the end to allow components to be declared before put into listener
		table.addMouseListener(new TableMouseListener(table, CarReg, Date, Time));
		
		JButton btnDeleteBooking = new JButton("Delete Booking");
		btnDeleteBooking.setBackground(Color.LIGHT_GRAY);
		btnDeleteBooking.setBounds(280, 258, 110, 23);
		btnDeleteBooking.addMouseListener(new DeleteButtonMouseListener(table, tableModel));
		add(btnDeleteBooking);
		
		JButton btnModifyBooking = new JButton("Modify Booking");
		btnModifyBooking.setBounds(280, 208, 110, 23);
		add(btnModifyBooking);
	}
	
	// Delete Button Mouse Listener
	class DeleteButtonMouseListener implements MouseListener{
		private JTable dTable;
		private TableModel dTableModel;
		
		public DeleteButtonMouseListener(JTable table, TableModel tableModel){
			dTable = table;
			dTableModel = tableModel;
		}
		
		@Override
		public void mouseClicked(MouseEvent e) {
			if (dTable.getSelectedRow() != -1){
				dTableModel.removeRow(dTable.getSelectedRow());
			}			
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
	
	// Table Mouse Listener
	class TableMouseListener implements MouseListener{
		private JLabel mCarReg;
		private JLabel mDate;
		private JLabel mTime;
		private JTable mTable;
		private Booking mBooking;
		
		public TableMouseListener(JTable bTable, JLabel bCarReg, JLabel bDate, JLabel bTime){
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
		public void removeRow(int rowIndex){
			bookings.remove(rowIndex);
			fireTableRowsDeleted(rowIndex, rowIndex);
			System.out.println("Deleted row: " + rowIndex);
		}
	}
}
