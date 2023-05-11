package librarysystem.admin.book;

import librarysystem.admin.author.AuthorPanel;
import utils.MyButton;
import java.util.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import business.Book;
import business.Author;
import business.SystemController;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import utils.MyJLabel;
import java.awt.event.ActionEvent;
import business.ControllerInterface;
import java.util.Map.Entry;
import utils.MyInput;


public class AddBook extends JPanel {
	private JTextField inputIsbn;
	private JTextField inputTitle;
	private JTextField inputCopy;
	private JTextField inputAuthors;
	private JTable table;
	private JLabel headerTitle;
	private JLabel labelIsbn;
	private JLabel labeltitle;
	private JLabel labelAuthor;
	private JLabel LabelMax;
	private JLabel labelCopies;
	private JButton buttonAuthor;
	private JComboBox<?> comboBoxMax;
	private JButton addButton;
	private JScrollPane tableContent;
	ControllerInterface cInterface = new SystemController();
	private HashMap<String, Book> bookList = cInterface.allBooks();
	public static final AddBook INSTANCE = new AddBook();
	AuthorPanel authorPanel = new AuthorPanel();
	
	public static AddBook getInstance() {
		return new AddBook();
	}
	
	public void defineTable() {
		String[] columns = {"ISBN", "Title", "Max Checkout", "Number of Copies"};
		DefaultTableModel tableModel = new DefaultTableModel(columns, 0);
		table = new JTable(tableModel);
		Iterator iterator = bookList.entrySet().iterator();
		while (iterator.hasNext()) {
			@SuppressWarnings("rawtypes")
			Entry mapElement = (Entry) iterator.next();
			Book book = (Book) mapElement.getValue();
			Object[] row = {book.getIsbn(), book.getTitle(), book.getMaxCheckoutLength(), book.getCopyNums()};
			tableModel.addRow(row);
		}
		table.setSize(new Dimension(500,300));
		table.setBackground(new Color(224,224,224));
	}

	/**
	 * Create the panel.
	 */

	private AddBook() {
		setBackground(new Color(243, 248, 246));
		setLayout(null);
		setPreferredSize(new Dimension(600,600));


		headerTitle = new JLabel("Add Book");
		headerTitle.setFont(new Font("PT Serif Caption", Font.BOLD, 20));
		headerTitle.setHorizontalAlignment(SwingConstants.LEFT);
		headerTitle.setBounds(10, 10, 584, 60);
		add(headerTitle);
		
		labeltitle = new MyJLabel("Title");
		labeltitle.setBounds(342, 106, 37, 13);
		add(labeltitle);
		
		inputIsbn = new MyInput().getInput();
		inputIsbn.setBounds(74, 106, 163, 28);
		add(inputIsbn);
		inputIsbn.setColumns(10);
		
		
		inputTitle = new MyInput().getInput();
		inputTitle.setBounds(389, 103, 153, 28);
		add(inputTitle);
		inputTitle.setColumns(10);
		
		labelIsbn = new MyJLabel("ISBN");
		labelIsbn.setBounds(10, 109, 45, 13);
		add(labelIsbn);
		
		labelAuthor = new MyJLabel("Authors");
		labelAuthor.setBounds(10, 157, 45, 13);
		add(labelAuthor);
		
		
		inputCopy = new MyInput().getInput();
		inputCopy.setBounds(74, 197, 163, 28);
		add(inputCopy);
		inputCopy.setColumns(10);
		
		LabelMax = new MyJLabel("Max CheckOut");
		LabelMax.setBounds(353, 196, 82, 13);
		add(LabelMax);
		
		labelCopies = new MyJLabel("Copies");
		labelCopies.setBounds(10, 200, 45, 13);
		add(labelCopies);
		
		inputAuthors = new MyInput().getInput();
		inputAuthors.setBounds(74, 153, 163, 28);
		List<Author> authorList = authorPanel.getAuthorList();

		inputAuthors.setEnabled(false);
		add(inputAuthors);
		inputAuthors.setColumns(10);
		
		
		comboBoxMax = new JComboBox();
		comboBoxMax.setModel(new DefaultComboBoxModel(new String[] {"7", "21"}));
		comboBoxMax.setBounds(445, 192, 108, 21);
		add(comboBoxMax);
		
	
		
		buttonAuthor = new MyButton("+");
		buttonAuthor.setFont(new Font("Tahoma", Font.PLAIN, 7));
		buttonAuthor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StringBuilder sb = new StringBuilder();
				for (Author autor: authorList) {
					sb.append(autor.getFirstName());
					sb.append(", ");
				}
				inputAuthors.setText(sb.toString());
				authorPanel.setVisible(true);
			}
		});
		
		buttonAuthor.setBounds(497, 141, 45, 30);
		add(buttonAuthor);
		addButton = new MyButton("Add Book", 170, 40, new Color(181, 229, 134));
		addButton.setBounds(424, 256, 170, 40);
		addButton.addActionListener((e)-> {
				String isbn = inputIsbn.getText();
				String title = inputTitle.getText();
				int copy;
				List<Author> authors = authorPanel.getAuthorList();
				if(inputCopy.getText().isEmpty() || inputCopy.getText().length() == 0) {
					copy = 0;
				} else {
					copy = Integer.parseInt(inputCopy.getText());
				}
				int maxCheckout = Integer.parseInt(comboBoxMax.getSelectedItem().toString()) ;
				if(isbn.isEmpty() || title.isEmpty() || authors.isEmpty()) {
					JOptionPane.showMessageDialog(AddBook.this, "Please fill all information");
				} else if(cInterface.allBookIds().contains(isbn)) {
					JOptionPane.showMessageDialog(AddBook.this, "Book already existed");
				} else {
					cInterface.addBookController(new Book(isbn, title, maxCheckout, authors, copy));
					defineTable();
				}
			
		});
		add(addButton);
		tableContent = new JScrollPane();
		tableContent.setBounds(10, 302, 584, 210);
		add(tableContent);
		defineTable();
		tableContent.add(table);
		tableContent.setViewportView(table);
	}


	public void publishAuthor(List<Author> authorList) {
		StringBuilder sb = new StringBuilder();
		for (Author a: authorList) {
			sb.append(a.getFirstName());
			sb.append(", ");
		}
		inputAuthors.setText(sb.toString());
		authorPanel.setVisible(true);
	}


	
}
