package librarysystem.starterCode;

import java.awt.Toolkit;
import librarysystem.LoginForm;
import java.awt.Component;
import java.awt.EventQueue;


public class Main {
//comment for commit
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			LoginForm.INSTANCE.setVisible(true);
		});
	}

	public static void centerFrameOnDesktop(Component f) {
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		int height = toolkit.getScreenSize().height;
		int width = toolkit.getScreenSize().width;
		int frameHeight = f.getSize().height;
		int frameWidth = f.getSize().width;
		f.setLocation(((width - frameWidth) / 2), (height - frameHeight) / 3);
	}
}
