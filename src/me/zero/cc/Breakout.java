package me.zero.cc;

import java.awt.Button;
import java.awt.Color;
import java.awt.Toolkit;
import java.util.logging.Logger;

import javax.swing.JFrame;

import me.zero.cc.Graphics.Ui.Level.LevelEditor;
import me.zero.cc.SQLite.DbTasks;
import me.zero.cc.SQLite.SqliteDb;
import me.zero.cc.Sound.SoundThread.SoundMP3Thread;
import me.zero.cc.listener.ButtonEvenListener;
import me.zero.cc.listener.StartWindowListener;
import me.zero.cc.utils.Player.Speicher;

public class Breakout extends JFrame{

	private static final long serialVersionUID = 1L;
	private static ButtonEvenListener listener;
	private static Speicher speicher;
	private static String path = "standart";
	
	public static void main(String[] args) {
				
		if(args.length >= 1){
			if(args[0].contains("secretmode")){
				System.out.println("secretmode");
				path = "secret";
			}			
		}	
		SoundMP3Thread sound = new SoundMP3Thread(path);
		sound.start();
		speicher = new Speicher();
		speicher.setMp3thread(sound);
		LevelEditor le = new LevelEditor(speicher);
		le.LoadLevel();
		listener = new ButtonEvenListener(speicher);
		speicher.setLog(Logger.getLogger( Breakout.class.getName() ));
		speicher.setMaxX(Toolkit.getDefaultToolkit() .getScreenSize().width);
		speicher.setMaxY(Toolkit.getDefaultToolkit().getScreenSize().height);
		speicher.setDbTasks(new DbTasks());

		LoadStartFrame();
	}
	private static void LoadStartFrame(){
		JFrame frame = new JFrame("Breakout");
		frame.setBounds(((speicher.getMaxX()/2)-250),(speicher.getMaxY()/2) -250, 500, 500);
		frame.setVisible(true);
		frame.setLayout(null);
		frame.getGraphics().drawImage(null, 500, 500, null);
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.addComponentListener(new StartWindowListener(speicher));
		frame.getContentPane().setBackground(Color.BLUE);
		speicher.setStartframe(frame);
		LoadUI(frame);		
	}
	private static void LoadUI(JFrame frame){
		Button start = new Button("Start");
		start.setName("start");
		Button close = new Button("Close");
		close.setName("Close");
		Button Author = new Button("Author");
		Author.setName("Author");
		Button Points = new Button("Bestenliste");
		Points.setName("Bestenliste");
		
		start.setBounds(frame.getSize().width/2 - 100,frame.getSize().height/8,200,50);
		Author.setBounds(frame.getSize().width/2 - 100,frame.getSize().height/8 + 100,200,50);
		Points.setBounds(frame.getSize().width/2 - 100,frame.getSize().height/8 + 200,200,50);	
		close.setBounds(frame.getSize().width/2 - 100,frame.getSize().height/8 + 300,200,50);	
				
		close.addActionListener(listener);
		Author.addActionListener(listener);
		start.addActionListener(listener);
		Points.addActionListener(listener);
		
		speicher.addButtons(start);		
		speicher.addButtons(Author);
		speicher.addButtons(Points);
		speicher.addButtons(close);
				
		frame.add(start);
		frame.add(close);
		frame.add(Author);	
		frame.add(Points);		
	}

}
