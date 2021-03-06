package WatchmakerTest;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.List;
import org.uncommons.watchmaker.framework.FitnessEvaluator;

public class FitnessFunction implements FitnessEvaluator<List<Circle>>{

	
	@Override
	public synchronized double getFitness(List<Circle> candidates, 
							 List<? extends List<Circle>> population) {
		int fitness;
		
		UserInterface.canvas.setCircles(candidates); //set the canvas circles, and paint them
		UserInterface.canvas.repaint();
		BufferedImage fitnessImage = createImage(UserInterface.canvas); //create a buffered image from the canvas
		fitness = generateFitness(UserInterface.targetImage, fitnessImage); //compare the two images
		
		return fitness;
	}

	/*
	 * return the int delta value between two images of a specific component (red, green or blue)
	 */
	public static int getDeltaComponent(String component, BufferedImage originalImage, BufferedImage candidate){
		int delta = 0;
		
		for (int i=0; i < originalImage.getWidth(); i++){
			for (int j=0; j < originalImage.getHeight(); j++){
				synchronized(candidate){
					Color c1 = new Color(originalImage.getRGB(i, j));
					Color c2 = new Color(candidate.getRGB(i, j));
					switch (component){
					case "red":
						int deltaRed = Math.abs(c1.getRed()-c2.getRed());	
							delta += deltaRed;
						break;
						
					case "green":
						int deltaGreen = Math.abs(c1.getGreen()-c2.getGreen());
							delta += deltaGreen;					
						break;
						
					case "blue":
						int deltaBlue = Math.abs(c1.getBlue()-c2.getBlue());
							delta += deltaBlue;
						break;
						
					}	
				}
			}	
		}
		return delta;
	}
	
	/*
	 * Returns an int value of the fitness of candidate against the original image.
	 * The two arguments are the original image and the candidate image.
	 */
	public synchronized static int generateFitness(BufferedImage originalImage, BufferedImage candidate){
		int fitness = 0;
		
		int red = getDeltaComponent("red", originalImage, candidate);
		fitness += red;
		
		int green = getDeltaComponent("green", originalImage, candidate);
		fitness += green;
	
		int blue = getDeltaComponent("blue", originalImage, candidate);	
		fitness += blue;
	
		return fitness;
	}
	

	@Override
	public boolean isNatural() {
		// TODO Auto-generated method stub
		return false;
	}

	/*
	 * Creating a bufferedImage object out of a Canvas object.
	 * This is to be able to retrieve its RGB value later
	 */
	public static synchronized BufferedImage createImage(Canvas panel) {
	    int width  = panel.getWidth();
	    int height = panel.getHeight();
	    BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	    Graphics2D g = bi.createGraphics();
	    panel.paint(g);
	    return bi;
	}

}
