package net.chouppy.tarzhiou.sample_game.view;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

public class SquareSpaceViewer extends JPanel {
	
	private static final int WIDTH = 600;
	private static final int HEIGHT = 600;
	private static final long serialVersionUID = -6311733479893062056L;

	public Dimension getPreferredSize() {
        return new Dimension(SquareSpaceViewer.WIDTH,SquareSpaceViewer.HEIGHT);
    }
	
	public void paintComponent(Graphics g) {
        super.paintComponent(g);       


    } 
}
