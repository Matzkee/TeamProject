package frames;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;

import core.Booking;
import core.Client;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BookingsTable extends JPanel {

	private static final long serialVersionUID = 1L;
	private ArrayList<Booking> allBookings = new ArrayList<>();
	private Client testClient;

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
		setSize(400,200);
		
		// Setting up the table
		JTable table = new JTable(new TableModel(allBookings));
		table.setBackground(Color.DARK_GRAY);
		table.setDefaultRenderer(Booking.class, new TableRenderer());
		setLayout(null);
		table.setRowHeight(40);
		table.setTableHeader(null);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
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
