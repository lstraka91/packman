package sk.tsystems.packman;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.Ellipse2D;

import javax.swing.JPanel;

public class PacManPanel extends JPanel {
	private Ellipse2D pacman = new Ellipse2D.Double();
	private int step=2;
	private Point pacmanDimension;
	
	public PacManPanel() {
		pacmanDimension=new Point(0, 0);
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				System.out.println("moving");
				movePacMan();
				repaint();
				
			}
		});
	}
	
	@Override
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.black);
		g2.fillRect(0, 0, getWidth(), getHeight());
		
		g2.setColor(Color.YELLOW);
		g2.fillArc(pacmanDimension.x, pacmanDimension.y, 50, 50, 30, 300);
		
	}
	private void movePacMan() {
		pacmanDimension.setLocation(pacmanDimension.x+step, pacmanDimension.y);
	}
}
