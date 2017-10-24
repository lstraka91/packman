package sk.tsystems.packman;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JComponent;
import javax.swing.JPanel;

public class Alien extends Rectangle2D.Double {

	public static final int RIGHT = 0;
	public static final int LEFT = 180;
	public static final int UP = 270;
	public static final int DOWN = 90;
	private int direction;
	private int step = 10;
	private int stepCounter;

	public Alien() {
		direction = RIGHT;
	}

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}

	public void moveByDirection(int direction) {
		switch (direction) {
		case RIGHT:
			setRect(getX() + step, getY(), 40, 40);
			break;
		case DOWN:
			setRect(getX(), getY() + step, 40, 40);
			break;
		case LEFT:
			setRect(getX() - step, getY(), 40, 40);
			break;
		case UP:
			setRect(getX(), getY() - step, 40, 40);
			break;
		default:
			break;
		}
	}

	public void incStepCount() {
		stepCounter++;
	}

	public int getStepCount() {
		return stepCounter;
	}

	public void resetStepCounter() {
		stepCounter = 0;
	}

	// @Override
	// public void paint(Graphics g) {
	// Graphics2D g2 = (Graphics2D) g;
	// g2.setColor(Color.white);
	// g2.fillRect(0, 0, 50, 150);
	// }
}
