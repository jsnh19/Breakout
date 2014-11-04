package me.zero.cc;

import java.sql.ResultSet;
import java.sql.SQLException;

import me.zero.cc.Graphics.GameJFrame;
import me.zero.cc.utils.Player.Speicher;

import javax.swing.JOptionPane;

public class BallThread extends Thread{

	private String directionUp = "fallen";
	private String directionLeft = "nichts";
	private Speicher speicher;
	private boolean Talive = true;
	
	public BallThread(Speicher speicher){
		this.speicher = speicher;		
	}
	
	public void run(){
		while(Talive){
			try {
				sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			hitPlayer();
			hitWall();
			hitBlock();
			if(directionUp.equalsIgnoreCase("steigen")){
				if(speicher.getBall().getY() < speicher.getFrame().getHeight()){
					speicher.getBall().setY(speicher.getBall().getY() - 2);
				}
				if(directionLeft.equalsIgnoreCase("links")){
					speicher.getBall().setX(speicher.getBall().getX() - 2);
				}else if(directionLeft.equalsIgnoreCase("rechts")){
					speicher.getBall().setX(speicher.getBall().getX() + 2);
				}
			}else{
				if(speicher.getBall().getY() < speicher.getFrame().getHeight()){
					speicher.getBall().setY(speicher.getBall().getY() + 2);
				}
				if(directionLeft.equalsIgnoreCase("links")){
					speicher.getBall().setX(speicher.getBall().getX() - 2);
				}else if(directionLeft.equalsIgnoreCase("rechts")){
					speicher.getBall().setX(speicher.getBall().getX() + 2);
				}
			}
		}
	}
	public boolean isTalive() {
		return Talive;
	}

	public void setTalive(boolean talive) {
		Talive = talive;
	}

	private void hitWall(){		
		if(speicher.getBall().getX() < 5){
			directionLeft = "rechts";
		}
		if(speicher.getBall().getX() > speicher.getFrame().getWidth() -5 ){
			directionLeft = "links";
		}
		if(speicher.getBall().getY() < 20){
			directionUp = "fallen";
			if(directionLeft == "rechts"){
				directionLeft = "rechts";
			}else{
				directionLeft = "links";
			}
		}
		if(speicher.getBall().getY() >= speicher.getFrame().getHeight()){	
			JOptionPane.showMessageDialog(null, "Fatale Niederlage :(");
			speicher.getFrame().setVisible(false);
			speicher.getFrame().dispose();
			speicher.getStartframe().setVisible(true);
			speicher.getBt().setTalive(false);
			savePointsToDb(speicher.getPoints());
			speicher.setAmountlvlplayed(0);
			speicher.setPoints(0);			
		}
	}
	private void hitBlock(){
		if(speicher.getBlocks().size() != 0){
			for(int i = 0; i < speicher.getBlocks().size();i++){
				if(speicher.getBall().getX() > speicher.getBlocks().get(i).getX() & speicher.getBall().getX() < (speicher.getBlocks().get(i).getX() + speicher.getBlocks().get(i).getXsize())){
					if(speicher.getBall().getY() > speicher.getBlocks().get(i).getY() -5 & speicher.getBall().getY() < (speicher.getBlocks().get(i).getY() + speicher.getBlocks().get(i).getYsize() -5)){
						speicher.setPoints(speicher.getPoints()+100);
						speicher.getFrame().setTitle("Breakout - " + speicher.getPoints() + " Points - ");
						speicher.getBlocks().remove(i);
						ChangeDirection();
					}
				}
			}
		}else{
			if(speicher.getLevel().size() > speicher.getAmountlvlplayed()){
				Talive = false;
				speicher.setAmountlvlplayed(speicher.getAmountlvlplayed()+ 1);
				speicher.getFrame().setVisible(false);
				speicher.getFrame().dispose();
				speicher.setJframe(new GameJFrame(speicher));
			}else{
				speicher.getJframe().setAlive(false);
				JOptionPane.showMessageDialog(null, "SIIIIEEG die b�sen Bl�cke wurden vernichtet!");
				speicher.getFrame().setVisible(false);
				speicher.getFrame().dispose();
				speicher.getStartframe().setVisible(true);
				speicher.getBt().setTalive(false);		
				savePointsToDb(speicher.getPoints());
				speicher.setAmountlvlplayed(0);
				speicher.setPoints(0);
			}
		}
	}
	private void savePointsToDb(int points){
		
		String awnser = JOptionPane.showInputDialog(null, "Bitte geben sie einen Namen an: ");
		ResultSet data = speicher.getDb().getResultSet("SELECT COUNT(id) FROM `Points` WHERE `Player` = '" + awnser + "'");	
		boolean succes = false;
		
		try {
		if(data.getInt(1) == 0){
			succes = true;
		}	
			while(!succes){
				awnser = JOptionPane.showInputDialog(null, "Bitte geben sie anderen Namen an: ");
				data = speicher.getDb().getResultSet("SELECT COUNT(id) FROM `Points` WHERE `Player` = '" + awnser + "'");	
				if(data.getInt(1) == 0){
					succes = true;
				}
			}	
		ResultSet count = speicher.getDb().getResultSet("SELECT COUNT(id) FROM `Points`");
		this.speicher.getDb().ExexuteQuery("INSERT INTO `Points`(`id`,`Player`, `points`) VALUES (" + (count.getInt(1)+1) + ", '" + awnser + "', " + points + ")");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	private void hitPlayer(){
		if(speicher.getBall().getY() + speicher.getBall().getYsize() > speicher.getPlayer().getY() & speicher.getBall().getY() + (speicher.getBall().getXsize()/2) < speicher.getPlayer().getY()){
			if(speicher.getPlayer().getX()-(speicher.getBall().getXsize() /2) < speicher.getBall().getX() & speicher.getPlayer().getX() + (speicher.getBall().getXsize() /2) + speicher.getPlayer().getXsize() > speicher.getBall().getX()){
				ChangeDirection();
			}
		}
	}
	private void ChangeDirection(){
		if(directionUp == "steigen"){
			directionUp = "fallen";
		}else{
			directionUp = "steigen";
		}
		if(directionLeft == "rechts"){
			directionLeft = "rechts";
		}else{
			directionLeft = "links";
		}	
	}
}
