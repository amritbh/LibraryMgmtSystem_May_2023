package librarysystem;



import javax.swing.*;
import business.ControllerInterface;
import business.SystemController;
import dataaccess.Auth;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import utils.MyButton;
import java.awt.Font;
import java.awt.SystemColor;
import librarysystem.admin.book.AddBookCopy;
import librarysystem.admin.book.ListBook;
import librarysystem.admin.member.AddMember;
import librarysystem.admin.member.EditMember;
import librarysystem.admin.member.ListMember;
import librarysystem.librarien.checkout.CheckoutBook;
import librarysystem.librarien.checkout.CheckoutRecordFrame;
import librarysystem.librarien.checkout.OverdueCheckoutFrame;
import librarysystem.admin.book.AddBook;



public class MainWindow extends JFrame {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public static final MainWindow INSTANCE = new MainWindow();
	ControllerInterface cInterface = new SystemController();

	public void setContent() {
		contentFrame.add(CheckoutBook.INSTANCE);
		contentFrame.add(CheckoutRecordFrame.INSTANCE);
		contentFrame.add(OverdueCheckoutFrame.INSTANCE);
		contentFrame.add(AddBook.INSTANCE);
		contentFrame.add(AddBookCopy.INSTANCE);
		contentFrame.add(AddMember.INSTANCE);
		contentFrame.add(EditMember.INSTANCE);
		contentFrame.add(ListBook.INSTANCE);
		contentFrame.add(ListMember.INSTANCE);
	}

	/**
	 * Create the frame.
	 */

	private JButton checkoutBookItem;
	private JButton checkoutRecordItem;
	private JButton overdueItem;
	private JPanel contentFrame;
	private JPanel panelAdminMenu;
	private JLabel lblNewLabel;
	private FlowLayout flowLayout_1;
	private JPanel panelLibrarienMenu;

