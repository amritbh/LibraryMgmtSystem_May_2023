package librarysystem.librarien.checkout;

import java.awt.Dimension;
import javax.swing.DefaultCellEditor;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.JTable;
import java.awt.Component;
import javax.swing.JScrollPane;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.table.TableCellRenderer;
import javax.swing.SwingConstants;
import business.SystemController;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import utils.MyButton;

public class CheckoutBook extends JPanel {
	public static final CheckoutBook INSTANCE = new CheckoutBook();
	private JTable contentTable;
	private JLabel lHeader;
	private JLabel labelID;
	private JComboBox memberDropdown;
	private JLabel isbnLabel;
	private JButton checkoutButton;
	private JComboBox<String> isbnDropdown;
	private JScrollPane scrollPaneContent;
	/**
	 * Create the panel.
	 */
	private CheckoutBook() {
		setBackground(new Color(243, 248, 246));
		setLayout(null);
		setPreferredSize(new Dimension(600,600));
		SystemController ci = new SystemController();
		
		lHeader = new JLabel("Checkout Book");
		lHeader.setFont(new Font("PT Serif Caption", Font.BOLD, 20));
		lHeader.setHorizontalAlignment(SwingConstants.LEFT);
		lHeader.setBounds(10, 10, 584, 60);
		add(lHeader);
		
		labelID = new JLabel("Member ID");
		labelID.setBounds(10, 129, 77, 13);
		add(labelID);
		
		memberDropdown = new JComboBox();
		memberDropdown.setBounds(91, 126, 198, 21);
		List<String> members = ci.allMemberIdsByName();
		memberDropdown.setModel(new DefaultComboBoxModel<>(members.toArray(new String[0])));
		add(memberDropdown);
		
		isbnLabel = new JLabel("ISBN");
		isbnLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		isbnLabel.setBounds(301, 129, 28, 13);
		add(isbnLabel);
		
		checkoutButton = new MyButton("Checkout", 170, 40, new Color(181, 229, 134));
		checkoutButton.setBounds(424, 171, 170, 40);
		checkoutButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String[] columnNames = {"Response"};
			    String[] data = ci.makeCheckoutController(memberDropdown.getSelectedItem().toString().split("#")[1], isbnDropdown.getSelectedItem().toString().split("#")[1]).split("\n");
			    DefaultTableModel model = new DefaultTableModel(columnNames, 0);		  
				  for (String line : data) {
					  Object[] row = {line};  
				        model.addRow(row);
				 }
				    contentTable.setModel(model);
	                contentTable.repaint();
			}
			
		});
		
		add(checkoutButton);
		
		isbnDropdown = new JComboBox();
		isbnDropdown.setBounds(341, 126, 253, 21);
		List<String> books = ci.allBookIdsByTitle();
		isbnDropdown.setModel(new DefaultComboBoxModel<>(books.toArray(new String[0])));
		add(isbnDropdown);
		
		scrollPaneContent = new JScrollPane();
		scrollPaneContent.setBounds(10, 218, 584, 294);
		add(scrollPaneContent);
		
	        
		contentTable = new JTable();
		  contentTable.setDefaultRenderer(String.class, new TextAreaRenderer());
		    contentTable.setDefaultEditor(String.class, new TextAreaEditor());
		scrollPaneContent.setViewportView(contentTable);
	}

	public CheckoutBook init() {
		CheckoutBook panel;
		panel = new CheckoutBook();
		panel.setVisible(true);
		return panel;
	}
	class TextAreaRenderer extends JTextArea implements TableCellRenderer {
	    public TextAreaRenderer() {
	        setLineWrap(true);
	        setWrapStyleWord(true);
	    }

	    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
	        setText((String) value);
	        setSize(table.getColumnModel().getColumn(column).getWidth(), getPreferredSize().height);
	        if (table.getRowHeight(row) != getPreferredSize().height) {
	            table.setRowHeight(row, getPreferredSize().height);
	        }
	        return this;
	    }
	}
	class TextAreaEditor extends DefaultCellEditor {
	    protected JTextArea textArea;

	    public TextAreaEditor() {
	        super(new JTextField());
	        textArea = new JTextArea();
	        textArea.setLineWrap(true);
	        textArea.setWrapStyleWord(true);
	        textArea.setBorder(null);
	    }

	    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
	        textArea.setText((String) value);
	        return textArea;
	    }

	    public Object getCellEditorValue() {
	        return textArea.getText();
	    }
	}
	public static CheckoutBook getInstance() {
		// TODO Auto-generated method stub
		return new CheckoutBook();
	}
}
