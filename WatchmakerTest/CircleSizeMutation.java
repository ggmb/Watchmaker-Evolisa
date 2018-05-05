package WatchmakerTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.uncommons.maths.random.MersenneTwisterRNG;
import org.uncommons.watchmaker.framework.EvolutionaryOperator;

public class CircleSizeMutation implements EvolutionaryOperator<List<Circle>>{

	@Override
	public List<List<Circle>> apply(List<List<Circle>> candidates, Random rng) {
		List<List<Circle>> mutatedCandidates = new ArrayList<List<Circle>>(candidates.size());
		List<Circle> newCircles = new ArrayList<Circle>(candidates.size());
		
		if (rng.nextDouble() < 0.03){ // 3% probability	
			for (Circle circle : candidates.get(0)){
        		Circle mutatedCircle = mutateSize(circle);
	        	newCircles.add(mutatedCircle);	
	        }
	        mutatedCandidates.add(newCircles);
	        return mutatedCandidates;
		} else return candidates;
	}
	
	/*
	 * return the same circle with its size mutated by a maximum value of 20 pixels
	 */
	public Circle mutateSize(Circle circle){
		
		//the amount by which we will change the 
		Double xMutation = (new MersenneTwisterRNG().nextDouble() *20);
		Double yMutation = (new MersenneTwisterRNG().nextDouble() *20);
		
		if ((new MersenneTwisterRNG().nextDouble()<0.4 ) || (UserInterface.targetImage.getWidth()<circle.getWidth()) ){ //40% chance to subtract
    		xMutation *= -1;
    	}
		
		if ((new MersenneTwisterRNG().nextDouble()<0.4) || (UserInterface.targetImage.getHeight()<circle.getHeight())){ //40% chance to subtract
    		yMutation *= -1;
    	}
		circle.setWidth( (int)(circle.getWidth() +xMutation));
		if (circle.getWidth() < 5){
			circle.setWidth(5);
		} //minimum width/height should be 5pixels
		circle.setHeight((int)(circle.getHeight()+yMutation));
		if (circle.getHeight()<5){
			circle.setHeight(5);
		}		
		return circle;
	}
	
}
