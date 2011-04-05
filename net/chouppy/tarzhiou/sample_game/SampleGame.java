package net.chouppy.tarzhiou.sample_game;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Point2D;
import java.net.URL;

import javax.swing.JFrame;

import org.apache.batik.bridge.BridgeContext;
import org.apache.batik.bridge.DocumentLoader;
import org.apache.batik.bridge.GVTBuilder;
import org.apache.batik.bridge.UserAgent;
import org.apache.batik.bridge.UserAgentAdapter;
import org.apache.batik.dom.svg.SAXSVGDocumentFactory;
import org.apache.batik.dom.svg.SVGStylableElement;
import org.apache.batik.gvt.GraphicsNode;
import org.apache.batik.swing.JSVGCanvas;
import org.apache.batik.util.XMLResourceDescriptor;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import net.chouppy.tarzhiou.Game;
import net.chouppy.tarzhiou.NameSquareKey;
import net.chouppy.tarzhiou.Player;
import net.chouppy.tarzhiou.listeners.GameListener;
import net.chouppy.tarzhiou.sample_game.model.SampleGameModel;

public class SampleGame implements MouseListener, GameListener {
	private SampleGameModel game_model;
	private JFrame main_window;
	private JSVGCanvas svg_canvas;
	private Document svg_doc;

	private SampleGame ()
		throws Exception
	{
		game_model = new SampleGameModel();
		
		game_model.create_player("player 1");
		game_model.create_player("player 2");
		
		game_model.start();

		main_window = new JFrame ("Tarzhiou sample game");
		main_window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		main_window.setSize(250, 250);

		main_window.pack ();
		main_window.setVisible(true);
		
		load_background ();
	}
	
	private void load_background ()
		throws Exception
	{
		
		//Element svg;
		svg_canvas = new JSVGCanvas();
		main_window.getContentPane().add (svg_canvas);
		
		String parser = XMLResourceDescriptor.getXMLParserClassName();
        SAXSVGDocumentFactory f = new SAXSVGDocumentFactory(parser);
        URL url = new URL("file:///tmp/bg.svg");
        svg_doc = f.createDocument(url.toString());

        svg_canvas.setDocumentState(JSVGCanvas.ALWAYS_DYNAMIC);
        svg_canvas.setDocument(svg_doc);

		svg_canvas.addMouseListener(this);
	}
	
	public static void main(String[] args) 
		throws Exception
	{
		new SampleGame();
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		Point p = arg0.getPoint();
		
		UserAgent userAgent = new UserAgentAdapter();
		DocumentLoader loader = new DocumentLoader(userAgent);
		BridgeContext context = new BridgeContext(
		                                   userAgent, loader);
		context.setDynamicState(BridgeContext.DYNAMIC);
		GVTBuilder builder = new GVTBuilder();
		GraphicsNode rootGraphicsNode = builder.build(
		                                   context, svg_canvas.getSVGDocument());

		GraphicsNode graphicsNode =
		rootGraphicsNode.nodeHitAt(new Point2D.Float(p.x, p.y));

		if (graphicsNode != null)
		{
			Element e = context.getElement(graphicsNode);
			String name = e.getAttribute("id");
			game_model.play(new NameSquareKey (name), game_model.get_current_player());
			System.out.println (game_model.get_square_space_view().toString());
			
			/*
			 * TODO : find a way to change a square
			 */
			/*SVGStylableElement e = (SVGStylableElement)context.getElement(graphicsNode);
			e.getStyle().setProperty("fill", "red", "");
			*/
			
			/*Element e = context.getElement(graphicsNode);
			e.setAttribute("style", "fill:#ffffff");
			String attr = e.getAttribute("style");
			System.out.println (attr);*/
		}
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void on_win(Game thisGame, Player winner) {
		System.out.println (winner.get_name()+ " wins !");
		
	}

}
