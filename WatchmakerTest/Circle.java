package WatchmakerTest;

import java.awt.Color;


public class Circle {
	private int xCoordinate;
	private int yCoordinate;
	private int width;
	private int height;
	private Color color;
	
	/*
	 * Constructor, there is no default constructor as we will not need one
	 */
	public Circle(int x, int y, int width, int height, Color color){
		this.setX(x);
		this.setY(y);
		this.setWidth(width);
		this.setHeight(height);
		this.setColor(color);
	}
	
	void setX(int x){
		xCoordinate = x;
	}
	
	void setY(int y){
		yCoordinate = y;
	}
	
	public int getX(){
		return this.xCoordinate;
	}
	
	public int getY(){
		return this.yCoordinate;
	}

	public Color getColor(){
		return this.color;
	}
	
	public void setColor(Color newColor){
		this.color = newColor;
	}
	
	public void setWidth(int width){
		this.width = width;
	}
	
	public int getWidth(){
		return this.width;
	}
	
	public void setHeight(int height){
		this.height = height;
	}
	
	public int getHeight(){
		return this.height;
	}
}
