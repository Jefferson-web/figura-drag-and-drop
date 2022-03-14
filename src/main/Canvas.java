package main;

import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import models.Figure;

public class Canvas extends JPanel{

	private static final long serialVersionUID = 1L;
	private List<Figure> shapes;
	private JFrame frame;
	
	public Canvas(JFrame frame) {
		shapes = new ArrayList<>();
		this.frame = frame;
		initComponents();
	}
	
	void initComponents() {
		addMouseListener(new MouseHandler());
		addMouseMotionListener(new MouseMotionHandler());
		this.addKeyListener(new KeyAdapter() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				super.keyTyped(e);
			}
			
			@SuppressWarnings("deprecation")
			@Override
			public void keyPressed(KeyEvent e) {
				super.keyPressed(e);
				if(e.getKeyCode() == KeyEvent.VK_T &&  ((e.getModifiers() & KeyEvent.CTRL_MASK) != 0 )) {
					for (Figure shape : shapes) {
						if(shape.isSelected()) {
							shape.setResizable(true);
						}
					}
				} else if (e.getKeyCode() == KeyEvent.VK_D &&  ((e.getModifiers() & KeyEvent.CTRL_MASK) != 0 )){
					for (Figure shape : shapes) {
						if(shape.isSelected() && shape.isResizable()) {
							shape.setResizable(false);
							shape.setSelected(false);
						}
					}
				}
				repaint();
			}
			
		});
		this.requestFocus();
		this.setFocusable(true);
		this.requestFocusInWindow();
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		for (Figure shape : shapes) {
			shape.draw(g2d);
		}
	}
	
	public List<Figure> getShapes() {
		return shapes;
	}

	public void setShapes(List<Figure> shapes) {
		this.shapes = shapes;
	}
	
	public void addShape(Figure shape) {
		this.shapes.add(shape);
		repaint();
	}
	
	public void setCursor(int type) {
			frame.setCursor(Cursor.getPredefinedCursor(type));
	}
	
	class MouseMotionHandler extends MouseMotionAdapter {
		
		@Override
		public void mouseDragged(MouseEvent e) {
			super.mouseDragged(e);
			for (Figure shape : shapes) {
				shape.drag(e.getX(), e.getY());
				if(shape.isSelected() && !shape.isResizable()) {
					setCursor(Cursor.MOVE_CURSOR);
				} 
				repaint();
			}
		}
		

		@Override
		public void mouseMoved(MouseEvent e) {
			super.mouseMoved(e);
			for (Figure shape : shapes) {
				if(shape.isSelected() && shape.isResizable()) {
					List<Rectangle2D.Double> corners = shape.getCorners();
					for (int i = 0; i < corners.size(); i++) {
						Rectangle2D.Double rect = corners.get(i);
						if(rect.contains(e.getPoint())) {
							setCursor(Cursor.MOVE_CURSOR);
							break;
						} else {
							setCursor(Cursor.DEFAULT_CURSOR);
						}
					}
				}
			}
		}
		
	}
	
	class MouseHandler extends MouseAdapter {
		
		@Override
		public void mousePressed(MouseEvent e) {
			super.mousePressed(e);
			boolean hasResizable = false;
			for (Figure shape : shapes) {
				if(shape.isResizable()) {
					hasResizable = true;
				}
			}
			if(!hasResizable) {
				markAllAsUnselect();
				for (Figure shape : shapes) {
					Rectangle2D bounds = shape.getBounds();
					if(bounds.contains(e.getX(), e.getY())) {
						shape.select(e.getX(), e.getY());
						break;
					}
				}
			}
		}
		
		@Override
		public void mouseReleased(MouseEvent e) {
			super.mouseReleased(e);
			setCursor(Cursor.DEFAULT_CURSOR);
		}
		
		void markAllAsUnselect() {
			for (Figure shape : shapes) {
				shape.unselect();
			}
		}
		
	}
	
}
