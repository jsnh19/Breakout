package me.zero.cc.Graphics.WindowClosingAdapter;

import java.awt.event.*;

import me.zero.cc.utils.Player.Speicher;

public class StartWindowClosingAdapter extends WindowAdapter{

	private Speicher speicher;
  public StartWindowClosingAdapter(Speicher speicher)  {
	  this.speicher = speicher;
  }
  
  public void windowClosing(WindowEvent event)  {
    event.getWindow().setVisible(false);
    event.getWindow().dispose();
    speicher.getStartframe().setVisible(true);
  }
}