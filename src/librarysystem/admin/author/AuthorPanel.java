package librarysystem.admin.author;
import business.Author;
import java.awt.*;
import librarysystem.admin.book.AddBook;
import javax.swing.*;
import utils.MyInput;
import javax.swing.border.EmptyBorder;
import java.util.List;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.util.regex.Pattern;
import business.Address;
import utils.MyButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;





public class AuthorPanel extends JFrame {

	private JPanel contentPane;
	private JTextField inputFName;
	private JTextField inputLName;
	private JTextField inputPhone;
	private JTextField inputBio;
	private JTextField inputStreet;
	private JTextField inputCity;
	private JTextField inputState;
	private JTextField inputZC;

	private List<Author> authorList = new ArrayList<Author>();
	
	public List<Author> getAuthorList() {
		return authorList;
	}
	
	public Author addAuthor() {
		String fName = inputFName.getText();
		String lName = inputLName.getText();
		String phone = inputPhone.getText();
		String bio = inputBio.getText();
		String street = inputStreet.getText();
		String city = inputCity.getText();
		String state = inputState.getText();
		String zCode = inputZC.getText();
		if (fName.isEmpty() || lName.isEmpty() || phone.isEmpty() || bio.isEmpty() || street.isEmpty()
				|| city.isEmpty() || state.isEmpty() || zCode.isEmpty()) {
			JOptionPane.showMessageDialog(AuthorPanel.this, "Please fill all information");
		} else if (Pattern.matches("[a-zA-Z]+", phone) || phone.length() != 10) {
			JOptionPane.showMessageDialog(AuthorPanel.this, "Phone should be number and have 10 digits");
		} else if (Pattern.matches("[a-zA-Z]+", zCode) || zCode.length() != 5) {
			JOptionPane.showMessageDialog(AuthorPanel.this, "Zipcode should be number and have 5 digits");
		}
		return new Author(fName, lName, phone, new Address(street, city, state, zCode), bio);
	}

	public AuthorPanel() {
		setLocationRelativeTo(null);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel headerTitle = new JLabel("Add Author");
		headerTitle.setFont(new Font("Tahoma", Font.BOLD, 12));
		headerTitle.setHorizontalAlignment(SwingConstants.CENTER);
		headerTitle.setBounds(10, 10, 416, 21);
		contentPane.add(headerTitle);

	
		JLabel labelLN = new JLabel("Last Name");
		labelLN.setBounds(204, 45, 67, 13);
		contentPane.add(labelLN);

		inputLName = new MyInput().getInput();
		inputLName.setColumns(10);
		inputLName.setBounds(281, 42, 96, 19);
		contentPane.add(inputLName);
		
		JLabel labelState = new JLabel("State");
		labelState.setBounds(10, 160, 67, 13);
		contentPane.add(labelState);

		inputState = new MyInput().getInput();
		inputState.setColumns(10);
		inputState.setBounds(87, 157, 96, 19);
		contentPane.add(inputState);

		JLabel labelPhone = new JLabel("Phone");
		labelPhone.setBounds(10, 68, 67, 13);
		contentPane.add(labelPhone);

		inputPhone = new MyInput().getInput();
		inputPhone.setColumns(10);
		inputPhone.setBounds(87, 65, 96, 19);
		contentPane.add(inputPhone);



		JLabel labelAddr = new JLabel("Adress");
		labelAddr.setBounds(10, 106, 45, 13);
		contentPane.add(labelAddr);

		JLabel labelStreet = new JLabel("Street");
		labelStreet.setBounds(10, 131, 67, 13);
		contentPane.add(labelStreet);
		
		JLabel labelBio = new JLabel("Short Bio");
		labelBio.setBounds(204, 68, 67, 13);
		contentPane.add(labelBio);

		inputBio = new MyInput().getInput();
		inputBio.setColumns(10);
		inputBio.setBounds(281, 65, 96, 19);
		contentPane.add(inputBio);

		inputStreet = new MyInput().getInput();
		inputStreet.setColumns(10);
		inputStreet.setBounds(87, 128, 96, 19);
		contentPane.add(inputStreet);

		JButton addButton = new MyButton("Add");
		addButton.setBounds(135, 200, 85, 30);
		addButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Author author = addAuthor();
				authorList.add(author);
				AddBook.INSTANCE.publishAuthor(authorList);
			}
		});

		inputCity = new MyInput().getInput();
		inputCity.setColumns(10);
		inputCity.setBounds(281, 128, 96, 19);
		contentPane.add(inputCity);

	
		JLabel labelCity = new JLabel("City");
		labelCity.setBounds(204, 131, 67, 13);
		contentPane.add(labelCity);
		
		JLabel labelFN = new JLabel("First Name");
		labelFN.setBounds(10, 45, 67, 13);
		contentPane.add(labelFN);

		inputFName = new MyInput().getInput();
		inputFName.setBounds(87, 42, 96, 19);
		contentPane.add(inputFName);
		inputFName.setColumns(10);

		JLabel lZC = new JLabel("ZipCode");
		lZC.setBounds(204, 160, 67, 13);
		contentPane.add(lZC);

		inputZC = new MyInput().getInput();
		inputZC.setColumns(10);
		inputZC.setBounds(281, 157, 96, 19);
		contentPane.add(inputZC);
		contentPane.add(addButton);

		JButton cancelButton = new MyButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		cancelButton.setBounds(249, 200, 85, 30);
		contentPane.add(cancelButton);
	}
	
	
}
