package me.zero.cc.Graphics;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.TextArea;
import java.awt.TextField;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JFrame;

import me.zero.cc.Graphics.WindowClosingAdapter.StartWindowClosingAdapter;
import me.zero.cc.listener.KeyPressListener;
import me.zero.cc.listener.StartWindowListener;
import me.zero.cc.utils.Player.Speicher;

public class StatsFrame{
	
	private Speicher speicher;
	
	public StatsFrame(Speicher speicher){
		this.speicher = speicher;
		CreateWindow();
	}
	private void CreateWindow(){
		speicher.getStartframe().setVisible(false);
		JFrame frame = new JFrame("Breakout Statistik");
		frame.setBounds(((speicher.getMaxX()/2)-250),(speicher.getMaxY()/2) -250, 500, 500);
		frame.setVisible(true);
		frame.getGraphics().drawImage(null, 500, 500, null);
		frame.addWindowListener(new StartWindowClosingAdapter(speicher));
		frame.addComponentListener(new StartWindowListener(speicher));
		try {
			ArrayList<String> daten = getStatistik();
			TextArea txt = new TextArea();
			txt.setEditable(false);
			for(int i = 0; i < daten.size();i++){
				txt.append(daten.get(i));
			}		
			
			frame.add(txt);
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}
	private ArrayList<String> getStatistik() throws SQLException{
		ArrayList<String> array = new ArrayList<>();
		ResultSet data = speicher.getDb().getResultSet("SELECT * FROM `Points` WHERE points != 0 ORDER BY points DESC LIMIT 0,10");		
		do {
			String name = data.getString(2);
			String punkte = data.getString(3);
			if(!containsName(name, array)){
				array.add("\t\t\t\t" + name + " mit " + punkte + " Punkten \n");
			}			
		} while (data.next());
		data.close();
		return array;
	}
	public boolean containsName(String name,ArrayList<String> list){
		for(int i = 0; i < list.size();i++){
			System.out.println(list.get(i).split(" ")[0]);
			if(list.get(i).split(" ")[0].equals(name)){
				return true;
			}
		}
		return false;
	}
}
