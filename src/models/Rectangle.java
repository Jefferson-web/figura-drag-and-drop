package models;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

public class Rectangle extends Figure {

	private int width;
	private int height;
	
	private Rectangle2D.Double c1;
	private Rectangle2D.Double c2;
	private Rectangle2D.Double c3;
	private Rectangle2D.Double c4;
	
	Vector<Rectangle2D.Double> corners = new Vector<Rectangle2D.Double>();
	
	public Rectangle(int x, int y, int width, int height) {
		super(x, y);
		this.setWidth(width);
		this.setHeight(height);
	}
	
	@Override
	public void draw(Graphics2D g2d) {
		g2d.setStroke(getStroke());
		shape = new Rectangle2D.Double(x, y, width, height);
		g2d.draw(shape);
		if(isResizable()) {
			c1 = new Rectangle2D.Double(getX() - 3, getY() - 3, 6, 6);
			c2 = new Rectangle2D.Double(getX() + width - 3, getY() - 3, 6, 6);
			c3 = new Rectangle2D.Double(getX() - 3 , getY() + height - 3, 6, 6);
			c4 = new Rectangle2D.Double(getX() + width -3 , getY() + height - 3, 6, 6);
			g2d.setColor(Color.BLACK);
			g2d.fill(c1);
			g2d.fill(c2);
			g2d.fill(c3);
			g2d.fill(c4);
		}
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
	@Override
	public List<Rectangle2D.Double> getCorners(){
		return Arrays.asList(c1, c2, c3, c4);
	}
	
}
