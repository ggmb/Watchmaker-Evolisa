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
		
		int red   = rng.nextInt(255);   //creating random color 0-255
		int green = rng.nextInt(255);
		int blue  = rng.nextInt(255);
		int alpha = rng.nextInt(117) + 10; //we want alpha to not be very opaque, so maximum value is just 127
										   //we also want it to be a bit visible, so minimum is 10
		x = rng.nextInt(UserInterface.targetImage.getWidth());
		y = rng.nextInt(UserInterface.targetImage.getHeight());
		
		int width  = rng.nextInt(20)+30;
		int height = rng.nextInt(20)+30;
		
		c = new Color(red, green, blue, alpha);		
		return new Circle(x, y, width, height, c); //choose 30*30 size while testing
	}

}
