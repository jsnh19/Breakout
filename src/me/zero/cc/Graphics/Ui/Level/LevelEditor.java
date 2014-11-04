package me.zero.cc.Graphics.Ui.Level;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import me.zero.cc.utils.Player.Speicher;

public class LevelEditor {

	private Speicher speicher;
	
	public LevelEditor(Speicher speicher){
		this.speicher = speicher;
	}	
	public void LoadLevel(){		
		try {
			WriteLevelToHDD();
			loadLvlFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void loadLvlFile() throws FileNotFoundException{
		
		File dir = new File(System.getProperty("user.dir") + "/level");
		//File dir = new File("C:/Users/Julius/Desktop/break/level");
		
		int count = dir.list().length;
		for(int i = 0; i < count;i++){
			System.out.println("Loading level" + i + ".lvl (...)");
			FileReader reader = new FileReader(new File(System.getProperty("user.dir") + "/level/level" + i + ".lvl"));
			//FileReader reader = new FileReader(new File("C:/Users/Julius/Desktop/break/level/level" + i + ".lvl"));
			@SuppressWarnings("resource")
			BufferedReader bf = new BufferedReader(reader);
			
			Level level = new Level();
			try {
				String msg = bf.readLine();		
		
				while(msg != null){
					if(!msg.contains("#")){
						if(msg.contains("music=")){
							level.setPathtomusik(msg.split("music=")[1]);
						}else if(msg.contains("framesizex=")){
							level.setFramesizex(Integer.parseInt(msg.split("framesizex=")[1]));
						}else if(msg.contains("framesizey=")){
							level.setFramesizey(Integer.parseInt(msg.split("framesizey=")[1]));
						}else if(msg.contains("playersizex=")){
							level.setPlayersizex(Integer.parseInt(msg.split("playersizex=")[1]));
						}else if(msg.contains("playersizey=")){
							level.setPlayersizey(Integer.parseInt(msg.split("playersizey=")[1]));
						}else if(msg.contains("ballsizex=")){
							level.setBallsizex(Integer.parseInt(msg.split("ballsizex=")[1]));
						}else if(msg.contains("ballsizey=")){
							level.setBallsizey(Integer.parseInt(msg.split("ballsizey=")[1]));
						}else{
							level.addAufbau(msg);
						}
					}
					msg = bf.readLine();					
				}
				speicher.addLevel(level);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}	
	}
	public void WriteLevelToHDD() throws IOException{
		File fold = new File("level");
		//File fold = new File("C:/Users/Julius/Desktop/break/level");
		
		if(!fold.exists()){
			fold.mkdir();
		}
		for(int i = 0; i < 4;i++){
			File file = new File(System.getProperty("user.dir") + "/level/level" + i + ".lvl");
			//File file = new File("C:/Users/Julius/Desktop/break/level/level" + i + ".lvl");
			if(!file.exists()){
				file.createNewFile();
				FileWriter fileWriter = new FileWriter(file);
		        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
		        
		        InputStreamReader inputStreamReader = new InputStreamReader(getClass().getResourceAsStream("/level/level" + i + ".lvl"));
				BufferedReader bf = new BufferedReader(inputStreamReader);
		        
				String msg = bf.readLine();
				while(msg != null){			
					bufferedWriter.write(msg);
					bufferedWriter.newLine();
					msg = bf.readLine();
				}
				 bufferedWriter.close();
			}			
		}		
	}
}
