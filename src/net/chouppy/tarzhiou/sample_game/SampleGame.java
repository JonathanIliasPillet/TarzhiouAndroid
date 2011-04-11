package net.chouppy.tarzhiou.sample_game;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Point2D;
import java.net.URL;

import javax.swing.JFrame;

import net.chouppy.tarzhiou.Game;
import net.chouppy.tarzhiou.NameCellKey;
import net.chouppy.tarzhiou.Player;
import net.chouppy.tarzhiou.listeners.GameListener;
import net.chouppy.tarzhiou.sample_game.model.SampleGameModel;
import net.chouppy.tarzhiou.sample_game.view.SampleCellSpaceViewer;

public class SampleGame {
	private SampleGameModel game_model;
	private JFrame main_window;
	private SampleCellSpaceViewer cell_space_viewer;

	private SampleGame ()
		throws Exception
	{
		game_model = new SampleGameModel();
		
		game_model.create_player("player 1");
		game_model.create_player("player 2");
		
		game_model.start();

		main_window = new JFrame ("Tarzhiou sample game");
		main_window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		cell_space_viewer = new SampleCellSpaceViewer();
		main_window.add(cell_space_viewer);

		main_window.pack ();
		main_window.setVisible(true);
		
		load_background ();
	}
	
	private void load_background ()
		throws Exception
	{
		
	}
	
	public static void main(String[] args) 
		throws Exception
	{
		new SampleGame();
	}
}
