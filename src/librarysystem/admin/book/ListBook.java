package librarysystem.admin.book;

import librarysystem.admin.author.AuthorPanel;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.*;
import business.Author;
import business.SystemController;
import java.awt.*;
import business.ControllerInterface;
import business.Book;
import java.util.Map.Entry;


@SuppressWarnings("serial")
public class ListBook extends JPanel {
	public static final ListBook INSTANCE = new ListBook();
	private JTextField inputAuthors;
	private JTable table;
	private JLabel headerTitle;
	private JScrollPane tableContent;
	ControllerInterface cInterface = new SystemController();
	private HashMap<String, Book> booksList = cInterface.allBooks();
	AuthorPanel authorTable = new AuthorPanel();


	public static ListBook getInstance() {
		return new ListBook();
	}

	public void defineTable() {
		String[] column = { "ISBN", "Title", "Max Checkout", "Number of Copies" };
		DefaultTableModel tableModel = new DefaultTableModel(column, 0);
		table = new JTable(tableModel);
		Iterator<Entry<String, Book>> iterator = booksList.entrySet().iterator();
		while (iterator.hasNext()) {
			@SuppressWarnings("rawtypes")
			Entry mapElement = (Entry) iterator.next();
			Book book = (Book) mapElement.getValue();
			Object[] row = { book.getIsbn(), book.getTitle(), book.getMaxCheckoutLength(), book.getCopyNums() };
			tableModel.addRow(row);
		}
		table.setSize(new Dimension(500, 300));
		table.setBackground(new Color(224, 224, 224));
	}
	/**
	 * Create the panel.
	 */

	private ListBook() {
		setLayout(null);
		setPreferredSize(new Dimension(600, 600));
		setBackground(new Color(243, 248, 246));

		headerTitle = new JLabel("List Books");
		headerTitle.setFont(new Font("PT Serif Caption", Font.BOLD, 20));
		headerTitle.setHorizontalAlignment(SwingConstants.LEFT);
		headerTitle.setBounds(10, 10, 584, 60);
		add(headerTitle);

		tableContent = new JScrollPane();
		tableContent.setBounds(10, 218, 584, 294);
		add(tableContent);

		defineTable();
		tableContent.add(table);
		tableContent.setViewportView(table);
	}


}