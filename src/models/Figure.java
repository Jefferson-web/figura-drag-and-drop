package models;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import java.util.List;

public abstract class Figure implements Draggable {

	public int x;
	public int y;
	
	private BasicStroke stroke;
	private float strokeWidth = 1;
	
	protected boolean selected = false;
	protected boolean resizable = false;
	
	private Color color = Color.WHITE;
	
	private int dx;
	private int dy;
	
	public Shape shape = null;
	
	public Figure(int x, int y) {
		this.x = x;
		this.y = y;
		stroke = new BasicStroke(strokeWidth);
	}
	
	public abstract void draw(Graphics2D g2d);
	public abstract List<Rectangle2D.Double> getCorners();
	
	public void select(int x, int y) {
		selected = true;
		this.dx = x - this.x;
		this.dy = y - this.y;
	}

	public void unselect() {
		selected = false;
	}

	@Override
	public void drag(int x, int y) {
		if(isSelected() && !isResizable()) {
			this.x = x - dx;
			this.y = y - dy;
		}
	}
	
	public Rectangle2D getBounds() {
		return shape.getBounds2D();
	}

	public BasicStroke getStroke() {
		return stroke;
	}

	public void setStroke(BasicStroke stroke) {
		this.stroke = stroke;
	}

	public float getStrokeWidth() {
		return strokeWidth;
	}

	public void setStrokeWidth(float strokeWidth) {
		this.strokeWidth = strokeWidth;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Shape getShape() {
		return shape;
	}

	public void setShape(Shape shape) {
		this.shape = shape;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public boolean isResizable() {
		return resizable;
	}

	public void setResizable(boolean resizable) {
		this.resizable = resizable;
	}
	
}
