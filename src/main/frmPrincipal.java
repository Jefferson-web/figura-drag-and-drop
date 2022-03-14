package main;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;

public class frmPrincipal extends JFrame{

	private static final long serialVersionUID = 1L;
	
	public frmPrincipal() {
		super("Canvas");
		setSize(500, 500);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		initComponents();
	}
	
	void initComponents() {
		Canvas canvas = new Canvas(this);
		Controls controls = new Controls(canvas);
		add(canvas, BorderLayout.CENTER);
		add(controls, BorderLayout.PAGE_START);
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				new frmPrincipal().setVisible(true);
			}
		});
	}
	
	
}
