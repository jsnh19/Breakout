package me.zero.cc.utils.Player;

import java.awt.Button;
import java.awt.Frame;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.logging.Logger;

import me.zero.cc.BallThread;
import me.zero.cc.Graphics.GameJFrame;
import me.zero.cc.Graphics.StatsFrame;
import me.zero.cc.Graphics.Ui.Ball;
import me.zero.cc.Graphics.Ui.Block;
import me.zero.cc.Graphics.Ui.Player;
import me.zero.cc.Graphics.Ui.Level.Level;
import me.zero.cc.SQLite.DbTasks;
import me.zero.cc.SQLite.SqliteDb;
import me.zero.cc.Sound.SoundThread.SoundMP3Thread;

public class Speicher {

	private Graphics g;
	private Logger log;
	private ArrayList<Block> blocks = new ArrayList<>();
	private Player player;
	private Ball ball;
	private boolean started = false;
	private Frame frame = null;
	private int maxX = 0;
	private int maxY = 0;
	private Frame startframe;
	private BallThread bt;
	private ArrayList<Level> level = new ArrayList<>();
	private int amountlvlplayed = 0;
	private SoundMP3Thread mp3thread;
	private GameJFrame jframe;
	private int points = 0;
	private DbTasks db = null;
	private StatsFrame statsframe = null;
	
	public GameJFrame getJframe() {
		return jframe;
	}
	public void setJframe(GameJFrame jframe) {
		this.jframe = jframe;
	}
	public SoundMP3Thread getMp3thread() {
		return mp3thread;
	}
	public void setMp3thread(SoundMP3Thread mp3thread) {
		this.mp3thread = mp3thread;
	}
	public int getAmountlvlplayed() {
		return amountlvlplayed;
	}
	public void setAmountlvlplayed(int amountlvlplayed) {
		this.amountlvlplayed = amountlvlplayed;
	}
	public ArrayList<Level> getLevel() {
		return level;
	}
	public BallThread getBt() {
		return bt;
	}
	public void setBt(BallThread bt) {
		this.bt = bt;
	}
	public Frame getStartframe() {
		return startframe;
	}
	public void setStartframe(Frame startframe) {
		this.startframe = startframe;
	}
	private ArrayList<Button> buttons = new ArrayList<>();
	
	public int getMaxX() {
		return maxX;
	}
	public void setMaxX(int maxX) {
		this.maxX = maxX;
	}
	public int getMaxY() {
		return maxY;
	}
	public void setMaxY(int maxY) {
		this.maxY = maxY;
	}
	public Frame getFrame() {
		return frame;
	}
	public void setFrame(Frame frame) {
		this.frame = frame;
	}
	public ArrayList<Block> getBlocks() {
		return blocks;
	}
	public void addBlock(Block block) {
		blocks.add(block);
	}
	public void removeBlock(Block block){
		blocks.remove(block);
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public Ball getBall() {
		return ball;
	}

	public void setBall(Ball ball) {
		this.ball = ball;
	}

	public boolean isStarted() {
		return started;
	}

	public void setStarted(boolean started) {
		this.started = started;
	}

	public Graphics getGraphic() {
		return g;
	}

	public void setGraphic(Graphics g) {
		this.g = g;
	}

	public Logger getLog() {
		return log;
	}

	public void setLog(Logger log) {
		this.log = log;
	}
	public ArrayList<Button> getButtons() {
		return buttons;
	}
	public void addButtons(Button button) {
		buttons.add(button);
	}
	public void addLevel(Level level1){
		level.add(level1);
	}
	public int getPoints() {
		return points;
	}
	public void setPoints(int points) {
		this.points = points;
	}
	public DbTasks getDb() {
		return db;
	}
	public void setDbTasks(DbTasks db) {
		this.db = db;
	}
	public StatsFrame getStatsframe() {
		return statsframe;
	}
	public void setStatsframe(StatsFrame statsframe) {
		this.statsframe = statsframe;
	}
	
	
}
