package sk.tsystems.packman;

import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class MainFrame extends JFrame {

	public MainFrame() {
		super("PacMan");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setUndecorated(true);
		setSize(Toolkit.getDefaultToolkit().getScreenSize());
		getContentPane().add(new PacManPanel());
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				new MainFrame().setVisible(true);

			}
		});
	}

}
