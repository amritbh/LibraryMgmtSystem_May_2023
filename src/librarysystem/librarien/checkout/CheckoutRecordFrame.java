package librarysystem.librarien.checkout;

import business.CheckoutRecord;
import business.SystemController;
import dataaccess.DataAccessFacade;
import utils.MyButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;





public class CheckoutRecordFrame extends JPanel {
	public static final CheckoutRecordFrame INSTANCE = new CheckoutRecordFrame();
	private JTable tableContent;
	private JLabel headerLabel;
	private JLabel idlabel;
	private JComboBox idDropdown;
	private JButton submitButton;
	private JScrollPane scrollPaneContent;

	/**
	 * Create the panel.
	 */
	private CheckoutRecordFrame() {
		setBackground(new Color(243, 248, 246));
		SystemController ci = new SystemController();
		setLayout(null);
		setPreferredSize(new Dimension(600,600));

		headerLabel = new JLabel("Checkout Record");
		headerLabel.setFont(new Font("PT Serif Caption", Font.BOLD, 20));
		headerLabel.setHorizontalAlignment(SwingConstants.LEFT);
		headerLabel.setBounds(10, 10, 584, 60);
		add(headerLabel);

		idlabel = new JLabel("Member ID");
		idlabel.setBounds(10, 129, 77, 13);
		add(idlabel);

		idDropdown = new JComboBox();
		idDropdown.setBounds(91, 126, 198, 21);
		List<String> members = ci.allMemberIdsByName();
		idDropdown.setModel(new DefaultComboBoxModel<>(members.toArray(new String[0])));
		add(idDropdown);

		submitButton = new MyButton("Submit", 170, 40, new Color(181, 229, 134));
		submitButton.setBounds(424, 171, 170, 40);
		add(submitButton);

		submitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String[] columnNames = {"Response"};
				List<CheckoutRecord> records= ci.findMemberCheckoutRecordController(idDropdown.getSelectedItem().toString().split("#")[1]);
				String[] data = { };
				DefaultTableModel model = new DefaultTableModel(columnNames, 0);


				for (CheckoutRecord line : records) {
					String properties = DataAccessFacade.findProperties(Arrays.asList("checkoutId", "memberId", "bookCopyId", "checkoutDate", "dueDate" ),
							line.toString() );

					for (String row : properties.split("\n")) {
						System.out.println(row);
						Object[] rows = {row};
						model.addRow(rows);
					}
					model.addRow(new   Object[] {});

				}

				//  model.addRow(data);
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
			CheckoutRecordFrame panel = new CheckoutRecordFrame();
			panel.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static CheckoutRecordFrame getInstance() {
		return new CheckoutRecordFrame();
	}
}
