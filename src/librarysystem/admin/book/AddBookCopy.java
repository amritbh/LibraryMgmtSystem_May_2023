package librarysystem.admin.book;

import java.util.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import business.ControllerInterface;
import business.SystemController;
import utils.MyButton;
import java.awt.*;
import business.Book;
import utils.MyJLabel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class AddBookCopy extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final AddBookCopy INSTANCE = new AddBookCopy();
	private JTable tableData;
	private JLabel headerTitle;
	private JLabel bookISBN;
	private JButton addButton;
	private JScrollPane tableContent;
	SystemController ca = new SystemController();
	ControllerInterface cInterface = new SystemController();
	private HashMap<String, Book> bookList = cInterface.allBooks();
	
	public static AddBookCopy getInstance() {
		return new AddBookCopy();
	}
	
	public void defineTable() {
		String[] column = { "ISBN", "Title", "Max Checkout", "Number of Copies" };
		DefaultTableModel tableModel = new DefaultTableModel(column, 0);
		tableData = new JTable(tableModel);
		Iterator iterator = bookList.entrySet().iterator();
		while (iterator.hasNext()) {
			@SuppressWarnings("rawtypes")
			Map.Entry mapElement = (Map.Entry) iterator.next();
			Book book = (Book) mapElement.getValue();
			Object[] row = { book.getIsbn(), book.getTitle(), book.getMaxCheckoutLength(), book.getCopyNums() };
			tableModel.addRow(row);
		}
		tableData.setSize(new Dimension(500, 300));
		tableData.setBackground(new Color(224, 224, 224));
	}

	/**
	 * Create the panel.
	 */
	private AddBookCopy() {
		setBackground(new Color(243, 248, 246));
		setLayout(null);
		setPreferredSize(new Dimension(600, 600));

		headerTitle = new JLabel("ADD COPY");
		headerTitle.setFont(new Font("PT Serif Caption", Font.BOLD, 20));
		headerTitle.setHorizontalAlignment(SwingConstants.LEFT);
		headerTitle.setBounds(10, 10, 584, 60);
		add(headerTitle);

	
		JComboBox selectISBN = new JComboBox();
		selectISBN.setBounds(57, 126, 251, 21);
		List<String> books = ca.allBookIdsByTitle();
		selectISBN.setModel(new DefaultComboBoxModel<>(books.toArray(new String[0])));
		add(selectISBN);
		
		bookISBN = new MyJLabel("ISBN");
		bookISBN.setBounds(10, 129, 77, 13);
		add(bookISBN);


		addButton = new MyButton("Add Copy", 170, 40, new Color(181, 229, 134));
		addButton.setBounds(424, 171, 170, 40);
		addButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String book = selectISBN.getSelectedItem().toString().split("#")[1];
				if (!cInterface.allBooks().containsKey(book)) {
					JOptionPane.showMessageDialog(AddBookCopy.this, "Book not found - " + book);
				} else {
					cInterface.addBookCopyController(book);
					bookList = cInterface.allBooks();
					defineTable();
					tableContent.setViewportView(tableData);
					JOptionPane.showMessageDialog(AddBookCopy.this, "New copy added successfully!");
				}

			}
		});
		add(addButton);
		tableContent = new JScrollPane();
		tableContent.setBounds(10, 218, 584, 294);
		add(tableContent);
		defineTable();
		tableContent.setViewportView(tableData);
	}

	public void init() {
		try {
			AddBookCopy panel = new AddBookCopy();
			panel.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
