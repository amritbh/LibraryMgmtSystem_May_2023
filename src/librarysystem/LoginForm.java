package librarysystem;

import business.LoginException;
import business.SystemController;
import dataaccess.Auth;
import utils.MyButton;
import utils.PlaceholderPasswordField;
import utils.PlaceholderTextField;

import javax.swing.*;
import java.awt.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class LoginForm extends JFrame {
	public final static LoginForm INSTANCE =new LoginForm();
	
	private JButton exitButton;
	private JLabel jLabel1;
	private JLabel jLabel2;
	private JLabel jLabel3;
	private JLabel jLabel4;
	private JPanel jPanel1;
	private JPanel jPanel2;

	private JSeparator jSeparator1;
	private JSeparator jSeparator2;
	private JButton loginButton;

	private PlaceholderPasswordField password;
	private PlaceholderTextField username;

	/**
	 * Creates new LoginForm
	 */
	public LoginForm() {
		initComponents();
	}

	private boolean isInitialized = false;

	private void initComponents() {

		jPanel1 = new JPanel();
		jLabel1 = new JLabel();
		jPanel2 = new JPanel();
		username = new PlaceholderTextField("Enter user ID ...");
		loginButton = new MyButton("Login", 170, 40, new Color(181, 229, 134));
		loginButton.setBounds(424, 171, 170, 40);
		jLabel2 = new JLabel();
		jLabel3 = new JLabel();
		jSeparator1 = new JSeparator();
		jSeparator2 = new JSeparator();
		jLabel4 = new JLabel();
		exitButton = new JButton();
		password = new PlaceholderPasswordField("Enter password...");

		Color mainColor = new Color(221, 255, 187);
		//Color mainColor = new Color(200, 255, 180);

		Color secondColor = new Color(199, 233, 176);
		Color textColor = Color.black;

		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setUndecorated(true);

		jPanel1.setBackground(mainColor);

		//jLabel1.setIcon(new ImageIcon(getClass().getResource("/librarysystem/miuLogo.png")));
		jLabel1.setIcon(new ImageIcon(getClass().getResource("/librarysystem/everestlibrary.png")));

		GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
		jPanel1.setLayout(jPanel1Layout);
		jPanel1Layout.setHorizontalGroup(
				jPanel1Layout.createParallelGroup(Alignment.LEADING)
						.addGroup(jPanel1Layout.createSequentialGroup()
								.addGap(53, 53, 53)
								.addComponent(jLabel1)
								.addContainerGap(63, Short.MAX_VALUE))
		);
		jPanel1Layout.setVerticalGroup(
				jPanel1Layout.createParallelGroup(Alignment.LEADING)
						.addGroup(jPanel1Layout.createSequentialGroup()
								.addGap(69, 69, 69)
								.addComponent(jLabel1)
								.addContainerGap(87, Short.MAX_VALUE))
		);

		jPanel2.setBackground(secondColor);

		username.setBackground(secondColor);
		username.setForeground(textColor);
		username.setBorder(null);

		loginButton.setForeground(textColor);
		loginButton.setText("Login");
		loginButton.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				loginButtonMouseClicked(evt);
			}
		});

		jLabel2.setIcon(new ImageIcon(getClass().getResource("/librarysystem/user icon15.png")));

		jLabel3.setIcon(new ImageIcon(getClass().getResource("/librarysystem/lock_15.png")));

		jLabel4.setFont(new Font("Segoe UI", 1, 24));
		jLabel4.setText("x");
		jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				jLabel4MouseClicked(evt);
			}
		});

		exitButton.setBackground(secondColor);
		exitButton.setForeground(textColor);
		exitButton.setText("Exit");
		exitButton.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				exitButtonMouseClicked(evt);
			}
		});

		password.setBackground(secondColor);
		password.setForeground(textColor);
		password.setBorder(null);

		GroupLayout jPanel2Layout = new GroupLayout(jPanel2);
		jPanel2Layout.setHorizontalGroup(
			jPanel2Layout.createParallelGroup(Alignment.LEADING)
				.addGroup(jPanel2Layout.createSequentialGroup()
					.addContainerGap(441, Short.MAX_VALUE)
					.addComponent(jLabel4)
					.addGap(15))
				.addGroup(jPanel2Layout.createSequentialGroup()
					.addGroup(jPanel2Layout.createParallelGroup(Alignment.LEADING, false)
						.addGroup(jPanel2Layout.createSequentialGroup()
							.addGap(99)
							.addComponent(exitButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addGroup(jPanel2Layout.createSequentialGroup()
							.addGap(60)
							.addGroup(jPanel2Layout.createParallelGroup(Alignment.TRAILING)
								.addComponent(jLabel2, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
								.addComponent(jLabel3))
							.addGap(18)
							.addGroup(jPanel2Layout.createParallelGroup(Alignment.LEADING)
								.addGroup(jPanel2Layout.createSequentialGroup()
									.addGroup(jPanel2Layout.createParallelGroup(Alignment.LEADING)
										.addComponent(loginButton, GroupLayout.PREFERRED_SIZE, 162, GroupLayout.PREFERRED_SIZE)
										.addGroup(jPanel2Layout.createParallelGroup(Alignment.LEADING)
											.addComponent(username)
											.addGroup(jPanel2Layout.createParallelGroup(Alignment.TRAILING)
												.addComponent(jSeparator1, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addComponent(jSeparator2, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
									.addGap(8))
								.addComponent(password, GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE))))
					.addContainerGap(193, Short.MAX_VALUE))
		);
		jPanel2Layout.setVerticalGroup(
			jPanel2Layout.createParallelGroup(Alignment.LEADING)
				.addGroup(jPanel2Layout.createSequentialGroup()
					.addGroup(jPanel2Layout.createParallelGroup(Alignment.TRAILING)
						.addGroup(jPanel2Layout.createSequentialGroup()
							.addComponent(jLabel4)
							.addGap(119)
							.addComponent(jLabel2))
						.addComponent(username, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(16)
					.addGroup(jPanel2Layout.createParallelGroup(Alignment.TRAILING)
						.addGroup(jPanel2Layout.createSequentialGroup()
							.addComponent(jSeparator1, GroupLayout.PREFERRED_SIZE, 10, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(password, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE))
						.addComponent(jLabel3))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(jSeparator2, GroupLayout.PREFERRED_SIZE, 10, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(loginButton, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(exitButton)
					.addContainerGap(156, Short.MAX_VALUE))
		);
		jPanel2.setLayout(jPanel2Layout);

		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(
				layout.createParallelGroup(Alignment.LEADING)
						.addGroup(layout.createSequentialGroup()
								.addComponent(jPanel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addGap(0, 0, 0)
								.addComponent(jPanel2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		layout.setVerticalGroup(
				layout.createParallelGroup(Alignment.LEADING)
						.addComponent(jPanel1, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(jPanel2, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
		);

		pack();
		setLocationRelativeTo(null);
		loginButton.setBounds(424, 171, 170, 40);

	}

	private void addLoginButtonListener(JButton butn) {
		butn.addActionListener(evt -> {
			SystemController cs = new SystemController();
			try {
				cs.login(username.getText(), password.getText());
				if (cs.currentAuth == Auth.LIBRARIAN) {
					JOptionPane.showMessageDialog(this, "Welcome Librarian");
					MainWindow.INSTANCE.init(Auth.LIBRARIAN);
					LoginForm.INSTANCE.dispose();
				} else if (cs.currentAuth == Auth.ADMIN) {
					JOptionPane.showMessageDialog(this, "Welcome Admin");
					MainWindow.INSTANCE.init(Auth.ADMIN);
					LoginForm.INSTANCE.dispose();
				} else {
					JOptionPane.showMessageDialog(this, "Welcome Super User");
					MainWindow.INSTANCE.init(Auth.BOTH);
					LoginForm.INSTANCE.dispose();
				}
			} catch (LoginException e) {
				if (SystemController.currentAuth == null)
					JOptionPane.showMessageDialog(this, e.getMessage());
			}
		});
	}

	private void loginButtonMouseClicked(java.awt.event.MouseEvent evt) {
		SystemController cs = new SystemController();
		try {
			cs.login(username.getText(), password.getText());
			if (cs.currentAuth == Auth.LIBRARIAN) {
				JOptionPane.showMessageDialog(this, "Welcome Librarian");
				MainWindow.INSTANCE.init(Auth.LIBRARIAN);
				LoginForm.INSTANCE.setVisible(false);
			} else if (cs.currentAuth == Auth.ADMIN) {
				JOptionPane.showMessageDialog(this, "Welcome Admin");
				MainWindow.INSTANCE.init(Auth.ADMIN);
				LoginForm.INSTANCE.setVisible(false);
			} else {
				JOptionPane.showMessageDialog(this, "Welcome Super User");
				MainWindow.INSTANCE.init(Auth.BOTH);
				LoginForm.INSTANCE.setVisible(false);
			}
		} catch (LoginException e) {
			if (SystemController.currentAuth == null)
				JOptionPane.showMessageDialog(this, e.getMessage());
		}
	}

	private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {
		System.exit(0);
	}

	private void exitButtonMouseClicked(java.awt.event.MouseEvent evt) {
		System.exit(0);
	}


	public static void main(String args[]) {

		/* Create and display the form */
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				new LoginForm().setVisible(true);
			}
		});
	}

}