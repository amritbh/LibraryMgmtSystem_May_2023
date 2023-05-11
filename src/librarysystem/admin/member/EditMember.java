package librarysystem.admin.member;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import utils.MyComboBoxRenderer;
import business.LibraryMember;
import utils.MyButton;
import utils.MyJLabel;
import business.Address;
import utils.MyInput;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import business.ControllerInterface;
import business.SystemController;
import java.util.regex.Pattern;

public class EditMember extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final EditMember INSTANCE = new EditMember();
	private JTextField inputFName;
	private JTextField inputStreet;
	private JTextField inputCity;
	private JTextField inputState;
	private JTextField inputZipCode;
	private JTextField inputLastName;
	private JTextField inputPhone;
	private JLabel headerTitle;
	private JLabel labelId;
	private JComboBox<?> cbookBook;
	private JLabel labelFN;
	private JLabel labelLN;
	private JLabel labelAddr;
	private JLabel labelStreet;
	private JLabel labelState;
	private JLabel labelCity;
	private JLabel labelZC;
	private JButton saveButton;
	private JLabel labelPhone;
	
	ControllerInterface cInterface = new SystemController();

	/**
	 * Create the panel.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private EditMember() {
		setBackground(new Color(243, 248, 246));
		setLayout(null);
		setPreferredSize(new Dimension(600,600));


		headerTitle = new MyJLabel("Edit Member");
		headerTitle.setFont(new Font("PT Serif Caption", Font.BOLD, 20));
		headerTitle.setHorizontalAlignment(SwingConstants.LEFT);
		headerTitle.setBounds(10, 10, 584, 60);
		add(headerTitle);

		labelId = new MyJLabel("Memeber Id");
		labelId.setBounds(10, 129, 77, 28);
		add(labelId);


		cbookBook =  new JComboBox<>();
		List<String> allMember = cInterface.allMemberIds();
     	cbookBook.setModel(new DefaultComboBoxModel(allMember.toArray(new String[0])));
		cbookBook.setBounds(95, 127, 197, 28);
		cbookBook.setRenderer(new MyComboBoxRenderer());
		cbookBook.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String selectedId = cbookBook.getSelectedItem().toString();
				LibraryMember member = cInterface.getLibraryMemberByIdController(selectedId);
				inputFName.setText(member.getFirstName());
				inputLastName.setText(member.getLastName());
				inputPhone.setText(member.getTelephone());
				inputStreet.setText(member.getAddress().getStreet());
				inputCity.setText(member.getAddress().getCity());
				inputState.setText(member.getAddress().getState());
				inputZipCode.setText(member.getAddress().getZip());
			}
		});
		add(cbookBook);
		
		labelLN = new MyJLabel("Last Name");
		labelLN.setBounds(314, 183, 52, 28);
		add(labelLN);
		
		labelState = new MyJLabel("State");
		labelState.setBounds(10, 375, 45, 28);
		add(labelState);


		inputFName = new MyInput().getInput();
		inputFName.setColumns(10);
		inputFName.setBounds(95, 181, 197, 28);
		add(inputFName);

		labelFN = new MyJLabel("First Name");
		labelFN.setBounds(10, 185, 77, 28);
		add(labelFN);

		labelCity = new MyJLabel("City");
		labelCity.setBounds(314, 316, 45, 28);
		add(labelCity);

		labelZC = new MyJLabel("Zip Code");
		labelZC.setBounds(314, 372, 45, 28);
		add(labelZC);

		labelAddr = new MyJLabel("Address");
		labelAddr.setFont(new Font("PT Serif Caption", Font.BOLD, 18));
		labelAddr.setBounds(35, 271, 77, 20);
		add(labelAddr);

		labelStreet = new MyJLabel("Street");
		labelStreet.setBounds(10, 319, 45, 28);
		add(labelStreet);

		inputStreet = new MyInput().getInput();
		inputStreet.setBounds(93, 316, 199, 28);
		add(inputStreet);
		inputStreet.setColumns(10);
		
		inputZipCode = new MyInput().getInput();
		inputZipCode.setBounds(391, 371, 185, 28);
		add(inputZipCode);
		inputZipCode.setColumns(10);

		inputLastName = new MyInput().getInput();
		inputLastName.setColumns(10);
		inputLastName.setBounds(391, 174, 185, 28);
		add(inputLastName);

		inputCity =  new MyInput().getInput();
		inputCity.setBounds(391, 315, 185, 28);
		add(inputCity);
		inputCity.setColumns(10);

		inputState = new MyInput().getInput();
		inputState.setBounds(93, 372, 199, 28);
		add(inputState);
		inputState.setColumns(10);
		
		saveButton = new MyButton("Save", 170, 40, new Color(181, 229, 134));
		saveButton.setBounds(406, 482, 170, 40);
		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});

		saveButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String id = cbookBook.getSelectedItem().toString();
				String fName = inputFName.getText();
				String lName = inputLastName.getText();
				String state = inputState.getText();
				String phone = inputPhone.getText();
				String zC = inputZipCode.getText();
				String street = inputStreet.getText();
				String city = inputCity.getText();
			

				if(fName.isEmpty() || lName.isEmpty() || street.isEmpty() || city.isEmpty() || state.isEmpty() || phone.isEmpty() || zC.isEmpty()) {
					JOptionPane.showMessageDialog(EditMember.this, "Please fill all information");
				} else if (Pattern.matches("[a-zA-Z]+",phone) || phone.length() != 10) {
					JOptionPane.showMessageDialog(EditMember.this, "Phone should be number and have 10 digits");
				} else if(Pattern.matches("[a-zA-Z]+",zC) || zC.length() != 5) {
					JOptionPane.showMessageDialog(EditMember.this, "Zipcode should be number and have 5 digits");
				} else {
					cInterface.updateMemberController(new LibraryMember(id, fName, lName, phone, new Address(street, city, state,zC)));
				}
			}
		});
		add(saveButton);

		inputPhone = new MyInput().getInput();
		inputPhone.setBounds(391, 125, 185, 28);
		add(inputPhone);
		inputPhone.setColumns(10);
		
		labelPhone = new MyJLabel("Phone");
		labelPhone.setBounds(314, 131, 52, 28);
		add(labelPhone);
	}

	public void init() {
		try {
			EditMember panel = new EditMember();
			panel.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static EditMember getInstance() {
		return new EditMember();
	}
}
