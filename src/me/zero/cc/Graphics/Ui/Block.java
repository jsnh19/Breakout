package me.zero.cc.Graphics.Ui;

import java.awt.Color;
import java.awt.Graphics;

public class Block {

	private int x,y;
	private int oldx,oldy;
	private int xsize,ysize;
	
	public Block(int x,int y,int ysize,int xsize){
		this.x = x;
		this.y = y;
		oldx = x;
		oldy = y;
		this.xsize = xsize;
		this.ysize = ysize;
	}
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		oldx = this.x;
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		oldy = this.y;
		this.y = y;
	}

	public void draw(Graphics g){
		g.setColor(Color.GRAY);
		g.fillRect(x, y, xsize, ysize);
	}
	public void clear(Graphics g){
		g.clearRect(oldx, oldy, xsize, ysize);
	}

	public int getXsize() {
		return xsize;
	}

	public void setXsize(int xsize) {
		this.xsize = xsize;
	}

	public int getYsize() {
		return ysize;
	}

	public void setYsize(int ysize) {
		this.ysize = ysize;
	}
	
}
