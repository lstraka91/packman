package sk.tsystems.packman;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Arc2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;

public class PacManPanel extends JPanel {
	private int step = 10;
	private Point pacmanDimension;
	private int angle = 0;
	private PacManObject pacman = new PacManObject();
	private List<Alien> aliens = new LinkedList<>();
	private List<Points> points = new LinkedList<>();
	private int score;
	private int lives = 5;
	private boolean gameOver, dead = false;

	public PacManPanel() {
		setSize(Toolkit.getDefaultToolkit().getScreenSize());
		score = 0;
		setFocusable(true);
		generateAliens(15);
		generatePoints(150);
		pacmanDimension = new Point(0, 0);
		Timer timer = new Timer(100, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				moveAlien();
				repaint();
				for (Alien a : aliens) {
					if (pacman.intersects(a) && !gameOver ) {
						lives--;
						pacmanDimension = new Point(0, 0);
						dead = true;
						if (lives == 0)
							gameOver = true;
						repaint();
					}
				}
			}
		});
		timer.start();
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
					System.exit(0);
				}
				System.out.println(e.getKeyCode());
				movePacMan(e.getKeyCode());

				for (Points p : points) {
					if (pacman.intersects(p.getBounds())) {
						score++;
						points.remove(p);
						return;
					}
				}
				repaint();
			}
		});
	}

	@Override
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.black);
		g2.fillRect(0, 0, getWidth(), getHeight());
		g2.setColor(Color.white);
		g2.drawString("Score :" + score + "   Lives :" + lives, 15, 10);

		if (dead) {
			g2.setColor(Color.white);
			g2.drawString("You dead!", getWidth() / 2 - 50, getHeight() / 2);
			dead = !dead;
			
			
		if (gameOver) {
			g2.setColor(Color.white);
			g2.setFont(new Font("Courier New", 1, 25));
			String text = "GAME OVER ! Your score is " + score;

			g2.drawString(text, getWidth() / 2 - 150, getHeight() / 2);
			}
		} else {
			
			g2.setColor(Color.YELLOW);
			AffineTransform transform = g2.getTransform();
			AffineTransform rotation = new AffineTransform();
			rotation.transform(pacmanDimension, new Point(pacmanDimension.x + 25, pacmanDimension.y + 25));
			rotation.rotate(Math.toRadians(angle), pacmanDimension.x + 25, pacmanDimension.y + 25);
			g2.transform(rotation);
			pacman.setArc(pacmanDimension, new Dimension(50, 50), 30, 300, 2);
			g2.fill(pacman);
			g2.setTransform(transform);
			g2.setColor(Color.BLUE);
			for (Alien r : aliens) {
				g2.fill(r);
			}
			g2.setColor(Color.green);
			for (Points p : points) {
				g2.fill(p);
			}
		}
	}

	private void movePacMan(int keyCode) {

		if (keyCode == 37 && pacmanDimension.x - step >= 0) {
			// left
			angle = 180;
			pacmanDimension.setLocation(pacmanDimension.x - step, pacmanDimension.y);
		} else if (keyCode == 38 && pacmanDimension.y - step >= 0) {
			// up
			angle = 270;
			pacmanDimension.setLocation(pacmanDimension.x, pacmanDimension.y - step);
		} else if (keyCode == 39 && pacmanDimension.x + step <= getWidth() - 50) {
			// right
			angle = 0;
			pacmanDimension.setLocation(pacmanDimension.x + step, pacmanDimension.y);
		} else if (keyCode == 40 && pacmanDimension.y + step <= getHeight() - 50) {
			// down
			angle = 90;
			pacmanDimension.setLocation(pacmanDimension.x, pacmanDimension.y + step);
		}
	}

	private void generateAliens(int count) {
		for (int i = 0; i < count; i++) {
			Alien r = new Alien();
			r.setRect(new Random().nextInt(getWidth() - 40), new Random().nextInt(getHeight() - 40), 40, 40);
			if (i % 2 == 0) {
				r.setDirection(Alien.DOWN);
			}
			aliens.add(r);

		}
	}

	private void generatePoints(int count) {
		for (int i = 0; i < count; i++) {
			Points p = new Points();
			p.setFrame(new Random().nextInt(getWidth() - 15), new Random().nextInt(getHeight() - 15), 15, 15);

			points.add(p);

		}
	}

	private void moveAlien() {

		for (Alien alien : aliens) {
			int stepCount = alien.getStepCount();
			alien.incStepCount();

			if (stepCount >= 10 || alien.getX() - 1 <= 0 || alien.getY() - 1 <= 0 || alien.getX() + 41 >= getWidth()
					|| alien.getY() + 41 >= getHeight()) {
				generateDirection(alien);
				alien.resetStepCounter();
			}
			alien.moveByDirection(alien.getDirection());
		}
	}

	private void generateDirection(Alien alien) {
		int currentDir = alien.getDirection();
		switch (new Random().nextInt(4)) {
		case 0:
			if (currentDir == Alien.UP)
				generateDirection(alien);
			else
				alien.setDirection(Alien.UP);
			break;
		case 1:
			if (currentDir == Alien.DOWN)
				generateDirection(alien);
			else
				alien.setDirection(Alien.DOWN);
			break;
		case 2:
			if (currentDir == Alien.LEFT)
				generateDirection(alien);
			else
				alien.setDirection(Alien.LEFT);
			break;
		case 3:
			if (currentDir == Alien.RIGHT)
				generateDirection(alien);
			else
				alien.setDirection(Alien.RIGHT);
			break;
		default:
			break;
		}
	}

}
