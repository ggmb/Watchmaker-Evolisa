package WatchmakerTest;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.uncommons.watchmaker.framework.factories.AbstractCandidateFactory;

public class CircleFactory extends AbstractCandidateFactory<List<Circle>>{

	@Override
	public List<Circle> generateRandomCandidate(Random rng) {
		List<Circle> circles = new ArrayList<Circle>(2);
				
        for (int i = 0; i < 2; i++){
            circles.add(createRandomCircle(rng));
        }		
        //circles.add(createRandomCircle(rng)); //TEMP CODE TESTING		
        return circles;
	}
	
	/*
	 * Creates a random circle object
	 */
	public static Circle createRandomCircle(Random rng){
		int x; int y; Color c;
		
		int red   = (int)(rng.nextDouble()*255);   //creating random color 0-255
		int green = (int)(rng.nextDouble()*255);
		int blue  = (int)(rng.nextDouble()*255);
		int alpha = (int)(rng.nextDouble()*127);

		x = (int)(rng.nextDouble()*UserInterface.targetImage.getWidth());
		y = (int)(rng.nextDouble()*UserInterface.targetImage.getHeight());
		c = new Color(red, green, blue, alpha);		
		return new Circle(x, y, 30, 30, c); //choose 30*30 size while testing
	}

}
