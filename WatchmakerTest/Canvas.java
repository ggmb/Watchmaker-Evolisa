package WatchmakerTest;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

public class Canvas extends JPanel{

	public List<Circle> paintingCircles = new ArrayList<Circle>(2);
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//Painting all the circles in the particular canvas instance
	public void paintComponent(Graphics g){
		super.paintComponent(g); 
		
		if (!paintingCircles.isEmpty()){
			for (Circle circle : paintingCircles){
				g.setColor(circle.getColor());
		        g.fillOval(circle.getX() , circle.getY(), circle.getWidth(), circle.getHeight()); 
			}
		}
	}
	
	public void setCircles(List<Circle> circles){
		this.paintingCircles.clear();
		for (Circle circle : circles){
			this.paintingCircles.add(circle);
		}
	}
}