	private MainWindow() {
		getContentPane().setBackground(new Color(238, 238, 238));
		//getContentPane().setBackground(new Color(100, 50, 90));
		getContentPane().setLayout(new BorderLayout(0, 0));

		JSplitPane splitPane = new JSplitPane();
		splitPane.setBackground(new Color(242, 248, 246));
		splitPane.setContinuousLayout(true);
		splitPane.setAutoscrolls(true);
		splitPane.setEnabled(false);

		getContentPane().add(splitPane);

		JPanel panelLeft = new JPanel();
		panelLeft.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		panelLeft.setBackground(Color.ORANGE);
		splitPane.setLeftComponent(panelLeft);
		panelLeft.setLayout(new BorderLayout(0, 0));
		panelLeft.setSize(500, 400);

		JPanel panelTop = new JPanel();
		panelTop.setBackground(new Color(255, 200, 0));
		panelLeft.add(panelTop, BorderLayout.NORTH);
		panelTop.setLayout(new BorderLayout(0, 0));

		JPanel panel_4 = new JPanel();
		flowLayout_1 = (FlowLayout) panel_4.getLayout();
		flowLayout_1.setVgap(35);
		flowLayout_1.setHgap(75);
		panel_4.setAlignmentX(0.0f);
		panel_4.setBackground(new Color(229, 238, 235));
		panelTop.add(panel_4);

		lblNewLabel = new JLabel("Admin");
		lblNewLabel.setFont(new Font("PT Serif Caption", Font.BOLD, 15));
		panel_4.add(lblNewLabel);

		JPanel panelBottom = new JPanel();
		panelBottom.setBackground(new Color(229, 238, 235));
		FlowLayout flowLayout = (FlowLayout) panelBottom.getLayout();
		flowLayout.setVgap(20);
		panelLeft.add(panelBottom, BorderLayout.SOUTH);

		JButton logout = new MyButton("Logout", 179, 40, new Color(181, 229, 134));
		logout.setBackground(new Color(89, 189, 123));
		logout.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				LoginForm.INSTANCE.setVisible(true);
				setDefaultCloseOperation(EXIT_ON_CLOSE);
				setVisible(false);

			}
		});
		panelBottom.add(logout);

		JPanel panelMiddle = new JPanel();
		panelMiddle.setBackground(new Color(229, 238, 235));
		panelLeft.add(panelMiddle, BorderLayout.CENTER);
		panelMiddle.setLayout(null);

		panelLibrarienMenu = new JPanel();
		panelLibrarienMenu.setLayout(null);
		panelLibrarienMenu.setBackground(SystemColor.window);
		panelLibrarienMenu.setBounds(9, 16, 181, 227);
		panelMiddle.add(panelLibrarienMenu);

		checkoutBookItem = new MyButton("Checkout", 170, 40, new Color(181, 229, 134));
		checkoutBookItem.setBounds(1, 6, 179, 40);
		panelLibrarienMenu.add(checkoutBookItem);

		checkoutRecordItem = new MyButton("Records", 170, 40, new Color(181, 229, 134));
		checkoutRecordItem.setBounds(1, 50, 179, 40);
		panelLibrarienMenu.add(checkoutRecordItem);

		overdueItem = new MyButton("Overdue", 170, 40, new Color(181, 229, 134));
		overdueItem.setBounds(1, 94, 179, 40);
		panelLibrarienMenu.add(overdueItem);



		JButton btnNewButton_1_1_4 = new MyButton("All Books", 170, 40, new Color(181, 229, 134));
		btnNewButton_1_1_4.setBounds(1, 184, 179, 40);
		panelLibrarienMenu.add(btnNewButton_1_1_4);
		btnNewButton_1_1_4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				contentFrame.removeAll();
				contentFrame.add(ListBook.getInstance());
				contentFrame.getComponent(0).revalidate();
				contentFrame.getComponent(0).repaint();
			}
		});
		JButton listMembers = new MyButton("All Members", 170, 40, new Color(181, 229, 134));
		listMembers.setBounds(1, 139, 179, 40);
		panelLibrarienMenu.add(listMembers);
		listMembers.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				contentFrame.removeAll();
				contentFrame.add(ListMember.getInstance());
				contentFrame.getComponent(0).revalidate();
				contentFrame.getComponent(0).repaint();
			}
		});
		overdueItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				contentFrame.removeAll();
				contentFrame.add(OverdueCheckoutFrame.getInstance());
				contentFrame.getComponent(0).revalidate();
				contentFrame.getComponent(0).repaint();
			}
		});
		checkoutRecordItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				contentFrame.removeAll();
				contentFrame.add(CheckoutRecordFrame.getInstance());
				contentFrame.getComponent(0).revalidate();
				contentFrame.getComponent(0).repaint();
			}
		});
		checkoutBookItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				contentFrame.removeAll();
				contentFrame.add(CheckoutBook.getInstance());
				contentFrame.getComponent(0).revalidate();
				contentFrame.getComponent(0).repaint();

			}
		});

		panelAdminMenu = new JPanel();
		panelAdminMenu.setLayout(null);
		panelAdminMenu.setBackground(SystemColor.window);
		panelAdminMenu.setBounds(9, 244, 181, 189);
		panelMiddle.add(panelAdminMenu);

		JButton addBookItem = new MyButton("Add Book", 170, 40, new Color(181, 229, 134));
		addBookItem.setBackground(new Color(181, 229, 134));
		addBookItem.setBounds(1, 134, 179, 40);
		panelAdminMenu.add(addBookItem);

		JButton addCopyItem = new MyButton("Add copy", 170, 40, new Color(181, 229, 134));
		addCopyItem.setBounds(1, 90, 179, 40);
		panelAdminMenu.add(addCopyItem);

		JButton editMemberItem = new MyButton("Edit Member", 170, 40, new Color(181, 229, 134));
		editMemberItem.setBounds(1, 46, 179, 40);
		panelAdminMenu.add(editMemberItem);

		JButton addMemberItem = new MyButton("Add Member", 170, 40, new Color(181, 229, 134));
		addMemberItem.setBackground(new Color(181, 229, 134));
		addMemberItem.setBounds(1, 2, 179, 40);
		panelAdminMenu.add(addMemberItem);
		addMemberItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				contentFrame.removeAll();
				contentFrame.add(AddMember.getInstance());
				contentFrame.getComponent(0).revalidate();
				contentFrame.getComponent(0).repaint();
			}
		});
		editMemberItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				contentFrame.removeAll();
				contentFrame.add(EditMember.getInstance());
				contentFrame.getComponent(0).revalidate();
				contentFrame.getComponent(0).repaint();
			}
		});
		addCopyItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				contentFrame.removeAll();
				contentFrame.add(AddBookCopy.getInstance());
				contentFrame.getComponent(0).revalidate();
				contentFrame.getComponent(0).repaint();
			}
		});
		addBookItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				contentFrame.removeAll();
				contentFrame.add(AddBook.getInstance());
				contentFrame.getComponent(0).revalidate();
				contentFrame.getComponent(0).repaint();
			}
		});

		contentFrame = new JPanel();
		contentFrame.setBackground(new Color(242, 248, 246));
		contentFrame.setBounds(10, 10, 543, 318);
		splitPane.setRightComponent(contentFrame);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 877, 638);
		setLocationRelativeTo(null);
	}

	public void init(Auth auth) {
		try {
			MainWindow frame = new MainWindow();
			if (auth.equals(Auth.LIBRARIAN)) {
				frame.panelAdminMenu.setVisible(false);
				frame.lblNewLabel.setText("LIBRARIAN");
				frame.flowLayout_1.setHgap(59);
				frame.contentFrame.add(CheckoutBook.getInstance());
				frame.contentFrame.getComponent(0).revalidate();
				frame.contentFrame.getComponent(0).repaint();
				frame.contentFrame.getComponent(0).setVisible(true);
			} else if (auth.equals(Auth.ADMIN)) {
				frame.panelLibrarienMenu.setVisible(false);
				frame.panelAdminMenu.setBounds(9, 16, 181, 227);
				frame.contentFrame.add(AddMember.getInstance());
				frame.contentFrame.getComponent(0).revalidate();
				frame.contentFrame.getComponent(0).repaint();
				frame.contentFrame.getComponent(0).setVisible(true);
				frame.lblNewLabel.setText("ADMIN");
				frame.flowLayout_1.setHgap(74);

			} else {
				frame.panelAdminMenu.setVisible(true);
				frame.lblNewLabel.setText("BOTH");
				frame.contentFrame.add(CheckoutBook.getInstance());
				frame.contentFrame.getComponent(0).revalidate();
				frame.contentFrame.getComponent(0).repaint();
				frame.contentFrame.getComponent(0).setVisible(true);
				frame.flowLayout_1.setHgap(80);

			}
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}