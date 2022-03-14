package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import models.Figure;
import models.Rectangle;

public class Controls extends JPanel {

	private static final long serialVersionUID = 1L;
	public Canvas canvas;
	public JButton addRectangle;
	private JButton addCircle;
	
	public Controls(Canvas canvas) {
		this.canvas = canvas;
		initComponents();
	}
	
	void initComponents() {
		addRectangle = new JButton("Rectangle");
		MouseHandler clickHandler = new MouseHandler();
		addRectangle.addActionListener(clickHandler);
		add(addRectangle);
		addRectangle.setFocusable(false);
	}
	
	class MouseHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Figure figure = null;
			if(e.getSource() == addRectangle) {
				figure = new Rectangle(20, 20, 100, 100);
			}
			canvas.addShape(figure);
		}
		
	}
	
}
