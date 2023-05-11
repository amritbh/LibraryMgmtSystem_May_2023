package librarysystem.admin.member;

import librarysystem.admin.author.AuthorPanel;
import javax.swing.*;
import java.util.*;
import business.Author;
import java.awt.*;
import javax.swing.table.DefaultTableModel;
import business.LibraryMember;
import java.util.List;
import business.ControllerInterface;
import business.SystemController;

@SuppressWarnings("serial")
public class ListMember extends JPanel {
	public static final ListMember INSTANCE = new ListMember();
	private JTextField inputAuthor;
	private JTable tableContent;
	private JLabel headerTitle;
	private JScrollPane contentMembers;
	AuthorPanel authorFrame = new AuthorPanel();
	ControllerInterface cInterface = new SystemController();

	private HashMap<String, LibraryMember> memberList = cInterface.allMembers();

	public void defineTable() {
		String[] column = { "ID", "First Name", "Last Name" };
		DefaultTableModel tableModel = new DefaultTableModel(column, 0);
		tableContent = new JTable(tableModel);
		Iterator iterator = memberList.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry mapElement = (Map.Entry) iterator.next();
			LibraryMember member = (LibraryMember) mapElement.getValue();

			Object[] row = { member.getMemberId(), member.getFirstName(), member.getLastName() };
			tableModel.addRow(row);
		}
		tableContent.setSize(new Dimension(500, 300));
		tableContent.setBackground(new Color(224, 224, 224));
	}

	/**
	 * Create the panel.
	 */
	
	public static ListMember getInstance() {
		return new ListMember();
	}

	private ListMember() {
		setLayout(null);
		setPreferredSize(new Dimension(600, 600));
		setBackground(new Color(243, 248, 246));


		headerTitle = new JLabel("List Member");
		headerTitle.setFont(new Font("PT Serif Caption", Font.BOLD, 20));
		headerTitle.setHorizontalAlignment(SwingConstants.LEFT);
		headerTitle.setBounds(10, 10, 584, 60);
		add(headerTitle);

		contentMembers = new JScrollPane();
		contentMembers.setBounds(10, 218, 584, 294);
		add(contentMembers);

		defineTable();
		contentMembers.add(tableContent);
		contentMembers.setViewportView(tableContent);
	}

	public void publishAuthor(List<Author> authorList) {
		StringBuilder sb = new StringBuilder();
		for (Author author : authorList) {
			sb.append(author.getFirstName());
			sb.append(", ");
		}
		inputAuthor.setText(sb.toString());
		authorFrame.setVisible(true);
	}


}