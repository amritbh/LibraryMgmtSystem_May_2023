package librarysystem.librarien.checkout;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.*;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import business.SystemController;
import utils.MyButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.util.List;


public class OverdueCheckoutFrame extends JPanel {
	public static final OverdueCheckoutFrame INSTANCE = new OverdueCheckoutFrame();
	private JTable tableContent;
	private JLabel headerLabel;
	private JLabel isbnLabel;
	private JComboBox isbnDropdown;
	private JButton submitButton;
	private JScrollPane scrollPaneContent;
	/**
	 * Create the panel.
	 */
	private OverdueCheckoutFrame() {
		setBackground(new Color(243, 248, 246));
		SystemController ci = new SystemController();
		setLayout(null);
		setPreferredSize(new Dimension(600,600));
		
		headerLabel = new JLabel("Overdue Checkout");
		headerLabel.setFont(new Font("PT Serif Caption", Font.BOLD, 20));
		headerLabel.setHorizontalAlignment(SwingConstants.LEFT);
		headerLabel.setBounds(10, 10, 584, 60);

		add(headerLabel);
		
		isbnLabel = new JLabel("ISBN");
		isbnLabel.setBounds(10, 129, 77, 13);
		add(isbnLabel);
		
		isbnDropdown = new JComboBox();
		isbnDropdown.setBounds(57, 126, 251, 21);
		List<String> books = ci.allBookIdsByTitle();
		isbnDropdown.setModel(new DefaultComboBoxModel<>(books.toArray(new String[0])));
		add(isbnDropdown);
		
		submitButton = new MyButton("Submit", 170, 40, new Color(181, 229, 134));
		submitButton.setBounds(424, 171, 170, 40);
		add(submitButton);
		
		submitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String[] columnNames = {"Response"};
				String records= ci.overDueCheckoutController(isbnDropdown.getSelectedItem().toString().split("#")[1]);
				System.out.println(records);
			    String[] data = records.split("\n");
			    DefaultTableModel model = new DefaultTableModel(columnNames, 0);
			    
				int counter =1;
				  for (String line : data) {
					  Object[] row = {line};  
				        model.addRow(row);
				        if(counter==3) {
				        	 model.addRow(new   Object[] {});
				        	 counter=0;
				        }
				        counter++;
				 }
				 
			    			    tableContent.setModel(model);
			  


			 
			//	model.setValueAt(), 0, 0);
	               // model.setValueAt(35, 0, 1);
	                // update the table to reflect the changes in the model
	                tableContent.repaint();
			}
		});
		
		
		scrollPaneContent = new JScrollPane();
		scrollPaneContent.setBounds(10, 218, 584, 294);
		add(scrollPaneContent);
		
		tableContent = new JTable();
		scrollPaneContent.setViewportView(tableContent);
	}

	public void init() {
		try {
			OverdueCheckoutFrame panel = new OverdueCheckoutFrame();
			panel.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static OverdueCheckoutFrame getInstance() {
		return new OverdueCheckoutFrame();
	}
}
