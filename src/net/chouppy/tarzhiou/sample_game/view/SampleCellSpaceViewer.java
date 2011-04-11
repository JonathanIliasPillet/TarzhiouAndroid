package net.chouppy.tarzhiou.sample_game.view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.List;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.util.LinkedList;

import javax.swing.JPanel;

public class SampleCellSpaceViewer extends JPanel {
	
	private static final int WIDTH = 800;
	private static final int HEIGHT = 700;
	
	private static final int SCALE_WIDTH = 30;
	private static final int SCALE_HEIGHT = 30;
	
	private static final double CELL_RAY = 3;
	
	private static final long serialVersionUID = -6311733479893062056L;
	private Ellipse2D c1;
	private Ellipse2D c2;
	private Ellipse2D c3;
	private Ellipse2D p11;
	private Ellipse2D p12;
	private Ellipse2D p21;
	private Ellipse2D p22;
	private Ellipse2D p31;
	private Ellipse2D p32;
	private List<Shape> all_cells;
	private List<Shape> all_arcs;

	private double translate_x_from_original (double original_x)
	{
		double x;
		
		x = original_x + 5; // recenter the drawing
		x = x * SCALE_WIDTH;  // scale
		x = x - (CELL_RAY*SCALE_WIDTH/2.0); // center the ellipse
		x += CELL_RAY*SCALE_WIDTH / 2.0; // shift from border
		
		return x;
	}
	
	private double translate_y_from_original (double original_y)
	{
		double y;
		
		y = original_y + 5; // recenter the drawing
		y = y * SCALE_HEIGHT;  // scale
		y = y - (CELL_RAY*SCALE_HEIGHT/2.0); // center the ellipse
		y += CELL_RAY*SCALE_HEIGHT / 2.0; // shift from border
		
		return y;
	}
	
	private Ellipse2D build_new_cell (double original_x, double original_y)
	{		
		return new Ellipse2D.Double 
			(translate_x_from_original(original_x), 
			 translate_y_from_original(original_y), 
			 CELL_RAY*SCALE_WIDTH, 
			 CELL_RAY*SCALE_HEIGHT);
	}
	
	private Line2D build_new_arc (Ellipse2D from, Ellipse2D to)
	{
		return new Line2D.Double (from.getCenterX(), from.getCenterY(),
				                  to.getCenterX(), to.getCenterY());
	}
	
	public SampleCellSpaceViewer ()
	{
		c1  = build_new_cell (0.0, 2.2);
        c2  = build_new_cell (1.9, -1.1);
        c3  = build_new_cell (-1.9, -1.1);
        p11 = build_new_cell (-2.5, 4.3);
        p12 = build_new_cell (2.5, 4.3);
        p21 = build_new_cell (5, 0);
        p22 = build_new_cell (2.5, -4.3);
        p31 = build_new_cell (-2.5, -4.3);
        p32 = build_new_cell (-5, 0);
        
        all_cells = new LinkedList<Shape> ();
        all_cells.add (c1);
        all_cells.add (c2);
        all_cells.add (c3);
        all_cells.add (p11);
        all_cells.add (p12);
        all_cells.add (p21);
        all_cells.add (p22);
        all_cells.add (p31);
        all_cells.add (p32);
        
        all_arcs = new LinkedList<Shape>();
        all_arcs.add(build_new_arc(c1, c2));
        all_arcs.add(build_new_arc(c2, c3));
        all_arcs.add(build_new_arc(c3, c1));
        all_arcs.add(build_new_arc(c1, p11));
        all_arcs.add(build_new_arc(c1, p12));
        all_arcs.add(build_new_arc(c2, p21));
        all_arcs.add(build_new_arc(c2, p22));
        all_arcs.add(build_new_arc(c3, p31));
        all_arcs.add(build_new_arc(c3, p32));
        all_arcs.add(build_new_arc(p11, p12));
        all_arcs.add(build_new_arc(p12, p21));
        all_arcs.add(build_new_arc(p21, p22));
        all_arcs.add(build_new_arc(p22, p31));
        all_arcs.add(build_new_arc(p31, p32));
        all_arcs.add(build_new_arc(p32, p11));
	}
	
	public Dimension getPreferredSize() {
        return new Dimension(SampleCellSpaceViewer.WIDTH,SampleCellSpaceViewer.HEIGHT);
    }
	
	public void paintComponent(Graphics g) {
        super.paintComponent(g);      
        
        Graphics2D g2d = (Graphics2D)g;

        Color cell_bg = new Color (0xffbe79);
        Color arc_bg = cell_bg.darker();
        		
        g2d.setPaint(arc_bg);
        g2d.setStroke(new BasicStroke(1f * SCALE_WIDTH));
        for (Shape arc : all_arcs) {
        	g2d.draw(arc);
        }
        
        g2d.setPaint(cell_bg);
        g2d.setStroke(new BasicStroke(1f));
        for (Shape cell : all_cells) {
        	g2d.fill(cell);
        }
    }
	
	
}
