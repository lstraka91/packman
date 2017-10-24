package sk.tsystems.packman;

import java.awt.EventQueue;

import javax.swing.JFrame;

public class MainFrame extends JFrame {

	public MainFrame() {
		super("PacMan");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(600, 400);

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
