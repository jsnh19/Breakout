package me.zero.cc.Graphics.WindowClosingAdapter;

import java.awt.event.*;

import me.zero.cc.utils.Player.Speicher;

public class GameClosingAdapter extends WindowAdapter {

	public Speicher speicher;
	
	public GameClosingAdapter(Speicher speicher) {
		this.speicher = speicher;
	}
	public void windowClosing(WindowEvent event) {
		event.getWindow().setVisible(false);
		event.getWindow().dispose();
		speicher.getStartframe().setVisible(true);
		speicher.getBt().setTalive(false);
		speicher.setAmountlvlplayed(0);
	}
}