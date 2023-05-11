package librarysystem.admin.member;

import java.util.List;
import javax.swing.*;
import business.SystemController;
import business.ControllerInterface;
import utils.MyButton;
import utils.MyInput;
import java.awt.*;
import business.Address;
import business.LibraryMember;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddMember extends JPanel {
	public static final AddMember INSTANCE = new AddMember();
	private JTextField InputId;
	private JTextField inputFName;
	private JTextField inputLName;
	private JTextField inputPhone;
	private JTextField inputStreet;
	private JTextField inputCity;
	private JTextField inputState;
	private JTextField inputZipCode;
	private JLabel headerTitle;
	private JLabel labelId;
	private JLabel labelFname;
	private JLabel labelLn;
	private JLabel labelPhone;
	private JLabel labelAdd;
	private JLabel labelStreet;
	private JLabel labelCity;
	private JLabel labelState;
	private JLabel labelZc;
	private JButton addButton;

	ControllerInterface cInterface = new SystemController();
	List<String> listId = cInterface.allMemberIds();

	
	public static AddMember getInstance() {
		return new AddMember();
	}

	/**
	 * Create the panel.
	 */
	private AddMember() {
		setBackground(new Color(243, 248, 246));
		setLayout(null);
		setPreferredSize(new Dimension(600, 600));

		headerTitle = new JLabel("Add Member");
		headerTitle.setFont(new Font("PT Serif Caption", Font.BOLD, 20));
		headerTitle.setHorizontalAlignment(SwingConstants.LEFT);
		headerTitle.setBounds(10, 10, 584, 60);
		add(headerTitle);

		labelId = new JLabel("ID");
		labelId.setBounds(10, 185, 77, 13);
		add(labelId);

		inputFName = new MyInput().getInput();
		inputFName.setColumns(10);
		inputFName.setBounds(95, 127, 197, 28);
		add(inputFName);

		labelLn = new JLabel("Last Name");
		labelLn.setBounds(314, 131, 52, 13);
		add(labelLn);

		inputLName = new MyInput().getInput();
		inputLName.setColumns(10);
		inputLName.setBounds(391, 122, 185, 28);
		add(inputLName);

		InputId = new MyInput().getInput();
		InputId.setBounds(95, 181, 197, 28);
		add(InputId);
		InputId.setColumns(10);

		labelFname = new JLabel("First Name");
		labelFname.setBounds(10, 129, 77, 13);
		add(labelFname);

		labelPhone = new JLabel("Phone");
		labelPhone.setBounds(314, 185, 45, 13);
		add(labelPhone);

		labelStreet = new JLabel("Street");
		labelStreet.setBounds(10, 319, 45, 28);
		add(labelStreet);

		inputStreet = new MyInput().getInput();
		inputStreet.setBounds(93, 316, 199, 28);
		add(inputStreet);
		inputStreet.setColumns(10);

		labelCity = new JLabel("City");
		labelCity.setBounds(314, 316, 45, 13);
		add(labelCity);

		inputPhone = new MyInput().getInput();
		inputPhone.setBounds(391, 181, 185, 28);
		add(inputPhone);
		inputPhone.setColumns(10);

		labelAdd = new JLabel("Address");
		labelAdd.setFont(new Font("Skia", Font.ITALIC, 14));
		labelAdd.setBounds(35, 271, 77, 20);
		add(labelAdd);

		inputCity = new MyInput().getInput();
		inputCity.setBounds(391, 315, 185, 28);
		add(inputCity);
		inputCity.setColumns(10);

		labelState = new JLabel("State");
		labelState.setBounds(10, 375, 45, 13);
		add(labelState);

		inputState = new MyInput().getInput();
		inputState.setBounds(93, 372, 199, 28);
		add(inputState);
		inputState.setColumns(10);

		labelZc = new JLabel("Zip code ");
		labelZc.setBounds(314, 372, 45, 13);
		add(labelZc);

		inputZipCode = new MyInput().getInput();
		inputZipCode.setBounds(391, 371, 185, 28);
		add(inputZipCode);
		inputZipCode.setColumns(10);

		addButton = new MyButton("Add", 170, 40, new Color(181, 229, 134));
		addButton.setBounds(406, 482, 170, 40);
		addButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String id = InputId.getText();
				String firstName = inputFName.getText();
				String lastName = inputLName.getText();
				String street = inputStreet.getText();
				String city = inputCity.getText();
				String state = inputState.getText();
				String phone = inputPhone.getText();
				String zipCode = inputZipCode.getText();
				if (id.isEmpty() || firstName.isEmpty() || lastName.isEmpty() || street.isEmpty() || city.isEmpty()
						|| state.isEmpty() || phone.isEmpty() || zipCode.isEmpty()) {
					JOptionPane.showMessageDialog(AddMember.this, "Please fill all information");
				} else if (Pattern.matches("[a-zA-Z]+", phone) || phone.length() != 10) {
					JOptionPane.showMessageDialog(AddMember.this, "Phone should be number and have 10 digits");
				} else if (listId.contains(InputId.getText())) {
					JOptionPane.showMessageDialog(AddMember.this, "Id already existed");
				} else if (Pattern.matches("[a-zA-Z]+", zipCode) || zipCode.length() != 5) {
					JOptionPane.showMessageDialog(AddMember.this, "Zipcode should be number and have 5 digits");
				} else {
					Address add = new Address(street, city, state, zipCode);
					LibraryMember member = new LibraryMember(id, firstName, lastName, phone, add);
					cInterface.addNewMemberController(member);
					JOptionPane.showMessageDialog(AddMember.this, "Saved");
				}

			}
		});
		add(addButton);
	}

	public void init() {
		try {
			AddMember panel = new AddMember();
			panel.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}


