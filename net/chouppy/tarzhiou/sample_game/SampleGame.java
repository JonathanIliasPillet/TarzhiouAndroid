package net.chouppy.tarzhiou.sample_game;

import net.chouppy.tarzhiou.sample_game.model.SampleGameModel;

public class SampleGame {
	public static void main(String[] args) {
		
		SampleGameModel game_model = new SampleGameModel();
		
		game_model.create_player("player 1");
		game_model.create_player("player 2");

		
	}

}
